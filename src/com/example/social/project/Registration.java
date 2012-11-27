package com.example.social.project;

import java.lang.reflect.Array;
import java.util.ArrayList;

import com.example.social.project.client.Client;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Registration extends Activity
	{
		private EditText name,age,pounds,about;
		private RadioGroup gender;
		private RadioGroup punds_kg;
		RadioButton punds,kg;
		private Button register;
		RadioButton rbutton,rbutton2;
		AlertDialog alertDialog;
		ArrayList<String> list;
		Boolean isUpdateRequired;
		LinearLayout layout,layout2;
		private MyMessagesList app;
	    private final Handler uiHandler=new Handler();
	    @Override
	    public void onCreate(Bundle savedInstanceState) 
	    {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.registration);
	        app=(MyMessagesList) getApplication();
	        
	        register();
	    }
	    private void register()
	    {
	    	name=(EditText) findViewById(R.id.editText1);
	    	age=(EditText) findViewById(R.id.editText2);
	    	pounds=(EditText) findViewById(R.id.editText3);
	    	about=(EditText) findViewById(R.id.about);
	    	register=(Button) findViewById(R.id.button1);
	    	layout=(LinearLayout) findViewById(R.id.proglayout);
	    	layout2=(LinearLayout) findViewById(R.id.reglayout);
	    	gender=(RadioGroup) findViewById(R.id.radioSex);
	    	punds_kg=(RadioGroup) findViewById(R.id.punds_kg);
	    	kg=(RadioButton) findViewById(R.id.kg);
	    	punds=(RadioButton) findViewById(R.id.punds);
	    	kg.setId(1);
	    	punds.setId(2);
	    	rbutton=(RadioButton) findViewById(R.id.radioButton1);
	    	rbutton2=(RadioButton) findViewById(R.id.radioButton2);
	    	rbutton.setId(1);
	    	rbutton2.setId(2);
	    	alertDialog = new AlertDialog.Builder(Registration.this).create();
	    	register.setOnClickListener(new View.OnClickListener()
	    	{
				
				public void onClick(View v) 
				{
					
                	if(validate())
                	{
                		layout.setVisibility(View.VISIBLE);
                    	layout2.setVisibility(View.INVISIBLE);
					 try
				     {
				            new Thread()
				            {
				                public void run() 
				                {	
				                	isUpdateRequired=true;
				                	isUpdateRequired=init_req();	
				                    uiHandler.post( new Runnable()
				                    {
				                        public void run() 
				                        {
				                            if(isUpdateRequired)
				                            {
				                            }
				                            else
				                            {
				                                	//Intent intent =new Intent(Registration.this,MainActivity.class);
				                                	//startActivity(intent); 
				               					 	layout.setVisibility(View.GONE);
				                                finish();
				                            }
				                        }
				                    } );
				                }
				            
				                
				        }.start();
				        }catch (Exception e)
				        {
				        	
				        }
                	}
				}
			});
	    	
	    }
	    private Boolean validate()
	    {
	    	if(name.getText().toString().equals(""))
	    	{
	    		alertDialog.setTitle("Alert");
	    		alertDialog.setMessage("Name cannot be empty");
	    		 alertDialog.setButton("OK", new DialogInterface.OnClickListener() 
	    		 {

	    		      public void onClick(DialogInterface dialog, int which) 
	    		      {
	    		    	  alertDialog.cancel();
	    		      } });
	    		 alertDialog.show();
	    		 return false;
	    	}
	    	if(age.getText().toString().equals(""))
	    	{
	    		alertDialog.setTitle("Alert");
	    		alertDialog.setMessage("Age cannot be empty");
	    		 alertDialog.setButton("OK", new DialogInterface.OnClickListener() 
	    		 {

	    		      public void onClick(DialogInterface dialog, int which) 
	    		      {
	    		    	  alertDialog.cancel();
	    		      } });
	    		 alertDialog.show();
	    		 return false;
	    	}
	    	if(pounds.getText().toString().equals(""))
	    	{
	    		alertDialog.setTitle("Alert");
	    		alertDialog.setMessage("pounds cannot be empty");
	    		 alertDialog.setButton("OK", new DialogInterface.OnClickListener() 
	    		 {

	    		      public void onClick(DialogInterface dialog, int which) 
	    		      {
	    		    	  alertDialog.cancel();
	    		      } });
	    		 alertDialog.show();
	    		 return false;
	    	}
	    	if(about.getText().toString().equals(""))
	    	{
	    		alertDialog.setTitle("Alert");
	    		alertDialog.setMessage("about cannot be empty");
	    		 alertDialog.setButton("OK", new DialogInterface.OnClickListener() 
	    		 {

	    		      public void onClick(DialogInterface dialog, int which) 
	    		      {
	    		    	  alertDialog.cancel();
	    		      } });
	    		 alertDialog.show();
	    		 return false;
	    	}
	    	switch (gender.getCheckedRadioButtonId()) 
	    	{
			case -1:
				alertDialog.setTitle("Alert");
	    		alertDialog.setMessage("Please select a gender");
	    		 alertDialog.setButton("OK", new DialogInterface.OnClickListener() 
	    		 {

	    		      public void onClick(DialogInterface dialog, int which) 
	    		      {
	    		    	  alertDialog.cancel();
	    		      } });
	    		 alertDialog.show();
				return false;

			default:
				break;
			}
	    	switch (punds_kg.getCheckedRadioButtonId()) 
	    	{
			case -1:
				alertDialog.setTitle("Alert");
	    		alertDialog.setMessage("Please select a pouns or kg");
	    		 alertDialog.setButton("OK", new DialogInterface.OnClickListener() 
	    		 {

	    		      public void onClick(DialogInterface dialog, int which) 
	    		      {
	    		    	  alertDialog.cancel();
	    		      } });
	    		 alertDialog.show();
				return false;

			default:
				break;
			}
	    	return true;
	    }
	    private Boolean init_req()
	    {
	    	list=new ArrayList<String>();
	    	list.add(getMyPhoneid());
			list.add(name.getText().toString());
			list.add(age.getText().toString());
			switch (gender.getCheckedRadioButtonId()) 
			{
			case 1:list.add("male");
			break;
			case 2:list.add("female");
				break;

			default:
				break;
			}
			list.add(pounds.getText().toString());
			switch (punds_kg.getCheckedRadioButtonId()) 
			{
			case 1:list.add("Kg");
			break;
			case 2:list.add("Pounds");
				break;

			default:
				break;
			}
			list.add(about.getText().toString());
			if(app.getclient().register(list))
			{
				return false;
			}
			return true;
	    }
	    public String getMyPhoneid()
		   {
		       return ((TelephonyManager) getSystemService(TELEPHONY_SERVICE))
		               .getDeviceId();
		   }
	}

