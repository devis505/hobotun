package hobotun.news;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import hobotun.core.Misc;
import hobotun.core.UserSession;
import hobotun.db.DBUtil;
import hobotun.db.news.NewsDao;
import hobotun.db.news.table.NewsMsgTbl;
import hobotun.db.news.table.NewsTbl;

@ManagedBean(name = "newsViev")
@ViewScoped
public class NewsView implements Serializable {

	private static final long serialVersionUID = -176279925739707433L;

	private String id_news;
	private NewsDao newsDao;
	private NewsTbl newsTbl;
	
	private List<NewsMsgTbl> newsMsg;
	
	private String text;
	
	public NewsView() {
		id_news = Misc.getRequestParam(FacesContext.getCurrentInstance(), "id");
		
		newsDao = DBUtil.getInstance().getBean("newsDao", NewsDao.class);
		newsDao.UpdateCountView(new Long(id_news));
		newsTbl = newsDao.selectNewsById(new Long(id_news));
		newsMsg = newsDao.selectModeleMsgByIdModele(new Long(id_news));
	}

	public NewsTbl getNewsTbl() {
		return newsTbl;
	}

	public void setNewsTbl(NewsTbl newsTbl) {
		this.newsTbl = newsTbl;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public void onSaveText() {
		
		NewsMsgTbl newNewsMsg = new NewsMsgTbl();
		
		newNewsMsg.setDt_news_msg(new Date());
		newNewsMsg.setId_news(new Long(id_news));
		newNewsMsg.setId_user(UserSession.getInstance().getUser().getUserTbl().getId_user());
		newNewsMsg.setVl_msg(text);
		
		newsDao.insertNewsMsg(newNewsMsg);

		Misc.redirect("/pages/news/news.jsf?id=" + id_news);
		
	}

	public List<NewsMsgTbl> getNewsMsg() {
		return newsMsg;
	}

	public void setNewsMsg(List<NewsMsgTbl> newsMsg) {
		this.newsMsg = newsMsg;
	}
}
