package hobotun.db.forum.action;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class UpdateLock extends SqlUpdate {

	private static final String SQL_UPDATE_LOCK = 
			"UPDATE hb_thema " + 
			"	SET isBlock = 1 " +
			" WHERE id_thema = :id_thema";

	public UpdateLock(DataSource dataSource) {
		super(dataSource, SQL_UPDATE_LOCK);

		super.declareParameter(new SqlParameter("id_thema", Types.INTEGER));
	}
}
