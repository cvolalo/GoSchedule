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

    public static class Resource {
        String date;
        String name;
        String type;
        String backup;
        String status;
        String checker;

        public Resource() {
            // empty default constructor, necessary for Firebase to be able to deserialize blog posts
        }



        public String getDate() {
            return date;
        }
        public String getName() {
            return name;
        }
        public String getType() {
            return type;
        }
        public String getBackup() {
            return backup;
        }
        public String getStatus() {
            return status;
        }
        public String getChecker() {
            return checker;
        }

    }

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

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //System.out.println("There are " + snapshot.getChildrenCount() + " blog posts");

                db.deleteAll();
                for (DataSnapshot personSnapshot: snapshot.getChildren()) {
                    Resource person = personSnapshot.getValue(Resource.class);
                    nameConverted = person.getName();
                    nameConverted = nameConverted.replace("-",".");
                    db.createLog(nameConverted,person.getDate(),person.getType(),person.getBackup(),person.getStatus(),person.getChecker());


                    //System.out.println(post.getAuthor() + " - " + post.getTitle());
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //System.out.println("The read failed: " + firebaseError.getMessage());
            }

            //@Override
            //public void onCancelled(FirebaseError firebaseError) {
            //      System.out.println("The read failed: " + firebaseError.getMessage());
            //}
        });
        /*public void editLeave(View view) {
            FirebaseUser user = mAuth.getCurrentUser();
            String c = "[@]";
            String[] nameEID = user.getEmail().split(c);
            Intent intent1 = new Intent(this, EditLeaveActivity.class);
            intent1.putExtra("nameEID", nameEID[0]);
            intent1.putExtra("")
            startActivity(intent1);
        }*/


        String c = "[@]";
        String[] nameEID = user.getEmail().split(c);
        listLeave = db.getAllInName(nameEID[0]);

        //Log.i("Adap", listLeave.get(0));

        //lv = (ListView) findViewById(android.R.id.list);
        this.setListAdapter(new ArrayAdapter<String>(this, R.layout.leave_viewer, R.id.ListMyLeave, listLeave));
        getListView().setOnItemClickListener(this);

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
        String toEdit = (String) caption.getText();
        //Toast.makeText(ApproveLeaveActivity.this, toEdit, Toast.LENGTH_SHORT).show();
        String delim = "[\n]";
        String[] finalLeave = toEdit.split(delim);

        String c = "[@]";
        String[] tempname = user.getEmail().split(c);
        String name = tempname[0].toLowerCase();
        String date = finalLeave[0];
        String type = finalLeave[1];
        String backup = finalLeave[2];
        String checker = finalLeave[3];

        intent.putExtra("name", name);
        intent.putExtra("date", date);
        intent.putExtra("type", type);
        intent.putExtra("backup", backup);
        intent.putExtra("checker", checker);

        startActivity(intent);




    }
}
