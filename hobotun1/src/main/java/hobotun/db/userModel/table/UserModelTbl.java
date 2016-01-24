package hobotun.db.userModel.table;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModelTbl {

	private Long idUser;
	private Long idModel;
	private Long idEntityType;
	
	public UserModelTbl() {
		
	}
	
	public UserModelTbl(ResultSet rs) throws SQLException {
		idUser = rs.getLong("idUser");
		idModel = rs.getLong("IdModel");
		idEntityType = rs.getLong("idEntityType");
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public Long getIdModel() {
		return idModel;
	}

	public void setIdModel(Long idModel) {
		this.idModel = idModel;
	}

	public Long getIdEntityType() {
		return idEntityType;
	}

	public void setIdEntityType(Long idEntityType) {
		this.idEntityType = idEntityType;
	}
}
