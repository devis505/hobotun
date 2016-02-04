package hobotun.forum;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import hobotun.db.DBUtil;
import hobotun.db.forum.ForumDao;
import hobotun.db.forum.table.ForumSectionTbl;

@ManagedBean(name = "forumSection")
@ViewScoped
public class ForumSection implements Serializable {

	private static final long serialVersionUID = 4207631485152406643L;
	private List<ForumSectionTbl> forumSections;

	private ForumSection() {

		ForumDao forumDao = DBUtil.getInstance().getBean("forumDao", ForumDao.class);
		forumSections = forumDao.getAllForumSection();

	}

	public List<ForumSectionTbl> getForumSections() {
		return forumSections;
	}

	public void setForumSections(List<ForumSectionTbl> forumSections) {
		this.forumSections = forumSections;
	}

}
