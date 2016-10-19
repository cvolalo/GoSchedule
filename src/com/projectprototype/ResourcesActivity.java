package com.projectprototype;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ResourcesActivity extends ListActivity implements OnItemClickListener{

    Button add_resources;
    ResourceHelper db = new ResourceHelper(this);
    List<String> listResource;

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        add_resources = (Button) findViewById(R.id.addresources);

        add_resources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.i("network frag list"," test convert view");
                addResource(v);
            }
        });


        listResource = db.getAllResource();
        //Log.i("Adap", listLeave.get(0));

        //lv = (ListView) findViewById(android.R.id.ist);
        //myAdapter = new ArrayAdapter<String>(ViewLeaveActivity.this, android.R.layout.simple_list_item_1, listLeave);

        this.setListAdapter(new ArrayAdapter<String>(this, R.layout.list_viewer, R.id.ListLeave, listResource));
        getListView().setOnItemClickListener(this);

        //DatabaseReference ref = database.getReference("resources");

    }

    public void addResource(View view) {
        Intent intent1 = new Intent(this, AddResourceActivity.class);
        //intent1.putExtra("message", "Please Log Details");
        startActivity(intent1);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
