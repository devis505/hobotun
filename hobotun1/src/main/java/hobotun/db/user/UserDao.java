package hobotun.db.user;

import hobotun.db.user.action.*;
import hobotun.db.user.table.ForgetPassTbl;
import hobotun.db.user.table.OutUserBalanceTbl;
import hobotun.db.user.table.UserTbl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

public class UserDao implements IUserDao, Serializable {

	private static final long serialVersionUID = 4489468539901015901L;

	private DataSource dataSource;
	private FindUserByLoginAndPass findUserByLoginPass;
	private FindUserByMailAndPass findUserByMailPass;
	private FindUserByMail finUserByMail;
	private FindUserByMailBlock finUserByMailBlock;
	private UpdateUserById updateUserById;
	private UpdateUserBalanceById updateUserBalanceById;
	private FindUserOutBalance findUserOutBalance;
	private UpdateUserMinusBalanceById updateUserMinusBalanceById;
	private InsertOutBalanc insertOutBalanc;
	private FindUserById findUserById;
	private InsertForgetPass insertForgetPass;
	private FindForgetPass findForgetPass;
	private FindUserByLogin findUserByLogin;
	private InsertUser insertUser;
	private AllUsers allUsers;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.findUserByLoginPass = new FindUserByLoginAndPass(dataSource);
		this.findUserByMailPass = new FindUserByMailAndPass(dataSource);
		this.finUserByMail = new FindUserByMail(dataSource);
		this.finUserByMailBlock = new FindUserByMailBlock(dataSource);
		this.updateUserById = new UpdateUserById(dataSource);
		this.updateUserBalanceById = new UpdateUserBalanceById(dataSource);
		this.findUserOutBalance = new FindUserOutBalance(dataSource);
		this.updateUserMinusBalanceById = new UpdateUserMinusBalanceById(dataSource);
		this.insertOutBalanc = new InsertOutBalanc(dataSource);
		this.findUserById = new FindUserById(dataSource);
		this.insertForgetPass = new InsertForgetPass(dataSource);
		this.findForgetPass = new FindForgetPass(dataSource);
		this.findUserByLogin = new FindUserByLogin(dataSource);
		this.insertUser = new InsertUser(dataSource);
		this.allUsers = new AllUsers(dataSource);
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	@Override
	public List<UserTbl> getUserByLoginAndPass(Map<String, Object> params) {
		return findUserByLoginPass.executeByNamedParam(params);
	}

	@Override
	public List<UserTbl> getUserByMailAndPass(Map<String, Object> params) {
		return findUserByMailPass.executeByNamedParam(params);
	}

	@Override
	public List<UserTbl> getUserByMail(Map<String, Object> params) {
		return finUserByMail.executeByNamedParam(params);
	}
	
	public List<UserTbl> getUserByMailBlock(Map<String, Object> params) {
		return finUserByMailBlock.executeByNamedParam(params);
	}

	@Override
	public void UpdateUserById(Map<String, Object> params) {
		updateUserById.updateByNamedParam(params);
	}

	@Override
	public void UpdateBalanceById(Map<String, Object> params) {
		updateUserBalanceById.updateByNamedParam(params);
	}

	@Override
	public List<OutUserBalanceTbl> getOutUserBalance(Map<String, Object> params) {
		return findUserOutBalance.executeByNamedParam(params);
	}

	@Override
	public void UpdateMinesBalanceById(Map<String, Object> params) {
		updateUserMinusBalanceById.updateByNamedParam(params);
	}

	@Override
	public void InsertOutBalance(Map<String, Object> params) {
		insertOutBalanc.updateByNamedParam(params);
	}

	@Override
	public List<UserTbl> getUserById(Map<String, Object> params) {
		return findUserById.executeByNamedParam(params);
	}
	
	public List<UserTbl> getUserById(Long id) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id_user", id);
		
		return findUserById.executeByNamedParam(params);
	}
	
	public void InsertForgetPass(Map<String, Object> params) {
		insertForgetPass.updateByNamedParam(params);
	}

	public List<ForgetPassTbl> findForgetPass(Map<String, Object> params) {
 		return findForgetPass.executeByNamedParam(params);
	}
	
	public List<UserTbl> findUserByLogin(Map<String, Object> params) {
		return findUserByLogin.executeByNamedParam(params);
	}
	
	public void insertUser(Map<String, Object> params){
		insertUser.updateByNamedParam(params);
	}
	
	public List<UserTbl> allUsers () {
		return allUsers.execute();
	}
}
