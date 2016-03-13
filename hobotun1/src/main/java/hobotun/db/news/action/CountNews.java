package hobotun.db.news.action;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.object.MappingSqlQuery;

import hobotun.db.news.table.NewsCountTbl;

public class CountNews extends MappingSqlQuery<NewsCountTbl> {

	private static final String SQL_ALL_NEWS = 
			"select count(*) cnt_news from hb_news";
	
	public CountNews(DataSource dataSource) {
		super(dataSource, SQL_ALL_NEWS);
	}
	
	@Override
	protected NewsCountTbl mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new NewsCountTbl(rs);
	}

}
