package hobotun.db.news;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import hobotun.db.news.action.CountNews;
import hobotun.db.news.action.InsertNewsMsg;
import hobotun.db.news.action.SelectAllNews;
import hobotun.db.news.action.SelectFirst3News;
import hobotun.db.news.action.SelectNewsById;
import hobotun.db.news.action.SelectNewsMsgByIdNews;
import hobotun.db.news.action.UpdateCountView;
import hobotun.db.news.table.NewsMsgTbl;
import hobotun.db.news.table.NewsTbl;

public class NewsDao {
	
	private DataSource dataSource;
	private SelectAllNews selectAllNews;
	private SelectFirst3News selectFirst3News;
	private CountNews countNews;
	private SelectNewsById selectNewsById;
	private UpdateCountView updateCountView;
	private SelectNewsMsgByIdNews selectNewsMsgByIdNews;
	private InsertNewsMsg insertNewsMsg;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		
		selectAllNews = new SelectAllNews(dataSource);
		selectFirst3News = new SelectFirst3News(dataSource);
		countNews = new CountNews(dataSource);
		selectNewsById = new SelectNewsById(dataSource);
		updateCountView = new UpdateCountView(dataSource);
		selectNewsMsgByIdNews = new SelectNewsMsgByIdNews(dataSource);
		insertNewsMsg = new InsertNewsMsg(dataSource);
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
	
	public List<NewsMsgTbl> selectModeleMsgByIdModele (Long id_news) {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id_news", id_news);
		
		return selectNewsMsgByIdNews.executeByNamedParam(paramMap);
	}
	
	public void insertNewsMsg(NewsMsgTbl newsMsg) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("id_user", newsMsg.getId_user());
		paramMap.put("id_news", newsMsg.getId_news());
		paramMap.put("vl_msg", newsMsg.getVl_msg());
		paramMap.put("dt_news_msg", newsMsg.getDt_news_msg());
		
		KeyHolder keyHolder = new GeneratedKeyHolder();

		insertNewsMsg.updateByNamedParam(paramMap, keyHolder);

		newsMsg.setId_news_msg(keyHolder.getKey().longValue());
	}
	
}
