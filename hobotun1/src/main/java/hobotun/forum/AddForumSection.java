package hobotun.forum;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import hobotun.core.Misc;
import hobotun.db.DBUtil;
import hobotun.db.forum.ForumDao;
import hobotun.db.forum.table.ForumSectionTbl;

@ManagedBean (name = "addSection")
@ViewScoped
public class AddForumSection implements Serializable{

	private static final long serialVersionUID = -361537952026677129L;
	private ForumDao forumDao;
	private ForumSectionTbl forumSection;	
	
	private List<ForumSectionTbl> forumsSections; 
	
	public AddForumSection() {
		forumSection = new ForumSectionTbl();
		forumDao = DBUtil.getInstance().getBean("forumDao", ForumDao.class);
		setForumsSections(forumDao.getAllForumSection());
	}
	
	public void onSave() {
		forumDao.InsertForumSection(forumSection);
		Misc.redirect("/pages/forum/addGroup.jsf");
	}

	public ForumSectionTbl getForumSection() {
		return forumSection;
	}

	public void setForumSection(ForumSectionTbl forumSection) {
		this.forumSection = forumSection;
	}

	public List<ForumSectionTbl> getForumsSections() {
		return forumsSections;
	}

	public void setForumsSections(List<ForumSectionTbl> forumsSections) {
		this.forumsSections = forumsSections;
	}

	
	
}
