package hobotun.db.user.table;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OutUserBalanceTbl {

	private Integer id;
	private Integer id_user;
	private BigDecimal outMoney;
	private Integer state;
	private String stateName;
	private Date dtStartOut;
	private Date dtFinishOut;

	public OutUserBalanceTbl(ResultSet rs) throws SQLException {

		setId(rs.getInt("id"));
		setId_user(rs.getInt("id_user"));
		setOutMoney(rs.getBigDecimal("outMoney"));
		setState(rs.getInt("state"));
		setStateName(rs.getString("stateName"));
		setDtStartOut(rs.getDate("dtStartOut"));
		setDtFinishOut(rs.getDate("dtFinishOut"));
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_user() {
		return id_user;
	}

	public void setId_user(Integer id_user) {
		this.id_user = id_user;
	}

	public BigDecimal getOutMoney() {
		return outMoney;
	}

	public void setOutMoney(BigDecimal outMoney) {
		this.outMoney = outMoney;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getDtStartOut() {
		return dtStartOut;
	}

	public void setDtStartOut(Date dtStartOut) {
		this.dtStartOut = dtStartOut;
	}

	public Date getDtFinishOut() {
		return dtFinishOut;
	}

	public void setDtFinishOut(Date dtFinishOut) {
		this.dtFinishOut = dtFinishOut;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

}
