package hobotun.db.file.tbl;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FileTbl {

	private Long idFile;
	private byte[] file;

	public FileTbl() {

	}

	public FileTbl(ResultSet rs) throws SQLException {
		this.idFile = rs.getLong("idFile");
		this.file = rs.getBytes("file");
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

}
