package hobotun.db.SystemParam.action;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.jdbc.object.MappingSqlQuery;
import hobotun.db.SystemParam.table.SystemParamTbl;

public class SelectAllParams extends MappingSqlQuery<SystemParamTbl> {

	private static final String SQL_SELECT_PARAM_BY_ID = "select * from hb_params p";

	public SelectAllParams(DataSource dataSource) {
		super(dataSource, SQL_SELECT_PARAM_BY_ID);
	}

	@Override
	protected SystemParamTbl mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new SystemParamTbl(rs);
	}

}
