package hobotun.db.model.action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import hobotun.db.model.tbl.ModelTbl;

public class SelectAllModelOrderByDate extends MappingSqlQuery<ModelTbl> {

	private static final String SQL_SELECT_MODEL_BY_USER_ID = 
			  "SELECT m.* \n"
	        + "  FROM hb_model m\n"
			+ "  JOIN hb_user_model um \n" 
			+ "    ON um.idModel = m.idModel and um.idEntityType = 1\n"
			+ " WHERE m.is_moderation = :is_moderation \n"
			+ "   AND ((m.idCategory = :idCategory) or (:idCategory = 0)) \n"
			+ "   AND ((upper(m.nmModel) like (upper(:keyWord))) or (upper(m.tegs) like (upper(:keyWord))))"
			+ " ORDER by m.idModel DESC";

	public SelectAllModelOrderByDate(DataSource dataSource) {
		super(dataSource, SQL_SELECT_MODEL_BY_USER_ID);
		super.declareParameter(new SqlParameter("is_moderation", Types.INTEGER));
		super.declareParameter(new SqlParameter("idCategory", Types.INTEGER));
		super.declareParameter(new SqlParameter("keyWord", Types.VARCHAR));
	}

	@Override
	protected ModelTbl mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		ModelTbl model = new ModelTbl(rs);
		model.setIdModel(rs.getLong("idModel"));
		
		return model;
	}
}
