package hobotun.db.category.action;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class DeleteCategory extends SqlUpdate  {

	private static final String SQL_DELETE_CATEGORY = "DELETE FROM hb_category WHERE idCategory = :idCategory";
	
	public DeleteCategory(DataSource dataSource) {
		super(dataSource, SQL_DELETE_CATEGORY);

		super.declareParameter(new SqlParameter("idCategory", Types.INTEGER));
	}
}
