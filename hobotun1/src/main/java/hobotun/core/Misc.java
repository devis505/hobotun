package hobotun.core;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class Misc {

	public static <T> T findManagedBean(FacesContext context, String beanName, Class<T> beanClass) {
		return beanClass
				.cast(context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", beanClass));
	}

	public static void setMessageElement(String idElement, Severity typeIcon, String msg) {
		FacesContext.getCurrentInstance().addMessage(idElement, new FacesMessage(typeIcon, "PrimeFaces", msg));
	}

	public static void redirect(String path) {
		redirect(path, FacesContext.getCurrentInstance());
	}

	private static void redirect(String path, FacesContext context) {

		try {
			String prefix = getAppPath();
			context.getExternalContext().redirect(prefix + path);

		} catch (IOException e) {

		}
	}

	public static String getAppPath() {

		String addr = ApplicationProperties.getInstance().getPropertyValue("ru.abrr.currantPortal.site.addr");
		String port = ApplicationProperties.getInstance().getPropertyValue("ru.abrr.currantPortal.site.port");

		String appPath = addr;
		
		if (port != null)
			appPath += ":" + port;

		String appName = ApplicationProperties.getInstance().getPropertyValue("ru.abrr.currantPortal.app.name");

		if (appName == null || appName.isEmpty())
			return appPath;

		return appPath + "/" + appName;
	}
	
	public static String getRequestParam(FacesContext context, String paramName) {
		return context.getExternalContext().getRequestParameterMap().get(paramName);
	}
	
	public static String getUnicValue(int count) {
		
		StringBuilder randString = new StringBuilder();
		
        for(int i=0;i<count;i++)
          randString.append(symbols.charAt((int)(Math.random()*symbols.length())));
		
		return randString.toString();
	}
	
	public static String md5Custom(String st) {
	    MessageDigest messageDigest = null;
	    byte[] digest = new byte[0];
	 
	    try {
	        messageDigest = MessageDigest.getInstance("MD5");
	        messageDigest.reset();
	        messageDigest.update(st.getBytes());
	        digest = messageDigest.digest();
	    } catch (NoSuchAlgorithmException e) {
	        // тут можно обработать ошибку
	        // возникает она если в передаваемый алгоритм в getInstance(,,,) не существует
	        e.printStackTrace();
	    }
	 
	    BigInteger bigInt = new BigInteger(1, digest);
	    String md5Hex = bigInt.toString(16);
	 
	    while( md5Hex.length() < 32 ){
	        md5Hex = "0" + md5Hex;
	    }
	 
	    return md5Hex;
	}
	
	private final static String symbols = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890_!?$#@";
}
