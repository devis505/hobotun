package hobotun.sod.request;

import java.util.List;

public class JsonRequestParams {

    private List<RequestParam> params;
    private String queryName;
    private String owner;
    private String key_holder;
    
    public List<RequestParam> getParams() {
        return params;
    }

    public void setParams(List<RequestParam> params) {
        this.params = params;
    }

    public String getQueryName() {
	return queryName;
    }

    public void setQueryName(String queryName) {
	this.queryName = queryName;
    }

    @Override
    public String toString() {
	return "JsonRequestParams [params=" + params + ", queryName=" + queryName + "]";
    }

    public String getOwner() {
	return owner;
    }

    public void setOwner(String owner) {
	this.owner = owner;
    }

    public String getKey_holder() {
	return key_holder;
    }

    public void setKey_holder(String key_holder) {
	this.key_holder = key_holder;
    }	
    
    
}
