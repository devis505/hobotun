package hobotun.core;

import hobotun.user.User;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "userSession")
@SessionScoped
public class UserSession implements Serializable {

	private static final long serialVersionUID = 5714777107983472870L;
	private User user = null;
	private String admin = ApplicationProperties.getInstance().getPropertyValue("ru.hobotun.admin");
	private boolean iaAdmin = false;

	public UserSession() {

	}

	public static UserSession getInstance() {
		return Misc.findManagedBean(FacesContext.getCurrentInstance(), "userSession", UserSession.class);
	}

	public boolean authorization(String email, String password) {

		if (user == null) {
			user = User.getUser();
		}

		user.authUser(email, password);

		return user.isAuthorization();
	}

	public String getTopMenu() {
		if (user != null && user.isAuthorization()) {
			return "../common/topbar.xhtml";
		} else {
			return "../common/topbar-not-sign.xhtml";
		}
	}

	public User getUser() {
		return user;
	}

	public boolean getClose() {

		if (user != null) {
			user.onClear();			
			user = null;
		}

		return true;
	}

	// Проверяем одминл ли это
	public boolean isIaAdmin() {

		iaAdmin = false;
		
		try {
			if (user != null) {
				if (admin.equals(user.getUserTbl().getId_user().toString())) {
					iaAdmin = true;
				}
			}
		} catch (Exception e) {
			iaAdmin = false;
		}

		return iaAdmin;
	}

}
