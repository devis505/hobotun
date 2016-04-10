package hobotun.db.user.action;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.object.MappingSqlQuery;

import hobotun.db.user.table.UserTbl;

public class AllUsers extends MappingSqlQuery<UserTbl> {

	private static final String SQL_FIND_USER_BY_ID = 
   			  "select u.*, \n"
   			+ "       IFNULL((select sum(rm.vl_rating) from hb_user_model um inner join hb_rating_modele rm on rm.id_model = um.IdModel where um.idUser = u.id_user and um.idEntityType = 1), 0) rating_user \n"
   			+ "  from hb_user u \n";

	public AllUsers(DataSource dataSource) {
		super(dataSource, SQL_FIND_USER_BY_ID);
	}

	@Override
	protected UserTbl mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new UserTbl(rs);
	}

}
