package hobotun.db.category.action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import hobotun.db.category.table.CategoryTbl;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

public class SelectCategoryById extends MappingSqlQuery<CategoryTbl> {

	private static final String SQL_SELECT_ALL_CATEGORY = "SELECT * FROM hb_category c where c.idCategory = :id_category";

	public SelectCategoryById(DataSource dataSource) {
		super(dataSource, SQL_SELECT_ALL_CATEGORY);
		super.declareParameter(new SqlParameter("id_category", Types.INTEGER));
	}

	@Override
	protected CategoryTbl mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new CategoryTbl(rs);
	}

}
