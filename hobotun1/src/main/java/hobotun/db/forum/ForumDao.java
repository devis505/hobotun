package hobotun.db.forum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import hobotun.db.forum.action.SelectAllForumSection;
import hobotun.db.forum.action.SelectForumById;
import hobotun.db.forum.action.SelectForumByIdSection;
import hobotun.db.forum.action.SelectThemaByIdForum;
import hobotun.db.forum.table.ForumSectionTbl;
import hobotun.db.forum.table.ForumTbl;
import hobotun.db.forum.table.ThemaTbl;

public class ForumDao implements IForumDao {

	private DataSource dataSource;

	private SelectAllForumSection selectAllForumSection;
	private SelectForumByIdSection selectForumByIdSection;
	private SelectThemaByIdForum selectThemaByIdForum;
	private SelectForumById selectForumById;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;

		selectAllForumSection = new SelectAllForumSection(dataSource);
		selectForumByIdSection = new SelectForumByIdSection(dataSource);
		selectThemaByIdForum = new SelectThemaByIdForum(dataSource);
		selectForumById = new SelectForumById(dataSource);
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	@Override
	public List<ForumSectionTbl> getAllForumSection() {
		return selectAllForumSection.execute();
	}

	public List<ForumTbl> getAllForumBySelection(Long id) {

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id_forum_section", id);

		return selectForumByIdSection.executeByNamedParam(paramMap);
	}

	public List<ThemaTbl> getThemasByIdForum(Long id) {

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id_forum", id);

		return selectThemaByIdForum.executeByNamedParam(paramMap);
	}
	
	public ForumTbl getForumById(Long id) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id_forum", id);
		
		return selectForumById.executeByNamedParam(paramMap).get(0);
	}
}