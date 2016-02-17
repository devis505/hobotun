package hobotun.db.forum.action;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class InsertForumMsg extends SqlUpdate  {

	private static final String SQL_INSERT_FORUM = "INSERT INTO hb_forum_msg(vl_msg, id_user, dt_msg, id_thema) VALUES (:vl_msg, :id_user, :dt_msg, :id_thema)";
	
	public InsertForumMsg(DataSource dataSource) {
		super(dataSource, SQL_INSERT_FORUM);

		super.declareParameter(new SqlParameter("vl_msg", Types.VARCHAR));
		super.declareParameter(new SqlParameter("id_user", Types.INTEGER));
		super.declareParameter(new SqlParameter("dt_msg", Types.DATE));
		super.declareParameter(new SqlParameter("id_thema", Types.INTEGER));
		
		super.setGeneratedKeysColumnNames(new String[] { "id_forum_msg" });
		super.setReturnGeneratedKeys(true);
	}
}
