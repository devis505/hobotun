package hobotun.db.forum.table;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import hobotun.core.Misc;
import hobotun.core.ParamsForQuery;
import hobotun.db.DBUtil;
import hobotun.db.forum.ForumDao;

public class ForumMsgTbl implements Serializable {

	private static final long serialVersionUID = -7279617639359565454L;
	private Long id_forum_msg;
	private String vl_msg;
	private Long id_user;
	private Date dt_msg;
	private Long id_thema;
	
	private String login;
	private String mail;
	private Date dtReg;
	private Long idImage;
	
	private Integer rating_user = 0; 
	
	public ForumMsgTbl() {
		
	}
	
	public void onDelete() {
		ParamsForQuery inParam = new ParamsForQuery();
		inParam.setParam("id_forum_msg", id_forum_msg);
		
		ForumDao forumDao = DBUtil.getInstance().getBean("forumDao", ForumDao.class);
		forumDao.deleteForumMsg(inParam.getAllParam());		
		
		Misc.redirect("/pages/forum/topic.jsf?id=" + id_thema);
	}
	
	public ForumMsgTbl (ResultSet rs) throws SQLException {
		id_forum_msg = rs.getLong("id_forum_msg");
		vl_msg = rs.getString("vl_msg");
		id_user = rs.getLong("id_user");
		dt_msg = rs.getDate("dt_msg");
		id_thema = rs.getLong("id_thema");
		
		login = rs.getString("login");
		mail = rs.getString("mail");
		dtReg = rs.getDate("dtReg");
		idImage = rs.getLong("idImage");
		
		rating_user = rs.getInt("rating_user");
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Date getDtReg() {
		return dtReg;
	}

	public void setDtReg(Date dtReg) {
		this.dtReg = dtReg;
	}

	public Long getIdImage() {
		return idImage;
	}

	public void setIdImage(Long idImage) {
		this.idImage = idImage;
	}
	
	public Long getId_forum_msg() {
		return id_forum_msg;
	}
	public void setId_forum_msg(Long id_forum_msg) {
		this.id_forum_msg = id_forum_msg;
	}
	public String getVl_msg() {
		return vl_msg;
	}
	public void setVl_msg(String vl_msg) {
		this.vl_msg = vl_msg;
	}
	public Long getId_user() {
		return id_user;
	}
	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}
	public Date getDt_msg() {
		return dt_msg;
	}
	public void setDt_msg(Date dt_msg) {
		this.dt_msg = dt_msg;
	}
	public Long getId_thema() {
		return id_thema;
	}
	public void setId_thema(Long id_thema) {
		this.id_thema = id_thema;
	}

	public Integer getRating_user() {
		return rating_user;
	}

	public void setRating_user(Integer rating_user) {
		this.rating_user = rating_user;
	}
	
}
