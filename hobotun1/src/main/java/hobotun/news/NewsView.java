package hobotun.news;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import hobotun.core.Misc;
import hobotun.db.DBUtil;
import hobotun.db.news.NewsDao;
import hobotun.db.news.table.NewsTbl;

@ManagedBean(name = "newsViev")
@ViewScoped
public class NewsView implements Serializable {

	private static final long serialVersionUID = -176279925739707433L;

	private String id_news;
	private NewsDao newsDao;
	private NewsTbl newsTbl;
	
	public NewsView() {
		id_news = Misc.getRequestParam(FacesContext.getCurrentInstance(), "id");
		
		newsDao = DBUtil.getInstance().getBean("newsDao", NewsDao.class);
		newsDao.UpdateCountView(new Long(id_news));
		newsTbl = newsDao.selectNewsById(new Long(id_news));
	}

	public NewsTbl getNewsTbl() {
		return newsTbl;
	}

	public void setNewsTbl(NewsTbl newsTbl) {
		this.newsTbl = newsTbl;
	}
}
