package hobotun.db;

import org.springframework.context.support.GenericXmlApplicationContext;

public class DBUtil {

	private static DBUtil dbUtil;
	private GenericXmlApplicationContext ctx;

	private DBUtil() {
		ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:META-INF/app-context.xml");
		ctx.refresh();
	}

	public <T> T getBean(String beanName, Class<T> clazz) {
		return ctx.getBean(beanName, clazz);
	}

	public static DBUtil getInstance() {
		if (dbUtil == null) {
			synchronized (DBUtil.class) {
				if (dbUtil == null)
					dbUtil = new DBUtil();
			}
		}

		return dbUtil;
	}
}
