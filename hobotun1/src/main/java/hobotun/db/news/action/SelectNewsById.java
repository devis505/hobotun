package hobotun.db.news.action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import hobotun.db.news.table.NewsTbl;

public class SelectNewsById extends MappingSqlQuery<NewsTbl> {

	private static final String SQL_ALL_NEWS = 
			"select n.*, 0 msg \n" +
            "  from hb_news n where n.id_news = :id_news";
	
	public SelectNewsById(DataSource dataSource) {
		super(dataSource, SQL_ALL_NEWS);
		super.declareParameter(new SqlParameter("id_news", Types.INTEGER));
	}
	
	@Override
	protected NewsTbl mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new NewsTbl(rs);
	}

}
