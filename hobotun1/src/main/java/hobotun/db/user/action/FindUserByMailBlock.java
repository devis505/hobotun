package hobotun.db.user.action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import hobotun.db.user.table.UserTbl;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

public class FindUserByMailBlock extends MappingSqlQuery<UserTbl> {

	private static final String SQL_FIND_USER_BY_MAIL = 
			  "select u.*, 0 rating_user \n" 
	        + "  from hb_user u \n"
			+ " where u.mail = :mail and u.is_block = 1 \n";

	public FindUserByMailBlock(DataSource dataSource) {
		super(dataSource, SQL_FIND_USER_BY_MAIL);

		super.declareParameter(new SqlParameter("mail", Types.VARCHAR));
	}

	@Override
	protected UserTbl mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new UserTbl(rs);
	}

}
