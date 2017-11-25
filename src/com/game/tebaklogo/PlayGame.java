package com.game.tebaklogo;

import java.util.Random;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class PlayGame extends Activity {

		public static int i=0;
		public static int nyawa=3;
		public static int hs=0;
		public static int score=0;
		public static int levels=0;
		public static int hints=3;
		public ImageView imgview;
		private SQLiteDatabase db;
		public EditText edittext;
		public TextView tvv,hr,tvv1,level,kesempatan;
		ContentValues contentv=new ContentValues();
		MediaPlayer audioBackground;
		
	
		 public static int[] images = {
				R.drawable.camel,R.drawable.cat,R.drawable.chiken,R.drawable.frog,R.drawable.komodo,
				R.drawable.lion,R.drawable.panda,R.drawable.rabits,R.drawable.scorpion,R.drawable.swan,R.drawable.zebra,
		};
		public static String[] names={"camel","cat","chiken","frog","komodo","lion","panda","rabits","scorpion","swan","zebra"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.playquiz);

		tvv=(TextView)findViewById(R.id.textView4);
		level=(TextView)findViewById(R.id.textlevel);
		tvv1=(TextView)findViewById(R.id.textView10);
		tvv.setText("score : "+score+"");
		tvv1.setText("player : "+MainActivity.m_Text);
		//nyawa
		kesempatan = (TextView)findViewById(R.id.nyawa);
		kesempatan.setText("nyawa : " +nyawa);
		
		levels=(i/10)+1;
		if(i!=0)
		{
			if(i%10==0)
			{
				hints++;
				nyawa++;
			}
		}
		level.setText("Level "+levels);
		hr=(TextView)findViewById(R.id.hintremaining);
		hr.setText("tersisa : "+hints+" bantuan");
		
		//Array(gambar);
		imgview=(ImageView)findViewById(R.id.imageView1);
		imgview.setImageResource(images[i]);
		
		db=openOrCreateDatabase("logo2",MODE_APPEND,null);
		
		edittext=(EditText)findViewById(R.id.guesslogo);
		
		edittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(hasFocus){
		            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE); 
		            imm.hideSoftInputFromWindow(edittext.getWindowToken(), 0);
		            }
			}
		});
	}
	

	
	
		 public void onBackPressed() {
   
			}

		 public void hintplease(View view)
		 {
			 if(hints>0)
			 {
		
			 String p =names[i];
			 toast(p.toUpperCase());
			 hints--;
			 hr.setText("tersisa : "+hints+ " bantuan");
			 
			 audioBackground = MediaPlayer.create(this, R.raw.hint);
				audioBackground.setLooping(false);
		        audioBackground.setVolume(1,1);
		        audioBackground.start();
		        
			 }
			 else
			 {
				 toast("BANTUAN SUDAH HABIS");
				 audioBackground = MediaPlayer.create(this, R.raw.habis);
					audioBackground.setLooping(false);
			        audioBackground.setVolume(1,1);
			        audioBackground.start();
			 }
		 }
		
		 
		 public void getout(View view)
		 {
			 contentv.put("highscore", (score+""));
			 db.insert("logo2", null, contentv);	
			 Intent openStartingPoint=new Intent(this , MainActivity.class);
				startActivity(openStartingPoint);
		 }
		 public void nextpage(View view)
		 {
			 
			 String s=(((edittext.getText().toString()).toUpperCase()).trim()).replaceAll("\\s", "");
			 if(s.equals((names[i]).toUpperCase()))
			 {
				 
				 i++;
				 score++;
		
				 if(i%10==0)
				 {
				 if(i!=0)
				 {
					 
					 toast("SELAMAT LEVEL "+levels+" SELESAI");
				 }
				 else
				 {
					 
					 toasbenar("BENAR"); 
					 audioBackground = MediaPlayer.create(this, R.raw.benar);
						audioBackground.setLooping(true);
				        audioBackground.setVolume(1,1);
				        audioBackground.start();
				        audioBackground.stop();
				        
				 }
				 }
				 else
				 {
					 toasbenar("BENAR");
	
					 audioBackground = MediaPlayer.create(this, R.raw.benar);
						audioBackground.setLooping(false);
				        audioBackground.setVolume(1,1);
				        audioBackground.start();
				       
				 }
				 
				 
				 if(i <100)
				 {
				 Intent openStartingPoint=new Intent("com.game.tebaklogo.PLAY1");
					startActivity(openStartingPoint);
				 }
				 else
				 {
					 Intent openStartingPoint=new Intent("com.game.tebaklogo.WINNING");
						startActivity(openStartingPoint);
				 }
				 
			 }
			 else if(s.length()==0)
			 {
				 nyawa--;
				 if(nyawa==0){
					 contentv.put("highscore", (score+""));
					 db.insert("logo2", null, contentv);
					 Intent openStartingPoint=new Intent("com.game.tebaklogo.GAMEOVER");
						startActivity(openStartingPoint); 
				 }
				 	kesempatan.setText("tersisa : "+nyawa);
				 	toasmasukanjawaban("MASUKAN JAWABAN !!!");
				 	audioBackground = MediaPlayer.create(this, R.raw.salah);
			        //Set looping ke true untuk mengulang audio jika telah selesai
					audioBackground.setLooping(false);
					//Set volume audio agar berbunyi
			        audioBackground.setVolume(1,1);
			        //Memulai audio
			        audioBackground.start();
			        
			 }
			 else
			 {
				 nyawa--;
				 if(nyawa==0){
					 contentv.put("highscore", (score+""));
					 db.insert("logo2", null, contentv);
					 Intent openStartingPoint=new Intent("com.game.tebaklogo.GAMEOVER");
						startActivity(openStartingPoint); 
				 }
				 kesempatan.setText("tersisa : "+nyawa);
				 toasalah("SALAH");
				 audioBackground = MediaPlayer.create(this, R.raw.salah);
			        //Set looping ke true untuk mengulang audio jika telah selesai
					audioBackground.setLooping(false);
					//Set volume audio agar berbunyi
			        audioBackground.setVolume(1,1);
			        //Memulai audio
			        audioBackground.start();
			        
			 }
			
		 }
		//mengacak data array
		 public static void shuffleArray(int[] ar,String[] name)
		  {
		    Random rnd = new Random();
		    for (int i = ar.length - 1; i > 0; i--)
		    {
		      int index = rnd.nextInt(i + 1);
		      // Simple swap
		      int a = ar[index];
		      String b = name[index];
		      ar[index] = ar[i];
		      name[index] = name[i];
		      ar[i] = a;
		      name[i] = b;
		    }
		    
	}
		 public void toast(String s)
		    {
		    	LinearLayout  layout=new LinearLayout(this);
		        

		        TextView  tv=new TextView(this);
		        tv.setTextColor(Color.WHITE);
		        tv.setTextSize(45);  
		        tv.setGravity(Gravity.CENTER);
		        tv.setText(s); 
		        layout.addView(tv);
		        Toast toast=new Toast(this);
		        toast.setView(layout); 
		         toast.setGravity(Gravity.BOTTOM, 0, 50);
		         toast.show();
		    }
		 public void toasbenar(String string){
			 Toast tb = new Toast(getApplicationContext());
			 tb.setGravity(Gravity.CENTER_VERTICAL, 0,0);
			 tb.setDuration(Toast.LENGTH_SHORT);
			 tb.setView(getLayoutInflater().inflate(R.layout.viewbenar, null));
			 tb.show();
			 		 
		 }
		 public void toasalah(String string){
			 Toast ts = new Toast(getApplicationContext());
			 ts.setGravity(Gravity.CENTER_VERTICAL, 0,0);
			 ts.setDuration(Toast.LENGTH_SHORT);
			 ts.setView(getLayoutInflater().inflate(R.layout.viewslah, null));
			 ts.show();
			 		 
		 }
		 public void toasmasukanjawaban(String string){
			 Toast ts = new Toast(getApplicationContext());
			 ts.setGravity(Gravity.CENTER_VERTICAL, 0,0);
			 ts.setDuration(Toast.LENGTH_SHORT);
			 ts.setView(getLayoutInflater().inflate(R.layout.toastmasukanjawaban, null));
			 ts.show();
			 		 
		 }
		 
	}

	

