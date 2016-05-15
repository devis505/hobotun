package hobotun.db.user.action;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class InsertOutBalanc extends SqlUpdate {

	private static final String SQL_INSERT_USER = "INSERT INTO hb_balance_hist (id_user, sm, dt_create, dt_change, desc, kd_state, kd_type) "
			+ " VALUE (:id_user, :sm, TO_DAYS(NOW()), TO_DAYS(NOW()), :desc, :kd_state, :kd_type) ";

	public InsertOutBalanc(DataSource dataSource) {
		super(dataSource, SQL_INSERT_USER);

		super.declareParameter(new SqlParameter("id_user", Types.INTEGER));
		super.declareParameter(new SqlParameter("sm", Types.DECIMAL));
		super.declareParameter(new SqlParameter("desc", Types.VARCHAR));
		super.declareParameter(new SqlParameter("kd_state", Types.INTEGER));
		super.declareParameter(new SqlParameter("kd_type", Types.INTEGER));
		
		super.setGeneratedKeysColumnNames(new String[] { "id_balance_hist" });
		super.setReturnGeneratedKeys(true);
	}
}
