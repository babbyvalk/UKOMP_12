package com.fafa.adminb_aquaa;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends FragmentActivity {
	final String[] data ={"Home","List Penjualan","Laporan Penjualan","Stock Barang"};
	final String[] fragments ={
			"com.fafa.adminb_aquaa.HomeActivity",
			"com.fafa.adminb_aquaa.LaporanActivity",
			"com.fafa.adminb_aquaa.PenjualanActivity",
			"com.fafa.adminb_aquaa.Tambah"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActionBar().getThemedContext(), android.R.layout.simple_list_item_1, data);

		 final DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
		 final ListView navList = (ListView) findViewById(R.id.drawer);
		 navList.setAdapter(adapter);
		 navList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					final int pos, long id) {
				// TODO Auto-generated method stub
				drawer.setDrawerListener(new DrawerLayout.SimpleDrawerListener() {
					@Override
                    public void onDrawerClosed(View drawerView){
                            super.onDrawerClosed(drawerView);
                            FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
                            tx.replace(R.id.main, Fragment.instantiate(MainActivity.this, fragments[pos]));
                            tx.commit();
                    }
				});
				drawer.closeDrawer(navList);
			}
			 
		});
		 FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
		 tx.replace(R.id.main,Fragment.instantiate(MainActivity.this, fragments[0]));
		 tx.commit();
	}  
}
