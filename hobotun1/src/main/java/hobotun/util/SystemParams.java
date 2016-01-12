package hobotun.util;

import hobotun.db.DBUtil;
import hobotun.db.SystemParam.SystemParamDao;

import java.util.HashMap;
import java.util.Map;

public class SystemParams {

    private static SystemParams instance = null;
    private Map<Integer, String> params = new HashMap<Integer, String>();

    private SystemParams() {

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

    public String getParamByName(Integer key) {

	if (!params.containsKey(key)) {
	    SystemParamDao sysDao = DBUtil.getInstance().getBean("systemParamDao", SystemParamDao.class);
	    params.put(key, sysDao.getParamById(key).get(0).getVlParam());
	}

	return params.get(key);

    }

}
