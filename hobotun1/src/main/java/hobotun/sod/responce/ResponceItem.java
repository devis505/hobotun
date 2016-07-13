package hobotun.sod.responce;

public class ResponceItem {

    private String name;
    private Object values;

    public ResponceItem(String name, Object object) {
	super();
	this.name = name;
	this.values = object;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValues() {
        return values;
    }

    public void setValues(Object values) {
        this.values = values;
    }
    
    
}
