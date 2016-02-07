package hobotun.db.forum.table;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ThemaTbl {

	private Long id_thema;
	private Long id_forum;
	private String nm_thema;
	private Long id_user;
	private String nm_user;
	private Integer nn_count_view;
	private Integer isUp;
	
	private String classStyle;

	public ThemaTbl(ResultSet rs) throws SQLException {
		id_thema = rs.getLong("id_thema");
		id_forum = rs.getLong("id_forum");
		nm_thema = rs.getString("nm_thema");
		id_user = rs.getLong("id_user");
		nm_user = rs.getString("nm_user");
		nn_count_view = rs.getInt("nn_count_view");
		isUp = rs.getInt("isUp");
	}
	
	

	public ThemaTbl() {

	}
	
	public Long getId_thema() {
		return id_thema;
	}

	public String getNm_user() {
		return nm_user;
	}

	public void setNm_user(String nm_user) {
		this.nm_user = nm_user;
	}

	public void setId_thema(Long id_thema) {
		this.id_thema = id_thema;
	}

	public Long getId_forum() {
		return id_forum;
	}

	public void setId_forum(Long id_forum) {
		this.id_forum = id_forum;
	}

	public String getNm_thema() {
		return nm_thema;
	}

	public void setNm_thema(String nm_thema) {
		this.nm_thema = nm_thema;
	}

	public Long getId_user() {
		return id_user;
	}

	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}

	public Integer getNn_count_view() {
		return nn_count_view;
	}

	public void setNn_count_view(Integer nn_count_view) {
		this.nn_count_view = nn_count_view;
	}

	public Integer getIsUp() {
		return isUp;
	}

	public void setIsUp(Integer isUp) {
		this.isUp = isUp;
	}

	public String getClassStyle() {
		if (isUp.equals(1)) {
			classStyle = "fa fa-exclamation-triangle";
		} else {
			classStyle = "padding_left18";
		}
		
		return classStyle;
	}

	public void setClassStyle(String classStyle) {
		this.classStyle = classStyle;
	}

}