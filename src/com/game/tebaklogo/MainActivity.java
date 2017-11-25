package com.game.tebaklogo;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	MediaPlayer audioBackground;
	MediaPlayer audio;
static TextView tvq;
	public static String m_Text = "";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvq=(TextView)findViewById(R.id.textView4);
        
        //suara
        audio = MediaPlayer.create(this, R.raw.bg_sound);
		audio.setLooping(true);
        audio.setVolume(1,1);
        audio.start();
        
        SQLiteDatabase db = openOrCreateDatabase("logo2",MODE_APPEND,null);
        db.execSQL("create table if not exists logo2(highscore text)");
		Cursor c=db.rawQuery("select * from logo2",null);
		int max=0;
		while(c.moveToNext())
		{
			if(max<Integer.parseInt(c.getString(c.getColumnIndex("highscore"))))
			{
				max=Integer.parseInt(c.getString(c.getColumnIndex("highscore")));
			}
		}
		tvq.setText("HIGHSCORE : "+max);
    }

    public void play(View view)
    {
		 audioBackground = MediaPlayer.create(this, R.raw.click);
			audioBackground.setLooping(false);
	        audioBackground.setVolume(1,1);
	        audioBackground.start();
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setTitle("MASUKAN NAMA");

    	
    	final EditText input = new EditText(this);
    	input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_NUMBER_VARIATION_NORMAL);
    	builder.setView(input);

    	
    	builder.setPositiveButton("Mulai", new DialogInterface.OnClickListener() { 
    	    @Override
    	    public void onClick(DialogInterface dialog, int which) {
    	        m_Text = input.getText().toString();
    	        if(m_Text.equals(""))
    	    	{
    	        	toast();
    	    	}
    	    	else
    	    	{
    	    		PlayGame.hints=3;
    	        	PlayGame.score=0;
    	        	PlayGame.nyawa=3;
    	        	PlayGame.i=0;
    	        	PlayGame.shuffleArray(PlayGame.images,PlayGame.names);
    	    	Intent openStartingPoint=new Intent("com.game.tebaklogo.PLAY1");
    			startActivity(openStartingPoint);
    			audio.stop();
    			audioBackground.stop();
    	    	}
    	    }
    	});
    	builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
    	    @Override
    	    public void onClick(DialogInterface dialog, int which) {
    	        dialog.cancel();
    	        
    	    }
    	});

    	builder.show();
    }
    public void exitgame(View view)
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("yakin mau keluar ?");
        
        alertDialogBuilder.setPositiveButton("ya", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface arg0, int arg1) {
        	   finish();
               Intent intent = new Intent(Intent.ACTION_MAIN);
               intent.addCategory(Intent.CATEGORY_HOME);
               intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               startActivity(intent);
               audio.stop();
           }
        });
        
        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
              dialog.cancel();
              
           }
        });
        
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    public void howtoplay(View view)
    {
    	Intent openStartingPoint=new Intent("com.game.tebaklogo.HOWTOPLAY");
		startActivity(openStartingPoint);
		audio.stop();
    }
    public void getinfo(View view)
    {
    	Intent openStartingPoint=new Intent("com.game.tebaklogo.INFO");
		startActivity(openStartingPoint);
    	setContentView(R.layout.info);
    	audio.stop();
    }
	public void onBackPressed() {
	    
	}
    public void toast()
    {
    	LinearLayout  layout=new LinearLayout(this);

        TextView  tv=new TextView(this);
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(40);
        tv.setGravity(Gravity.CENTER);
        tv.setText("MASUKAN NAMA !!");  
        layout.addView(tv);
        Toast toast=new Toast(this); 
        toast.setView(layout);
        toast.setDuration(Toast.LENGTH_SHORT); 
         toast.setGravity(Gravity.BOTTOM, 0, 50);
         toast.show();
    }
    
}
