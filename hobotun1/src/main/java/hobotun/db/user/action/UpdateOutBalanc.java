package hobotun.db.user.action; 

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class UpdateOutBalanc extends SqlUpdate {

	private static final String SQL_INSERT_USER = 
			  "UPDATE hb_balance_hist " +
              "   SET dt_change = TO_DAYS(NOW()), desc = :desc, kd_state = :kd_state " +
              " WHERE id_balance_hist = :id_balance_hist ";

	public UpdateOutBalanc(DataSource dataSource) {
		super(dataSource, SQL_INSERT_USER);

		super.declareParameter(new SqlParameter("id_balance_hist", Types.INTEGER));
		super.declareParameter(new SqlParameter("desc", Types.VARCHAR));
		super.declareParameter(new SqlParameter("kd_state", Types.INTEGER));
		super.declareParameter(new SqlParameter("kd_type", Types.INTEGER));
	}
}
