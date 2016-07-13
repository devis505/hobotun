package hobotun.sod.responce;

import java.util.List;
import java.util.Map;

public class JsonResponceParams {

    private Integer itemsCount;
    private List<Map<String, Object>> items;
    private List<Map<String, Object>> result;

    public Integer getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(Integer itemsCount) {
        this.itemsCount = itemsCount;
    }

    public List<Map<String, Object>> getItems() {
        return items;
    }

    public void setItems(List<Map<String, Object>> items) {
        this.items = items;
    }

    public List<Map<String, Object>> getResult() {
	return result;
    }

    public void setResult(List<Map<String, Object>> result) {
	this.result = result;
    }	
    
    
}
