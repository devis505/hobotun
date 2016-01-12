package hobotun.core;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@RequestScoped
@ManagedBean(name = "defValue")
public class DefaultValue {
    
    public String getTitle() {
	return "HOBOTUN";
    }

}
