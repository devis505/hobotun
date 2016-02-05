package hobotun.db.forum.action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import hobotun.db.forum.table.ForumTbl;

public class SelectForumByIdSection extends MappingSqlQuery<ForumTbl>{
	
	private static final String SQL_SELECT_ALL_FORUM_SECTION = "SELECT * FROM hb_forum f WHERE f.id_forum_section = :id_forum_section";

	public SelectForumByIdSection(DataSource dataSource) {
		super(dataSource, SQL_SELECT_ALL_FORUM_SECTION);
		super.declareParameter(new SqlParameter("id_forum_section", Types.INTEGER));
	}
	
	@Override
	protected ForumTbl mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new ForumTbl(rs);
	}

}
