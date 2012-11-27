package com.example.social.project.global;

import android.util.Log;

import com.example.social.project.MyActivity;
import com.example.social.project.MyMessagesList;

public class GlobalMessage
{
	private String message;
	private String dateAndTime;
	private String sender;
	private String messageid;
	private String phoneid;
	private MyMessagesList mml ;
	public GlobalMessage(String messageid,String sender,String message,String dateAndTime,String phoneid)
	{
		mml=new MyMessagesList();
		this.phoneid=phoneid;
		this.messageid=messageid;
		this.message=message;
		this.dateAndTime=dateAndTime;
		if(sender==null | sender.equals(mml.get_my_name()))
		{
			this.sender="me";
		}
		else
		{
			this.sender=sender;
		}
		

	}
	public String getMessage()
	{
		return sender+": "+message;
	}
	public String gettime()
	{
		return this.dateAndTime;
	}
	public String getsender()
	{
		return this.sender;
	}
	public String getphoneid()
	{
		return this.phoneid;
	}
	public String getmessageid() 
	{
		return this.messageid;
		
	}
	
}
