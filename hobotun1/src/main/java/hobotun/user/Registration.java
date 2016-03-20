package hobotun.user;

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

@ManagedBean(name = "reg")
@ViewScoped
public class Registration implements Serializable{

	private static final long serialVersionUID = -1447281213578189127L;
	
	private UserDao userDao = DBUtil.getInstance().getBean("userDao", UserDao.class);
	private List<UserTbl> users;
	
	private String login;
	private String email;
	private String pass;
	private String pass2;
	
	public void onSaveUser() {
		
		Map<String, Object> params = new HashMap<>(); 
		params.put("login", login);
		
		users = userDao.findUserByLogin(params);
		
		if (users.isEmpty()) {
			
			params.clear();
			params.put("mail", email);
			
			users.clear();
			users = userDao.getUserByMail(params);
			
			if (users.isEmpty()) {
				
				if (pass2.equals(pass)) {
					
					Map<String, Object> paramUser = new HashMap<>();
					
					String tmpPass = Misc.md5Custom(Misc.getUnicValue(10));
					String passMd5 = Misc.md5Custom(pass);
					
					paramUser.put("login", login);
					paramUser.put("mail", email);
					paramUser.put("password", tmpPass);
					paramUser.put("dtReg", new Date());
					
					userDao.insertUser(paramUser);
					
					Map<String, Object> userTmp = new HashMap<>();
					userTmp.put("login", login);
					userTmp.put("password", tmpPass);
					
					UserTbl user = userDao.getUserByLoginAndPass(userTmp).get(0);
					
					Map<String, Object> paramForgetPass = new HashMap<>();

					paramForgetPass.put("id_user", user.getId_user());
					paramForgetPass.put("vl_hash", passMd5);
					paramForgetPass.put("dt_create", new Date());

					String path = Misc.getAppPath() + "/pages/user/recover.jsf?e=" + email + "&c=" + passMd5 + "&i=" + paramForgetPass.get("id_user") + "&u=2";
					String body = String.format(bodyMsg, login, path, path);
					
					userDao.InsertForgetPass(paramForgetPass);
					
					SendEmail.getInstance().SendMail(email, "Подтверждение почты для HOBOTUN", body);
					
				} else {
					Misc.setMessageElement(ID_MSG_FOR_PASS2_ELEMENT, FacesMessage.SEVERITY_ERROR, "Пароли не совпадают");
				}
				
			} else {
				Misc.setMessageElement(ID_MSG_FOR_MAIL_ELEMENT, FacesMessage.SEVERITY_ERROR, "Пользователь с таким e-mail уже существует");
			}
			
		} else {
			Misc.setMessageElement(ID_MSG_FOR_LOGIN_ELEMENT, FacesMessage.SEVERITY_ERROR, "Пользователь с таким логином уже существует");
		}
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPass() {
		return pass;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String getPass2() {
		return pass2;
	}
	
	public void setPass2(String pass2) {
		this.pass2 = pass2;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	private static final String ID_MSG_FOR_LOGIN_ELEMENT = "login-form:login";
	private static final String ID_MSG_FOR_MAIL_ELEMENT = "login-form:Email";
	private static final String ID_MSG_FOR_PASS2_ELEMENT = "login-form:password2";
	
	private static final String bodyMsg = 
			"<p>Приветствуем, %s!</p> " +
			"<p>Для подтверждения электронной почты и завершения процесса регистрации, пройдите, пожалуйста, по ссылке:</p><br/>" +
			"<a href=\"%s\">%s</a><br/>" +
			"<p>Если вы получили это письмо по ошибке, просто игнорируйте его.</p>";	
}
