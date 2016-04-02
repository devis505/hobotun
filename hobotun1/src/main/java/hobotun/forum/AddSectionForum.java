package hobotun.forum;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import hobotun.core.Misc;
import hobotun.db.DBUtil;
import hobotun.db.forum.ForumDao;
import hobotun.db.forum.table.ForumTbl;

@ManagedBean (name = "addSectionForum")
@ViewScoped
public class AddSectionForum implements Serializable{

	private static final long serialVersionUID = 5640568831137952210L;
	private ForumDao forumDao;
	private ForumTbl forum;
	
	private Section sections;
	private Integer section;
	
	private List<ForumTbl> forums; 
	
	public AddSectionForum() {
		forum = new ForumTbl();
		
		sections = new Section();
		sections.init();
		
		forumDao = DBUtil.getInstance().getBean("forumDao", ForumDao.class);
		setForums(forumDao.getAllForumBySelection(new Long(-1)));

	}
	
	public void onSave() {
		forum.setId_forum_section(new Long(section));
		forumDao.insertForumForSection(forum);
		Misc.redirect("/pages/forum/addGroupForum.jsf");
	}
	
	public ForumTbl getForum() {
		return forum;
	}

	public void setForum(ForumTbl forum) {
		this.forum = forum;
	}

	public List<ForumTbl> getForums() {
		return forums;
	}

	public void setForums(List<ForumTbl> forums) {
		this.forums = forums;
	}

	public Integer getSection() {
		return section;
	}

	public void setSection(Integer section) {
		this.section = section;
	}
	
	public Section getSections() {
		return sections;
	}

	public void setSections(Section sections) {
		this.sections = sections;
	}

}
