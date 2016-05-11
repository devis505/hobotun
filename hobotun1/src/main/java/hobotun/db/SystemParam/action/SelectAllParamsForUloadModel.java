package hobotun.db.SystemParam.action;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.jdbc.object.MappingSqlQuery;
import hobotun.db.SystemParam.table.SystemParamTbl;

public class SelectAllParamsForUloadModel extends MappingSqlQuery<SystemParamTbl> {

	private static final String SQL_SELECT_PARAM_BY_ID = "select * from hb_params p where p.idParam in (2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25)";

	public SelectAllParamsForUloadModel(DataSource dataSource) {
		super(dataSource, SQL_SELECT_PARAM_BY_ID);
	}

	@Override
	protected SystemParamTbl mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new SystemParamTbl(rs);
	}

}
