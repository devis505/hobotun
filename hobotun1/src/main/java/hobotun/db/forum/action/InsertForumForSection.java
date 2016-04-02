package hobotun.db.forum.action;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class InsertForumForSection extends SqlUpdate  {

	private static final String SQL_INSERT_FORUM = "INSERT INTO hb_forum (nm_forum, vl_discription, id_forum_section) VALUE (:nm_forum, :vl_discription, :id_forum_section)";
	
	public InsertForumForSection(DataSource dataSource) {
		super(dataSource, SQL_INSERT_FORUM);

		super.declareParameter(new SqlParameter("nm_forum", Types.VARCHAR));
		super.declareParameter(new SqlParameter("vl_discription", Types.VARCHAR));
		super.declareParameter(new SqlParameter("id_forum_section", Types.INTEGER));
		
		super.setGeneratedKeysColumnNames(new String[] { "id_forum" });
		super.setReturnGeneratedKeys(true);
	}
}
