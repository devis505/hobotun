package hobotun.db.forum;

import java.util.List;

import hobotun.db.forum.table.ForumSectionTbl;

public interface IForumDao {
	public List<ForumSectionTbl> getAllForumSection();
}
