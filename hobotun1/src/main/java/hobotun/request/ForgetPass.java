package hobotun.request;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import hobotun.core.Misc;
import hobotun.db.DBUtil;
import hobotun.db.user.UserDao;
import hobotun.db.user.table.UserTbl;
import hobotun.util.SendEmail;

@ManagedBean(name = "forgetPass")
@ViewScoped
public class ForgetPass implements Serializable {

	private static final long serialVersionUID = 1809141635144679751L;
	private UserDao userDao = DBUtil.getInstance().getBean("userDao", UserDao.class);

	private String email;

	public void onSendMail() {

		Map<String, Object> params = new HashMap<>();
		params.put("mail", email);

		List<UserTbl> userTbl = userDao.getUserByMail(params);

		if (userTbl.isEmpty()) {
			Misc.setMessageElement(ID_MSG_FOR_EMAIL_ELEMENT, FacesMessage.SEVERITY_ERROR, MSG_ERROR_EMAIL);
		} else {

			Map<String, Object> paramForgetPass = new HashMap<>();

			paramForgetPass.put("id_user", userTbl.get(0).getId_user());
			paramForgetPass.put("vl_hash", Misc.md5Custom(Misc.getUnicValue(10)));
			paramForgetPass.put("dt_create", new Date());

			String path = Misc.getAppPath() + "/pages/user/recover.jsf?e=" + email + "&c="
					+ paramForgetPass.get("vl_hash") + "&i=" + paramForgetPass.get("id_user") + "&u=1";
			
			userDao.InsertForgetPass(paramForgetPass);
			
			String body = String.format(text, email, path, path);

			SendEmail.getInstance().SendMail(email, "Восстановление пароля", body);
			Misc.redirect("/pages/user/recover.jsf?e=" + email + "&c=&i=&u=4");
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private static final String ID_MSG_FOR_EMAIL_ELEMENT = "login-form:Email";
	private static final String MSG_ERROR_EMAIL = "Пользовтаель с таком e-mail не найден!";

	private static final String text = "<p>Кто-то (возможно даже Вы) забыл пароль для входа на сайт и попросил создать новый для пользователя с адресом - %s.</p> "
			+ "<p>Если Вы ничего такого не просили, то просто проигнорируйте письмо.</p> "
			+ "<p>В случае если это все-таки Вы, то просьба пройти по этому адресу:</p><br/>"
			+ "<a href=\"%s\">%s</a><br/>" + "<p>Ссылка будет доступна в течении 1-х суток</p> "
			+ "<p>После этой несложной процедуры пароль Вашего аккаунта изменится и будет выслан Вам.</p>";
}
