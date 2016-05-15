package hobotun.db.category.action;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.object.MappingSqlQuery;

import hobotun.db.category.table.Category2Tbl;

public class SelectAllCategory2 extends MappingSqlQuery<Category2Tbl> {

	private static final String SQL_SELECT_ALL_CATEGORY = "SELECT * FROM hb_category_p";

	public SelectAllCategory2(DataSource dataSource) {
		super(dataSource, SQL_SELECT_ALL_CATEGORY);
	}

	@Override
	protected Category2Tbl mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Category2Tbl(rs);
	}

}
