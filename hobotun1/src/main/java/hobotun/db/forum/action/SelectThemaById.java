package hobotun.db.forum.action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import hobotun.db.forum.table.ThemaTbl;

public class SelectThemaById extends MappingSqlQuery<ThemaTbl> {

	private static final String SQL_SELECT_THEMA_BY_FORUM = 
			  "SELECT t.*, "
			+ "       (SELECT u.login FROM hb_user u WHERE u.id_user = t.id_user) nm_user, "
			+ "       (select count(*) from hb_forum_msg m where m.id_thema = t.id_thema) nn_count_otvet, "
			+ "       IFNULL((select sum(rm.vl_rating) from hb_user_model um inner join hb_rating_modele rm on rm.id_model = um.IdModel where um.idUser = t.id_user and um.idEntityType = 1), 0) rating_user"
			+ "  FROM hb_thema t WHERE t.id_thema = :id_thema";

	public SelectThemaById(DataSource dataSource) {
		super(dataSource, SQL_SELECT_THEMA_BY_FORUM);
		super.declareParameter(new SqlParameter("id_thema", Types.INTEGER));
	}

	@Override
	protected ThemaTbl mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new ThemaTbl(rs);
	}

}
