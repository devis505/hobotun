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

			SendEmail.getInstance().SendMail(email, "�������������� ������", body);
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private static final String ID_MSG_FOR_EMAIL_ELEMENT = "login-form:Email";
	private static final String MSG_ERROR_EMAIL = "������������ � ����� ������ �� ����������!";

	private static final String text = "<p>���-�� (�������� ���� ��) ����� ������ ��� ����� �� ���� � �������� ������� ����� ��� ������������ � ������� - %s.</p> "
			+ "<p>���� �� ������ ������ �� �������, �� ������ �������������� ������.</p> "
			+ "<p>� ������ ���� ��� ���-���� ��, �� ������� ������ �� ����� ������:</p><br/>"
			+ "<a href=\"%s\">%s</a><br/>" + "<p>������ ����� �������� � ������� 1-� �����</p> "
			+ "<p>����� ���� ��������� ��������� ������ ������ �������� ��������� � ����� ������ ���.</p>";
}
