package hobotun.request;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import hobotun.core.Misc;
import hobotun.db.DBUtil;
import hobotun.db.model.ModelDao;
import hobotun.db.model.tbl.ModelTbl;
import hobotun.db.user.UserDao;
import hobotun.db.user.table.UserTbl;

@ManagedBean(name="userProfil")
@ViewScoped
public class UserProfil {

	private String id;
	private UserDao userDao;
	private ModelDao modelDao;
	
	private UserTbl user;
	private List<ModelTbl> modeles;
	
	public UserProfil() {
		id = Misc.getRequestParam(FacesContext.getCurrentInstance(), "id");
		
		userDao = DBUtil.getInstance().getBean("userDao", UserDao.class);
		modelDao = DBUtil.getInstance().getBean("modelDao", ModelDao.class);
		
		user = userDao.getUserById(new Long(id)).get(0);
		modeles = modelDao.selectModelByIdUser(new Long(id), 1);		
	}

	public UserTbl getUser() {
		return user;
	}

	public void setUser(UserTbl user) {
		this.user = user;
	}

	public List<ModelTbl> getModeles() {
		return modeles;
	}

	public void setModeles(List<ModelTbl> modeles) {
		this.modeles = modeles;
	}
		
}
