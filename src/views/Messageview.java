package views;

import com.example.social.project.global.GlobalMessage;
import com.example.social.project.message.MyMessage;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Messageview extends LinearLayout 
{
	
	MyMessage message;
	GlobalMessage globalmessage;
	TextView text;
	public Messageview(Context context, AttributeSet attrs) 
	{
		super(context, attrs);
		
	}
	public MyMessage getMessage() {
		return message;
	}
	public void setMessage(MyMessage message) 
	{
		this.message = message;
		this.text.setText(message.getMessage());
	}
	public void setglobalmessage(GlobalMessage globalmessage)
	{
		this.globalmessage = globalmessage;
		this.text.setText(globalmessage.getMessage());
	}
	public void setreplayMessage(GlobalMessage globalmessage)
	{
		this.globalmessage = globalmessage;
		this.text.setText(globalmessage.getMessage());
	}
	
	@Override
	public void onFinishInflate()
	{
		super.onFinishInflate();
		this.text =(TextView) findViewById(android.R.id.text2);
		
	}
	
}
