package hobotun.db.model.action;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class UpdateModeration extends SqlUpdate {

	private static final String SQL_UPDATE_MODELE = 
			"UPDATE hb_model " + 
			"	SET is_moderation = 1 " +
			" WHERE idModel = :idModel";

	public UpdateModeration(DataSource dataSource) {
		super(dataSource, SQL_UPDATE_MODELE);

		super.declareParameter(new SqlParameter("idModel", Types.INTEGER));
	}
}
