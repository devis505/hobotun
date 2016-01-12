package hobotun.db_old;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class DBUtl2 {

    private static DBUtl2 dbUtil = null;

    private DBUtl2() {

    }

    private String setParams(String sql, Map<String, Object> params) {

	String tmpSql = sql;

	for (String key : params.keySet()) {

	    Object obj = params.get(key);
	    tmpSql = tmpSql.replace(key, (String) obj);
	}

	return tmpSql;
    }

    public static DBUtl2 getInstance() {
	if (dbUtil == null) {
	    synchronized (DBUtl2.class) {
		if (dbUtil == null)
		    dbUtil = new DBUtl2();
	    }
	}

	return dbUtil;
    }

    public ResultSet executeQuery(String sql, Map<String, Object> param) {

	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

	try {

	    Class.forName(JDBC_DRIVER);
	    conn = DriverManager.getConnection(DB_URL, USER, PASS);
	    stmt = conn.createStatement();

	    if (param != null) {
		rs = stmt.executeQuery(setParams(sql, param));
	    } else {
		rs = stmt.executeQuery(sql);
	    }

	} catch (SQLException | ClassNotFoundException e) {
	    e.printStackTrace();
	}

	return rs;
    }

    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://server120.hosting.reg.ru/u0081230_test";

    // Database credentials
    private static final String USER = "u0081230_test";
    private static final String PASS = "d5i0s5a";
}
