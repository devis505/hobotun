package hobotun.db.news;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import hobotun.db.news.action.CountNews;
import hobotun.db.news.action.SelectAllNews;
import hobotun.db.news.action.SelectFirst3News;
import hobotun.db.news.action.SelectNewsById;
import hobotun.db.news.action.UpdateCountView;
import hobotun.db.news.table.NewsTbl;

public class NewsDao {
	
	private DataSource dataSource;
	private SelectAllNews selectAllNews;
	private SelectFirst3News selectFirst3News;
	private CountNews countNews;
	private SelectNewsById selectNewsById;
	private UpdateCountView updateCountView;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		
		selectAllNews = new SelectAllNews(dataSource);
		selectFirst3News = new SelectFirst3News(dataSource);
		countNews = new CountNews(dataSource);
		selectNewsById = new SelectNewsById(dataSource);
		updateCountView = new UpdateCountView(dataSource);
	}
	
	public List<NewsTbl> selectAllNews() {
		return selectAllNews.execute();
	}
	
	public List<NewsTbl> selectFirst3News() {
		return selectFirst3News.execute();
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}
	
	public Integer countNews() {
		return countNews.execute().get(0).getCnt_news();	
	}
	
	public NewsTbl selectNewsById(Long id) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id_news", id);

		return selectNewsById.executeByNamedParam(paramMap).get(0);
	}
	
	public void UpdateCountView(Long id) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id_news", id);
		
		updateCountView.updateByNamedParam(paramMap);
	}
	
}
