package hobotun.db.forum.action;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class InsertForum extends SqlUpdate  {

	private static final String SQL_INSERT_FORUM = "INSERT INTO hb_thema(id_forum, nm_thema, id_user, nn_count_view, isUp) VALUES (:id_forum, :nm_thema, :id_user, 0, 0)";
	
	public InsertForum(DataSource dataSource) {
		super(dataSource, SQL_INSERT_FORUM);

		super.declareParameter(new SqlParameter("id_forum", Types.INTEGER));
		super.declareParameter(new SqlParameter("nm_thema", Types.VARCHAR));
		super.declareParameter(new SqlParameter("id_user", Types.INTEGER));
		
		super.setGeneratedKeysColumnNames(new String[] { "id_thema" });
		super.setReturnGeneratedKeys(true);
	}
}
