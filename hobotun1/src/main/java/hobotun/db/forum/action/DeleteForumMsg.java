package hobotun.db.forum.action;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class DeleteForumMsg extends SqlUpdate  {

	private static final String SQL_DELETE_FORUM_MSG = "DELETE FROM hb_forum_msg WHERE id_forum_msg = :id_forum_msg";
	
	public DeleteForumMsg(DataSource dataSource) {
		super(dataSource, SQL_DELETE_FORUM_MSG);

		super.declareParameter(new SqlParameter("id_forum_msg", Types.INTEGER));
	}
}
