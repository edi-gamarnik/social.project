package com.example.social.project.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

import android.util.Log;

public class Client
{
	
    private static final String host="social-project.no-ip.info";
    private static final int SERVERPORT = 5000;

    ObjectOutputStream out;
    ObjectOutputStream out2;
    ObjectInputStream in;
    Object replay=new Object();
    Object userinfo=new Object();
    String serverResponce="";
    ArrayList<String> replaylist=null;
    ArrayList<String> clientinfo;
    InetAddress serverAddr;
    Socket socket;
    
    
    public Client()
    {	
    }
    public String get_host()
    {
    	return host;
    }
    public int get_port()
    {
    	return SERVERPORT;
    }
    public int check_registration(String id){
        try
        {
        	serverAddr = InetAddress.getByName(host);
        	socket = new Socket(serverAddr, SERVERPORT);
        	out=new ObjectOutputStream(socket.getOutputStream());
        	out.writeUTF("check");
        	out.flush();
        	in=new ObjectInputStream(socket.getInputStream());
        	in.readUTF();
            out.writeUTF(id);
            out.flush();
            
            serverResponce=in.readUTF();  
            in.close();
            if(serverResponce.equals("true"))
            {
            	serverResponce="";
            	return 1;
            }
            
        }
        catch(Exception e)
        {
        	
        	e.printStackTrace();
        	return 2;
        }
        return 0;
    }
    public Boolean register(ArrayList<String> list)
    {
    	try
    	{
    		serverAddr = InetAddress.getByName(host);
    		socket = new Socket(serverAddr, SERVERPORT);
            out =new ObjectOutputStream(socket.getOutputStream());
            out.writeUTF("register");
            out.flush();
            in=new ObjectInputStream(socket.getInputStream());
            in.readUTF();
            out2=new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(list);
            out.flush();
            serverResponce=in.readUTF();
            in.close();
            out.close();
            if(serverResponce.equals("true"))
            {
            	serverResponce="";
            	return true;
            }
    	}
    	catch(IOException e)
    	{
    		e.printStackTrace();
    	}
    	return false;
    }
    public Boolean postMessage(ArrayList<String> list)
    {
    	final ArrayList<String> list2=list;
    	Log.d("list 2 is", list2.toString());
    	new Thread()
    	{
    		public void run()
    		{
    			try
    	    	{
    				serverAddr = InetAddress.getByName(host);
    	    		socket = new Socket(serverAddr, SERVERPORT);
    	            out =new ObjectOutputStream(socket.getOutputStream());
    	            out.writeUTF("post");
    	            out.flush();
    	            in=new ObjectInputStream(socket.getInputStream());
    	            in.readUTF();
    	            out2=new ObjectOutputStream(socket.getOutputStream());
    	            out.writeUTF(list2.get(0));
    	            out.flush();

    	            out.writeUTF(list2.get(1));
    	            out.flush();

    	            out.writeUTF(list2.get(2));
    	            out.flush();

    	            serverResponce=in.readUTF();
    	            in.close();
    	            out.close();
    	            if(serverResponce.equals("true"))
    	            {
    	            	serverResponce="";
    	            	
    	            }
    	    	}
    	    	catch(IOException e)
    	    	{
    	    		e.printStackTrace();
    	    	}
    		}
    	}.start();
    	
    	return false;
    }
	public ArrayList<String> get_my_m_file(String id) 
	{
		ArrayList<String> list=null;
		try
    	{
			serverAddr = InetAddress.getByName(host);
			socket = new Socket(serverAddr, SERVERPORT);
            out =new ObjectOutputStream(socket.getOutputStream());
            out.writeUTF("getmymessages");
            out.flush();
            in=new ObjectInputStream(socket.getInputStream());
            in.readUTF();
            out2=new ObjectOutputStream(socket.getOutputStream());
            out.writeUTF(id);
            out.flush();
            list=(ArrayList<String>) in.readObject();
            in.close();
            out.close();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
		if(list!=null)
		{

		}
		return list;
	}
	public ArrayList<String> get_my_global_file(String id) 
	{
		
		ArrayList<String> list=null;
		try
    	{
			InetAddress serverAddr = InetAddress.getByName(host);
            socket = new Socket(serverAddr, SERVERPORT);
            out =new ObjectOutputStream(socket.getOutputStream());
            out.writeUTF("getglobal");
            out.flush();
            in=new ObjectInputStream(socket.getInputStream());
            in.readUTF();
            out2=new ObjectOutputStream(socket.getOutputStream());
            out.writeUTF(id);
            out.flush();
            list=(ArrayList<String>) in.readObject();
            in.close();
            out.close();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
		if(list!=null)
		{

		}
		return list;
	}
	/*
	public void replay(final String message, final String phoneid,final String fileid,final String myphoneid,final String messageid,final String time)
	{
		
		new Thread()
		{
			public void run()
			{
		
	    			try
	    	    	{
	    				serverAddr = InetAddress.getByName(host);
	    	    		socket = new Socket(serverAddr, SERVERPORT);
	    	            out =new ObjectOutputStream(socket.getOutputStream());
	    	            out.writeUTF("replay");
	    	            out.flush();
	    	            in=new ObjectInputStream(socket.getInputStream());
	    	            in.readUTF();
	    	            out2=new ObjectOutputStream(socket.getOutputStream());
	    	            out.writeUTF(message);

	    	            out.flush();
	    	            out.writeUTF(phoneid);

	    	            out.flush();
	    	            out.writeUTF(fileid);

	    	            out.flush();
	    	            out.writeUTF(myphoneid);
	    	            out.flush();
	    	            out.writeUTF(messageid);
	    	            out.flush();
	    	            out.writeUTF(time);
	    	            out.flush();
	    	            serverResponce=in.readUTF();
	    	            in.close();
	    	            out.close();
	    	            if(serverResponce.equals("true"))
	    	            {
	    	            	serverResponce="";
	    	            	
	    	            }
	    	    	}
	    	    	catch(IOException e)
	    	    	{
	    	    		e.printStackTrace();

	    	    	}
	    			finally
	    			{
	    				try {
							in.close();
							out.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

	    			}
	    		}
			
		}.start();
	    
	
	}
*/
	public ArrayList<String> get_replay_file(final String getmessageid, final String phoneid) 
	{

		new Thread()
		{
			
			public void run()
			{
				ArrayList<String> list=null;
				try
		    	{
					serverAddr = InetAddress.getByName(host);
					socket = new Socket(serverAddr, SERVERPORT);
		            out =new ObjectOutputStream(socket.getOutputStream());
		            out.writeUTF("getreplay");
		            out.flush();
		            in=new ObjectInputStream(socket.getInputStream());
		            in.readUTF();
		            out2=new ObjectOutputStream(socket.getOutputStream());
		            out.writeUTF(getmessageid);
		            out.flush();
		            out.writeUTF(phoneid);
		            out.flush();
		            list=(ArrayList<String>) in.readObject();
		            in.close();
		            out.close();
		            replaylist=list;
		            synchronized (replay) 
		            {
						replay.notify();
					}
		    	}
		    	catch(Exception e)
		    	{
		    		e.printStackTrace();
		    	}
				if(list!=null)
				{

				}
			}
		}.start();
		if(replaylist==null)
		{
			try {
				synchronized (replay) 
				{
				replay.wait();
				}
			} catch (InterruptedException e)
			{

				e.printStackTrace();
			}
		}
		else
		{
			try {
				synchronized (replay) 
				{
				replay.wait();
				}
			} catch (InterruptedException e)
			{

				e.printStackTrace();
			}
			Log.d("the list get my replay file  returns",replaylist.toString() );
			return replaylist;
		}
		return replaylist;
		
	}

	public ArrayList<String> get_user_info(final String myPhoneid) 
	{

		new Thread("get_user_info")
		{
			public void run()
			{
				try
				{
					synchronized (userinfo) 
					{
						serverAddr = InetAddress.getByName(host);
						socket = new Socket(serverAddr, SERVERPORT);
						out =new ObjectOutputStream(socket.getOutputStream());
						out.writeUTF("getuserinfo");
						out.flush();
						in=new ObjectInputStream(socket.getInputStream());
						in.readUTF();
			            out2=new ObjectOutputStream(socket.getOutputStream());
							out.writeUTF(myPhoneid);
					out.flush();
					clientinfo=(ArrayList<String>) in.readObject();
					
						userinfo.notify();
					
						}
					
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
					
				}
			}.start();
			synchronized (userinfo) 
			{
				try
				{
				userinfo.wait();
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
					return clientinfo;
		}
	
		
		
	}