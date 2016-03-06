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
	}

	public Map<String, Object> getAllParam() {

		Map<String, Object> param = new HashMap<String, Object>();

		param.put("id_user", id_user);
		// param.put("login", login);
		param.put("mail", mail);
		param.put("password", password);
		// param.put("dtReg", dtReg);
		param.put("fio", fio);

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

}
