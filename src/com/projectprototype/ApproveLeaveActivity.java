package com.projectprototype;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApproveLeaveActivity extends ListActivity implements AdapterView.OnItemClickListener {

    DatabaseHelper db = new DatabaseHelper(this);
    private FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_leave);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();


        Button HomeButton = (Button) findViewById(R.id.faCancel);
        Button SIT = (Button) findViewById(R.id.faSIT);
        Button Training = (Button) findViewById(R.id.faTraining);
        Button PMO = (Button) findViewById(R.id.faPMO);
        Button Creative = (Button) findViewById(R.id.faCreative);
        Button Opps = (Button) findViewById(R.id.faOpps);


        HomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        SIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                faSIT(v);
            }
        });
        Training.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                faTraining(v);
            }
        });
        PMO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                faPMO(v);
            }
        });
        Creative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                faCreative(v);
            }
        });
        Opps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                faOpps(v);
            }
        });

    }

    private void faSIT(View v) {

        List<String> listLeave = db.getSIT();

        //Log.i("Adap", listLeave.get(0));

        //lv = (ListView) findViewById(android.R.id.list);
        this.setListAdapter(new ArrayAdapter<String>(this, R.layout.leave_viewer, R.id.ListMyLeave, listLeave));
        getListView().setOnItemClickListener(this);
    }

    private void faOpps(View v) {

        List<String> listLeave = db.getOpps();

        //Log.i("Adap", listLeave.get(0));

        //lv = (ListView) findViewById(android.R.id.list);
        this.setListAdapter(new ArrayAdapter<String>(this, R.layout.leave_viewer, R.id.ListMyLeave, listLeave));
        getListView().setOnItemClickListener(this);
    }

    private void faCreative(View v) {

        List<String> listLeave = db.getCreative();

        //Log.i("Adap", listLeave.get(0));

        //lv = (ListView) findViewById(android.R.id.list);
        this.setListAdapter(new ArrayAdapter<String>(this, R.layout.leave_viewer, R.id.ListMyLeave, listLeave));
        getListView().setOnItemClickListener(this);
    }

    private void faTraining(View v) {

        List<String> listLeave = db.getTraining();

        //Log.i("Adap", listLeave.get(0));

        //lv = (ListView) findViewById(android.R.id.list);
        this.setListAdapter(new ArrayAdapter<String>(this, R.layout.leave_viewer, R.id.ListMyLeave, listLeave));
        getListView().setOnItemClickListener(this);
    }

    private void faPMO(View v) {

        List<String> listLeave = db.getPMO();

        //Log.i("Adap", listLeave.get(0));

        //lv = (ListView) findViewById(android.R.id.list);
        this.setListAdapter(new ArrayAdapter<String>(this, R.layout.leave_viewer, R.id.ListMyLeave, listLeave));
        getListView().setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView caption = (TextView) findViewById(R.id.ListMyLeave);
        final DatabaseReference ref = database.getReference();
        String toEdit = (String) caption.getText();
        //Toast.makeText(ApproveLeaveActivity.this, toEdit, Toast.LENGTH_SHORT).show();
        String delim = "[,]";
        String[] finalLeave = toEdit.split(delim);

        String name = finalLeave[0].toLowerCase();
        name = name.replace(".", "-");
        String type = finalLeave[1];
        final String date = finalLeave[2];
        String backup = finalLeave[3];
        backup = backup.replace(".", "-");
        String checker = finalLeave[4];
        checker = checker.replace(".","-");

        ref.child("dates")
                .orderByChild("checker")
                .equalTo(checker)
                .addListenerForSingleValueEvent(new ValueEventListener() {


                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                            String clubkey = childSnapshot.getKey();


                            ref.child("dates").child(clubkey).child("status").setValue("Approved");
                        }






                        //ref.orderByChild("name").equalTo(name).orderByChild("date").equalTo(date);
            /*Query getname = ref.orderByChild("name").equalTo(name);
            Query getdate = getname.orderByChild("date").equalTo(date);
            Query gettype = getdate.orderByChild("type").equalTo(type);
            Query getbackup = gettype.orderByChild("backup").equalTo(backup);
            ChildEventListener status = getbackup.orderByChild("status").equalTo("For Approval").addListenerForSingleValueEvent (*/

                /*Map<String,Object> dataput = new HashMap<String, Object>();
                dataput.put("name", name);
                dataput.put("date", date);
                dataput.put("type", type);
                dataput.put("backup", backup);
                dataput.put("status", "Approved");
                ref.updateChildren(dataput);*/


                        //);


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }


                });
        Toast.makeText(ApproveLeaveActivity.this, toEdit, Toast.LENGTH_SHORT).show();

    }

    /*private void updateEntry(String name, String date, String type, String backup) {
        //DatabaseReference ref = database.getReference("dates").;

        //ref.orderByChild("name").equalTo(name).orderByChild("date").equalTo(date);
            /*Query getname = ref.orderByChild("name").equalTo(name);
            Query getdate = getname.orderByChild("date").equalTo(date);
            Query gettype = getdate.orderByChild("type").equalTo(type);
            Query getbackup = gettype.orderByChild("backup").equalTo(backup);
            ChildEventListener status = getbackup.orderByChild("status").equalTo("For Approval").addListenerForSingleValueEvent (*/

                /*Map<String,Object> dataput = new HashMap<String, Object>();
                dataput.put("name", name);
                dataput.put("date", date);
                dataput.put("type", type);
                dataput.put("backup", backup);
                dataput.put("status", "Approved");
                ref.updateChildren(dataput);*/


    //);*/


    private void updateEntry(final String name, String date, String type, String backup) {


        //Query yey = ref.child("name").equalTo(name);


    }
}