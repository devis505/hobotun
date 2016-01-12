package hobotun.user.modelParams;

public class ModeleIntegerParam extends AModeleParam {

	private Integer param = 0;

	public Integer getParam() {
		checParamValue();
		return param;
	}

	public void setParam(Integer param) {
		this.param = param;
		checParamValue();
	}

	@Override
	public void checParamValue() {
		if (param > 0) {
			setOpacity100();
		} else {
			setOpacity50();
		}
	}
	
	
}
