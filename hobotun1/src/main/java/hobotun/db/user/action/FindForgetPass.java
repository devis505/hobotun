package hobotun.db.user.action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import hobotun.db.user.table.ForgetPassTbl;

public class FindForgetPass extends MappingSqlQuery<ForgetPassTbl> {

	private static final String SQL_FIND_USER_BY_MAIL_AND_PASS = 
			"select fp.* \n" + 
		    "  from hb_forget_pass fp \n" +
			" where fp.id_user = :id_user \n" + 
		    "   and fp.vl_hash = :vl_hash \n" +
		    "   and fp.dt_create = :dt_create \n";

	public FindForgetPass(DataSource dataSource) {
		super(dataSource, SQL_FIND_USER_BY_MAIL_AND_PASS);

		super.declareParameter(new SqlParameter("id_user", Types.INTEGER));
		super.declareParameter(new SqlParameter("vl_hash", Types.VARCHAR));
		super.declareParameter(new SqlParameter("dt_create", Types.DATE));
	}

	@Override
	protected ForgetPassTbl mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new ForgetPassTbl(rs);
	}

}
