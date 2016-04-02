package hobotun.core;

import java.util.HashMap;
import java.util.Map;

public class ParamsForQuery {
	
	private Map<String, Object> params = new HashMap<String, Object>(); 
	
	public ParamsForQuery() {
		
	}
	
	public void setParam(String key, Object value) {
		params.put(key, value);
	}
	
	public Object getParam(String key) {
		return params.get(key);
	}
	
	public Map<String, Object> getAllParam () {
		return params;
	}
}
