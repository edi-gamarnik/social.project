package com.example.social.project.global;

import java.util.ArrayList;

import com.example.social.project.MyMessagesList;
import com.example.social.project.R;
import com.example.social.project.R.id;
import com.example.social.project.R.layout;
import com.example.social.project.adapter.MyGlobalMessage_adapter;
import com.example.social.project.client.Client;
import com.example.social.project.ReplayMessage;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class GlobalMessagesBoard extends ListActivity 
{
	
		private MyGlobalMessage_adapter adapter;
		MyMessagesList app;
		Client client;
		
		
		public void onCreate(Bundle savedInstanceState) 
		{
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.globalmymessageboard);
	        app=(MyMessagesList) getApplication();
	        adapter=new MyGlobalMessage_adapter(app.globalgetList(), this);
	        setListAdapter(adapter);
	        setSelection(adapter.getCount()-1);
	        client=app.getclient();
	        
	    }
		protected void onListItemClick(ListView l, View v, int position, long id) 
    	{
    		super.onListItemClick(l, v, position, id);
    		Intent intent =new Intent(this,ReplayMessage.class);
    		ArrayList<String> list=client.get_replay_file(app.globalgetList().get(position).getmessageid(),app.globalgetList().get(position).getphoneid());
    		intent.putExtra("filename",app.globalgetList().get(position).getmessageid());
    		intent.putExtra("phoneid",app.globalgetList().get(position).getphoneid());
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

