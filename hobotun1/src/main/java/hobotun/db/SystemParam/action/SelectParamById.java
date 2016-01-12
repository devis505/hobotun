package hobotun.db.SystemParam.action;

import hobotun.db.SystemParam.table.SystemParamTbl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

public class SelectParamById extends MappingSqlQuery<SystemParamTbl> {

    private static final String SQL_SELECT_PARAM_BY_ID = "select * from hb_params p where p.idParam = :idParam";

    public SelectParamById(DataSource dataSource) {
	super(dataSource, SQL_SELECT_PARAM_BY_ID);
	super.declareParameter(new SqlParameter("idParam", Types.INTEGER));
    }

    @Override
    protected SystemParamTbl mapRow(ResultSet rs, int rowNum) throws SQLException {
	return new SystemParamTbl(rs);
    }

}
