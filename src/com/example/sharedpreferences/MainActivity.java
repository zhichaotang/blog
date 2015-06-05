package com.example.sharedpreferences;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;
import android.preference.Preference;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	protected void onStart() {
		super.onStart();

		SharedPreferences sharedPreferences = this.getSharedPreferences("person", Context.MODE_PRIVATE);

		EditText nameEditText = (EditText) findViewById(R.id.name);
		String name = sharedPreferences.getString(R.id.name+"", "");
		nameEditText.setText(name);

		EditText ageEditText = (EditText) findViewById(R.id.age);
		String age = sharedPreferences.getString(R.id.age+"", "");
		ageEditText.setText(age);
	}

	public void saveSetting(View view) {
		
		SharedPreferences sharedPreferences = this.getSharedPreferences("person", Context.MODE_PRIVATE);
		
		Editor edit = sharedPreferences.edit();
		
		EditText nameEditText = (EditText) findViewById(R.id.name);
		edit.putString(R.id.name+"", nameEditText.getText().toString());

		EditText ageEditText = (EditText) findViewById(R.id.age);
		edit.putString(R.id.age+"", ageEditText.getText().toString());
		
		edit.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			return rootView;
		}
	}

}
