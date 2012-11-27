package com.example.social.project.adapter;

import java.util.ArrayList;

import views.Messageview;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.social.project.R;
import com.example.social.project.global.GlobalMessage;
import com.example.social.project.message.MyMessage;

public class MyGlobalMessage_adapter extends BaseAdapter
{
	private ArrayList<GlobalMessage> globalMessages;
	private Context context;
	
	
	
	
	public MyGlobalMessage_adapter(ArrayList<GlobalMessage> globalMessages, Context context) {
		super();
		this.globalMessages = globalMessages;
		this.context = context;
	}
	public int getCount() {
		
		return globalMessages.size();
	}
	public GlobalMessage getItem(int position) {
		
		return globalMessages.get(position);
	}
	public long getItemId(int position) {
		
		return position;
	}
	public View getView(int position, View convertView, ViewGroup parent) {
		Messageview mv;
		if(convertView==null)
		{
			mv=(Messageview) View.inflate(context, R.layout.messageview, null);
		}
		else
		{
			mv=(Messageview) convertView;
		}
		mv.setglobalmessage(globalMessages.get(position));
		
		return mv;
	}	
	public void reload()
	{
		notifyDataSetChanged();
	}
}
