package hobotun.db.user;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import hobotun.db.user.action.AllUsers;
import hobotun.db.user.action.FindForgetPass;
import hobotun.db.user.action.FindUserById;
import hobotun.db.user.action.FindUserByLogin;
import hobotun.db.user.action.FindUserByLoginAndPass;
import hobotun.db.user.action.FindUserByMail;
import hobotun.db.user.action.FindUserByMailAndPass;
import hobotun.db.user.action.FindUserByMailBlock;
import hobotun.db.user.action.FindUserOutBalance;
import hobotun.db.user.action.InsertForgetPass;
import hobotun.db.user.action.InsertOutBalanc;
import hobotun.db.user.action.InsertUser;
import hobotun.db.user.action.UpdateOutBalanc;
import hobotun.db.user.action.UpdateUserBalanceById;
import hobotun.db.user.action.UpdateUserById;
import hobotun.db.user.action.UpdateUserByIdBlock;
import hobotun.db.user.action.UpdateUserMinusBalanceById;
import hobotun.db.user.table.ForgetPassTbl;
import hobotun.db.user.table.OutUserBalanceTbl;
import hobotun.db.user.table.UserTbl;

public class UserDao implements IUserDao, Serializable {

	private static final long serialVersionUID = 4489468539901015901L;

	private DataSource dataSource;
	private FindUserByLoginAndPass findUserByLoginPass;
	private FindUserByMailAndPass findUserByMailPass;
	private FindUserByMail finUserByMail;
	private FindUserByMailBlock finUserByMailBlock;
	private UpdateUserById updateUserById;
	private UpdateUserByIdBlock updateUserByIdBlock;
	private UpdateUserBalanceById updateUserBalanceById;
	private UpdateUserMinusBalanceById updateUserMinusBalanceById;
	private FindUserById findUserById;
	private InsertForgetPass insertForgetPass;
	private FindForgetPass findForgetPass;
	private FindUserByLogin findUserByLogin;
	private InsertUser insertUser;
	private AllUsers allUsers;

	private InsertOutBalanc insertOutBalanc;
	private UpdateOutBalanc updateOutBalanc;
	private FindUserOutBalance findUserOutBalance;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.findUserByLoginPass = new FindUserByLoginAndPass(dataSource);
		this.findUserByMailPass = new FindUserByMailAndPass(dataSource);
		this.finUserByMail = new FindUserByMail(dataSource);
		this.finUserByMailBlock = new FindUserByMailBlock(dataSource);
		this.updateUserById = new UpdateUserById(dataSource);
		this.updateUserByIdBlock = new UpdateUserByIdBlock(dataSource);
		this.updateUserBalanceById = new UpdateUserBalanceById(dataSource);
		this.updateUserMinusBalanceById = new UpdateUserMinusBalanceById(dataSource);
		this.findUserById = new FindUserById(dataSource);
		this.insertForgetPass = new InsertForgetPass(dataSource);
		this.findForgetPass = new FindForgetPass(dataSource);
		this.findUserByLogin = new FindUserByLogin(dataSource);
		this.insertUser = new InsertUser(dataSource);
		this.allUsers = new AllUsers(dataSource);

		this.insertOutBalanc = new InsertOutBalanc(dataSource);
		this.updateOutBalanc = new UpdateOutBalanc(dataSource);
		this.findUserOutBalance = new FindUserOutBalance(dataSource);
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

	public void UpdateUserByIdBlock(Map<String, Object> params) {
		updateUserByIdBlock.updateByNamedParam(params);
	}

	@Override
	public void UpdateBalanceById(Map<String, Object> params) {
		updateUserBalanceById.updateByNamedParam(params);
	}

	@Override
	public List<OutUserBalanceTbl> getOutUserBalance(Map<String, Object> params) {
		return findUserOutBalance.executeByNamedParam(params);
	}

	public List<OutUserBalanceTbl> getOutUserBalance(Long id_user) {

		Map<String, Object> params = new HashMap<>();
		params.put("id_user", id_user);

		return findUserOutBalance.executeByNamedParam(params);
	}

	@Override
	public void UpdateMinesBalanceById(Map<String, Object> params) {
		updateUserMinusBalanceById.updateByNamedParam(params);
	}

	public void InsertOutBalance(OutUserBalanceTbl balance) {

		Map<String, Object> params = new HashMap<>();

		params.put("id_user", balance.getId_user());
		params.put("sm", balance.getSm());
		params.put("desc", balance.getDesc());
		params.put("kd_state", balance.getKd_state());
		params.put("kd_type", balance.getKd_type());

		KeyHolder keyHolder = new GeneratedKeyHolder();

		insertOutBalanc.updateByNamedParam(params, keyHolder);

		balance.setId_balance_hist(keyHolder.getKey().intValue());
	}

	public void UpdateOutBalanc(OutUserBalanceTbl balance) {
		Map<String, Object> params = new HashMap<>();

		params.put("id_balance_hist", balance.getId_balance_hist());
		params.put("desc", balance.getDesc());
		params.put("kd_state", balance.getKd_state());
		params.put("kd_type", balance.getKd_type());

		updateOutBalanc.updateByNamedParam(params);
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

	public void insertUser(Map<String, Object> params) {
		insertUser.updateByNamedParam(params);
	}

	public List<UserTbl> allUsers() {
		return allUsers.execute();
	}

	@Override
	public void InsertOutBalance(Map<String, Object> params) {
		// TODO Auto-generated method stub

	}

}
