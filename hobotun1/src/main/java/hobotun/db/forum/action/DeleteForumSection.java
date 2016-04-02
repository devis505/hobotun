package hobotun.db.forum.action;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class DeleteForumSection extends SqlUpdate  {

	private static final String SQL_DELETE_FORUM_SECTION = "DELETE FROM hb_forum_section WHERE id_forum_section = :id_forum_section";
	
	public DeleteForumSection(DataSource dataSource) {
		super(dataSource, SQL_DELETE_FORUM_SECTION);

		super.declareParameter(new SqlParameter("id_forum_section", Types.INTEGER));
	}
}
