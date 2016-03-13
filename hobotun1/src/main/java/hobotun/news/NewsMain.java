package hobotun.news;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import hobotun.db.DBUtil;
import hobotun.db.news.NewsDao;
import hobotun.db.news.table.NewsTbl;

@ManagedBean(name = "newsMain")
@ViewScoped
public class NewsMain implements Serializable {

	private static final long serialVersionUID = -518116625100690342L;

	private List<NewsTbl> newses;
	private Integer count_news;
	
	public NewsMain() {
		
		NewsDao newsDao = DBUtil.getInstance().getBean("newsDao", NewsDao.class);
		newses = newsDao.selectFirst3News();
		count_news = newsDao.countNews();
		
	}

	public List<NewsTbl> getNewses() {
		return newses;
	}

	public void setNewses(List<NewsTbl> newses) {
		this.newses = newses;
	}

	public Integer getCount_news() {
		return count_news;
	}

	public void setCount_news(Integer count_news) {
		this.count_news = count_news;
	}
	
}
