package hobotun.sod;

import java.io.UnsupportedEncodingException;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import hobotun.sod.request.JsonRequestParams;
import hobotun.sod.responce.JsonResponceParams;

public class Sender {

    private JsonRequestParams request;
    private JsonResponceParams responce;

    private String url = "http://env-0095980.jelastic.regruhosting.ru/sod/json?json={json}";

    public Sender() {

    }

    public Sender(JsonRequestParams request) {
	super();
	this.setRequest(request);
    }

    public void send() throws RestClientException, UnsupportedEncodingException {
	RestTemplate restTemplate = new RestTemplate();
	
	Gson gson = new Gson();
	String json = gson.toJson(request);
	
	responce = restTemplate.getForObject(url, JsonResponceParams.class, json);
    }

    public JsonRequestParams getRequest() {
	return request;
    }

    public void setRequest(JsonRequestParams request) {
	this.request = request;
    }

    public JsonResponceParams getResponce() {
	return responce;
    }

}
