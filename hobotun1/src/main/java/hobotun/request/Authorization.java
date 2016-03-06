package hobotun.request;

import hobotun.core.Misc;
import hobotun.core.UserSession;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "auth")
@ViewScoped
public class Authorization implements Serializable {

	private static final long serialVersionUID = -5446991966815543455L;

	private String pass;
	private String email;

	private boolean error = false;

	public void auth() {

		error = false;

		if (email.isEmpty()) {
			Misc.setMessageElement(ID_MSG_FOR_EMAIL_ELEMENT, FacesMessage.SEVERITY_ERROR, MSG_EMPTY_EMAIL);
			error = true;
		}

		if (pass.isEmpty()) {
			Misc.setMessageElement(ID_MSG_FOR_PASS_ELEMENT, FacesMessage.SEVERITY_ERROR, MSG_EMPTY_PASS);
			error = true;
		}

		if (!UserSession.getInstance().authorization(email, pass)) {
			Misc.setMessageElement(ID_MSG_FOR_EMAIL_ELEMENT, FacesMessage.SEVERITY_ERROR, MSG_ERROR_EMAIL);
			error = true;
		}

		if (!error) {
			Misc.redirect("/index.jsf");
		}

		return;

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

	private static final String ID_MSG_FOR_EMAIL_ELEMENT = "login-form:Email";
	private static final String ID_MSG_FOR_PASS_ELEMENT = "login-form:password";

	private static final String MSG_EMPTY_EMAIL = "E-mail не может быть пустым!";
	private static final String MSG_EMPTY_PASS = "Пароль не может быть пустым!";
	private static final String MSG_ERROR_EMAIL = "Неправильный логин или пароль!";

}
