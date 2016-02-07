package hobotun.db.forum.action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import hobotun.db.forum.table.ThemaTbl;

public class SelectThemaByIdForum extends MappingSqlQuery<ThemaTbl> {

	private static final String SQL_SELECT_THEMA_BY_FORUM = "SELECT t.*, (SELECT u.login FROM hb_user u WHERE u.id_user = t.id_user) nm_user FROM hb_thema t WHERE t.id_forum = :id_forum ORDER BY t.isUp DESC";

	public SelectThemaByIdForum(DataSource dataSource) {
		super(dataSource, SQL_SELECT_THEMA_BY_FORUM);
		super.declareParameter(new SqlParameter("id_forum", Types.INTEGER));
	}

	@Override
	protected ThemaTbl mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new ThemaTbl(rs);
	}

}
