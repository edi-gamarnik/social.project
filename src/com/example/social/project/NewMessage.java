package com.example.social.project;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.xmlpull.v1.XmlPullParser;

import com.example.social.project.client.Client;
import com.example.social.project.message.MyMessage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class NewMessage extends MyActivity
{

	private Button send_button;
	private EditText edit_text;
	private Client client;
	private MyMessagesList app;
	
	 public void onCreate(Bundle savedInstanceState) 
	    {
		 
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.new_message);
	        initialize();
	    }
	 public void initialize()
	 {
		 app=(MyMessagesList) getApplication();
		 client=app.getclient();
		 send_button=(Button) findViewById(R.id.button1);
	     edit_text=(EditText) findViewById(R.id.editText1);
	        new Thread()
	        {
	        	public void run()
	        	{
	        		send_button.setOnClickListener(new OnClickListener() {
	    				
	    				public void onClick(View v) 
	    				{
	    					MyMessage message=new MyMessage(edit_text.getText().toString(),DateFormat.getDateTimeInstance().format(new Date()).toString(),"me",getMyMessageApplication().getmymessagesize().toString(),getMyMessageApplication().getMyPhoneid());
	    					Log.d("adding new message", "message text ="+edit_text.getText().toString()+" time ="+DateFormat.getDateTimeInstance().format(new Date()).toString()+" sender name: me mymessage size list"+getMyMessageApplication().getmymessagesize().toString()+"my phone id"+getMyMessageApplication().getMyPhoneid());
	    					send_button.setEnabled(false);
	    					ArrayList<String> list=new ArrayList<String>();
	    					list.add(app.getMyPhoneid());
	    					list.add(edit_text.getText().toString());
	    					getMyMessageApplication().addMessage(message);
	    					list.add(getMyMessageApplication().getmymessagesize());
	    					client.postMessage(list);
	    					Log.d("date sent to client on post new my message",list.toString() );
	    					finish();
	    					

	                 	
	    				}
	    			});
	        	}
	        }.start();
	        
	    }
	 }	 
	 

