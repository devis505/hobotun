package hobotun.db.forum.action;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.object.MappingSqlQuery;

import hobotun.db.forum.table.ForumSectionTbl;

public class SelectAllForumSection extends MappingSqlQuery<ForumSectionTbl>{
	
	private static final String SQL_SELECT_ALL_FORUM_SECTION = "SELECT * FROM hb_forum_section";

	public SelectAllForumSection(DataSource dataSource) {
		super(dataSource, SQL_SELECT_ALL_FORUM_SECTION);
	}
	
	@Override
	protected ForumSectionTbl mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new ForumSectionTbl(rs);
	}

}
