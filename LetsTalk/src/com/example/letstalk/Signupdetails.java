package com.example.letstalk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Signupdetails extends Activity {
	Button proceed,edit;
	TextView tv6,tv7,tv8,tv9,tv10;
	String name;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_signupdetails);
	edit=(Button)findViewById(R.id.edit);
	proceed=(Button)findViewById(R.id.proceed);
	tv6=(TextView)findViewById(R.id.et6);
	tv7=(TextView)findViewById(R.id.et7);
	tv8=(TextView)findViewById(R.id.et8);
	tv9=(TextView)findViewById(R.id.et9);
	tv10=(TextView)findViewById(R.id.et10);
	Intent in =getIntent();
	name=in.getStringExtra("name");
	String college=in.getStringExtra("college");
	tv6.setText(name);
	tv7.setText(getIntent().getStringExtra("password"));
	tv8.setText(getIntent().getStringExtra("mobile"));
	tv9.setText(college);
	tv10.setText(getIntent().getStringExtra("sex"));
	edit.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			Intent in =new Intent(Signupdetails.this,Signup.class);
			startActivity(in);      
			
		}
	});
proceed.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			Intent in = new Intent(Signupdetails.this,Proceed.class);
			in.putExtra("Username",name);
			startActivity(in);     
			
		}
	});
	
	}
}
