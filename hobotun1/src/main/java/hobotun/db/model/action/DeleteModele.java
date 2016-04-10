package hobotun.db.model.action;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class DeleteModele extends SqlUpdate  {

	private static final String SQL_DELETE_MODEL = "DELETE FROM hb_model WHERE idModel = :idModel";
	
	public DeleteModele(DataSource dataSource) {
		super(dataSource, SQL_DELETE_MODEL);

		super.declareParameter(new SqlParameter("idModel", Types.INTEGER));
	}
}
