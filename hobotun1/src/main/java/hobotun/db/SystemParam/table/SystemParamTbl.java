package hobotun.db.SystemParam.table;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SystemParamTbl {

	private Long idParam;
	private String nmParam;
	private String vlParam;

	public SystemParamTbl() {

	}

	public SystemParamTbl(ResultSet rs) throws SQLException {
		idParam = rs.getLong("idParam");
		nmParam = rs.getString("nmParam");
		vlParam = rs.getString("vlParam");
	}

	public String getNmParam() {
		return nmParam;
	}

	public void setNmParam(String nmParam) {
		this.nmParam = nmParam;
	}

	public Long getIdParam() {
		return idParam;
	}

	public void setIdParam(Long idParam) {
		this.idParam = idParam;
	}

	public String getVlParam() {
		return vlParam;
	}

	public void setVlParam(String vlParam) {
		this.vlParam = vlParam;
	}

}
