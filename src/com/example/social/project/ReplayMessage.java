package com.example.social.project;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import com.example.social.project.adapter.Replay_Message_Adapter;
import com.example.social.project.client.Client;
import com.example.social.project.global.GlobalMessage;
import com.example.social.project.personalinfo.PersonalInfo;
import com.example.social.project.Chat_room;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class ReplayMessage extends ListActivity
{
	private Replay_Message_Adapter adapter;
	MyMessagesList app;
	String messageid;
	Button replay;
	EditText replaytext;
	Client client;
	ListView listview;
	Boolean running=true;
	Object o1;
    InetAddress serverAddr;
    Socket socket;
    ObjectOutputStream out;
    ObjectInputStream in;
    String filename="";
    String chatroomid;
   public  Chat_room room;
   Thread thread;
    

	
	public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singlemessage);
        o1=new Object();
        app=(MyMessagesList) getApplication();
        adapter=new Replay_Message_Adapter(app.get_replay_list(), this);
        setListAdapter(adapter);
        replay=(Button) findViewById(R.id.replay_button);
        replaytext=(EditText) findViewById(R.id.replay_text);
        setSelection(adapter.getCount()-1);
        client=app.getclient();
        initialize_chatroom();
        replay.setOnClickListener(new View.OnClickListener()
        
        {
			
			public void onClick(View v) 
			{
				replay.setEnabled(false);
				app.addreplaymassage(new GlobalMessage(app.get_replay_list().size()+"",app.get_my_name(),replaytext.getText().toString(),DateFormat.getDateTimeInstance().format(new Date()).toString(),app.getMyPhoneid()));
				ArrayList<String> list=new ArrayList<String>();
				list.add((app.get_replay_list().size())+""); //messageid
				list.add(app.get_my_name());					//myname
				list.add(replaytext.getText().toString());		//message
				list.add(app.get_replay_list().get(app.get_replay_list().size()-1).gettime()); //time
				list.add(app.getMyPhoneid());	//phoneid
				list.add(chatroomid);//replay tophone id
				room.send_message(list);
				//Log.d("adding new message to replay list from replay page "," replay list size "+app.globalgetList().size()+" get may name "+app.get_my_name()+" the text message "+replaytext.getText().toString()+" the time:"+DateFormat.getDateTimeInstance().format(new Date()).toString()+" phone id "+app.getMyPhoneid());
				//client.replay(replaytext.getText().toString(),app.get_replay_list().get(0).getphoneid(),app.get_replay_list().get(0).getmessageid(),app.getMyPhoneid(),app.get_replay_list().size()+"",DateFormat.getDateTimeInstance().format(new Date()));
				//Log.d("sending data to client  from replay page","text message "+replaytext.getText().toString()+" get phone id "+app.get_replay_list().get(0).getphoneid()+" messageid "+app.get_replay_list().get(0).getmessageid()+" my phone id "+app.getMyPhoneid()+" replay list size "+app.get_replay_list().size()+"");
				//synchronized (o1) 
			//	{
			//		try {
					//	o1.wait(1000);
			//		} catch (InterruptedException e) {
			//		// TODO Auto-generated catch block
			//			e.printStackTrace();
			//		}
					reload();
			//	}
				
				replay.setEnabled(true);
				replaytext.setText("");
				
			}
		});
        

    }
	private void initialize_chatroom() 
	{
	Intent intent = getIntent();
	filename = intent.getStringExtra("filename");
	chatroomid= intent.getStringExtra("phoneid");
	room =new Chat_room(this,o1,filename,app.getMyPhoneid(),chatroomid);
	thread=new Thread(room);
	thread.start();
	}
	protected void onListItemClick(ListView l, View v, int position, long id) 
	{
		super.onListItemClick(l, v, position, id);
		Intent intent =new Intent(this,PersonalInfo.class);
		app.userinfoid=app.get_replay_list().get(position).getphoneid();
		startActivity(intent);
	}
	@Override
	protected void onDestroy() 
	{
		synchronized (o1) 
		{
			o1.notify();
		}
		super.onDestroy();
		running=false;
		room.close_connetion();
	}
	public void reload()
	{
		adapter.reload();
		setSelection(adapter.getCount()-1);
	}
	
}
