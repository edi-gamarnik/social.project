package com.example.social.project;

import java.util.ArrayList;
import com.example.social.project.client.Client;
import com.example.social.project.global.GlobalMessage;
import com.example.social.project.message.MyMessage;

import android.app.Application;
import android.telephony.TelephonyManager;
import android.util.Log;

public class MyMessagesList extends Application 
{
	
	private static String my_name="";
	private ArrayList<MyMessage> mymessagelist;
	private ArrayList<GlobalMessage> globalmessage;
	private ArrayList<GlobalMessage> repliedlist;
	Object o1=new Object();
	Boolean isrunning=true;
	Client client;
	String userinfoid;
	@Override
	public void onCreate() 
	{
		super.onCreate();
		if(mymessagelist==null)
		{
			mymessagelist=new ArrayList<MyMessage>();
			globalmessage=new ArrayList<GlobalMessage>();
			repliedlist=new ArrayList<GlobalMessage>();
			client=new Client();
		}

	}
	public void setCurrentMessages(ArrayList<MyMessage> mymessagelist)
	{
		this.mymessagelist=mymessagelist;
	}
	public ArrayList<GlobalMessage> globalgetList() 
	{
		return globalmessage;
	}
	public void setCurrentglobalMessages(ArrayList<GlobalMessage> globalmessage)
	{
		this.globalmessage=globalmessage;
	}
	public ArrayList<MyMessage> getList()
	{
		return mymessagelist;
	}
	public void addMessage(MyMessage t)
	{
		assert(null != t);
		mymessagelist.add(t);
	}
	public void addGlobalMessage(GlobalMessage t)
	{
		assert(null != t);
		globalmessage.add(t);
	}
	public void clearMessages()
	{
	//	int size=mymessagelist.size();
	//	for(int i=0;i<size;i++)
	//	{
	//		mymessagelist.remove(0);
	//	}
		mymessagelist=new ArrayList<MyMessage>();
	}
	public void clearGlobalMessages()
	{
	//	int size=globalmessage.size();
	//	for(int i=0;i<size;i++)
	//	{
	//		globalmessage.remove(0);
	//	}
		globalmessage=new ArrayList<GlobalMessage>();
	}

	public void clear_replied_messages()
	{
		repliedlist=new ArrayList<GlobalMessage>();
	}
	public String getMyPhoneid()
	   {
	       return ((TelephonyManager) getSystemService(TELEPHONY_SERVICE))
	               .getDeviceId();
	   }
	public void setapprunning()
	{
		this.isrunning=true;
		
	}
	public void setnotapprunning()
	{
		this.isrunning=false;
		
	}
	public ArrayList<GlobalMessage> get_replay_list() 
	{

		return repliedlist;
	}

	public void setreplymessage(GlobalMessage msg)
	{
		if(repliedlist.isEmpty())
		{
			repliedlist.add(msg);
		}
		repliedlist.set(0,msg);	
	}
	public String getmymessagesize()
	{
		
		return ""+mymessagelist.size();
	}
	public String getglobalmessagesize()
	{
		
		return ""+globalmessage.size();
	}
	public void addreplaymassage(GlobalMessage msg) 
	{
		repliedlist.add(msg);
		
	}
	public Client getclient()
	{
		if(client==null)
		{
			client=new Client();
		}
		return this.client;
	}
	public void set_my_name(String name)
	{
		my_name=name;
		Log.d("changing my name to", name);
	}
	public String get_my_name()
	{
		return my_name;
	}
	public int get_replaylist_size()
	{
		return repliedlist.size();
	}
	public void set_replay_list(ArrayList<GlobalMessage> list)
	{
		this.repliedlist=list;
	}
	public String getuserinfoid() {
		
		return userinfoid;
	}

	
	
}
