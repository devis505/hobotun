package hobotun.db.news.action;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.object.MappingSqlQuery;

import hobotun.db.news.table.NewsTbl;

public class SelectFirst3News extends MappingSqlQuery<NewsTbl> {

	private static final String SQL_ALL_NEWS = 
			"select n.*, \n" +
		    "       (select count(*) from hb_news_msg nm where nm.id_news = n.id_news) msg \n" +
            "  from hb_news n \n" +
            " order by n.id_news DESC LIMIT 3";
	
	public SelectFirst3News(DataSource dataSource) {
		super(dataSource, SQL_ALL_NEWS);
	}
	
	@Override
	protected NewsTbl mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new NewsTbl(rs);
	}

}
