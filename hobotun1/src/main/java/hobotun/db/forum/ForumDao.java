package hobotun.db.forum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import hobotun.db.forum.action.SelectAllForumSection;
import hobotun.db.forum.action.SelectForumByIdSection;
import hobotun.db.forum.table.ForumSectionTbl;
import hobotun.db.forum.table.ForumTbl;

public class ForumDao implements IForumDao {

	private DataSource dataSource;

	private SelectAllForumSection selectAllForumSection;
	private SelectForumByIdSection selectForumByIdSection;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;

		selectAllForumSection = new SelectAllForumSection(dataSource);
		selectForumByIdSection = new SelectForumByIdSection(dataSource);
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
}
