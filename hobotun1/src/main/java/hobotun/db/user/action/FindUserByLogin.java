package hobotun.db.user.action;

import hobotun.db.user.table.UserTbl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

public class FindUserByLogin extends MappingSqlQuery<UserTbl> {

    private static final String SQL_FIND_USER_BY_LOGIN_AND_PASS = 
	    "select u.*, 0 rating_user \n"
	  + "  from hb_user u \n"
	  + " where u.login = :login and u.is_block = 0 \n";

    public FindUserByLogin(DataSource dataSource) {
    	super(dataSource, SQL_FIND_USER_BY_LOGIN_AND_PASS);

		super.declareParameter(new SqlParameter("login", Types.VARCHAR));
    }

    @Override
    protected UserTbl mapRow(ResultSet rs, int rowNum) throws SQLException {
    	return new UserTbl(rs);
    }
    
    
}
