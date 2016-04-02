package hobotun.forum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import hobotun.db.DBUtil;
import hobotun.db.forum.ForumDao;
import hobotun.db.forum.table.ForumSectionTbl;

@ManagedBean(name = "section")
@ViewScoped
public class Section implements Serializable{

	private static final long serialVersionUID = 3860279650640987008L;
	private Integer section = 0;
	private List<SelectItem> sections;

	public void init() {

		sections = new ArrayList<SelectItem>();

		ForumDao forumDao = DBUtil.getInstance().getBean("forumDao", ForumDao.class);
		List<ForumSectionTbl> forumSectionTbl = forumDao.getAllForumSection();

		for (ForumSectionTbl forumSectionItem : forumSectionTbl) {
			sections.add(new SelectItem(forumSectionItem.getId_forum_section(), forumSectionItem.getNm_forum_section()));
		}
	}

	public List<SelectItem> getSections() {
		return sections;
	}

	public Integer getSection() {
		return section;
	}

	public void setSection(Integer section) {
		this.section = section;
	}

}
