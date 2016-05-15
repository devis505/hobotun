package hobotun.db.category.action;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class DeleteCategory2 extends SqlUpdate  {

	private static final String SQL_DELETE_CATEGORY = "DELETE FROM hb_category_p WHERE id_category_p = :id_category_p";
	
	public DeleteCategory2(DataSource dataSource) {
		super(dataSource, SQL_DELETE_CATEGORY);

		super.declareParameter(new SqlParameter("id_category_p", Types.INTEGER));
	}
}
