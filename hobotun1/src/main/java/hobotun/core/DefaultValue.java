package hobotun.core;

import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@RequestScoped
@ManagedBean(name = "defValue")
public class DefaultValue {
	
	private String title = "HOBOTUN";
	private String randomLogo = "1";
	private Random rand = new Random();
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

	public String getRandomLogo() {
		randomLogo = String.valueOf(rand.nextInt(7) + 1);
		return randomLogo;
	}
	

}
