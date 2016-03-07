package hobotun.db.rating.action;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class InsertModeleRating extends SqlUpdate {

	private static final String SQL_INSERT_MODELE_RATING = "INSERT INTO hb_rating_modele (id_user, id_model, vl_rating) VALUE (:id_user, :id_model, :vl_rating);";
	
	public InsertModeleRating(DataSource dataSource) {
		super(dataSource, SQL_INSERT_MODELE_RATING);

		super.declareParameter(new SqlParameter("id_user", Types.INTEGER));
		super.declareParameter(new SqlParameter("id_model", Types.INTEGER));
		super.declareParameter(new SqlParameter("vl_rating", Types.INTEGER));

		super.setGeneratedKeysColumnNames(new String[] { "id_rating_modele" });
		super.setReturnGeneratedKeys(true);
	}

}
