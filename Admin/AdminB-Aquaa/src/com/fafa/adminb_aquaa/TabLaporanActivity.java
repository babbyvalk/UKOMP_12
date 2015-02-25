package com.fafa.adminb_aquaa;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class TabLaporanActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab_laporan);
		TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);


        TabSpec Bulan = tabHost.newTabSpec("Bulan");
        TabSpec Tahun = tabHost.newTabSpec("Tahun");
        

        Bulan.setIndicator("Bulan");
        Bulan.setContent(new Intent(this,BulananActivity.class));
       
        Tahun.setIndicator("Tahun");
        Tahun.setContent(new Intent(this,TahunanActivity.class));

      
        tabHost.addTab(Bulan);
        tabHost.addTab(Tahun);
	}
}
