package hobotun.admin;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import hobotun.db.DBUtil;
import hobotun.db.user.UserDao;
import hobotun.db.user.table.UserTbl;

@ManagedBean(name = "allUsers")
@ViewScoped
public class AllUsers {

	private List<UserTbl> users;
	
	private UserDao userDao;
	
	public AllUsers() {
		userDao = DBUtil.getInstance().getBean("userDao", UserDao.class);
		fill();
	}
	
	private void fill() {
		setUsers(userDao.allUsers());
	}

	public List<UserTbl> getUsers() {
		return users;
	}

	public void setUsers(List<UserTbl> users) {
		this.users = users;
	}

}
