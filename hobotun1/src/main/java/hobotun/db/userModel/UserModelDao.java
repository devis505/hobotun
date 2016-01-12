package hobotun.db.userModel;

import hobotun.db.userModel.action.InsertUserModel;
import hobotun.db.userModel.table.UserModelTbl;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

public class UserModelDao implements IUserModeDao {

	private DataSource dataSource;
	private InsertUserModel insertUserModel;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		insertUserModel = new InsertUserModel(dataSource);
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

}
