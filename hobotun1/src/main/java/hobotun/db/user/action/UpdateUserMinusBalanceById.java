package hobotun.db.user.action;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class UpdateUserMinusBalanceById extends SqlUpdate {
    
    private static final String SQL_UPDATE_USER = 
	    "UPDATE hb_user u "
	  + "   SET u.balance=u.balance - :balance "
	  + " WHERE u.id_user = :id_user";
    
    public UpdateUserMinusBalanceById(DataSource dataSource) {
	super(dataSource, SQL_UPDATE_USER);
	
	super.declareParameter(new SqlParameter("id_user", Types.INTEGER));
	super.declareParameter(new SqlParameter("balance", Types.DECIMAL));
    }
}
