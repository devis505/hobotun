package hobotun.db.model.action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import hobotun.db.model.tbl.ModelTbl;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

public class SelectModelByIdUser extends MappingSqlQuery<ModelTbl> {

	private static final String SQL_SELECT_MODEL_BY_USER_ID = "SELECT m.* \n" + " FROM hb_model m\n"
			+ " JOIN hb_user_model um \n" + " ON um.idModel = m.idModel and um.idEntityType = 1\n"
			+ " WHERE um.idUser = :idUser \n" + "   AND m.is_moderation = :is_moderation ";

	public SelectModelByIdUser(DataSource dataSource) {
		super(dataSource, SQL_SELECT_MODEL_BY_USER_ID);
		super.declareParameter(new SqlParameter("idUser", Types.INTEGER));
		super.declareParameter(new SqlParameter("is_moderation", Types.INTEGER));
	}

	@Override
	protected ModelTbl mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		ModelTbl mode = new ModelTbl(rs);
		mode.setIdModel(rs.getLong("idModel"));
		
		return mode;
	}

}
