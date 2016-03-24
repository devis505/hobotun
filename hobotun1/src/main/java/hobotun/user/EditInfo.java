package hobotun.user;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import hobotun.core.Misc;
import hobotun.core.UserSession;
import hobotun.db.DBUtil;
import hobotun.db.user.UserDao;
import hobotun.db.user.table.UserTbl;

@ManagedBean(name = "editInfo")
@ViewScoped
public class EditInfo implements Serializable{

	private static final long serialVersionUID = -7147632459564507473L;
    private UserTbl userTbl = UserSession.getInstance().getUser().getUserTbl();
    
    
	public UserTbl getUserTbl() {
		return userTbl;
	}
	
	public void setUserTbl(UserTbl userTbl) {
		this.userTbl = userTbl;
	} 
	
	public void onSave() {
		
		UserDao userDao = DBUtil.getInstance().getBean("userDao", UserDao.class);
		userDao.UpdateUserById(userTbl.getAllParam());
		
		Misc.redirect("/pages/user/user.jsf?userPageId=1");
	}
	
}
