package hobotun.db.model.action;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class InsertModelReturnId extends SqlUpdate {

	private static final String SQL_INSERT_FILE = "INSERT INTO hb_model(nmModel, description, idImg1, idImg2, idImg3, idImg4, idImg5, idFile, idCategory, idFormat, obj, fbx, idImg1min, idImg2min, idImg3min, idImg4min, idImg5min, tegs, price, count_poligon, texture, is_moderation, urlModel) "
			+ "VALUES (:nmModel, :description, :idImg1, :idImg2, :idImg3, :idImg4, :idImg5, :idFile, :idCategory, :idFormat, :obj, :fbx, :idImg1min, :idImg2min, :idImg3min, :idImg4min, :idImg5min, :tegs, :price, :count_poligon, :texture, :is_moderation, :urlModel)";

	public InsertModelReturnId(DataSource dataSource) {
		super(dataSource, SQL_INSERT_FILE);

		super.declareParameter(new SqlParameter("nmModel", Types.VARCHAR));
		super.declareParameter(new SqlParameter("description", Types.VARCHAR));

		super.declareParameter(new SqlParameter("idImg1", Types.INTEGER));
		super.declareParameter(new SqlParameter("idImg2", Types.INTEGER));
		super.declareParameter(new SqlParameter("idImg3", Types.INTEGER));
		super.declareParameter(new SqlParameter("idImg4", Types.INTEGER));
		super.declareParameter(new SqlParameter("idImg5", Types.INTEGER));

		super.declareParameter(new SqlParameter("idFile", Types.INTEGER));

		super.declareParameter(new SqlParameter("idCategory", Types.INTEGER));
		super.declareParameter(new SqlParameter("idFormat", Types.INTEGER));

		super.declareParameter(new SqlParameter("obj", Types.INTEGER));
		super.declareParameter(new SqlParameter("fbx", Types.INTEGER));

		super.declareParameter(new SqlParameter("idImg1min", Types.INTEGER));
		super.declareParameter(new SqlParameter("idImg2min", Types.INTEGER));
		super.declareParameter(new SqlParameter("idImg3min", Types.INTEGER));
		super.declareParameter(new SqlParameter("idImg4min", Types.INTEGER));
		super.declareParameter(new SqlParameter("idImg5min", Types.INTEGER));

		super.declareParameter(new SqlParameter("tegs", Types.VARCHAR));

		super.declareParameter(new SqlParameter("price", Types.DECIMAL));
		super.declareParameter(new SqlParameter("count_poligon", Types.INTEGER));
		super.declareParameter(new SqlParameter("texture", Types.INTEGER));
		super.declareParameter(new SqlParameter("is_moderation", Types.INTEGER));

		super.declareParameter(new SqlParameter("urlModel", Types.VARCHAR));

		super.setGeneratedKeysColumnNames(new String[] { "idModel" });
		super.setReturnGeneratedKeys(true);
	}

}
