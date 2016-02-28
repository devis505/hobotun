package hobotun.forum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import hobotun.core.Misc;
import hobotun.core.UserSession;
import hobotun.db.DBUtil;
import hobotun.db.forum.ForumDao;
import hobotun.db.forum.table.ForumMsgTbl;
import hobotun.db.forum.table.ForumTbl;
import hobotun.db.forum.table.ThemaTbl;

@ManagedBean(name = "topic")
@ViewScoped
public class Topic implements Serializable {

	private static final long serialVersionUID = 5654764192911644459L;
	private String idThema;
	private ThemaTbl themaTbl;
	private ForumTbl forumTbl;

	private ForumDao forumDao;
	private boolean isVisibleBlock = false;
	private boolean isVisibleAdd = false;
	
	private String text;

	private List<ForumMsgTbl> forumMsgTbl = new ArrayList<>();

	public Topic() {
		idThema = Misc.getRequestParam(FacesContext.getCurrentInstance(), "id");

		forumDao = DBUtil.getInstance().getBean("forumDao", ForumDao.class);

		themaTbl = forumDao.getThemaById(new Long(idThema));
		forumTbl = forumDao.getForumById(themaTbl.getId_forum());
		forumMsgTbl = forumDao.getForumMsgByIdThema(new Long(idThema));
		
		isVisibleBlock = true;

		if (themaTbl.getIsBlock().equals(0)) {
			if (UserSession.getInstance().getUser() != null) {
				if (!UserSession.getInstance().getUser().getUserTbl().getId_user().equals(themaTbl.getId_user())) {
					isVisibleBlock = false;
				}
			} else {
				isVisibleBlock = false;
			}
		} else {
			isVisibleBlock = false;
		}
		
		if (themaTbl.getIsBlock().equals(0)) {
			if (UserSession.getInstance().getUser() != null) {
				isVisibleAdd = true;
			}
		}

		forumDao.UpdateCountView(themaTbl.getId_thema());
	}
	
	public void onSaveText() {
		
		ForumMsgTbl forumMsgTbl = new ForumMsgTbl();
		
		forumMsgTbl.setDt_msg(new Date());
		forumMsgTbl.setId_thema(themaTbl.getId_thema());
		forumMsgTbl.setId_user(UserSession.getInstance().getUser().getUserTbl().getId_user());
		forumMsgTbl.setVl_msg(text);
		
		forumDao.InsertForumMsg(forumMsgTbl);
		Misc.redirect("/pages/forum/topic.jsf?id=" + idThema);		
	}

	public void onClose() {
		forumDao.UpdateLock(themaTbl.getId_thema());

		Misc.redirect("/pages/forum/forum.jsf?id=" + themaTbl.getId_forum());
	}

	public boolean isVisibleBlock() {
		return isVisibleBlock;
	}

	public void setVisibleBlock(boolean isVisibleBlock) {
		this.isVisibleBlock = isVisibleBlock;
	}

	public List<ForumMsgTbl> getForumMsgTbl() {
		return forumMsgTbl;
	}

	public void setForumMsgTbl(List<ForumMsgTbl> forumMsgTbl) {
		this.forumMsgTbl = forumMsgTbl;
	}

	public ForumTbl getForumTbl() {
		return forumTbl;
	}

	public void setForumTbl(ForumTbl forumTbl) {
		this.forumTbl = forumTbl;
	}

	public String getIdThema() {
		return idThema;
	}

	public void setIdThema(String idThema) {
		this.idThema = idThema;
	}

	public ThemaTbl getThemaTbl() {
		return themaTbl;
	}

	public void setThemaTbl(ThemaTbl themaTbl) {
		this.themaTbl = themaTbl;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isVisibleAdd() {
		return isVisibleAdd;
	}

	public void setVisibleAdd(boolean isVisibleAdd) {
		this.isVisibleAdd = isVisibleAdd;
	}

}
