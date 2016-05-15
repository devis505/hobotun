package hobotun.db.user.table;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OutUserBalanceTbl {

	private Integer id_balance_hist;
	private Integer id_user;
	private BigDecimal sm;
	private Date dt_create;
	private Date dt_change;
	private String desc;
	private Integer kd_state;
	private Integer kd_type;

	public OutUserBalanceTbl(ResultSet rs) throws SQLException {
		setId_balance_hist(rs.getInt("id_balance_hist"));
		setId_user(rs.getInt("id_user"));
		setSm(rs.getBigDecimal("sm"));
		
		dt_create = rs.getDate("dt_create");
		dt_change = rs.getDate("dt_change");
		desc = rs.getString("desc");
		kd_state = rs.getInt("kd_state");
		kd_type = rs.getInt("kd_type");
	}

	public Date getDt_create() {
		return dt_create;
	}

	public void setDt_create(Date dt_create) {
		this.dt_create = dt_create;
	}

	public Date getDt_change() {
		return dt_change;
	}

	public void setDt_change(Date dt_change) {
		this.dt_change = dt_change;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getKd_state() {
		return kd_state;
	}

	public void setKd_state(Integer kd_state) {
		this.kd_state = kd_state;
	}

	public Integer getKd_type() {
		return kd_type;
	}

	public void setKd_type(Integer kd_type) {
		this.kd_type = kd_type;
	}
	
	public Integer getId_user() {
		return id_user;
	}

	public void setId_user(Integer id_user) {
		this.id_user = id_user;
	}

	public Integer getId_balance_hist() {
		return id_balance_hist;
	}

	public void setId_balance_hist(Integer id_balance_hist) {
		this.id_balance_hist = id_balance_hist;
	}

	public BigDecimal getSm() {
		return sm;
	}

	public void setSm(BigDecimal sm) {
		this.sm = sm;
	}

}
