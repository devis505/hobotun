package hobotun.db.model.action;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class DeleteModeleLink extends SqlUpdate  {

	private static final String SQL_DELETE_USER_MODEL = "DELETE FROM hb_user_model WHERE IdModel = :idModel";
	
	public DeleteModeleLink(DataSource dataSource) {
		super(dataSource, SQL_DELETE_USER_MODEL);

		super.declareParameter(new SqlParameter("idModel", Types.INTEGER));
	}
}
