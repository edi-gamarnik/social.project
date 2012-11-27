package com.example.social.project;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.example.social.project.client.Client;
import com.example.social.project.global.GlobalMessage;
import com.example.social.project.global.GlobalMessagesBoard;
import com.example.social.project.message.MyMessage;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SyncResult;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends MyActivity
{
	private Client client;
	private ImageView img_button ;
	private TextView textmsg;
	private LinearLayout brogressbar;
	private LinearLayout textbutton;
	private Button newmessagebutton;
    private final Handler uiHandler=new Handler();
    private boolean isUpdateRequired=false;
    private int registered;
    AlertDialog alertDialog;
    private Intent intent=null;
    static Object o1=new Object();
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstview);
       // initialize();
    } 
	   public String getMyPhoneNumber()
	   {
	       return ((TelephonyManager) getSystemService(TELEPHONY_SERVICE))
	               .getLine1Number();
	   }
	   public String getMyPhoneid()
	   {
	       return ((TelephonyManager) getSystemService(TELEPHONY_SERVICE))
	               .getDeviceId();
	   }
    
	   private void initialize()
	   {
		client=getMyMessageApplication().getclient();
    	img_button = (ImageView)findViewById(R.id.imageView1);
    	brogressbar=(LinearLayout) findViewById(R.id.linearLayout1);
    	textbutton=(LinearLayout) findViewById(R.id.linearLayout2);
    	textmsg=(TextView) findViewById(R.id.wellcom_message);
    	newmessagebutton=(Button) findViewById(R.id.newmesssages);
    	alertDialog = new AlertDialog.Builder(MainActivity.this).create();
    	textbutton.setVisibility(View.INVISIBLE);
    	newmessagebutton.setVisibility(View.INVISIBLE);
    	brogressbar.setVisibility(View.VISIBLE);
    	if(registered!=1)
    	{
    		try
   	     {
   	            new Thread()
   	            {
   	                public void run() 
   	                {
   	                	isUpdateRequired=true;
   	                	checkRegistration();
   	                    uiHandler.post( new Runnable()
   	                    {
   	                        public void run() 
   	                        {
   	                            if(isUpdateRequired)
   	                            {
   	                            }
   	                            else
   	                            {
   	                               if(registered==1)
   	                                {	
   	                            	getMyMessageApplication().setapprunning();
   	                            	synchronized (getMyMessageApplication().o1) 
   	                            	{
   	                            		getMyMessageApplication().o1.notify();
									}
   	                            
   	                            	   Thread thread=new Thread(new Runnable() {
										
										public void run() 
										{
	   	                            	    ArrayList<String>list=client.get_my_m_file(getMyPhoneid());
	   	                            	    
	   	                            	    if(list!=null)
	   	                            	    {
	   	                            	    	Log.d("list reacived from get my file", list.toString());
	   	                            	    		for(int i=0;i<list.size();i=i+5)
	   	                            	    		{
	   	                            	    			try{
	   	                            	    		getMyMessageApplication().addMessage(new MyMessage(list.get(i),list.get(i+1),list.get(i+2),list.get(i+3),list.get(i+4)));
	   	                            	    			}
	   	                            	    			catch(Exception ex)
	   	                            	    			{
	   	                            	    				ex.printStackTrace();
	   	                            	    			}
	   	                            	    		}
	   	                            	    		if(getMyMessageApplication().getList().size()==0)
	   	                            	    		{
	   	                            	    			
	   	                            	    		}
	   	                            	    		else
	   	                            	    		{
	   	                            	    			Log.d("setting name",getMyMessageApplication().getList().get(0).getsender() );
	   	                            	    		getMyMessageApplication().set_my_name(getMyMessageApplication().getList().get(0).getsender());
	   	                            	    		
	   	                            	    		}
	   	                            	    }
	   	                            	    ArrayList<String> globallist=client.get_my_global_file(getMyPhoneid());
	   	                            	 if(globallist!=null)
		                            	    {
	   	                            		 		for(int i=0;i<globallist.size();i=i+5)
	   	                            		 		{
	   	                            		 			getMyMessageApplication().addGlobalMessage(new GlobalMessage(globallist.get(i),globallist.get(i+1),globallist.get(i+2),globallist.get(i+3),globallist.get(i+4)));
	   	                            		 		}
		                            	    }
	   	                            	 synchronized (MainActivity.o1) 
	   	                            	 {
	   	                            		o1.notify();
										}
											
										}
									});
   	                            	thread.start();
   	                            	synchronized(o1)
   	                         	{
   	                            		try {
											o1.wait();
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
   	                         	}	    
   	                            	 
   	                                	intent =new Intent(MainActivity.this,MyMessage_board.class);
   	                                	brogressbar.setVisibility(View.INVISIBLE);
   	                                	textbutton.setVisibility(View.VISIBLE);
   	                                	newmessagebutton.setVisibility(View.VISIBLE);
   	                                	
   	                                }
   	                                else if(registered==0)
   	                                {
   	                                	intent =new Intent(MainActivity.this,Registration.class);
   	                                	brogressbar.setVisibility(View.INVISIBLE);
   	                                	textbutton.setVisibility(View.INVISIBLE);
   	                                	newmessagebutton.setVisibility(View.INVISIBLE);
   	                                	startActivity(intent);
   	               					 	
   	                                }
   	                                else
   	                                {
   	                                	brogressbar.setVisibility(View.INVISIBLE);
   	                                	img_button.setVisibility(View.INVISIBLE);
   	                                	textmsg.setVisibility(View.INVISIBLE);
   	                                	newmessagebutton.setVisibility(View.INVISIBLE);
   	                                	alertDialog.setTitle("Alert");
   	            					 	alertDialog.setMessage("Server is down at the moment Please try again later!");
   	            					 	alertDialog.setButton("OK", new DialogInterface.OnClickListener() 
   	            					 	{

   	            		    		      public void onClick(DialogInterface dialog, int which) 
   	            		    		      {
   	            		    		    	  alertDialog.cancel();
   	            		    		    	  finish();
   	            		    		      } });
   	            					 	
   	            					 	alertDialog.show();
   	                                }
   	                                
   	                            }
   	                        }
   	                    } );
   	                }
   	                
   	        }.start();
   	        }catch (Exception e)
   	        {
   	        	
   	        }
    		
    	}
    	else
    	{
    		textbutton.setVisibility(View.VISIBLE);
    		newmessagebutton.setVisibility(View.VISIBLE);
        	brogressbar.setVisibility(View.INVISIBLE);
    	}
		newmessagebutton.setOnClickListener(new View.OnClickListener() 
		{
			
			public void onClick(View v) 
			{
				Intent intent2=new Intent(MainActivity.this,GlobalMessagesBoard.class);
				startActivity(intent2);
				
			}
		});
    	img_button.setOnClickListener(new View.OnClickListener() 
    	{	
			public void onClick(View v) 
			{
				startActivity(intent);

			}
		});
    }
    private void checkRegistration()
    {
    	String name;
    	registered = client.check_registration(getMyPhoneid());
    	isUpdateRequired=false;
    }
	@Override
	protected void onResume() 
	{
		super.onResume();
		initialize();
	}
	@Override
	protected void onDestroy() 
	{
		super.onDestroy();
		getMyMessageApplication().clearMessages();
		getMyMessageApplication().setnotapprunning();
		synchronized (getMyMessageApplication().o1) 
       	{
       		getMyMessageApplication().o1.notify();
		}
		
		
	}
    
    
}
