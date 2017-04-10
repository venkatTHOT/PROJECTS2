package com.example.letstalk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Signup extends Activity {
	Button loginBtn;
	EditText et1,et2,et3,et4;
	RadioButton rb;
	RadioGroup rg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		loginBtn=(Button)findViewById(R.id.loginBtn);
		et1=(EditText)findViewById(R.id.et1);
		et2=(EditText)findViewById(R.id.et2);
		et3=(EditText)findViewById(R.id.et3);
		et4=(EditText)findViewById(R.id.et4);
		rg=(RadioGroup)findViewById(R.id.rg);
		
		
		loginBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int id=rg.getCheckedRadioButtonId();
				rb=(RadioButton)findViewById(id);
				Intent in =new Intent(Signup.this,Signupdetails.class);
				
				in.putExtra("name", et1.getText().toString());
				in.putExtra("password",et2.getText().toString());
				in.putExtra("mobile",et3.getText().toString());
				in.putExtra("college",et4.getText().toString());
				in.putExtra("sex",rb.getText().toString());
				startActivity(in);
				
			}
		});
	}
}
