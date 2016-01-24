package hobotun.db.userModel.action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import hobotun.db.userModel.table.UserModelTbl;

public class FindUserModelByIdModel extends MappingSqlQuery<UserModelTbl>{
	
	private static final String SQL_SELECT_MODEL_BY_USER_ID = 
			  "SELECT * FROM hb_user_model u WHERE u.IdModel = :id_model AND u.idEntityType = 1";

	public FindUserModelByIdModel(DataSource dataSource) {
		super(dataSource, SQL_SELECT_MODEL_BY_USER_ID);
		super.declareParameter(new SqlParameter("id_model", Types.INTEGER));
	}
	
	@Override
	protected UserModelTbl mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new UserModelTbl(rs);
	}

}
