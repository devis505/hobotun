package hobotun.user;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import hobotun.core.Misc;
import hobotun.core.UserSession;
import hobotun.db.DBUtil;
import hobotun.db.user.UserDao;
import hobotun.db.user.table.UserTbl;

@ManagedBean(name = "editPass")
@ViewScoped
public class EditPass implements Serializable {

	private static final long serialVersionUID = -7147632459564507473L;
	private UserTbl userTbl = UserSession.getInstance().getUser().getUserTbl();

	private String passOld;
	private String passNew;
	private String passNew2;

	public String getPassOld() {
		return passOld;
	}

	public void setPassOld(String passOld) {
		this.passOld = passOld;
	}

	public String getPassNew() {
		return passNew;
	}

	public void setPassNew(String passNew) {
		this.passNew = passNew;
	}

	public String getPassNew2() {
		return passNew2;
	}

	public void setPassNew2(String passNew2) {
		this.passNew2 = passNew2;
	}

	public UserTbl getUserTbl() {
		return userTbl;
	}

	public void setUserTbl(UserTbl userTbl) {
		this.userTbl = userTbl;
	}

	public void onSave() {

		if (userTbl.getPassword().equals(Misc.md5Custom(passOld))) {

			if (passNew.equals(passNew2)) {

				userTbl.setPassword(Misc.md5Custom(passNew));
				
				UserDao userDao = DBUtil.getInstance().getBean("userDao", UserDao.class);
				userDao.UpdateUserById(userTbl.getAllParam());

				Misc.redirect("/pages/user/user.jsf?userPageId=1");
			} else {
				Misc.setMessageElement(ID_MSG_FOR_WRONG_PASS2, FacesMessage.SEVERITY_ERROR, MSG_ERROR_WRONG_PASS2);
			}

		} else {
			Misc.setMessageElement(ID_MSG_FOR_WRONG_PASS, FacesMessage.SEVERITY_ERROR, MSG_ERROR_WRONG_PASS);
		}

	}

	private static final String ID_MSG_FOR_WRONG_PASS = "info-form:pass";
	private static final String MSG_ERROR_WRONG_PASS = "Старый пароль не совпадает";
	
	private static final String ID_MSG_FOR_WRONG_PASS2 = "info-form:passNew2";
	private static final String MSG_ERROR_WRONG_PASS2 = "Пароль не совпадает с новым";
}
