package hobotun.springDB;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.asm.Type;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

public class SelectModelById extends MappingSqlQuery<Model> {

    private static final String SQL_FIND_MODEL_BY_ID = "select id_model, nm_model from model where id_model = :id_model";

    public SelectModelById(DataSource dataSource) {
	super(dataSource, SQL_FIND_MODEL_BY_ID);
	super.declareParameter(new SqlParameter("id_model", Type.INT));
    }

    @Override
    protected Model mapRow(ResultSet rs, int rowNum) throws SQLException {

	Model model = new Model();
	model.setIdModel(rs.getInt("id_model"));
	model.setNmModel(rs.getString("nm_model"));

	return model;
    }

}
