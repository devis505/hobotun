package hobotun.util;

import java.util.ArrayList;
import java.util.List;

import hobotun.db.DBUtil;
import hobotun.db.SystemParam.SystemParamDao;
import hobotun.db.SystemParam.table.SystemParamTbl;

public class SystemParams {

	private static SystemParams instance = null;
	private SystemParamDao sysDao = DBUtil.getInstance().getBean("systemParamDao", SystemParamDao.class);

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
	
	public List<String> getAllHintsForModelUpload() {
		List<String> hints = new ArrayList<>();
		
		for (SystemParamTbl sysParam : sysDao.getAllHintsForModelUpload()) {
			hints.add(sysParam.getVlParam());
		}
		
		return hints;
	}

	public String getParam(Integer key) {
		return sysDao.getParamById(key).get(0).getVlParam();
	}
	
	public String getParamByName(String key) {
		return sysDao.getParamByNm(key).get(0).getVlParam();
	}

}
