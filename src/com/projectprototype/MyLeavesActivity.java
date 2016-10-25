package com.projectprototype;

import android.app.ListActivity;
import android.content.Intent;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class MyLeavesActivity extends ListActivity implements AdapterView.OnItemClickListener {

    Button backHome;
    DatabaseHelper db = new DatabaseHelper(this);
    List<String> listLeave;

    ArrayAdapter<String> myAdapter;
    private FirebaseAuth.AuthStateListener mAuthListener;
    public String nameConverted;
    public Handler handler;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    Calendar myCalendar = Calendar.getInstance();
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_leaves);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        backHome = (Button) findViewById(R.id.backhome);

        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backHome(v);
            }
        });

        DatabaseReference ref = database.getReference("dates");

        String c = "[@]";
        String[] nameEID = user.getEmail().split(c);
        listLeave = db.getAllInName(nameEID[0]);

        //Log.i("Adap", listLeave.get(0));
        myAdapter = new ArrayAdapter<String>(this, R.layout.leave_viewer, R.id.ListMyLeave, listLeave);

        //lv = (ListView) findViewById(android.R.id.list);
        this.setListAdapter(myAdapter);
        getListView().setOnItemClickListener(this);

    }



    public void onResume(){
        super.onResume();

    }





    public void backHome(View view) {
        finish();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView caption = (TextView) findViewById(R.id.ListMyLeave);
        FirebaseUser user = mAuth.getCurrentUser();
        final DatabaseReference ref = database.getReference();

        Intent intent = new Intent(MyLeavesActivity.this, EditLeaveActivity.class);
        String toEdit = (String) parent.getItemAtPosition(position);
        //Toast.makeText(ApproveLeaveActivity.this, toEdit, Toast.LENGTH_SHORT).show();
        String delim = "[\n]";
        String[] finalLeave = toEdit.split(delim);

        String c = "[@]";
        String[] tempname = user.getEmail().split(c);
        String name = tempname[0].toLowerCase();

        String a = "[ ]";
        String splitdate = finalLeave[1];
        String [] tempdate = splitdate.split(a);
        String date = tempdate[1];

        String b = ": ";
        String splittype = finalLeave[2];
        String [] temptype = splittype.split(b);
        String type = temptype[1];

        String d = ": ";
        String splitbackup = finalLeave[3];
        String [] tempbackup = splitbackup.split(d);
        String backup = tempbackup[1];

        String e = ": ";
        String splitstatus = finalLeave[4];
        String [] tempstatus = splitstatus.split(d);
        String status = tempstatus[1];

        String checker = finalLeave[0];

        intent.putExtra("name", name);
        intent.putExtra("date", date);
        intent.putExtra("type", type);
        intent.putExtra("backup", backup);
        intent.putExtra("checker", checker);
        intent.putExtra("status", status);

        startActivity(intent);
        finish();




    }
}
