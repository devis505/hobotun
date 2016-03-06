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
			  "SELECT fm.*, "
			  + "     u.login, "
			  + "     u.mail,"
			  + "     u.dtReg, "
			  + "     u.idImage,"
			  + "     IFNULL((select sum(rm.vl_rating) from hb_user_model um inner join hb_rating_modele rm on rm.id_model = um.IdModel where um.idUser = u.id_user and um.idEntityType = 1), 0) rating_user "
			+ "  FROM hb_forum_msg fm "
			+ " INNER JOIN hb_user u ON u.id_user = fm.id_user "
			+ " WHERE fm.id_thema = :id_thema  "
			+ " ORDER BY fm.id_forum_msg ";

	public SelectForumMsgByIdThema(DataSource dataSource) {
		super(dataSource, SQL_SELECT_THEMA_BY_FORUM);
		super.declareParameter(new SqlParameter("id_thema", Types.INTEGER));
	}

	@Override
	protected ForumMsgTbl mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new ForumMsgTbl(rs);
	}

}
