package com.example.letstalk;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {
	private EditText username;
	private EditText password;
	private Button login,signup;
	private TextView loginLockedTV;
	private TextView attemptsLeftTV;
	private TextView numberOfRemainingLoginAttemptsTV;
	int numberOfRemainingLoginAttempts = 3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		setupVariables();
		signup.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intr = new Intent(Login.this,Signup.class);
				startActivity(intr);
			}
		});
		login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (username.getText().toString().equals("admin") && 
						password.getText().toString().equals("admin")) {
					
					Intent in = new Intent(Login.this,Proceed.class);
					in.putExtra("Username",username.getText().toString());
					startActivity(in);
				} else {
					Toast.makeText(getApplicationContext(), "Seems like you 're not admin!", 
							Toast.LENGTH_SHORT).show();
					numberOfRemainingLoginAttempts--;
					attemptsLeftTV.setVisibility(View.VISIBLE);
					numberOfRemainingLoginAttemptsTV.setVisibility(View.VISIBLE);
					numberOfRemainingLoginAttemptsTV.setText(Integer.toString(numberOfRemainingLoginAttempts));
					
					if (numberOfRemainingLoginAttempts == 0) {
						login.setEnabled(false);
						loginLockedTV.setVisibility(View.VISIBLE);
						loginLockedTV.setBackgroundColor(Color.RED);
						loginLockedTV.setText("LOGIN LOCKED!!!");
						
					}
				}
				
			}
		});
	}
	


	public void setupVariables() {
		username = (EditText) findViewById(R.id.editText1);
		password = (EditText) findViewById(R.id.editText2);
		login = (Button) findViewById(R.id.loginBtn);
		signup = (Button) findViewById(R.id.signup);
		loginLockedTV = (TextView) findViewById(R.id.loginLockedTV);
		attemptsLeftTV = (TextView) findViewById(R.id.attemptsLeftTV);
		numberOfRemainingLoginAttemptsTV = (TextView) findViewById(R.id.numberOfRemainingLoginAttemptsTV);
		numberOfRemainingLoginAttemptsTV.setText(Integer.toString(numberOfRemainingLoginAttempts));
	}

}
