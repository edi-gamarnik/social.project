package com.example.social.project;



public class Message 
{
	Boolean side;
	String message ;
	String dateAndTime ;
	
	public Message(Boolean side,String message,String dateAndTime)
	{
		this.side=side;
		this.message=message;
		this.dateAndTime=dateAndTime;
	}
	public String getMessage()
	{
		return this.message;
	}
	public String gettime()
	{
		return this.dateAndTime;
	}
	
	
}
