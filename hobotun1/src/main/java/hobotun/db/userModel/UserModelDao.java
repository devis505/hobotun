package hobotun.db.userModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import hobotun.db.userModel.action.CheckUserModel;
import hobotun.db.userModel.action.CheckUserModelOwner;
import hobotun.db.userModel.action.FindUserModelByIdModel;
import hobotun.db.userModel.action.InsertUserModel;
import hobotun.db.userModel.table.UserModelTbl;

public class UserModelDao implements IUserModeDao {

	private DataSource dataSource;
	private InsertUserModel insertUserModel;
	private FindUserModelByIdModel findUserModelByIdModel;
	private CheckUserModel checkUserModel;
	private CheckUserModelOwner checkUserModelOwner;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		insertUserModel = new InsertUserModel(dataSource);
		findUserModelByIdModel = new FindUserModelByIdModel(dataSource);
		checkUserModel = new CheckUserModel(dataSource);
		checkUserModelOwner = new CheckUserModelOwner(dataSource);
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	@Override
	public void insertUserModel(UserModelTbl userModel) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("idUser", userModel.getIdUser());
		paramMap.put("idModel", userModel.getIdModel());
		paramMap.put("idEntityType", userModel.getIdEntityType());

		insertUserModel.updateByNamedParam(paramMap);
	}

	public List<UserModelTbl> findUserModelByIdModel(Long id) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id_model", id);

		return findUserModelByIdModel.executeByNamedParam(params);
	}

	public List<UserModelTbl> checkUserModel(Long id_user, Long id_model) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id_model", id_model);
		params.put("id_user", id_user);

		return checkUserModel.executeByNamedParam(params);
	}
	
	public List<UserModelTbl> checkUserModelOwner(Long id_user, Long id_model) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id_model", id_model);
		params.put("id_user", id_user);

		return checkUserModelOwner.executeByNamedParam(params);
	}

}
