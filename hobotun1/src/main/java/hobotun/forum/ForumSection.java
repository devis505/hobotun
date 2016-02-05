package hobotun.forum;

import java.util.ArrayList;
import java.util.List;

import hobotun.db.DBUtil;
import hobotun.db.forum.ForumDao;
import hobotun.db.forum.table.ForumSectionTbl;
import hobotun.db.forum.table.ForumTbl;

public class ForumSection {
	
	private ForumSectionTbl forumSection;
	private List<ForumTbl> forums = new ArrayList<>();
		
	public ForumSection (ForumSectionTbl forumSection) {
		this.forumSection = forumSection;
		
		ForumDao forumDao = DBUtil.getInstance().getBean("forumDao", ForumDao.class);
		setForums(forumDao.getAllForumBySelection(forumSection.getId_forum_section()));
	}

	public ForumSectionTbl getForumSection() {
		return forumSection;
	}

	public void setForumSection(ForumSectionTbl forumSections) {
		this.forumSection = forumSections;
	}

	public List<ForumTbl> getForums() {
		return forums;
	}

	public void setForums(List<ForumTbl> forums) {
		this.forums = forums;
	}
	
}
