package hobotun.db.model.action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import hobotun.db.model.tbl.ModelTbl;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

public class SelectModelById extends MappingSqlQuery<ModelTbl> {

	private static final String SQL_SELECT_MODEL_BY_ID = "select * from hb_model m where m.idModel = :idModel";

	public SelectModelById(DataSource dataSource) {
		super(dataSource, SQL_SELECT_MODEL_BY_ID);
		super.declareParameter(new SqlParameter("idModel", Types.INTEGER));
	}

	@Override
	protected ModelTbl mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new ModelTbl(rs);
	}

}
