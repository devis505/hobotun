package hobotun.db.category.action;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class InsertCategory2 extends SqlUpdate  {

	private static final String SQL_INSERT_CATEGORY = "INSERT INTO hb_category_p (nm_category_p) VALUE (:nm_category_p)";
	
	public InsertCategory2(DataSource dataSource) {
		super(dataSource, SQL_INSERT_CATEGORY);

		super.declareParameter(new SqlParameter("nm_category_p", Types.VARCHAR));
		
		super.setGeneratedKeysColumnNames(new String[] { "id_category_p" });
		super.setReturnGeneratedKeys(true);
	}
}
