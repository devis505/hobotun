package hobotun.db.forum.action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import hobotun.db.forum.table.ForumMsgTbl;

public class SelectForumMsgByIdThema extends MappingSqlQuery<ForumMsgTbl> {

	private static final String SQL_SELECT_THEMA_BY_FORUM = 
			  "SELECT fm.*, u.login, u.mail, u.dtReg, u.idImage "
			+ "  FROM hb_forum_msg fm "
			+ " INNER JOIN hb_user u ON u.id_user = fm.id_user "
			+ " WHERE fm.id_thema = :id_thema  "
			+ " ORDER BY fm.id_forum_msg DESC ";

	public SelectForumMsgByIdThema(DataSource dataSource) {
		super(dataSource, SQL_SELECT_THEMA_BY_FORUM);
		super.declareParameter(new SqlParameter("id_thema", Types.INTEGER));
	}

	@Override
	protected ForumMsgTbl mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new ForumMsgTbl(rs);
	}

}
