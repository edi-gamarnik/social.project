package com.example.social.project;

import java.util.ArrayList;
import com.example.social.project.adapter.MyMessage_adapter;
import com.example.social.project.client.Client;
import com.example.social.project.global.GlobalMessage;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class MyMessage_board extends ListActivity 
{
	private MyMessage_adapter adapter;
	private Button send_button;
	private MyMessagesList app;
	private Client client;
	
	
	public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mymessageboard);
        app=(MyMessagesList) getApplication();
        client=app.getclient();
        adapter=new MyMessage_adapter(app.getList(), this);
        send_button=(Button) findViewById(R.id.send_button);
        setListAdapter(adapter);
        setSelection(adapter.getCount()-1);
        send_button.setOnClickListener(new OnClickListener() 
        {
			
			public void onClick(View v) 
			{
				Intent intent =new Intent(MyMessage_board.this,NewMessage.class);
            	startActivity(intent); 
			}
		});
        
        		
          
    }
	protected void onListItemClick(ListView l, View v, int position, long id) 
	{
		super.onListItemClick(l, v, position, id);
		Intent intent =new Intent(this,ReplayMessage.class);
		ArrayList<String> list=client.get_replay_file(app.getList().get(position).getmessageid(),app.getList().get(position).getphoneid());
		intent.putExtra("filename",app.getList().get(position).getmessageid());
		intent.putExtra("phoneid",app.getList().get(position).getphoneid());

		app.clear_replied_messages();
		for(int i=0;i<list.size();i=i+5)
	 		{
	 			app.addreplaymassage(new GlobalMessage(list.get(i),list.get(i+1),list.get(i+2),list.get(i+3),list.get(i+4)));
	 		}
		startActivity(intent);
	}


	@Override
	protected void onResume() 
	{
		super.onResume();
		adapter.reload();
		
	}
	
}

