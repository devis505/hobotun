package hobotun.db.Image.tbl;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageTbl {

	private Long idImage = null;
	private byte[] image = null;

	public ImageTbl() {

	}

	public ImageTbl(ResultSet rs) throws SQLException {
		this.idImage = rs.getLong("idImage");
		this.image = rs.getBytes("image");
	}

	public Long getIdImage() {
		return idImage;
	}

	public void setIdImage(Long idImage) {
		this.idImage = idImage;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

}
