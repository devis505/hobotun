package hobotun.db.user;

import hobotun.db.user.table.OutUserBalanceTbl;
import hobotun.db.user.table.UserTbl;

import java.util.List;
import java.util.Map;

public interface IUserDao {

    public List<UserTbl> getUserByLoginAndPass(Map<String, Object> params);

    public List<UserTbl> getUserByMailAndPass(Map<String, Object> params);

    public List<UserTbl> getUserByMail(Map<String, Object> params);

    public void UpdateUserById(Map<String, Object> params);

    public void UpdateBalanceById(Map<String, Object> params);
    
    public void UpdateMinesBalanceById(Map<String, Object> params);

    public List<OutUserBalanceTbl> getOutUserBalance(Map<String, Object> params);
    
    public void InsertOutBalance(Map<String, Object> params);
    
    public List<UserTbl> getUserById(Map<String, Object> params);

}
