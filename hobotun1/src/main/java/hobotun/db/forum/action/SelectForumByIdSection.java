package hobotun.db.forum.action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import hobotun.db.forum.table.ForumTbl;

public class SelectForumByIdSection extends MappingSqlQuery<ForumTbl>{
	
	private static final String SQL_SELECT_ALL_FORUM_SECTION = 
			"SELECT f.*, (SELECT COUNT(*) FROM hb_thema t WHERE t.id_forum = f.id_forum) cnt_them,"
			+ "		(SELECT COUNT(*) "
			+ "        FROM hb_thema t "
			+ "       INNER JOIN hb_forum_msg m ON m.id_thema = t.id_thema"
			+ "       WHERE t.id_forum = f.id_forum) cnt_msg "
			+ "FROM hb_forum f WHERE ((f.id_forum_section = :id_forum_section) or (-1 = :id_forum_section))";

	public SelectForumByIdSection(DataSource dataSource) {
		super(dataSource, SQL_SELECT_ALL_FORUM_SECTION);
		super.declareParameter(new SqlParameter("id_forum_section", Types.INTEGER));
	}
	
	@Override
	protected ForumTbl mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new ForumTbl(rs);
	}

}
