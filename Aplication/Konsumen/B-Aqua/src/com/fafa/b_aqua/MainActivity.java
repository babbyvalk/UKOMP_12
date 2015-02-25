package com.fafa.b_aqua;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

public class MainActivity extends Activity {
	private static final int SPLASH_TIME = 3 * 1000;// Set waktu 3 Detik
    ProgressBar prg;
 
    @SuppressLint("NewApi")

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		prg = (ProgressBar)findViewById(R.id.progressBar1);
		prg.setAlpha(SPLASH_TIME);
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent masuk = new Intent(MainActivity.this,BerandaActivity.class);
				startActivity(masuk);
				finish();
			}
		},SPLASH_TIME);
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		},SPLASH_TIME);
    }
}
