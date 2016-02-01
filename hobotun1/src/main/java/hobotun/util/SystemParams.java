package hobotun.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hobotun.db.DBUtil;
import hobotun.db.SystemParam.SystemParamDao;
import hobotun.db.SystemParam.table.SystemParamTbl;

public class SystemParams {

	private static SystemParams instance = null;
	private Map<Long, String> params = new HashMap<>();
	private List<SystemParamTbl> systemParams = null;

	private SystemParams() {
		SystemParamDao sysDao = DBUtil.getInstance().getBean("systemParamDao", SystemParamDao.class);
		systemParams = sysDao.getAllParams();
		
		for (SystemParamTbl systemParamTbl : systemParams) {
			params.put(systemParamTbl.getIdParam(), systemParamTbl.getVlParam());
		}
	}

	public static SystemParams getInstance() {
		if (instance == null) {
			synchronized (SystemParams.class) {
				if (instance == null) {
					instance = new SystemParams();
				}
			}
		}

		return instance;
	}

	public String getParam(Long key) {
		return params.get(key);
	}
	
	public String getParamByName(Long key) {
		return params.get(key);
	}

}
