package hobotun.request;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import hobotun.core.Misc;
import hobotun.db.DBUtil;
import hobotun.db.user.UserDao;
import hobotun.db.user.table.ForgetPassTbl;
import hobotun.db.user.table.UserTbl;
import hobotun.util.SendEmail;

@ManagedBean(name = "recover")
@ViewScoped
public class Recover implements Serializable {

	private static final long serialVersionUID = -6596635184360113684L;
	private UserDao userDao = DBUtil.getInstance().getBean("userDao", UserDao.class);

	private String msg;

	public Recover() {
		Map<String, Object> params = new HashMap<>();

		String id_user = Misc.getRequestParam(FacesContext.getCurrentInstance(), "i");
		String u = Misc.getRequestParam(FacesContext.getCurrentInstance(), "u");
		String c = Misc.getRequestParam(FacesContext.getCurrentInstance(), "c");
		String e = Misc.getRequestParam(FacesContext.getCurrentInstance(), "e");

		if (!"4".equals(u)) {
			if (!"3".equals(u)) {

				params.put("id_user", id_user);
				params.put("vl_hash", c);
				params.put("dt_create", new Date());

				List<ForgetPassTbl> forgetPass = userDao.findForgetPass(params);
				List<UserTbl> userTbl = userDao.getUserById(new Long(id_user));

				if (!forgetPass.isEmpty()) {

					String newPass = "";

					if ("1".equals(u)) {
						msg = "Вам на почту <b>" + userTbl.get(0).getMail() + "</b> был отправлен новый пароль!";
						newPass = Misc.getUnicValue(8);

					} else if ("2".equals(u)) {
						msg = "Поздравляем <b>" + userTbl.get(0).getLogin()
								+ "</b>, вы успешно зарегистрировались на HOBOTUN.COM!";
						newPass = c;
					}

					String mail = userTbl.get(0).getMail();
					String body = String.format(msgToMail, mail, newPass);

					Map<String, Object> userParams = userTbl.get(0).getAllParam();
					if ("1".equals(u)) {
						userParams.put("password", Misc.md5Custom(newPass));
					} else {
						userParams.put("password", newPass);
					}

					userDao.UpdateUserById(userParams);

					if ("1".equals(u)) {
						SendEmail.getInstance().SendMail(mail, "Восстановление пароля", body);
					}

				} else {
					msg = "";
				}
			} else {
				msg = "Зайдите на вашу почту <b>" + e + "</b>, для подтверждения регистрации!";
			}
		} else {
			msg = "На вашу почту <b>" + e + "</b>, отпралена инструкция!";
		}
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	private final static String msgToMail = "<p>Так как Вы забыли пароль и запросили его восстановление, генератор"
			+ "паролей, не побоимся этого слова, сгенерировал (!) Вам новый,"
			+ "несложный, легкозапоминающийся пароль.</p>"
			+ "<p>Собственно теперь информация, необходимая для доступа к нам на сайт"
			+ "выглядит следующим образом:</p><br/>" + "<b>Логин:</b> %s<br/>" + "<b>Пароль:</b> %s<br/><br/>"
			+ "<p>Добро пожаловать обратно на <a href=\"https://www.hobotun.com\">https://www.hobotun.com</a></p>";

}
