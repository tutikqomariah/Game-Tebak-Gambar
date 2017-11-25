package com.game.tebaklogo;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;

public class Splash2 extends Activity {
	private final int SPLASH_DISPLAY_LENGHT = 2000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash2);
		new Handler().postDelayed(new Runnable() {
	        @Override
	        public void run() {
	          Intent mainIntent = null;
	   
	          mainIntent = new Intent(Splash2.this,
	            MainActivity.class);
	   
	          Splash2.this.startActivity(mainIntent);
	          Splash2.this.finish();
	        }
	      }, SPLASH_DISPLAY_LENGHT);
	    }
	  
	}

