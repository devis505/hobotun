package hobotun.db.forum.action;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class DeleteForumForSection extends SqlUpdate  {

	private static final String SQL_DELETE_FORUM_SECTION = "DELETE FROM hb_forum WHERE id_forum = :id_forum";
	
	public DeleteForumForSection(DataSource dataSource) {
		super(dataSource, SQL_DELETE_FORUM_SECTION);

		super.declareParameter(new SqlParameter("id_forum", Types.INTEGER));
	}
}
