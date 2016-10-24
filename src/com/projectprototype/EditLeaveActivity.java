package com.projectprototype;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class EditLeaveActivity extends AppCompatActivity {

    Button editupdateButton;
    Button editcancelButton;
    EditText editname;
    EditText editdate;
    EditText editbackup;
    EditText editstatus;
    String editchecker;
    Spinner edittype;
    String item;
    boolean adminCheck = false;
    boolean dbSyncCheck = false;
    String[] names = new String[1];
    //String EID;
    private FirebaseAuth.AuthStateListener mAuthListener;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    Calendar myCalendar = Calendar.getInstance();
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_leave);

        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        editupdateButton = (Button) findViewById(R.id.editleaveSubmit);
        editcancelButton = (Button) findViewById(R.id.editleaveCancel);

        editname = (EditText) findViewById(R.id.editleaveName);
        editdate = (EditText) findViewById(R.id.editleaveDate);
        edittype = (Spinner) findViewById(R.id.editleaveType);
        editbackup = (EditText) findViewById(R.id.editleaveBackUp);
        editstatus = (EditText) findViewById(R.id.editleaveStatus);

        editstatus.setVisibility(View.GONE);

        String c = "[@]";
        final String[] nameEID = user.getEmail().split(c);




        Intent intentDateReceived = getIntent();
        String neweditDate= intentDateReceived.getExtras().getString("date");
        //String neweditName = intentDateReceived.getExtras().getString("name");
        String neweditType = intentDateReceived.getExtras().getString("type");

        String neweditBackup = intentDateReceived.getExtras().getString("backup");
        String neweditStatus = intentDateReceived.getExtras().getString("status");
        String neweditChecker = intentDateReceived.getExtras().getString("checker");


        editname.setText(nameEID[0]);
        editdate.setText(neweditDate);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.leave_arrays, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       edittype.setAdapter(adapter);
        if (!neweditDate.equals(null)) {
            int spinnerPosition = adapter.getPosition(neweditDate);
            edittype.setSelection(spinnerPosition);
        }




    }

    public boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        final FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            if (adminCheck) {
                getMenuInflater().inflate(R.menu.admin, menu);

            }
            Query adminQuery = database.getReference().child("admin").limitToFirst(20);

            //adminQuery.addValueEventListener(new ValueEventListener() {
            adminQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot adminSnapshot: dataSnapshot.getChildren()) {
                        String value = (String) adminSnapshot.getValue();
                        names[0] = value;
                        names[0] = names[0].replaceAll("\\s","");
                        //Toast.makeText(LoginActivity.this, names[0], Toast.LENGTH_SHORT).show();

                        String[] admins = names[0].split(",");

                        for (int i = 0; i < admins.length ; i++){
                            if (admins[i].equals(user.getEmail())){
                                //Toast.makeText(LoginActivity.this, "admin", Toast.LENGTH_SHORT).show();
                                adminCheck = true;
                            }
                        }

                        if (adminCheck) {
                            getMenuInflater().inflate(R.menu.admin, menu);
                        }
                        else{
                            getMenuInflater().inflate(R.menu.main, menu);
                        }
                        // do your stuff here with value
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }else {
            getMenuInflater().inflate(R.menu.main, menu);
        }


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

	/*if (id == R.id.action_settings) {
	    return true;
	}*/
        if (id == R.id.weekview){
            Intent intent = new Intent(this, WeekViewActivity.class);

            this.startActivity(intent);
            this.finish();
        }

        if (id == R.id.signout) {

            //mAuth = FirebaseAuth.getInstance();
            final ProgressDialog progressDialog2 = new ProgressDialog(EditLeaveActivity.this);
            progressDialog2.setIndeterminate(true);
            progressDialog2.setMessage("Signing out...");
            progressDialog2.show();


		/*Intent intent = new Intent(this, LoginActivity.class);

		MainActivity.this.finish();
		MainActivity.this.startActivity(intent);*/
            FirebaseAuth.getInstance().signOut();

// this listener will be called when there is change in firebase user session
            //auth.signOut();

// this listener will be called when there is change in firebase user session
            mAuthListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    if (user == null) {
                        // user auth state is changed - user is null
                        // launch login activity
                        Intent i = getBaseContext().getPackageManager()
                                .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                    }
                }
            };
            mAuth.addAuthStateListener(mAuthListener);
            EditLeaveActivity.this.finish();

        }

        if (id == R.id.myleaves) {

            Intent mainIntent = new Intent(EditLeaveActivity.this, MyLeavesActivity.class);
            EditLeaveActivity.this.startActivity(mainIntent);



        }

        if (id == R.id.search) {

            Intent mainIntent = new Intent(EditLeaveActivity.this, SearchEIDActivity.class);
            EditLeaveActivity.this.startActivity(mainIntent);
        }

        if (id == R.id.leaves) {

            Intent mainIntent = new Intent(EditLeaveActivity.this, ApproveLeaveActivity.class);
            EditLeaveActivity.this.startActivity(mainIntent);



        }

        if (id == R.id.reset) {

            FirebaseUser user = mAuth.getCurrentUser();

            final ProgressDialog progressDialog = new ProgressDialog(EditLeaveActivity.this);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Sending email...");
            progressDialog.show();
            mAuth.sendPasswordResetEmail(user.getEmail()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(EditLeaveActivity.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(EditLeaveActivity.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                    }

                    progressDialog.cancel();
                }
            });


        }

        return super.onOptionsItemSelected(item);
    }




}
