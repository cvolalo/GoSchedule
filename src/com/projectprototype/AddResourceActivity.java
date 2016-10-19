package com.projectprototype;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddResourceActivity extends AppCompatActivity implements OnItemSelectedListener {

    Button addResource;
    EditText resourceEid;
    EditText resourceName;
    Spinner resourceTower;
    RadioGroup resourceStatus;
    RadioButton radioButton;
    String item;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_resource);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        addResource = (Button) findViewById(R.id.addresource);

        resourceEid = (EditText) findViewById(R.id.resource_eid);
        resourceName = (EditText) findViewById(R.id.resource_name);
        resourceTower = (Spinner) findViewById(R.id.resource_tower);
        resourceStatus = (RadioGroup) findViewById(R.id.radioGroupstatus);

        resourceTower.setOnItemSelectedListener(this);

        addResource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAResource(v);
            }
        });

    }

    public void addAResource(View view) {


        if (resourceEid.getText().toString().length() > 0 && resourceName.getText().toString().length() > 0) {

            int selectStatus = resourceStatus.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(selectStatus);



            boolean logStatus = createResource(resourceEid.getText().toString(),resourceName.getText().toString(),item, radioButton.getText().toString());
            //Intent back = new Intent(this, MainActivity.class);
            if (logStatus){
                Toast.makeText(getApplicationContext(), "Added Resource!", Toast.LENGTH_LONG).show();
                //startActivity(back);
                finish();
            }
            else {
                Toast.makeText(getApplicationContext(), "Failed, please try again.", Toast.LENGTH_LONG).show();
            }
        }

        if (resourceEid.getText().toString().length() == 0) {
            Toast.makeText(getApplicationContext(), "EID is required.", Toast.LENGTH_LONG).show();
        }

        else if (resourceName.getText().toString().length() == 0) {
            Toast.makeText(getApplicationContext(), "Date is required.", Toast.LENGTH_LONG).show();
        }
    }

    public boolean createResource(String resourceEID, String resourceName, String resourceTower, String resourceStatus) {


        resourceEID = resourceEID.replace(".","-");

        //Firebase.setAndroidContext(this);
        //Firebase ref = new Firebase("https://goschedule-4ffe9.firebaseio.com/");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dateRef = database.getReference("resources");
        //Firebase dateRef = ref.child("dates");
        //DatabaseReference dateRef = ref.child("dates");

        Map<String, Object> dataput = new HashMap<String, Object>();
        dataput.put("eid", resourceEID);
        dataput.put("name", resourceName);
        dataput.put("tower", resourceTower);
        dataput.put("status", resourceStatus);
        dateRef.push().setValue(dataput);

        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
