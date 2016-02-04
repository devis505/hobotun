package hobotun.db.forum.table;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ForumSectionTbl {

	private Long id_forum_section;
	private String nm_forum_section;
	private String vl_icon;

	public ForumSectionTbl() {

	}

	public ForumSectionTbl(ResultSet rs) throws SQLException {
		this.id_forum_section = rs.getLong("id_forum_section");
		this.nm_forum_section = rs.getString("nm_forum_section");
		this.vl_icon = rs.getString("vl_icon");
	}

	public Long getId_forum_section() {
		return id_forum_section;
	}

	public void setId_forum_section(Long id_forum_section) {
		this.id_forum_section = id_forum_section;
	}

	public String getNm_forum_section() {
		return nm_forum_section;
	}

	public void setNm_forum_section(String nm_forum_section) {
		this.nm_forum_section = nm_forum_section;
	}

	public String getVl_icon() {
		return vl_icon;
	}

	public void setVl_icon(String vl_icon) {
		this.vl_icon = vl_icon;
	}

}
