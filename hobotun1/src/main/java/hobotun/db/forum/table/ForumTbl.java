package hobotun.db.forum.table;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ForumTbl implements Serializable {

	private static final long serialVersionUID = 4808851137997662454L;
	private Long id_forum;
	private String nm_forum;
	private String vl_discription;
	private Long id_forum_section;
	private Integer cnt_them;

	public ForumTbl(ResultSet rs) throws SQLException {
		id_forum = rs.getLong("id_forum");
		nm_forum = rs.getString("nm_forum");
		vl_discription = rs.getString("vl_discription");
		id_forum_section = rs.getLong("id_forum_section");
		cnt_them = rs.getInt("cnt_them");
	}

	public Long getId_forum() {
		return id_forum;
	}

	public void setId_forum(Long id_forum) {
		this.id_forum = id_forum;
	}

	public String getNm_forum() {
		return nm_forum;
	}

	public void setNm_forum(String nm_forum) {
		this.nm_forum = nm_forum;
	}

	public String getVl_discription() {
		return vl_discription;
	}

	public void setVl_discription(String vl_discription) {
		this.vl_discription = vl_discription;
	}

	public Long getId_forum_section() {
		return id_forum_section;
	}

	public void setId_forum_section(Long id_forum_section) {
		this.id_forum_section = id_forum_section;
	}

	public Integer getCnt_them() {
		return cnt_them;
	}

	public void setCnt_them(Integer cnt_them) {
		this.cnt_them = cnt_them;
	}

}
