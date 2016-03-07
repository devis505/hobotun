package hobotun.db.rating;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import hobotun.db.rating.action.InsertModeleRating;
import hobotun.db.rating.action.SelectUserModelRatingForModel;
import hobotun.db.rating.table.RatingModeleTbl;

public class RatingDao {

	private DataSource dataSource;
	private InsertModeleRating insertModeleRating;
	private SelectUserModelRatingForModel selectUserModelRatingForModel;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;

		insertModeleRating = new InsertModeleRating(dataSource);
		selectUserModelRatingForModel = new SelectUserModelRatingForModel(dataSource);
	}

	public void InsertModeleRating(RatingModeleTbl rating) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id_user", rating.getId_user());
		paramMap.put("id_model", rating.getId_model());
		paramMap.put("vl_rating", rating.getVl_rating());

		KeyHolder keyHolder = new GeneratedKeyHolder();

		insertModeleRating.updateByNamedParam(paramMap, keyHolder);

		rating.setId_rating_modele(keyHolder.getKey().longValue());
	}

	public List<RatingModeleTbl> selectUserModelRatingForModel(Long id_user, Long id_modele) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id_user", id_user);
		paramMap.put("id_modele", id_modele);

		return selectUserModelRatingForModel.executeByNamedParam(paramMap);
	}

}
