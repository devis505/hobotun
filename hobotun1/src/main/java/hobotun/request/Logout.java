package hobotun.request;

import hobotun.core.Misc;
import hobotun.core.UserSession;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "logout")
@RequestScoped
public class Logout implements Serializable {

	public Logout() {
		if (UserSession.getInstance().getClose()) {
			Misc.redirect("/pages/common/main.jsf");
		}
	}

	public String getMsg() {
		return msg;
	}

	private static final long serialVersionUID = 4954861313936026719L;
	private static final String msg = "Выполняется переход на главную страницу.";

}
