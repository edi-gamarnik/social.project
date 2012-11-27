package com.example.social.project.adapter;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.social.project.R;
import com.example.social.project.R.layout;
import com.example.social.project.message.MyMessage;

import views.Messageview;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyMessage_adapter extends BaseAdapter
{
	private ArrayList<MyMessage> myMessages;
	private Context context;
	
	
	
	
	public MyMessage_adapter(ArrayList<MyMessage> myMessages, Context context) {
		super();
		this.myMessages = myMessages;
		this.context = context;
	}
	public int getCount() {
		
		return myMessages.size();
	}
	public MyMessage getItem(int position) {
		
		return myMessages.get(position);
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
		mv.setMessage(myMessages.get(position));
		
		return mv;
	}	
	public void reload()
	{
		notifyDataSetChanged();
	}
}
