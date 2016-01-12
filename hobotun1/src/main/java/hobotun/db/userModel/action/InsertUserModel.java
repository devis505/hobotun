package hobotun.db.userModel.action;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class InsertUserModel extends SqlUpdate {
	private static final String SQL_INSERT_USER_MODEL = "INSERT INTO hb_user_model(idUser, IdModel, idEntityType) "
			+ "VALUES (:idUser, :idModel, :idEntityType)";

	public InsertUserModel(DataSource dataSource) {
		super(dataSource, SQL_INSERT_USER_MODEL);

		super.declareParameter(new SqlParameter("idUser", Types.INTEGER));
		super.declareParameter(new SqlParameter("idModel", Types.INTEGER));
		super.declareParameter(new SqlParameter("idEntityType", Types.INTEGER));

	}
}
