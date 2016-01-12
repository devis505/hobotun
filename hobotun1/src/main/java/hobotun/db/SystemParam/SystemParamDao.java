package hobotun.db.SystemParam;

import hobotun.db.SystemParam.action.SelectParamById;
import hobotun.db.SystemParam.table.SystemParamTbl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

public class SystemParamDao implements ISystemParamDao {

    private DataSource dataSource;
    private SelectParamById selectParamById;

    public void setDataSource(DataSource dataSource) {
	this.dataSource = dataSource;
	selectParamById = new SelectParamById(dataSource);
    }

    public DataSource getDataSource() {
	return dataSource;
    }

    @Override
    public List<SystemParamTbl> getParamById(Integer id) {
	Map<String, Object> paramMap = new HashMap<String, Object>();
	paramMap.put("idParam", id);

	return selectParamById.executeByNamedParam(paramMap);
    }

}
