package hobotun.db.user.table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ForgetPassTbl {

	private Long id_forget_pass;
	private Long id_user;
	private String vl_hash;
	private Date dt_create;
	
	public ForgetPassTbl(ResultSet rs) throws SQLException {
		id_forget_pass = rs.getLong("id_forget_pass");
		id_user = rs.getLong("id_user");
		vl_hash = rs.getString("vl_hash");
		dt_create = rs.getDate("dt_create");
	}
	
	public ForgetPassTbl() {
		
	}
	
	public Long getId_forget_pass() {
		return id_forget_pass;
	}
	public void setId_forget_pass(Long id_forget_pass) {
		this.id_forget_pass = id_forget_pass;
	}
	public Long getId_user() {
		return id_user;
	}
	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}
	public String getVl_hash() {
		return vl_hash;
	}
	public void setVl_hash(String vl_hash) {
		this.vl_hash = vl_hash;
	}
	public Date getDt_create() {
		return dt_create;
	}
	public void setDt_create(Date dt_create) {
		this.dt_create = dt_create;
	}
	
}
