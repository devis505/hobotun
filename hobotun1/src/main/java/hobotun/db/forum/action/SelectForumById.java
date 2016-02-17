package hobotun.db.forum.action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import hobotun.db.forum.table.ForumTbl;

public class SelectForumById extends MappingSqlQuery<ForumTbl>{
	
	private static final String SQL_SELECT_FORUM_BY_ID = "SELECT f.*, 0 cnt_them, 0 cnt_msg FROM hb_forum f WHERE f.id_forum = :id_forum";

	public SelectForumById(DataSource dataSource) {
		super(dataSource, SQL_SELECT_FORUM_BY_ID);
		super.declareParameter(new SqlParameter("id_forum", Types.INTEGER));
	}
	
	@Override
	protected ForumTbl mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new ForumTbl(rs);
	}

}
