package hobotun.db.forum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import hobotun.db.forum.action.InsertForum;
import hobotun.db.forum.action.InsertForumMsg;
import hobotun.db.forum.action.SelectAllForumSection;
import hobotun.db.forum.action.SelectForumById;
import hobotun.db.forum.action.SelectForumByIdSection;
import hobotun.db.forum.action.SelectForumMsgByIdThema;
import hobotun.db.forum.action.SelectThemaById;
import hobotun.db.forum.action.SelectThemaByIdForum;
import hobotun.db.forum.action.UpdateCountView;
import hobotun.db.forum.action.UpdateLock;
import hobotun.db.forum.table.ForumMsgTbl;
import hobotun.db.forum.table.ForumSectionTbl;
import hobotun.db.forum.table.ForumTbl;
import hobotun.db.forum.table.ThemaTbl;

public class ForumDao implements IForumDao {

	private DataSource dataSource;

	private SelectAllForumSection selectAllForumSection;
	private SelectForumByIdSection selectForumByIdSection;
	private SelectThemaByIdForum selectThemaByIdForum;
	private SelectForumById selectForumById;
	private InsertForum insertForum;
	private SelectThemaById selectThemaById;
	private SelectForumMsgByIdThema selectForumMsgByIdThema;
	private InsertForumMsg insertForumMsg;
	private UpdateCountView updateCountView;
	private UpdateLock updateLock;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;

		selectAllForumSection = new SelectAllForumSection(dataSource);
		selectForumByIdSection = new SelectForumByIdSection(dataSource);
		selectThemaByIdForum = new SelectThemaByIdForum(dataSource);
		selectForumById = new SelectForumById(dataSource);
		insertForum = new InsertForum(dataSource);
		selectThemaById = new SelectThemaById(dataSource);
		selectForumMsgByIdThema = new SelectForumMsgByIdThema(dataSource);
		insertForumMsg = new InsertForumMsg(dataSource);
		updateCountView = new UpdateCountView(dataSource);
		updateLock = new UpdateLock(dataSource);
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

	public ThemaTbl getThemaById(Long id) {

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id_thema", id);

		return selectThemaById.executeByNamedParam(paramMap).get(0);
	}

	public ForumTbl getForumById(Long id) {

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id_forum", id);

		return selectForumById.executeByNamedParam(paramMap).get(0);
	}

	public List<ForumMsgTbl> getForumMsgByIdThema(Long id) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id_thema", id);

		return selectForumMsgByIdThema.executeByNamedParam(paramMap);
	}
	
	public void UpdateCountView(Long id) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id_thema", id);
		
		updateCountView.updateByNamedParam(paramMap);
	}
	
	public void UpdateLock(Long id) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id_thema", id);
		
		updateLock.updateByNamedParam(paramMap);
	}

	public void InsertThema(ThemaTbl themaTbl) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id_forum", themaTbl.getId_forum());
		paramMap.put("nm_thema", themaTbl.getNm_thema());
		paramMap.put("id_user", themaTbl.getId_user());

		KeyHolder keyHolder = new GeneratedKeyHolder();

		insertForum.updateByNamedParam(paramMap, keyHolder);

		themaTbl.setId_thema(keyHolder.getKey().longValue());
	}

	public void InsertForumMsg(ForumMsgTbl forumMsg) {
		Map<String, Object> paramMap = new HashMap<String, Object>();

		paramMap.put("vl_msg", forumMsg.getVl_msg());
		paramMap.put("id_user", forumMsg.getId_user());
		paramMap.put("dt_msg", forumMsg.getDt_msg());
		paramMap.put("id_thema", forumMsg.getId_thema());

		KeyHolder keyHolder = new GeneratedKeyHolder();

		insertForumMsg.updateByNamedParam(paramMap, keyHolder);

		forumMsg.setId_forum_msg(keyHolder.getKey().longValue());
	}
}
