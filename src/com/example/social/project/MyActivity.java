package com.example.social.project;

import android.app.Activity;



public class MyActivity extends Activity {

	public MyMessagesList getMyMessageApplication() {
		MyMessagesList mml = (MyMessagesList)getApplication();
		return mml;
	}

}
