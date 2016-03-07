package hobotun.db.rating.table;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RatingModeleTbl {

	private Long id_rating_modele;
	private Long id_user;
	private Long id_model;
	private Integer vl_rating;

	public RatingModeleTbl() {

	}

	public RatingModeleTbl(ResultSet rs) throws SQLException {
		id_rating_modele = rs.getLong("id_rating_modele");
		id_user = rs.getLong("id_user");
		id_model = rs.getLong("id_model");
		vl_rating = rs.getInt("vl_rating");
	}

	public Long getId_rating_modele() {
		return id_rating_modele;
	}

	public void setId_rating_modele(Long id_rating_modele) {
		this.id_rating_modele = id_rating_modele;
	}

	public Long getId_user() {
		return id_user;
	}

	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}

	public Long getId_model() {
		return id_model;
	}

	public void setId_model(Long id_model) {
		this.id_model = id_model;
	}

	public Integer getVl_rating() {
		return vl_rating;
	}

	public void setVl_rating(Integer vl_rating) {
		this.vl_rating = vl_rating;
	}

}
