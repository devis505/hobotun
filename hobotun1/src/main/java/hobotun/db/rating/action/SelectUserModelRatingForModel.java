package hobotun.db.rating.action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import hobotun.db.rating.table.RatingModeleTbl;

public class SelectUserModelRatingForModel extends MappingSqlQuery<RatingModeleTbl> {

	private static final String SQL_SELECT_USER_MODELE_RATING_FOR_MODELE = 
			  "SELECT rm.* \n"
	        + "  FROM hb_rating_modele rm \n"
			+ " WHERE rm.id_user = :id_user \n"
			+ "   AND rm.id_model = :id_modele \n";

	public SelectUserModelRatingForModel(DataSource dataSource) {
		super(dataSource, SQL_SELECT_USER_MODELE_RATING_FOR_MODELE);
		super.declareParameter(new SqlParameter("id_user", Types.INTEGER));
		super.declareParameter(new SqlParameter("id_modele", Types.INTEGER));
	}

	@Override
	protected RatingModeleTbl mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		RatingModeleTbl model = new RatingModeleTbl(rs);
		model.setId_rating_modele(rs.getLong("id_rating_modele"));
		
		return model;
	}
}
