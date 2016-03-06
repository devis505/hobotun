package hobotun.db.file.Action;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class InsertFileReturnId extends SqlUpdate {

	private static final String SQL_INSERT_FILE = "insert into hb_file (file, nm_file) value (:file, :nm_file)";

	public InsertFileReturnId(DataSource dataSource) {
		super(dataSource, SQL_INSERT_FILE);

		super.declareParameter(new SqlParameter("file", Types.BLOB));
		super.declareParameter(new SqlParameter("nm_file", Types.VARCHAR));
		super.setGeneratedKeysColumnNames(new String[] { "idFile" });
		super.setReturnGeneratedKeys(true);
	}

}
