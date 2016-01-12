package hobotun.db.user;

import hobotun.db.user.action.*;
import hobotun.db.user.table.OutUserBalanceTbl;
import hobotun.db.user.table.UserTbl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

public class UserDao implements IUserDao, Serializable {

	private static final long serialVersionUID = 4489468539901015901L;

	private DataSource dataSource;
	private FindUserByLoginAndPass findUserByLoginPass;
	private FindUserByMailAndPass findUserByMailPass;
	private FindUserByMail finUserByMail;
	private UpdateUserById updateUserById;
	private UpdateUserBalanceById updateUserBalanceById;
	private FindUserOutBalance findUserOutBalance;
	private UpdateUserMinusBalanceById updateUserMinusBalanceById;
	private InsertOutBalanc insertOutBalanc;
	private FindUserById findUserById;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.findUserByLoginPass = new FindUserByLoginAndPass(dataSource);
		this.findUserByMailPass = new FindUserByMailAndPass(dataSource);
		this.finUserByMail = new FindUserByMail(dataSource);
		this.updateUserById = new UpdateUserById(dataSource);
		this.updateUserBalanceById = new UpdateUserBalanceById(dataSource);
		this.findUserOutBalance = new FindUserOutBalance(dataSource);
		this.updateUserMinusBalanceById = new UpdateUserMinusBalanceById(dataSource);
		this.insertOutBalanc = new InsertOutBalanc(dataSource);
		this.findUserById = new FindUserById(dataSource);
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

}
