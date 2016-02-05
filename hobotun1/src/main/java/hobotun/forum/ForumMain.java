package hobotun.forum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import hobotun.db.DBUtil;
import hobotun.db.forum.ForumDao;
import hobotun.db.forum.table.ForumSectionTbl;

@ManagedBean(name = "forumMain")
@ViewScoped
public class ForumMain implements Serializable {

	private static final long serialVersionUID = 4207631485152406643L;
	private List<ForumSection> forumSections = new ArrayList<>();

	public ForumMain() {
		ForumDao forumDao = DBUtil.getInstance().getBean("forumDao", ForumDao.class);
		
		List<ForumSectionTbl> section = forumDao.getAllForumSection();
		
		for (ForumSectionTbl forumSectionTbl : section) {
			forumSections.add(new ForumSection(forumSectionTbl));
		}
		
	}

	public List<ForumSection> getForumSections() {
		return forumSections;
	}

	public void setForumSections(List<ForumSection> forumSections) {
		this.forumSections = forumSections;
	}

}
