package edu.sjsu.jobfair;

import java.util.HashMap;
import java.util.Map;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.os.Build;

public class StudentProfile extends ActionBarActivity {

	RestClient restClient = new RestClient();
	EditText txtFirstName, txtLastName, txtStudentId, txtResumeLink, txtLinkedlnLink, txtIntro;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_student_profile);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.student_profile, menu);
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

	public void submitStudentData(View v) {
		HashMap<String, String> studentData = new HashMap<String,String>();
		
		txtFirstName = (EditText)findViewById(R.id.txtFirstName);
		txtLastName = (EditText)findViewById(R.id.txtLastName);
		txtStudentId = (EditText)findViewById(R.id.txtStudentId);
		txtIntro = (EditText)findViewById(R.id.txtIntro);
		txtResumeLink = (EditText)findViewById(R.id.txtResume);
		txtLinkedlnLink = (EditText)findViewById(R.id.txtLinkedln);
		
		studentData.put("A", "AB");
		studentData.put("FirstName", txtFirstName.getText().toString());
		studentData.put("LastName", txtLastName.getText().toString());
		studentData.put("StudentId", txtStudentId.getText().toString());
		studentData.put("Intro", txtIntro.getText().toString());
		studentData.put("ResumeLink", txtResumeLink.getText().toString());
		studentData.put("LinkedlnLink", txtLinkedlnLink.getText().toString());
		
		
		System.out.println("Post Student Data");
		restClient.postData(studentData);
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
			View rootView = inflater.inflate(R.layout.fragment_student_profile,
					container, false);
			return rootView;
		}
	}

}
