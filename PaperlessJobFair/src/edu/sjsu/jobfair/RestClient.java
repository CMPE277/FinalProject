package edu.sjsu.jobfair;


import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class RestClient {
	
	public void postData(HashMap<String,String> hmData) {
        
		new AsyncTask<HashMap, Void, String>() {
            @Override
            protected String doInBackground(HashMap... params) { 

            	// Create a new HttpClient and Post Header
            	HttpClient httpclient = new DefaultHttpClient();
            	HttpPost httppost = new HttpPost("http://192.168.1.7:80/test.php");

            	try {
            		// Add your data
            		
            		JSONObject json = new JSONObject(params[0]);
            		Log.i("JSONArray", json.toString());
            		
            		httppost.setEntity(new StringEntity(json.toString()));

            		//httppost.addHeader("Authorization", "key=AIzaSyDnVGPX9KiJgnl9LFg9GyyOCw3JONW6cj8");
            		//httppost.addHeader("Content-Type"," application/json");
            		// Execute HTTP Post Request
            		HttpResponse response = httpclient.execute(httppost);
            	} catch (Exception e) {
            		// TODO Auto-generated catch block
            		Log.i(">>>>", e.toString());
            	} 
            	return "Data Posted Successfully !!!!!";
            	}
            
            @Override
            protected void onPostExecute(String msg) {
                Log.i("XYZ", msg);
            }
            
            }.execute(hmData);	
	}
	
	public void postToGCM(Integer x, Integer y) {
        
		new AsyncTask<Integer, Void, String>() {
            @Override
            protected String doInBackground(Integer... params) { 

            	// Create a new HttpClient and Post Header
            	HttpClient httpclient = new DefaultHttpClient();
            	HttpPost httppost = new HttpPost("https://android.googleapis.com/gcm/send");

            	try {
            		// Add your data
            		HashMap<String, Object> hm = new HashMap<String, Object>();
            		HashMap<String, Integer> coordinates = new HashMap<String, Integer>();
            		ArrayList<String> regIds = new ArrayList<String>();
            		coordinates.put("x", params[0]);
            		coordinates.put("y", params[1]);

            		regIds.add("APA91bF4B6pGbNzT3ETsrj3gbbzeSLnEl7rr-p51kWYE6Em-BY9Z20aou9Q9qigRYC21ePwWQp11tVnQWVEk0BdekS0XMp_vj4gFVT241KjhHoHc6eezV7oDUxj1KNdIADUrsqmddqq26-MMZf8MIlXbNQEu9cUkkWPXf0pCuryd3W9-jJmQR-A");
            		hm.put("registration_ids", regIds);
            		hm.put("data", coordinates);

            		JSONObject json = new JSONObject(hm);
            		Log.i("JSONArray", json.toString());
            		
            		httppost.setEntity(new StringEntity(json.toString()));

            		httppost.addHeader("Authorization", "key=AIzaSyDnVGPX9KiJgnl9LFg9GyyOCw3JONW6cj8");
            		httppost.addHeader("Content-Type"," application/json");
            		// Execute HTTP Post Request
            		HttpResponse response = httpclient.execute(httppost);
            	} catch (Exception e) {
            		// TODO Auto-generated catch block
            		Log.i(">>>>", e.toString());
            	} 
            	return "Data Posted Successfully !!!!!";
            	}
            
            @Override
            protected void onPostExecute(String msg) {
                Log.i("XYZ", msg);
            }
            
            }.execute(x, y, null);	    
	} 
		
} 

	
