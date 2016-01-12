package hobotun.db.SystemParam;

import hobotun.db.SystemParam.table.SystemParamTbl;

import java.util.List;

public interface ISystemParamDao {

    public List<SystemParamTbl> getParamById(Integer id);
}
