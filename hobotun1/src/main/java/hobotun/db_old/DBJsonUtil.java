package hobotun.db_old;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class DBJsonUtil {

    private String requestData;

    public void writeRows(ResultSet responseRows, String metadata) {

	Gson gson = new GsonBuilder().serializeNulls().create();
	JsonArray dataRows = new JsonArray();
	Set<String> fieldNames = new LinkedHashSet<>();

	try {
	    int columnCount = responseRows.getMetaData().getColumnCount();

	    while (responseRows.next()) {
		List<Object> rowValues = new ArrayList<>();

		for (int i = 0; i < columnCount; i++) {
		    fieldNames.add(responseRows.getMetaData().getColumnName(i));
		    rowValues.add(responseRows.getString(i));
		}

		dataRows.add(gson.toJsonTree(rowValues));
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}

	JsonObject response = new JsonObject();
	response.addProperty("code", 0);
	response.addProperty("message", "Запрос обработан");
	response.add("fields", gson.toJsonTree(fieldNames));
	response.add("rows", dataRows);
	if (metadata != null)
	    response.addProperty("metadata", metadata);

	setRequestData(gson.toJson(response));
    }

    public String getRequestData() {
	return requestData;
    }

    public void setRequestData(String requestData) {
	this.requestData = requestData;
    }

}
