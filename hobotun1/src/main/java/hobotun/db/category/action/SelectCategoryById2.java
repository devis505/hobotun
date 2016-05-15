package hobotun.db.category.action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import hobotun.db.category.table.Category2Tbl;

public class SelectCategoryById2 extends MappingSqlQuery<Category2Tbl> {

	private static final String SQL_SELECT_ALL_CATEGORY = "SELECT * FROM hb_category_p c where c.id_category_p = :id_category_p";

	public SelectCategoryById2(DataSource dataSource) {
		super(dataSource, SQL_SELECT_ALL_CATEGORY);
		super.declareParameter(new SqlParameter("id_category_p", Types.INTEGER));
	}

	@Override
	protected Category2Tbl mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Category2Tbl(rs);
	}

}
