package hobotun.user.modelParams;

public class ModeleBooleanParam extends AModeleParam {

	private boolean param = false;
	private Integer intParam = 0;

	public boolean getParam() {
		checParamValue();
		return param;
	}
	
	public Integer getIntParam() {
		return intParam;
	}

	public void setParam(boolean param) {
		this.param = param;
		checParamValue();
	}

	@Override
	public void checParamValue() {
		if (!param) {
			setOpacity50();
			intParam = 0;
		} else {
			setOpacity100();
			intParam = 1;
		}
	}
	
	
}
