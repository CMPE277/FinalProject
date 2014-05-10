package edu.sjsu.jobfair;

import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class EmployeeSearch extends ActionBarActivity {
	
	EditText ed;

	Map<String, String> studentIdRegIdLookup = new HashMap<String, String>();
	RestClient restClient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_employee_search);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}

		restClient = new RestClient();
		ed = (EditText) findViewById(R.id.txtStudentId);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.employee_search, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_employee_search,
					container, false);
			return rootView;
		}
	}

	private boolean pingStudent(String studentId) {
		if (studentIdRegIdLookup.containsKey(studentId)) {
			Map<String, Object> data = new HashMap<String, Object>();

			data.put("title", "Employee X");
			data.put("message", "Meet me at booth.");
			data.put(Home.PROPERTY_REG_ID, studentIdRegIdLookup.get(studentId));

			Map<String, Object> headers = new HashMap<String, Object>();
			headers.put("Authorization",
					"key=AIzaSyDnVGPX9KiJgnl9LFg9GyyOCw3JONW6cj8");
			headers.put("Content-Type", " application/json");

			restClient.postGCMData(data);
		}
		return true;
	}
	
	public void commentsActivity(View v) {
		
		Bundle data = new Bundle();
		data.putString("stud_id", ed.getText().toString());
		
		//ed.getText().toString()
		
		Intent i = new Intent(this, EmployeeComments.class);
		/*Bundle b = new Bundle();
		b.putString("stud_id",ed.getText().toString() );
		i.putExtras(b);*/
		i.putExtras(data);

		startActivity(i); 
	}
	

}
