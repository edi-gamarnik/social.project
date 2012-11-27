package com.example.social.project.personalinfo;

import java.util.ArrayList;

import com.example.social.project.MyMessagesList;
import com.example.social.project.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PersonalInfo extends Activity 
{
	private MyMessagesList app;
	TextView name,age,gender,pounds,info;
	Button back_button;
	ArrayList<String> list;
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
        setContentView(R.layout.userinfo);
        app=(MyMessagesList) getApplication();
        list=new ArrayList<String>();
        getuserinfo();
	}

	private void getuserinfo() 
	{
		back_button=(Button) findViewById(R.id.back_button);
		name=(TextView) findViewById(R.id.per_name);
		age=(TextView) findViewById(R.id.per_age);
		gender=(TextView) findViewById(R.id.per_gender);
		pounds=(TextView) findViewById(R.id.per_pounds);
		info=(TextView) findViewById(R.id.per_info);
		list=app.getclient().get_user_info(app.getuserinfoid());
		name.setText("Name: "+list.get(0));
		age.setText("Age: "+list.get(1));
		gender.setText("Gender: "+list.get(2));
		pounds.setText(list.get(0)+" would like to lose: "+list.get(3)+" "+list.get(4));
		info.setText("about "+list.get(0)+": "+list.get(5));
		back_button.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) 
			{
				finish();
				
			}
		});
		
	}

}
