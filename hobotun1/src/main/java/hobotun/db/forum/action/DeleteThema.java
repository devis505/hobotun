package hobotun.db.forum.action;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class DeleteThema extends SqlUpdate  {

	private static final String SQL_DELETE_THEMA = "DELETE FROM hb_thema WHERE id_thema = :id_thema";
	
	public DeleteThema(DataSource dataSource) {
		super(dataSource, SQL_DELETE_THEMA);

		super.declareParameter(new SqlParameter("id_thema", Types.INTEGER));
	}
}
