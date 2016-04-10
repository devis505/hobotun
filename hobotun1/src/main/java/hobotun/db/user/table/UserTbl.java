package hobotun.db.user.table;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserTbl {

	private Long id_user;
	private String login;
	private String mail;
	private String password;
	private Date dtReg;
	private String fio;
	private BigDecimal balance;
	private Long idImage;
	private Integer rating_user = 0;
	private String desc;

	private Integer is_block;
	private Integer is_block_forum;

	public UserTbl(ResultSet rs) throws SQLException {
		setId_user(rs.getLong("id_user"));
		setLogin(rs.getString("login"));
		setPassword(rs.getString("password"));
		setMail(rs.getString("mail"));
		setId_user(rs.getLong("id_user"));
		setDtReg(rs.getDate("dtReg"));
		setFio(rs.getString("FIO"));
		setBalance(rs.getBigDecimal("balance"));
		setIdImage(rs.getLong("idImage"));
		setRating_user(rs.getInt("rating_user"));
		setDesc(rs.getString("desc"));

		try {
			is_block = rs.getInt("is_block");
			is_block_forum = rs.getInt("is_block_forum");
		} catch (Exception e) {
			is_block = 0;
			is_block_forum = 0;
		}
	}

	public Map<String, Object> getAllParam() {

		Map<String, Object> param = new HashMap<String, Object>();

		param.put("id_user", id_user);
		// param.put("login", login);
		param.put("mail", mail);
		param.put("password", password);
		// param.put("dtReg", dtReg);
		param.put("fio", fio);
		param.put("desc", desc);
		param.put("idImage", idImage);

		return param;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId_user() {
		return id_user;
	}

	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}

	public Date getDtReg() {
		return dtReg;
	}

	public void setDtReg(Date dtReg) {
		this.dtReg = dtReg;
	}

	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getIs_block() {
		return is_block;
	}

	public void setIs_block(Integer is_block) {
		this.is_block = is_block;
	}

	public Integer getIs_block_forum() {
		return is_block_forum;
	}

	public void setIs_block_forum(Integer is_block_forum) {
		this.is_block_forum = is_block_forum;
	}

}
