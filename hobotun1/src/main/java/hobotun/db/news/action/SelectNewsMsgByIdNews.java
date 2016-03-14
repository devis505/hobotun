package hobotun.db.news.action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import hobotun.db.news.table.NewsMsgTbl;

public class SelectNewsMsgByIdNews extends MappingSqlQuery<NewsMsgTbl> {

	private static final String SQL_SELECT_THEMA_BY_FORUM = 
			  "SELECT mm.*, "
			  + "     u.login, "
			  + "     u.mail,"
			  + "     u.dtReg, "
			  + "     u.idImage,"
			  + "     IFNULL((select sum(rm.vl_rating) from hb_user_model um inner join hb_rating_modele rm on rm.id_model = um.IdModel where um.idUser = u.id_user and um.idEntityType = 1), 0) rating_user "
			+ "  FROM hb_news_msg mm "
			+ " INNER JOIN hb_user u ON u.id_user = mm.id_user "
			+ " WHERE mm.id_news = :id_news  "
			+ " ORDER BY mm.id_news_msg ";

	public SelectNewsMsgByIdNews(DataSource dataSource) {
		super(dataSource, SQL_SELECT_THEMA_BY_FORUM);
		super.declareParameter(new SqlParameter("id_news", Types.INTEGER));
	}

	@Override
	protected NewsMsgTbl mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new NewsMsgTbl(rs);
	}

}
