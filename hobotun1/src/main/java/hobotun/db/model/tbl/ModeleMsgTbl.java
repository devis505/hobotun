package hobotun.db.model.tbl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ModeleMsgTbl {

	private Long id_modele_msg;
	private Long id_user;
	private Long id_modele;
	private Date dt_msg;
	private String vl_msg;
	
	private String login;
	private String mail;
	private Date dtReg;
	private Long idImage;
	private Integer rating_user;
	
	public ModeleMsgTbl() {

	}

	public ModeleMsgTbl(ResultSet rs) throws SQLException {
		id_modele_msg = rs.getLong("id_modele_msg");
		id_user = rs.getLong("id_user");
		id_modele = rs.getLong("id_modele");
		dt_msg = rs.getDate("dt_msg");
		vl_msg = rs.getString("vl_msg");
		
		login = rs.getString("login");
		mail = rs.getString("mail");
		dtReg = rs.getDate("dtReg");
		idImage = rs.getLong("idImage");
		rating_user = rs.getInt("rating_user");
	}

	public Long getId_modele_msg() {
		return id_modele_msg;
	}

	public void setId_modele_msg(Long id_modele_msg) {
		this.id_modele_msg = id_modele_msg;
	}

	public Long getId_user() {
		return id_user;
	}

	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}

	public Long getId_modele() {
		return id_modele;
	}

	public void setId_modele(Long id_modele) {
		this.id_modele = id_modele;
	}

	public Date getDt_msg() {
		return dt_msg;
	}

	public void setDt_msg(Date dt_msg) {
		this.dt_msg = dt_msg;
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
