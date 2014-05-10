package edu.sjsu.jobfair;

import org.json.JSONException;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

public class EmployeeComments extends ActionBarActivity {
	
	Intent intent;
	
	//BASE API + PATH
	private static String url = "http://54.215.205.214/student/";
	
	private static final String TAG_NAME = "name";
    private static final String TAG_STUDENT_ID = "student_id";
    private static final String TAG_RESUME = "resume";
    private static final String TAG_LINKEDIN = "linkedin";
    
    EditText edName, edStudentID, edResume, edLinkedin, edComments, edRating;
    
    String name, resume, linkedin, studentID;
    
    private ProgressDialog pDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_employee_comments);
		
		intent = getIntent();
		
	/*	Bundle b = getIntent().getExtras();
        String id = b.getString("stud_id");*/
		String id = intent.getStringExtra("stud_id");
        
        edName = (EditText) findViewById(R.id.fname);
        edStudentID = (EditText) findViewById(R.id.studentid);
        
        edLinkedin = (EditText) findViewById(R.id.LinkedinProfile);
        edComments = (EditText) findViewById(R.id.comments);
        
        
        
        url += id;
        
        new GetStudent().execute();

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.employee_comments, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.fragment_employee_comments, container, false);
			return rootView;
		}
	}
	
	 private class GetStudent extends AsyncTask<Void, Void, Void> {
		 
	        @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            // Showing progress dialog
	            pDialog = new ProgressDialog(EmployeeComments.this);
	            pDialog.setMessage("Please wait...");
	            pDialog.setCancelable(false);
	            pDialog.show();
	 
	        }
	 
	        @Override
	        protected Void doInBackground(Void... arg0) {
	            // Creating service handler class instance
	            ServiceHandler sh = new ServiceHandler();
	 
	            // Making a request to url and getting response
	            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
	 
	            Log.d("Response: ", "> " + jsonStr);
	 
	            if (jsonStr != null) {
	                try {
	                    JSONObject jsonObj = new JSONObject(jsonStr);
	                     
	                    // Getting JSON Array node
	                    name = jsonObj.getString(TAG_NAME);
	                    resume = jsonObj.getString(TAG_RESUME);
	                    studentID = jsonObj.getString(TAG_STUDENT_ID);
	                    linkedin = jsonObj.getString(TAG_LINKEDIN);
	                    
	 
	                } catch (JSONException e) {
	                    e.printStackTrace();
	                }
	            } else {
	                Log.e("ServiceHandler", "Couldn't get any data from the url");
	            }

	            return null;
	        }
	 
	        @Override
	        protected void onPostExecute(Void result) {
	            super.onPostExecute(result);
	            // Dismiss the progress dialog
	            if (pDialog.isShowing())
	                pDialog.dismiss();
	            /**
	* Updating parsed JSON data into Activity
	* */
	            
	         edName.setText(name);
	         edLinkedin.setText(linkedin);
	       //  edResume.setText(resume);
	         edStudentID.setText(studentID);
	            
	 
	            
	        }
	 
	    }
	 
	 public void submitComments (View v) {
		new SubmitComments().execute(); 
	 }
	 public void submitApp(View v) {
		 
		 Toast.makeText(getApplicationContext(),"Application saved!", Toast.LENGTH_LONG).show();
	     
	     Intent searchIntent = new Intent (getApplicationContext(),EmployeeSearch.class);
	     startActivity(searchIntent);
			
		}
	 
	 
	 private class SubmitComments extends AsyncTask<Void, Void, Void> {
		 
	        @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            // Showing progress dialog
	            pDialog = new ProgressDialog(EmployeeComments.this);
	            pDialog.setMessage("Please wait...");
	            pDialog.setCancelable(false);
	            pDialog.show();
	 
	        }
	 
	        @Override
	        protected Void doInBackground(Void... arg0) {
	            // Creating service handler class instance
	            ServiceHandler sh = new ServiceHandler();
	            
	        	JSONObject jsonObjSend = new JSONObject();

	        	try {
	        		// Add key/value pairs
	        		jsonObjSend.put("student_id", edStudentID.getText().toString());
	        		jsonObjSend.put("emp_id", "1");
	        		jsonObjSend.put("comments", edComments.getText().toString());
	        		jsonObjSend.put("comments", edRating.getText().toString());
	        		

	        		} catch (JSONException e) {
	        		e.printStackTrace();
	        		}
	 
	            // Making a request to url and getting response
	            String response = sh.makeServiceCall("http://54.215.205.214/application/", ServiceHandler.POST,jsonObjSend);
	 
	            Log.d("Response: ", "> " + response);
	

	            return null;
	        }
	 
	        @Override
	        protected void onPostExecute(Void result) {
	            super.onPostExecute(result);
	            // Dismiss the progress dialog
	            if (pDialog.isShowing())
	                pDialog.dismiss();
	            /**
	* Updating parsed JSON data into Activity
	* */
	            
	            
	 
	            
	        }
	 
	    }

}