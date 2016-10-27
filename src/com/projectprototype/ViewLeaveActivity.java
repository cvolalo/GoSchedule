package com.projectprototype;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ViewLeaveActivity extends ListActivity implements OnItemClickListener{
	DatabaseHelper db;
	List<String> listLeave;
	//ListView lv;
	ArrayAdapter<String> myAdapter;
	String monthString;
	String dayString;
	String dateString;
	private FirebaseAuth mAuth;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_view_leave);
		db = new DatabaseHelper(this);
		mAuth = FirebaseAuth.getInstance();
		FirebaseUser user = mAuth.getCurrentUser();
		Intent intentDateReceived = getIntent();
		String year = intentDateReceived.getExtras().getString("year");
		String month = intentDateReceived.getExtras().getString("month");
		String day = intentDateReceived.getExtras().getString("day");

		Button backHome = (Button) findViewById(R.id.backhome2);

		backHome.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				backHome(v);
			}
		});

		dateString = month + "/" + day + "/" + year;

		listLeave = db.getAllInDate(dateString);

		//Log.i("Adap", listLeave.get(0));

		//lv = (ListView) findViewById(android.R.id.ist);
		//myAdapter = new ArrayAdapter<String>(ViewLeaveActivity.this, android.R.layout.simple_list_item_1, listLeave);
		this.setListAdapter(new ArrayAdapter<String>(this, R.layout.list_viewer, R.id.ListLeave, listLeave));
		getListView().setOnItemClickListener(this);
		//lv.setAdapter(myAdapter);

		Toast.makeText(getApplicationContext(), dateString, Toast.LENGTH_LONG).show();
	}

	public void backHome(View view) {
		finish();
	}

	@Override
	public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		db.close();
	}
}