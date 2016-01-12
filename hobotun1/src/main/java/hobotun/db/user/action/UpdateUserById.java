package hobotun.db.user.action;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class UpdateUserById extends SqlUpdate {
    
    private static final String SQL_UPDATE_USER = 
	    "UPDATE hb_user u "
	  + "   SET u.mail=:mail, u.password=:password, u.FIO=:fio"
	  + " WHERE u.id_user = :id_user";

    public UpdateUserById(DataSource dataSource) {
	super(dataSource, SQL_UPDATE_USER);
	
	super.declareParameter(new SqlParameter("mail", Types.VARCHAR));
	super.declareParameter(new SqlParameter("password", Types.VARCHAR));
	super.declareParameter(new SqlParameter("fio", Types.VARCHAR));
	super.declareParameter(new SqlParameter("id_user", Types.INTEGER));
    }
}
