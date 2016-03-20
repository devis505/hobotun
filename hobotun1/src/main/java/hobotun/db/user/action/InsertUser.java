package hobotun.db.user.action;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class InsertUser extends SqlUpdate {

	private static final String SQL_INSERT_FORGET_PASS = "INSERT INTO hb_user (login, mail, password, dtReg, FIO, balance, idImage) VALUE (:login, :mail, :password, :dtReg, '', 0, null)";

	public InsertUser(DataSource dataSource) {
		super(dataSource, SQL_INSERT_FORGET_PASS);

		super.declareParameter(new SqlParameter("login", Types.VARCHAR));
		super.declareParameter(new SqlParameter("mail", Types.VARCHAR));
		super.declareParameter(new SqlParameter("password", Types.VARCHAR));
		super.declareParameter(new SqlParameter("dtReg", Types.DATE));
	}
}
