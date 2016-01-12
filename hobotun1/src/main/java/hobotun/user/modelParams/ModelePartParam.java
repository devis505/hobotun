package hobotun.user.modelParams;

import javax.servlet.http.Part;

public class ModelePartParam extends AModeleParam {

	private Part param = null;
	private String fileName = "";

	public Part getParam() {
		checParamValue();
		return param;
	}

	public void setParam(Part param) {
		this.param = param;
		checParamValue();
	}

	@Override
	public void checParamValue() {
		if (param == null) {
			setOpacity50();
		} else {
			setOpacity100();
			fileName = getFileName(param);
		}
	}

	public String getText() {
		return fileName;
	}

	private String getFileName(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}
}
