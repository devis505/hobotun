package hobotun.forum;

import javax.faces.context.FacesContext;

import hobotun.core.Misc;

public class Forum {
	
	private String idForum;

	public Forum () {
		idForum = Misc.getRequestParam(FacesContext.getCurrentInstance(), "id");
	}
	
}
