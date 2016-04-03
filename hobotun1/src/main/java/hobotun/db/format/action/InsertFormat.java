package hobotun.db.format.action;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class InsertFormat extends SqlUpdate  {

	private static final String SQL_INSERT_FORMAT = "INSERT INTO hb_format (nmFormat) VALUE (:nmFormat)";
	
	public InsertFormat(DataSource dataSource) {
		super(dataSource, SQL_INSERT_FORMAT);

		super.declareParameter(new SqlParameter("nmFormat", Types.VARCHAR));
		
		super.setGeneratedKeysColumnNames(new String[] { "idFormat" });
		super.setReturnGeneratedKeys(true);
	}
}
