package hobotun.db.user.action;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class InsertOutBalanc extends SqlUpdate {
    
    private static final String SQL_INSERT_USER = 
	    "INSERT INTO hb_outUserBalance(id_user, outMoney, state, dtStartOut) VALUES (:id_user, :balance, :state, :dtStartOut)";

    public InsertOutBalanc(DataSource dataSource) {
	super(dataSource, SQL_INSERT_USER);
	
	super.declareParameter(new SqlParameter("id_user", Types.INTEGER));
	super.declareParameter(new SqlParameter("balance", Types.DECIMAL));
	super.declareParameter(new SqlParameter("state", Types.INTEGER));
	super.declareParameter(new SqlParameter("dtStartOut", Types.DATE));
    }
}
