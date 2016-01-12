package hobotun.db_old;

import java.util.Map;


public class DataModel {
    
    public String GetJsonByDb(String sql, Map<String, Object> param) {
	
	DBJsonUtil jDb = new DBJsonUtil();
	
	jDb.writeRows(DBUtl2.getInstance().executeQuery(sql, param), null);
	
	return jDb.getRequestData();
    }
   
}
