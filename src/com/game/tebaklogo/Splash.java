package com.game.tebaklogo;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;

import com.game.tebaklogo.R;

public class Splash extends Activity {

	private final int SPLASH_DISPLAY_LENGHT = 4000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
	    new Handler().postDelayed(new Runnable() {
	        @Override
	        public void run() {
	          Intent mainIntent = null;
	   
	          mainIntent = new Intent(Splash.this,
	            Splash2.class);
	   
	          Splash.this.startActivity(mainIntent);
	          Splash.this.finish();
	        }
	      }, SPLASH_DISPLAY_LENGHT);
	    }
	

	  
	}

