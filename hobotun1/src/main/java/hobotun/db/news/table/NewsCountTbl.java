package hobotun.db.news.table;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewsCountTbl {
	
	private Integer cnt_news;
	
	private NewsCountTbl() {
		
	}
	
	public NewsCountTbl(ResultSet rs) throws SQLException {
		cnt_news = rs.getInt("cnt_news");
	}

	public Integer getCnt_news() {
		return cnt_news;
	}

	public void setCnt_news(Integer cnt_news) {
		this.cnt_news = cnt_news;
	}
	
}
