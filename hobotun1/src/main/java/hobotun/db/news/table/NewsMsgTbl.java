package hobotun.db.news.table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class NewsMsgTbl {

	private Long id_news_msg;
	private Long id_user;
	private Long id_news;
	private Date dt_news_msg;
	private String vl_msg;
	
	private String login;
	private String mail;
	private Date dtReg;
	private Long idImage;
	private Integer rating_user;
	
	public NewsMsgTbl() {

	}

	public NewsMsgTbl(ResultSet rs) throws SQLException {
		id_news_msg = rs.getLong("id_news_msg");
		id_user = rs.getLong("id_user");
		id_news = rs.getLong("id_news");
		dt_news_msg = rs.getDate("dt_news_msg");
		vl_msg = rs.getString("vl_msg");
		
		login = rs.getString("login");
		mail = rs.getString("mail");
		dtReg = rs.getDate("dtReg");
		idImage = rs.getLong("idImage");
		rating_user = rs.getInt("rating_user");
	}

	public Long getId_news_msg() {
		return id_news_msg;
	}

	public void setId_news_msg(Long id_news_msg) {
		this.id_news_msg = id_news_msg;
	}

	public Long getId_user() {
		return id_user;
	}

	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}

	public Long getId_news() {
		return id_news;
	}

	public void setId_news(Long id_news) {
		this.id_news = id_news;
	}

	public Date getDt_news_msg() {
		return dt_news_msg;
	}

	public void setDt_news_msg(Date dt_news_msg) {
		this.dt_news_msg = dt_news_msg;
	}

	public String getVl_msg() {
		return vl_msg;
	}

	public void setVl_msg(String vl_msg) {
		this.vl_msg = vl_msg;
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

	public Integer getRating_user() {
		return rating_user;
	}

	public void setRating_user(Integer rating_user) {
		this.rating_user = rating_user;
	}

}
