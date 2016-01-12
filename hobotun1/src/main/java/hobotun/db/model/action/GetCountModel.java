package hobotun.db.model.action;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.object.MappingSqlQuery;

import hobotun.db.model.tbl.CountModelTbl;

public class GetCountModel extends MappingSqlQuery<CountModelTbl> {

	private static final String SQL_SELECT_MODEL_BY_ID = "select count(*) as c from hb_model m where m.is_moderation = 1";

	public GetCountModel(DataSource dataSource) {
		super(dataSource, SQL_SELECT_MODEL_BY_ID);
	}

	@Override
	protected CountModelTbl mapRow(ResultSet rs, int rowNum) throws SQLException {
		CountModelTbl count = new CountModelTbl();
		count.setCount(rs.getInt("c"));
		
		return count;
	}

}
