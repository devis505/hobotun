package hobotun.db.format.action;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class DeleteFormat extends SqlUpdate  {

	private static final String SQL_DELETE_FORMAT = "DELETE FROM hb_format WHERE idFormat = :idFormat";
	
	public DeleteFormat(DataSource dataSource) {
		super(dataSource, SQL_DELETE_FORMAT);

		super.declareParameter(new SqlParameter("idFormat", Types.INTEGER));
	}
}
