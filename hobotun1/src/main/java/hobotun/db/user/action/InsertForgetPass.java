package hobotun.db.user.action;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class InsertForgetPass extends SqlUpdate {

	private static final String SQL_INSERT_FORGET_PASS = "INSERT INTO hb_forget_pass (id_user, vl_hash, dt_create) VALUE (:id_user, :vl_hash, :dt_create)";

	public InsertForgetPass(DataSource dataSource) {
		super(dataSource, SQL_INSERT_FORGET_PASS);

		super.declareParameter(new SqlParameter("id_user", Types.INTEGER));
		super.declareParameter(new SqlParameter("vl_hash", Types.VARCHAR));
		super.declareParameter(new SqlParameter("dt_create", Types.DATE));
	}
}
