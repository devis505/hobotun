package hobotun.springDB;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.object.MappingSqlQuery;

public class SelectAllModel extends MappingSqlQuery<Model> {

    private static final String SQL_SELECT_ALL_MODEL = "select id_model, nm_model from model";

    public SelectAllModel(DataSource dataSource) {
	super(dataSource, SQL_SELECT_ALL_MODEL);
    }

    @Override
    protected Model mapRow(ResultSet rs, int rowNum) throws SQLException {
	Model model = new Model();

	model.setIdModel(rs.getInt("id_model"));
	model.setNmModel(rs.getString("nm_model"));
	
	return model;
    }

}
