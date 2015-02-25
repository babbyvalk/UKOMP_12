package com.fafa.b_aqua;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class BerandaActivity extends Activity {
	Button pesan, data, daftar;
	ImageView listHargaa, penjualan, datapenjualan;
	EditText Editalert;
	private ProgressDialog pDialog;

	JSONParser jsonParser = new JSONParser();

	private static final String url_alert_details = "http://192.168.1.148/BAqua/get_alertDialog.php";
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_PENJUALAN = "penjualan";
	private static final String TAG_KET = "keterangan";
	final Context context = this;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_beranda);
		listHargaa = (ImageView) findViewById(R.id.imageList);
		penjualan = (ImageView) findViewById(R.id.imagePenjualan);
		datapenjualan = (ImageView) findViewById(R.id.imageData);
		pesan = (Button) findViewById(R.id.btnPesan);
		data = (Button) findViewById(R.id.btnData);
		daftar = (Button) findViewById(R.id.btnDaftar);
		
		pesan.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent penjualan = new Intent(BerandaActivity.this, PenjualanActivity.class);
				startActivity(penjualan);
			}
		});
		data.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Intent dataPenjualan = new Intent(BerandaActivity.this, DataActivity.class);
				//startActivity(dataPenjualan);
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
				alertDialogBuilder.setTitle("Barang Pesanan Anda Sedang Di Proses");
				alertDialogBuilder.setMessage("Click Ya Untuk setuju!").setCancelable(false).setPositiveButton("Lanjutkan", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Intent dataPenjualan = new Intent(BerandaActivity.this, DataActivity.class);
						startActivity(dataPenjualan);
						
					}
				})
				.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.cancel();
					}
				});
				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();
			}
			
		});
		daftar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent daftar = new Intent(BerandaActivity.this, ListHargaActivity.class);
				startActivity(daftar);	
			}
		});
		
		listHargaa.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent daftar = new Intent(BerandaActivity.this, ListHargaActivity.class);
				startActivity(daftar);	
			}
		});
		
		penjualan.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent penjualan = new Intent(BerandaActivity.this, PenjualanActivity.class);
				startActivity(penjualan);
			}
		});
		datapenjualan.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
				alertDialogBuilder.setTitle("Barang Pesanan Anda Sedang Di Proses");
				alertDialogBuilder.setMessage("Click Ya Untuk setuju!").setCancelable(false).setPositiveButton("Lanjutkan", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Intent dataPenjualan = new Intent(BerandaActivity.this, DataActivity.class);
						startActivity(dataPenjualan);
						
					}
				})
				.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.cancel();
					}
				});
				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();
			}
		});
	}
}
