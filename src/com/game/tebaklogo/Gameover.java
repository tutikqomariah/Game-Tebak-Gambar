package com.game.tebaklogo;


import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Gameover extends Activity {
	MediaPlayer audioBackground;
	TextView tv,tvv1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gameover);
		tv=(TextView)findViewById(R.id.textView2);
		tvv1=(TextView)findViewById(R.id.nyawa);
		tv.setText("Score Akhir : "+PlayGame.score);
		tvv1.setText("player : "+MainActivity.m_Text);
		
		audioBackground = MediaPlayer.create(this, R.raw.gameover);
        //Set looping ke true untuk mengulang audio jika telah selesai
		audioBackground.setLooping(false);
		//Set volume audio agar berbunyi
        audioBackground.setVolume(1,1);
        //Memulai audio
        audioBackground.start();
		
	}
	public void onBackPressed() {
	    // TODO Auto-generated method stub
	   
	}
	public void playagain(View view)
	{
		Intent openStartingPoint=new Intent(this , MainActivity.class);
		startActivity(openStartingPoint);
		finish();
		
	}

}
