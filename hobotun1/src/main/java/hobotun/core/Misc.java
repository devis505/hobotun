package hobotun.core;

import java.io.IOException;

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
	Integer port = Integer.valueOf(ApplicationProperties.getInstance().getPropertyValue(
		"ru.abrr.currantPortal.site.port"));

	String appPath = addr + ":" + port;

	String appName = ApplicationProperties.getInstance().getPropertyValue("ru.abrr.currantPortal.app.name");

	if (appName == null || appName.isEmpty())
	    return appPath;

	return appPath + "/" + appName;
    }
}
