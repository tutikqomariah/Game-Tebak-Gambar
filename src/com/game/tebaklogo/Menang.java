package com.game.tebaklogo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Menang extends Activity {
TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wnning);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wnning);
		tv=(TextView)findViewById(R.id.kem);
		tv.setText("Score Akhir : "+PlayGame.score);
	}
	public void gohome(View view)
	{
		Intent openStartingPoint=new Intent(this , MainActivity.class);
		startActivity(openStartingPoint);
	}

}
