package hobotun.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.Properties;

public class ApplicationProperties implements Serializable {

	private Properties properties;
	private volatile static ApplicationProperties applicationProperties;

	private ApplicationProperties() {

		properties = new Properties(System.getProperties());

		try {
			properties.load(new FileInputStream(CONFIG_FOLDER + APP_FILE_NAME));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static ApplicationProperties getInstance() {

		if (applicationProperties == null)
			synchronized (ApplicationProperties.class) {
				if (applicationProperties == null) {
					applicationProperties = new ApplicationProperties();
				}
			}

		return applicationProperties;
	}

	private static String findWebInfPath() {

		URL url = ApplicationProperties.class.getResource("ApplicationProperties.class");
		String classPath = url.getFile();
		int start = 0;

		if (classPath.startsWith(url.getProtocol())) {
			start = url.getProtocol().length() + 1;
		}

		return classPath.substring(start, classPath.lastIndexOf(WEBINF) + WEBINF.length()) + "/";
	}

	public static Properties getProperties() {
		return getInstance().properties;
	}

	public String getPropertyValue(String name) {

		if (!properties.containsKey(name)) {
			System.out.println("Отсутствует свойство " + name);
			return null;
		}

		return properties.getProperty(name);
	}

	public String getPropertyValue(String name, String def) {
		return properties.containsKey(name) ? properties.getProperty(name) : def;
	}

	private static final long serialVersionUID = -5611219568277046869L;
	private static final String WEBINF_PATH = findWebInfPath();
	private static final String CONFIG_FOLDER = "/" + WEBINF_PATH + "classes/";
	private static final String WEBINF = "WEB-INF";
	private static final String APP_FILE_NAME = "application.properties";
}
