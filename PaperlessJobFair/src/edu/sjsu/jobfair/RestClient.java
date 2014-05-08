package edu.sjsu.jobfair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class RestClient {

	public void postRESTData(Map<String, Object> data) {

		new AsyncTask<Map, Void, String>() {
			@Override
			protected String doInBackground(Map... params) {

				// Create a new HttpClient and Post Header
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost(
						"http://192.168.1.7:80/test.php");

				try {
					// Add your data

					JSONObject json = new JSONObject(params[0]);
					Log.i("JSONArray", json.toString());

					httppost.setEntity(new StringEntity(json.toString()));

					httpclient.execute(httppost);
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

		}.execute(data);
	}

	public void postGCMData(Map<String, Object> data) {

		new AsyncTask<Map, Void, String>() {
			@Override
			protected String doInBackground(Map... params) {

				// Create a new HttpClient and Post Header
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost(
						"https://android.googleapis.com/gcm/send");

				try {
					Map<String, Object> hm = params[0];
					// Add your data
					ArrayList<String> regIds = new ArrayList<String>();
					regIds.add((String)hm.get("registration_id"));
					hm.put("registration_ids", regIds);
					hm.put("data", hm.get("message"));

					JSONObject json = new JSONObject(hm);
					Log.i("JSONArray", json.toString());

					httppost.setEntity(new StringEntity(json.toString()));

					httppost.addHeader("Authorization",
							"key=AIzaSyDnVGPX9KiJgnl9LFg9GyyOCw3JONW6cj8");
					httppost.addHeader("Content-Type", " application/json");
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

		}.execute(data);
	}

}
