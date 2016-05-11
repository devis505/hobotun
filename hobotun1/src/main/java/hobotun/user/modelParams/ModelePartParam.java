package hobotun.user.modelParams;

import org.primefaces.model.UploadedFile;

public class ModelePartParam extends AModeleParam {

	private UploadedFile param = null;

	public UploadedFile getParam() {
		checParamValue();
		return param;
	}

	public void setParam(UploadedFile param) {
		this.param = param;
		checParamValue();
	}

	@Override
	public void checParamValue() {
		if (param == null) {
			setOpacity50();
		} else {
			setOpacity100();
		}
	}

}
