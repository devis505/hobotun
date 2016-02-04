package hobotun.db.forum;

import java.io.Serializable;
import java.util.List;

import javax.sql.DataSource;

import hobotun.db.forum.action.SelectAllForumSection;
import hobotun.db.forum.table.ForumSectionTbl;

public class ForumDao implements Serializable{

	private static final long serialVersionUID = 2296886326502715893L;
	private DataSource dataSource;
	
	private SelectAllForumSection selectAllForumSection;
	
	public ForumDao(DataSource dataSource) {
		this.dataSource = dataSource;
		this.selectAllForumSection = new SelectAllForumSection(dataSource);
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}
	
	public List<ForumSectionTbl> getAllForumSection() {
		return selectAllForumSection.execute();
	}

}
