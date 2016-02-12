package hobotun.forum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import hobotun.core.Misc;
import hobotun.db.DBUtil;
import hobotun.db.forum.ForumDao;
import hobotun.db.forum.table.ForumMsgTbl;
import hobotun.db.forum.table.ForumTbl;
import hobotun.db.forum.table.ThemaTbl;

@ManagedBean(name="topic")
@ViewScoped
public class Topic implements Serializable {
	
	private static final long serialVersionUID = 5654764192911644459L;
	private String idThema;
	private ThemaTbl themaTbl;
	private ForumTbl forumTbl;
	
	private List<ForumMsgTbl> forumMsgTbl = new ArrayList<>();
	
	public Topic () {
		idThema = Misc.getRequestParam(FacesContext.getCurrentInstance(), "id");
		
		ForumDao forumDao = DBUtil.getInstance().getBean("forumDao", ForumDao.class);
		
		themaTbl = forumDao.getThemaById(new Long(idThema));
		forumTbl = forumDao.getForumById(themaTbl.getId_forum());
		forumMsgTbl = forumDao.getForumMsgByIdThema(new Long(idThema));
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

}
