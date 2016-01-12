package hobotun.user.modelParams;

public class ModeleStringParam extends AModeleParam {

	private String param = "";

	public String getParam() {
		checParamValue();
		return param;
	}

	public void setParam(String param) {
		this.param = param;
		checParamValue();
	}

	@Override
	public void checParamValue() {
		if (param.isEmpty()) {
			setOpacity50();
		} else {
			setOpacity100();
		}
	}
	
	
}
