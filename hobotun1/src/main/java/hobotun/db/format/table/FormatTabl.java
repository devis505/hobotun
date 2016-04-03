package hobotun.db.format.table;

import java.sql.ResultSet;
import java.sql.SQLException;

import hobotun.core.Misc;
import hobotun.core.ParamsForQuery;
import hobotun.db.DBUtil;
import hobotun.db.format.FormatDao;

public class FormatTabl {

	private Integer idFormat;
	private String nmFormat;

	public FormatTabl(ResultSet rs) throws SQLException {
		idFormat = rs.getInt("idFormat");
		nmFormat = rs.getString("nmFormat");
	}

	public Integer getIdFormat() {
		return idFormat;
	}

	public void setIdFormat(Integer idFormat) {
		this.idFormat = idFormat;
	}

	public String getNmFormat() {
		return nmFormat;
	}

	public void setNmFormat(String nmFormat) {
		this.nmFormat = nmFormat;
	}

	public void onDelete() {
		ParamsForQuery paramMap = new ParamsForQuery();
		paramMap.setParam("idFormat", idFormat);
		
		FormatDao formatDao = DBUtil.getInstance().getBean("formatDao", FormatDao.class);
		formatDao.deleteFormat(paramMap.getAllParam());
		
		Misc.redirect("/pages/admin/directoryes.jsf");
	}

}
