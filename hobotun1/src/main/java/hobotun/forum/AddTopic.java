package hobotun.forum;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import hobotun.core.Misc;

@ManagedBean (name="addTopic")
@ViewScoped
public class AddTopic {
	
	private String text;
	private String id_forum;
	private String nm_topic;
	
	public AddTopic() {
		id_forum = Misc.getRequestParam(FacesContext.getCurrentInstance(), "id");
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public void onSave() {
		
	}

	public String getNm_topic() {
		return nm_topic;
	}

	public void setNm_topic(String nm_topic) {
		this.nm_topic = nm_topic;
	}
	 
}
