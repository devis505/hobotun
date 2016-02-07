package hobotun.forum;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import hobotun.core.Misc;
import hobotun.db.DBUtil;
import hobotun.db.forum.ForumDao;
import hobotun.db.forum.table.ForumTbl;
import hobotun.db.forum.table.ThemaTbl;

@ManagedBean(name = "thema")
@ViewScoped
public class Forum {

	private String idForum;
	private List<ThemaTbl> themas = new ArrayList<>();
	private ForumTbl forum;

	public Forum() {
		setIdForum(Misc.getRequestParam(FacesContext.getCurrentInstance(), "id"));

		ForumDao forumDao = DBUtil.getInstance().getBean("forumDao", ForumDao.class);
		themas = forumDao.getThemasByIdForum(new Long(idForum));
		
		forum = forumDao.getForumById(new Long(idForum));
	}

	public String getIdForum() {
		return idForum;
	}

	public void setIdForum(String idForum) {
		this.idForum = idForum;
	}

	public List<ThemaTbl> getThemas() {
		return themas;
	}

	public void setThemas(List<ThemaTbl> themas) {
		this.themas = themas;
	}

	public ForumTbl getForum() {
		return forum;
	}

	public void setForum(ForumTbl forum) {
		this.forum = forum;
	}

}
