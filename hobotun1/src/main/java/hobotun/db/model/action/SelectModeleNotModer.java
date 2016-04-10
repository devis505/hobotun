package hobotun.db.model.action;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.object.MappingSqlQuery;

import hobotun.db.model.tbl.ModelTbl;

public class SelectModeleNotModer extends MappingSqlQuery<ModelTbl> {

	private static final String SQL_SELECT_MODEL_BY_USER_ID = 
			  "SELECT m.*, "
			+ "       IFNULL((select sum(rm.vl_rating) from hb_rating_modele rm where rm.id_model = m.idModel), 0) rating, \n"
			+ "       (select count(*) from hb_user_model um where um.IdModel = m.idModel and um.idEntityType = 2) download, \n"
			+ "       (select count(*) from hb_modele_msg mm where mm.id_modele = m.idModel) msg, \n"
			+ "       IF(m.price > 0,     																				"
			+ "          (IF(m.tegs is not null, (select p.vlParam from hb_params p where p.idParam = 17), 0 ) + " 
			+ "	          IF(m.description is not null, (select p.vlParam from hb_params p where p.idParam = 18), 0 ) + " 
			+ "	          IF(m.idFormat > 0, (select p.vlParam from hb_params p where p.idParam = 19), 0 ) + " 
			+ "	          IF(m.idCategory > 0, (select p.vlParam from hb_params p where p.idParam = 20), 0 ) + " 
			+ "	          IF(m.fbx = 1 or m.obj = 1, (select p.vlParam from hb_params p where p.idParam = 21), 0 ) + " 
			+ "	          IF(m.texture > 0, (select p.vlParam from hb_params p where p.idParam = 22), 0 ) + " 
			+ "	          IF(m.count_poligon > 0, (select p.vlParam from hb_params p where p.idParam = 23), 0 ) + " 
			+ "	          IF(m.idImg4 is not null, (select p.vlParam from hb_params p where p.idParam = 24), 0 ) + " 
			+ "	          IF(m.idImg5 is not null, (select p.vlParam from hb_params p where p.idParam = 24), 0 )), 0) price_calc \n"
	        + "  FROM hb_model m\n"
			+ "  JOIN hb_user_model um \n" 
	        + "    ON um.idModel = m.idModel and um.idEntityType = 1\n"
			+ " WHERE m.is_moderation = 0 ";

	public SelectModeleNotModer(DataSource dataSource) {
		super(dataSource, SQL_SELECT_MODEL_BY_USER_ID);
	}

	@Override
	protected ModelTbl mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		ModelTbl mode = new ModelTbl(rs);
		mode.setIdModel(rs.getLong("idModel"));
		
		return mode;
	}

}
