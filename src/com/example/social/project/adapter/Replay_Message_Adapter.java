package com.example.social.project.adapter;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.social.project.R;
import com.example.social.project.R.layout;
import com.example.social.project.global.GlobalMessage;
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

public class Replay_Message_Adapter extends BaseAdapter
{
	private ArrayList<GlobalMessage> replayMessages;
	private Context context;
	public Replay_Message_Adapter(ArrayList<GlobalMessage> arrayList, Context context) {
		super();
		this.replayMessages = arrayList;
		this.context = context;
	}
	public int getCount() {
		
		return replayMessages.size();
	}
	public GlobalMessage getItem(int position) {
		
		return replayMessages.get(position);
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
		mv.setreplayMessage(replayMessages.get(position));
		
		return mv;
	}	
	public void reload()
	{
		notifyDataSetChanged();
	}
}
