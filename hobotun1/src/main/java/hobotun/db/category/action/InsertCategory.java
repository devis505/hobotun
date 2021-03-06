package hobotun.db.category.action;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class InsertCategory extends SqlUpdate  {

	private static final String SQL_INSERT_CATEGORY = "INSERT INTO hb_category (nmCategory, id_category_p) VALUE (:nmCategory, :id_category_p)";
	
	public InsertCategory(DataSource dataSource) {
		super(dataSource, SQL_INSERT_CATEGORY);

		super.declareParameter(new SqlParameter("nmCategory", Types.VARCHAR));
		super.declareParameter(new SqlParameter("id_category_p", Types.INTEGER));
		
		super.setGeneratedKeysColumnNames(new String[] { "idCategory" });
		super.setReturnGeneratedKeys(true);
	}
}
