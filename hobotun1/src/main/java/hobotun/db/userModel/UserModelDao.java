package hobotun.db.userModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import hobotun.db.userModel.action.FindUserModelByIdModel;
import hobotun.db.userModel.action.InsertUserModel;
import hobotun.db.userModel.table.UserModelTbl;

public class UserModelDao implements IUserModeDao {

	private DataSource dataSource;
	private InsertUserModel insertUserModel;
	private FindUserModelByIdModel findUserModelByIdModel;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		insertUserModel = new InsertUserModel(dataSource);
		findUserModelByIdModel = new FindUserModelByIdModel(dataSource);
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
	
	public List<UserModelTbl> findUserModelByIdModel (Long id) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id_model", id);
		
		return findUserModelByIdModel.executeByNamedParam(params);
	}

}
