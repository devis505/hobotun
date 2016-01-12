package hobotun.user;

import hobotun.db.DBUtil;
import hobotun.db.user.UserDao;
import hobotun.db.user.table.OutUserBalanceTbl;
import hobotun.db.user.table.UserTbl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private UserTbl userTbl;
    private Integer userPageId;
    private UserDao userDao;
    private boolean editMode = false;

    private BigDecimal addBalance;
    private BigDecimal outBalance;
    
    

    public void authUser(String email, String password) {
	userDao = DBUtil.getInstance().getBean("userDao", UserDao.class);

	Map<String, Object> params = new HashMap<String, Object>();

	params.put("mail", email);
	params.put("password", password);

	List<UserTbl> users = userDao.getUserByMailAndPass(params);

	if (users.size() == 1) {
	    userTbl = users.get(0);
	}
    }

    public List<OutUserBalanceTbl> getUserOutBalance() {

	Map<String, Object> params = new HashMap<String, Object>();
	params.put("id_user", userTbl.getId_user());

	return userDao.getOutUserBalance(params);
    }

    public void fillBalance() {
	Map<String, Object> params = new HashMap<String, Object>();

	params.put("id_user", userTbl.getId_user());
	params.put("balance", addBalance);

	userDao.UpdateBalanceById(params);

	params.clear();
	params.put("id_user", userTbl.getId_user());

	List<UserTbl> users = userDao.getUserById(params);
	if (users.size() == 1) {
	    userTbl = users.get(0);
	}
    }

    public void outBalance() {
	Map<String, Object> params = new HashMap<String, Object>();

	params.put("id_user", userTbl.getId_user());
	params.put("balance", outBalance.negate());

	userDao.UpdateBalanceById(params);

	params.put("state", 1);
	params.put("dtStartOut", new Date());

	userDao.InsertOutBalance(params);

	params.clear();
	params.put("id_user", userTbl.getId_user());

	List<UserTbl> users = userDao.getUserById(params);
	if (users.size() == 1) {
	    userTbl = users.get(0);
	}
    }

    public boolean isEditMode() {
	return editMode;
    }

    public void changeEditMode() {
	editMode = !editMode;

	if (!editMode) {
	    userDao.UpdateUserById(userTbl.getAllParam());
	}
    }

    public static User getUser() {
	return new User();
    }

    public String getNmUser() {
	return userTbl.getLogin();
    }

    public UserTbl getUserTbl() {
	return userTbl;
    }

    public UserTbl getUserInfo() {
	return userTbl;
    }

    public String getUserBirthdayFormat() {
	return null;// SimpleDateFormat("dd.MM.yyyy").format(userInfo.getDt_birthday());
    }

    public boolean isAuthorization() {
	return userTbl != null;
    }

    public Integer getUserPageId() {
	return userPageId;
    }

    public void setUserPageId(Integer userPageId) {
	this.userPageId = userPageId;
    }

    public String getUserPage() {

	if (userPageId.equals(1)) {
	    return "aboutInfo";
	}
	if (userPageId.equals(2)) {
	    return "fillBalance";
	}
	if (userPageId.equals(3)) {
	    return "outBalance";
	}
	if (userPageId.equals(4)) {
	    return "myModels";
	}
	if (userPageId.equals(5)) {
	    return "myBayModels";
	}
	if (userPageId.equals(9)) {
	    return "downloadModels";
	} else {
	    return "emtyPage";
	}

    }

    public BigDecimal getAddBalance() {
	return addBalance;
    }

    public void setAddBalance(BigDecimal addBalance) {
	this.addBalance = addBalance;
    }

    public BigDecimal getOutBalance() {
	return outBalance;
    }

    public void setOutBalance(BigDecimal outBalance) {
	this.outBalance = outBalance;
    }

}
