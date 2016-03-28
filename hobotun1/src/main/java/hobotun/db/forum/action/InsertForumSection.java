package hobotun.db.forum.action;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class InsertForumSection extends SqlUpdate  {

	private static final String SQL_INSERT_FORUM = "INSERT INTO hb_forum_section (nm_forum_section, vl_icon) VALUE (:nm_forum_section, :vl_icon);";
	
	public InsertForumSection(DataSource dataSource) {
		super(dataSource, SQL_INSERT_FORUM);

		super.declareParameter(new SqlParameter("nm_forum_section", Types.VARCHAR));
		super.declareParameter(new SqlParameter("vl_icon", Types.VARCHAR));
		
		super.setGeneratedKeysColumnNames(new String[] { "id_forum_section" });
		super.setReturnGeneratedKeys(true);
	}
}
