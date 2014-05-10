package edu.sjsu.jobfair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;


 
public class ServiceHandler {
 
    static String response = null;
    public final static int GET = 1;
    public final static int POST = 2;
 
    public ServiceHandler() {
 
    }
 
    /**
* Making service call
* @url - url to make request
* @method - http request method
* */
    public String makeServiceCall(String url, int method) {
        return this.makeServiceCall(url, method, null);
    }
 
    /**
* Making service call
* @url - url to make request
* @method - http request method
* @params - http request params
* */
    public String makeServiceCall(String url, int method,
    		JSONObject jsonObjSend) {
        try {
            // http client
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpEntity httpEntity = null;
            HttpResponse httpResponse = null;
             
            if (method == GET) {
               
                HttpGet httpGet = new HttpGet(url);
 
                httpResponse = httpClient.execute(httpGet);
 
            }
            
            if (method == POST) {
            	DefaultHttpClient httpclient = new DefaultHttpClient();
            	HttpPost httpPostRequest = new HttpPost(url);
            	
            	StringEntity se;
            	se = new StringEntity(jsonObjSend.toString());
            	
            	httpPostRequest.setEntity(se);
            	httpPostRequest.setHeader("Content-type", "application/json");
            	
            	@SuppressWarnings("unused")
				HttpResponse response = (HttpResponse) httpclient.execute(httpPostRequest);
            	
 
            }
            httpEntity = httpResponse.getEntity();
            response = EntityUtils.toString(httpEntity);
 
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
         
        return response;
 
    }
}