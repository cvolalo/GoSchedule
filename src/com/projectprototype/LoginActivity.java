package com.projectprototype;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity{
	
	TextView loginE;
	String email;
	String password;
	TextView loginPass;
	Button loginButt;

	private FirebaseAuth mAuth;
	private FirebaseAuth.AuthStateListener mAuthListener;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_authentication);

		mAuth = FirebaseAuth.getInstance();

		loginE = (TextView) findViewById(R.id.loginEmail);
		loginPass = (TextView) findViewById(R.id.loginPassword);
		loginButt = (Button) findViewById(R.id.loginButton);
		
		loginButt.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	            login(v);
	        }
	    });

		mAuthListener = new FirebaseAuth.AuthStateListener() {
			@Override
			public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
				FirebaseUser user = firebaseAuth.getCurrentUser();
				if (user != null) {
					Toast.makeText(LoginActivity.this, "Log-in Success!"
							,Toast.LENGTH_SHORT).show();
					Intent mainIntent = new Intent(LoginActivity.this,MainActivity.class);
					LoginActivity.this.startActivity(mainIntent);
					LoginActivity.this.finish();
					// User is signed in
					//Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
				} else {
					Toast.makeText(LoginActivity.this, "Log-in Success but no user found!"
							,Toast.LENGTH_SHORT).show();
					// User is signed out
					//Log.d(TAG, "onAuthStateChanged:signed_out");
				}
				// ...
			}
		};
	}

	@Override
	public void onStart() {
		super.onStart();
		mAuth.addAuthStateListener(mAuthListener);
	}

	@Override
	public void onStop() {
		super.onStop();
		if (mAuthListener != null) {
			mAuth.removeAuthStateListener(mAuthListener);
		}
	}
	
	public void login(View view) {
		
		//Toast.makeText(getApplicationContext(), "Log-in Success!", Toast.LENGTH_LONG).show();
		email = loginE.getText().toString();
		password = loginPass.getText().toString();
		email = email + "@accenture.com";
		
		//Firebase.setAndroidContext(this);
		//Firebase ref = new Firebase("https://goschedule-12ee6.firebaseio.com/");
		
		/**ref.authWithPassword(email, password, new Firebase.AuthResultHandler() {
		    @Override
		    public void onAuthenticated(AuthData authData) {
		    	Toast.makeText(getApplicationContext(), "Log-in Success!", Toast.LENGTH_LONG).show();
		    	//Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                //startActivity(mainIntent);

		    }
		    @Override
		    public void onAuthenticationError(FirebaseError firebaseError) {
		        // there was an error
		    	Toast.makeText(getApplicationContext(), firebaseError.getMessage(), Toast.LENGTH_LONG).show();
		    }
		});			**/

		mAuth.signInWithEmailAndPassword(email, password)
				.addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
					@Override
					public void onComplete(@NonNull Task<AuthResult> task) {
						//Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

						// If sign in fails, display a message to the user. If sign in succeeds
						// the auth state listener will be notified and logic to handle the
						// signed in user can be handled in the listener.
						if (!task.isSuccessful()) {
							//Log.w(TAG, "signInWithEmail:failed", task.getException());
							Toast.makeText(LoginActivity.this, "Log-in Failed!",
									Toast.LENGTH_SHORT).show();
						}

						// ...
					}
				});

	}
	
	
}
