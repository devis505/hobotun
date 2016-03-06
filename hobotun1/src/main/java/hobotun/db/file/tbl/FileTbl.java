package hobotun.db.file.tbl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FileTbl {

	private Long idFile;
	private byte[] file = null;
	private BigDecimal mb;
	private String nm_file;

	public FileTbl() {

	}

	public FileTbl(ResultSet rs) throws SQLException {
		this.idFile = rs.getLong("idFile");
		this.file = rs.getBytes("file");
		this.mb = rs.getBigDecimal("mb");
		this.nm_file = rs.getString("nm_file");
	}

	public Long getIdFile() {
		return idFile;
	}

	public void setIdFile(Long idFile) {
		this.idFile = idFile;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public BigDecimal getMb() {
		return mb;
	}

	public void setMb(BigDecimal mb) {
		this.mb = mb;
	}

	public String getNm_file() {
		return nm_file;
	}

	public void setNm_file(String nm_file) {
		this.nm_file = nm_file;
	}

}
