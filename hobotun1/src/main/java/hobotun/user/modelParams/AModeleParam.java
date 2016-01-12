package hobotun.user.modelParams;

public abstract class AModeleParam {
	
	private static String OPACITY_0 = "opacity0";
	private static String OPACITY_50 = "opacity50";
	private static String OPACITY_100 = "opacity100";
	private boolean altruist = false;
	private Integer isEmpty = 1;
	
	private String opacity = OPACITY_50;
	
	public void setOpacity0() {
		opacity = OPACITY_0;
	}
	
	public void setOpacity50() {
		
		if (!altruist)
			opacity = OPACITY_50;
		else
			setOpacity0();
		
		isEmpty = 1;
	}
	
	public void setOpacity100() {
		
		if (!altruist)
			opacity = OPACITY_100;
		else
			setOpacity0();
		
		isEmpty = 0;
	}
	
	public String getOpacity() {
		return opacity;
	}
	
	public boolean isEmpty() {
		return isEmpty.equals(1);
	}
	
	public void setAltruist(boolean altruist) {
		this.altruist = altruist;
	}
	
	public abstract void checParamValue();
	
}
