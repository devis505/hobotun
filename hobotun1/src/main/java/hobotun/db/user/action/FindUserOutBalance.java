package hobotun.db.user.action;

import hobotun.db.user.table.OutUserBalanceTbl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

public class FindUserOutBalance extends MappingSqlQuery<OutUserBalanceTbl> {

    private static final String SQL_FIND_USER_OUT_BALANCE = 
	    "SELECT outb.*, case when outb.state = 1 then 'В обработке' when outb.state = 2 then 'Обработан' else 'Отменен' end stateName \n"
	  + "  FROM hb_outUserBalance outb \n"
	  + " WHERE outb.id_user = :id_user ";

    public FindUserOutBalance(DataSource dataSource) {
	super(dataSource, SQL_FIND_USER_OUT_BALANCE);

	super.declareParameter(new SqlParameter("id_user", Types.INTEGER));
    }

    @Override
    protected OutUserBalanceTbl mapRow(ResultSet rs, int rowNum) throws SQLException {
	return new OutUserBalanceTbl(rs);
    }


}


