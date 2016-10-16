package com.projectprototype;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class SearchEIDActivity extends ListActivity implements AdapterView.OnItemClickListener {
    DatabaseHelper db = new DatabaseHelper(this);
    List<String> listLeave;
    ListView lv;
    ArrayAdapter<String> myAdapter;
    String nameString;
    EditText EID;
    Button searchEIDButton;
    Button cancelEIDButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_eid);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        Intent intentReceived = getIntent();

        searchEIDButton = (Button) findViewById(R.id.searchSubmit);
        cancelEIDButton = (Button) findViewById(R.id.searchCancel);

        EID = (EditText) findViewById(R.id.searchName);

        searchEIDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchLeave(v);
            }
        });
        cancelEIDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelEIDView(v);
            }
        });
    }

    public void searchLeave(View view) {
        //Intent intent1 = new Intent(this, MainActivity.class);
        //intent1.putExtra("message", "Canceled.");
        //startActivity(intent1);
        nameString = EID.getText().toString();
        listLeave = db.getAllInName(nameString);

        //Log.i("Adap", listLeave.get(0));

        lv = (ListView) findViewById(android.R.id.list);
        myAdapter = new ArrayAdapter<String>(SearchEIDActivity.this, android.R.layout.simple_list_item_1, listLeave);
        getListView().setOnItemClickListener(this);
        lv.setAdapter(myAdapter);
    }

    public void cancelEIDView(View view) {
        //Intent intent1 = new Intent(this, MainActivity.class);
        //intent1.putExtra("message", "Canceled.");
        //startActivity(intent1);
        finish();
    }

    @Override
    public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
        // TODO Auto-generated method stub

    }


}
