package com.example.social.project;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

import android.os.AsyncTask;
import android.util.Log;

import com.example.social.project.adapter.Replay_Message_Adapter;
import com.example.social.project.client.Client;
import com.example.social.project.global.GlobalMessage;

public class Chat_room implements Runnable
{
	Socket socket;
	ReplayMessage replayMessage;
	ObjectInputStream  ois;
	ObjectOutputStream oos;
	Client client;
	InetAddress serverAddr;
	Object o1;
	Boolean running=true;
	String filename;
	String phoneid;
	String replaytophone;
	

	public Chat_room(ReplayMessage replayMessage,Object o1,String filename,String phoneid,String replaytophone) 
	{
		this.phoneid=phoneid;
		this.filename=filename;
		this.replayMessage=replayMessage;
		this.o1=o1;
		client=new Client();
	}

	public void run() 
	{
		try
		{
		serverAddr = InetAddress.getByName(client.get_host());
		socket = new Socket(serverAddr, client.get_port());
		oos=new ObjectOutputStream(socket.getOutputStream());
		oos.writeUTF("chat");
		oos.flush();
		ois=new ObjectInputStream(socket.getInputStream());
		String msg=ois.readUTF();
		oos.writeUTF(filename);
		oos.flush();
		oos.writeUTF(phoneid);
		Log.d("blabla", msg);
		Thread t=new Thread(new Reciver());
		t.setDaemon(true);
		t.start();
		}
		catch(Exception ex)
		{
			
		}
		
	}
	public void send_message(ArrayList<String>list)
	{
		try {
			oos.writeObject(list);
			oos.flush();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	class Reciver implements Runnable
	{
		public void run()
		{
			try
			{	
				while(socket.isConnected())
				{	
					ArrayList<String> msglist=(ArrayList<String>) ois.readObject();
					Log.d("mmmm", msglist.toString());
					replayMessage.app.addreplaymassage(new GlobalMessage(msglist.get(0), msglist.get(1), msglist.get(2), msglist.get(3), msglist.get(4)));
					replayMessage.runOnUiThread(new Runnable() {
						
						public void run() 
						{
							replayMessage.reload();
							
						}
					});
					//synchronized (o1) 
					//{
					//	o1.notify();
				//	}
					
				}
			}
			catch(Exception e)		{
				e.printStackTrace();
			}
		}
	}
	public void close_connetion()
	{
		try{
		oos.close();
		ois.close();
		}
		catch(Exception ex)
		{
			
		}
	}
	
	
}
