package hobotun.db.category.action;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import hobotun.db.category.table.CategoryTbl;

import org.springframework.jdbc.object.MappingSqlQuery;

public class SelectAllCategory extends MappingSqlQuery<CategoryTbl> {

	private static final String SQL_SELECT_ALL_CATEGORY = "SELECT * FROM hb_category c where c.idCategory <> 0";

	public SelectAllCategory(DataSource dataSource) {
		super(dataSource, SQL_SELECT_ALL_CATEGORY);
	}

	@Override
	protected CategoryTbl mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new CategoryTbl(rs);
	}

}
