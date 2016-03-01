package hobotun.core;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@RequestScoped
@ManagedBean(name = "defValue")
public class DefaultValue {
	
	private String title = "HOBOTUN";
	private final static String appPath = Misc.getAppPath();
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAppPath() {
		return appPath;
	}

}
