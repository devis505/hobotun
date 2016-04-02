package hobotun.forum;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import hobotun.core.Misc;
import hobotun.core.UserSession;
import hobotun.db.DBUtil;
import hobotun.db.forum.ForumDao;
import hobotun.db.forum.table.ForumMsgTbl;
import hobotun.db.forum.table.ThemaTbl;

@ManagedBean (name="addTopic")
@ViewScoped
public class AddTopic implements Serializable {
	
	private static final long serialVersionUID = 1497169896744545928L;
	private String text;
	private String id_forum;
	private String nm_topic;
	
	private boolean important = false;
	
	public AddTopic() {
		id_forum = Misc.getRequestParam(FacesContext.getCurrentInstance(), "id");
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public void onSave() {
		ThemaTbl themaTbl = new ThemaTbl();
		themaTbl.setId_forum(new Long(id_forum));
		themaTbl.setNm_thema(nm_topic);
		
		if (important) {
			themaTbl.setIsUp(1);
		} else {
			themaTbl.setIsUp(0);
		}
		
		themaTbl.setId_user(UserSession.getInstance().getUser().getUserTbl().getId_user());		
		
		ForumDao forumDao = DBUtil.getInstance().getBean("forumDao", ForumDao.class);
		forumDao.InsertThema(themaTbl);
		
		ForumMsgTbl forumMsgTbl = new ForumMsgTbl();
		
		forumMsgTbl.setDt_msg(new Date());
		forumMsgTbl.setId_thema(themaTbl.getId_thema());
		forumMsgTbl.setId_user(UserSession.getInstance().getUser().getUserTbl().getId_user());
		forumMsgTbl.setVl_msg(text);
		
		forumDao.InsertForumMsg(forumMsgTbl);
		
		Misc.redirect("/pages/forum/topic.jsf?id=" + forumMsgTbl.getId_thema());
	}

	public String getNm_topic() {
		return nm_topic;
	}

	public void setNm_topic(String nm_topic) {
		this.nm_topic = nm_topic;
	}

	public boolean isImportant() {
		return important;
	}

	public void setImportant(boolean important) {
		this.important = important;
	}
	 
}
