package hobotun.db.user.action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import hobotun.db.user.table.UserTbl;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

public class FindUserById extends MappingSqlQuery<UserTbl> {

    private static final String SQL_FIND_USER_BY_ID = 
	    "select * \n"
	  + "  from hb_user u \n"
	  + " where u.id_user = :id_user \n";

    public FindUserById(DataSource dataSource) {
	super(dataSource, SQL_FIND_USER_BY_ID);

	super.declareParameter(new SqlParameter("id_user", Types.INTEGER));
    }

    @Override
    protected UserTbl mapRow(ResultSet rs, int rowNum) throws SQLException {
	return new UserTbl(rs);
    }

}
