package com.fafa.adminb_aquaa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fafa.adminb_aquaa.BulananActivity.LoadDataJanuari;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class TahunanActivity extends ListActivity {
Button kembaliTahun, cariTahun;
TextView tahunedit, count;
Spinner cariTahunSpin;

private ProgressDialog pDialog;
JSONParser jParser = new JSONParser();
ArrayList<HashMap<String, String>> listharga22;
private static String url_semua_penjualan = "http://192.168.1.148/BAqua/getPenjualan2015.php";
private static String url_semua_penjualan2016 = "http://192.168.1.148/BAqua/getPenjualan2016.php";
private static String url_semua_penjualan2017 = "http://192.168.1.148/BAqua/getPenjualan2017.php";
private static String url_semua_penjualan2018 = "http://192.168.1.148/BAqua/getPenjualan2018.php";
private static String url_semua_penjualan2019 = "http://192.168.1.148/BAqua/getPenjualan2019.php";
private static String url_semua_penjualan2020 = "http://192.168.1.148/BAqua/getPenjualan2020.php";

private static final String TAG_SUCCESS = "success";
private static final String TAG_PENJUALAN = "penjualan";
private static final String TAG_NAMABARANG = "nama_barang";
private static final String TAG_HARGA = "harga_barang";
private static final String TAG_QTY = "qty";
private static final String TAG_TOTAL = "total_harga";
private static final String TAG_NAMA = "nama";
private static final String TAG_JALAN = "jalan";
private static final String TAG_TAHUN = "tahun";
JSONArray listharga2 = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tahunan);
		listharga22 = new ArrayList<HashMap<String, String>>();
		count = (TextView) findViewById(R.id.textCount);
		cariTahun = (Button) findViewById(R.id.btnCariTAHUNAN);
		kembaliTahun = (Button) findViewById(R.id.btnKembaliTahunan);
		tahunedit = (TextView) findViewById(R.id.textTahun);
		cariTahunSpin = (Spinner) findViewById(R.id.spinTahun);
		cariTahun.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(tahunedit.getText().equals("2015"))
				{
					count.setText("12 Data");
					listharga22.clear();
					new LoadData2015().execute();
				}
				else if(tahunedit.getText().equals("2016"))
				{
					count.setText("1 Data");
					listharga22.clear();
					new LoadData2016().execute();
				}
				else if(tahunedit.getText().equals("2017"))
				{
					count.setText("0 Data");
					listharga22.clear();
					new LoadData2017().execute();
				}
				else if(tahunedit.getText().equals("2018"))
				{
					count.setText("0 Data");
					listharga22.clear();
					new LoadData2018().execute();
				}
				else if(tahunedit.getText().equals("2019"))
				{
					count.setText("0 Data");
					listharga22.clear();
					new LoadData2019().execute();
				}
				else if(tahunedit.getText().equals("2020"))
				{
					count.setText("0 Data");
					listharga22.clear();
					new LoadData2020().execute();
				}
			}
		});
		kembaliTahun.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent kembali = new Intent(TahunanActivity.this, MainActivity.class);
				startActivity(kembali);	
			}
		});
		List<String> tahun = new ArrayList<String>();
	    tahun.add("2015");
	    tahun.add("2016");
	    tahun.add("2017");
	    tahun.add("2018");
	    tahun.add("2019");
	    tahun.add("2020");
	    ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>
	    					(this, android.R.layout.simple_spinner_item,tahun);
	    dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    cariTahunSpin.setAdapter(dataAdapter2);
	    cariTahunSpin.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				String p = parent.getItemAtPosition(position).toString();
				tahunedit.setText(p);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});

	}
	class LoadData2015 extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(TahunanActivity.this);
			pDialog.setMessage("Mohon tunggu, loading data...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			List<NameValuePair> param  = new ArrayList<NameValuePair>();
			
			JSONObject json = jParser.makeHTTPRequest(url_semua_penjualan, "GET", param);
			
			Log.d("Semua Daftar Penjualan", json.toString());
			try
			{
				int success = json.getInt(TAG_SUCCESS);
				if(success == 1)
				{
					listharga2 = json.getJSONArray(TAG_PENJUALAN);
					for(int i = 0; i < listharga2.length(); i++)
					{
						JSONObject c = listharga2.getJSONObject(i);
						
						String nama_barang = c.getString(TAG_NAMABARANG);
						String harga_barang = c.getString(TAG_HARGA);
						String qty = c.getString(TAG_QTY);
						String total_harga = c.getString(TAG_TOTAL);
						String nama = c.getString(TAG_NAMA);
						String jalan = c.getString(TAG_JALAN);
						String tahun = c.getString(TAG_TAHUN);
						
						HashMap<String, String> map = new HashMap<String, String>(); 
						
						map.put(TAG_NAMABARANG, nama_barang);
						map.put(TAG_HARGA, harga_barang);
						map.put(TAG_QTY, qty);
						map.put(TAG_TOTAL, total_harga);
						map.put(TAG_NAMA, nama);
						map.put(TAG_JALAN, jalan);
						map.put(TAG_TAHUN, tahun);
						listharga22.add(map);
					}
				}
				else
				{
					
				}
			}
			catch(JSONException e)
			{
				e.printStackTrace();
			}
			return null;
		}
		@Override
		protected void onPostExecute(String file_url) {
			// TODO Auto-generated method stub
			pDialog.dismiss();
			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					ListAdapter adapter = new SimpleAdapter(TahunanActivity.this, listharga22, R.layout.komponentahunan, 
							new String[] {TAG_NAMABARANG, TAG_HARGA, TAG_QTY, TAG_TOTAL, TAG_NAMA, TAG_JALAN, TAG_TAHUN}, new int[] 
									{R.id.textNamaBTAHUN, R.id.textHargaTAHUN, R.id.textQTYTAHUN, R.id.textTotalTAHUN, R.id.textNamaTAHUN, R.id.textJalanTAHUN, R.id.textTAHUNDI});
					setListAdapter(adapter);
				}
				
			});
		}
	}
	class LoadData2016 extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(TahunanActivity.this);
			pDialog.setMessage("Mohon tunggu, loading data...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			List<NameValuePair> param  = new ArrayList<NameValuePair>();
			
			JSONObject json = jParser.makeHTTPRequest(url_semua_penjualan2016, "GET", param);
			
			Log.d("Semua Daftar Penjualan", json.toString());
			try
			{
				int success = json.getInt(TAG_SUCCESS);
				if(success == 1)
				{
					listharga2 = json.getJSONArray(TAG_PENJUALAN);
					for(int i = 0; i < listharga2.length(); i++)
					{
						JSONObject c = listharga2.getJSONObject(i);
						
						String nama_barang = c.getString(TAG_NAMABARANG);
						String harga_barang = c.getString(TAG_HARGA);
						String qty = c.getString(TAG_QTY);
						String total_harga = c.getString(TAG_TOTAL);
						String nama = c.getString(TAG_NAMA);
						String jalan = c.getString(TAG_JALAN);
						String tahun = c.getString(TAG_TAHUN);
						
						HashMap<String, String> map = new HashMap<String, String>(); 
						
						map.put(TAG_NAMABARANG, nama_barang);
						map.put(TAG_HARGA, harga_barang);
						map.put(TAG_QTY, qty);
						map.put(TAG_TOTAL, total_harga);
						map.put(TAG_NAMA, nama);
						map.put(TAG_JALAN, jalan);
						map.put(TAG_TAHUN, tahun);
						listharga22.add(map);
					}
				}
				else
				{
					
				}
			}
			catch(JSONException e)
			{
				e.printStackTrace();
			}
			return null;
		}
		@Override
		protected void onPostExecute(String file_url) {
			// TODO Auto-generated method stub
			pDialog.dismiss();
			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					ListAdapter adapter = new SimpleAdapter(TahunanActivity.this, listharga22, R.layout.komponentahunan, 
							new String[] {TAG_NAMABARANG, TAG_HARGA, TAG_QTY, TAG_TOTAL, TAG_NAMA, TAG_JALAN, TAG_TAHUN}, new int[] 
									{R.id.textNamaBTAHUN, R.id.textHargaTAHUN, R.id.textQTYTAHUN, R.id.textTotalTAHUN, R.id.textNamaTAHUN, R.id.textJalanTAHUN, R.id.textTAHUNDI});
					setListAdapter(adapter);
				}
				
			});
		}
	}
	class LoadData2017 extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(TahunanActivity.this);
			pDialog.setMessage("Mohon tunggu, loading data...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			List<NameValuePair> param  = new ArrayList<NameValuePair>();
			
			JSONObject json = jParser.makeHTTPRequest(url_semua_penjualan2017, "GET", param);
			
			Log.d("Semua Daftar Penjualan", json.toString());
			try
			{
				int success = json.getInt(TAG_SUCCESS);
				if(success == 1)
				{
					listharga2 = json.getJSONArray(TAG_PENJUALAN);
					for(int i = 0; i < listharga2.length(); i++)
					{
						JSONObject c = listharga2.getJSONObject(i);
						
						String nama_barang = c.getString(TAG_NAMABARANG);
						String harga_barang = c.getString(TAG_HARGA);
						String qty = c.getString(TAG_QTY);
						String total_harga = c.getString(TAG_TOTAL);
						String nama = c.getString(TAG_NAMA);
						String jalan = c.getString(TAG_JALAN);
						String tahun = c.getString(TAG_TAHUN);
						
						HashMap<String, String> map = new HashMap<String, String>(); 
						
						map.put(TAG_NAMABARANG, nama_barang);
						map.put(TAG_HARGA, harga_barang);
						map.put(TAG_QTY, qty);
						map.put(TAG_TOTAL, total_harga);
						map.put(TAG_NAMA, nama);
						map.put(TAG_JALAN, jalan);
						map.put(TAG_TAHUN, tahun);
						listharga22.add(map);
					}
				}
				else
				{
					
				}
			}
			catch(JSONException e)
			{
				e.printStackTrace();
			}
			return null;
		}
		@Override
		protected void onPostExecute(String file_url) {
			// TODO Auto-generated method stub
			pDialog.dismiss();
			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					ListAdapter adapter = new SimpleAdapter(TahunanActivity.this, listharga22, R.layout.komponentahunan, 
							new String[] {TAG_NAMABARANG, TAG_HARGA, TAG_QTY, TAG_TOTAL, TAG_NAMA, TAG_JALAN, TAG_TAHUN}, new int[] 
									{R.id.textNamaBTAHUN, R.id.textHargaTAHUN, R.id.textQTYTAHUN, R.id.textTotalTAHUN, R.id.textNamaTAHUN, R.id.textJalanTAHUN, R.id.textTAHUNDI});
					setListAdapter(adapter);
				}
				
			});
		}
	}
	class LoadData2018 extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(TahunanActivity.this);
			pDialog.setMessage("Mohon tunggu, loading data...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			List<NameValuePair> param  = new ArrayList<NameValuePair>();
			
			JSONObject json = jParser.makeHTTPRequest(url_semua_penjualan2018, "GET", param);
			
			Log.d("Semua Daftar Penjualan", json.toString());
			try
			{
				int success = json.getInt(TAG_SUCCESS);
				if(success == 1)
				{
					listharga2 = json.getJSONArray(TAG_PENJUALAN);
					for(int i = 0; i < listharga2.length(); i++)
					{
						JSONObject c = listharga2.getJSONObject(i);
						
						String nama_barang = c.getString(TAG_NAMABARANG);
						String harga_barang = c.getString(TAG_HARGA);
						String qty = c.getString(TAG_QTY);
						String total_harga = c.getString(TAG_TOTAL);
						String nama = c.getString(TAG_NAMA);
						String jalan = c.getString(TAG_JALAN);
						String tahun = c.getString(TAG_TAHUN);
						
						HashMap<String, String> map = new HashMap<String, String>(); 
						
						map.put(TAG_NAMABARANG, nama_barang);
						map.put(TAG_HARGA, harga_barang);
						map.put(TAG_QTY, qty);
						map.put(TAG_TOTAL, total_harga);
						map.put(TAG_NAMA, nama);
						map.put(TAG_JALAN, jalan);
						map.put(TAG_TAHUN, tahun);
						listharga22.add(map);
					}
				}
				else
				{
					
				}
			}
			catch(JSONException e)
			{
				e.printStackTrace();
			}
			return null;
		}
		@Override
		protected void onPostExecute(String file_url) {
			// TODO Auto-generated method stub
			pDialog.dismiss();
			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					ListAdapter adapter = new SimpleAdapter(TahunanActivity.this, listharga22, R.layout.komponentahunan, 
							new String[] {TAG_NAMABARANG, TAG_HARGA, TAG_QTY, TAG_TOTAL, TAG_NAMA, TAG_JALAN, TAG_TAHUN}, new int[] 
									{R.id.textNamaBTAHUN, R.id.textHargaTAHUN, R.id.textQTYTAHUN, R.id.textTotalTAHUN, R.id.textNamaTAHUN, R.id.textJalanTAHUN, R.id.textTAHUNDI});
					setListAdapter(adapter);
				}
				
			});
		}
	}
	class LoadData2019 extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(TahunanActivity.this);
			pDialog.setMessage("Mohon tunggu, loading data...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			List<NameValuePair> param  = new ArrayList<NameValuePair>();
			
			JSONObject json = jParser.makeHTTPRequest(url_semua_penjualan2019, "GET", param);
			
			Log.d("Semua Daftar Penjualan", json.toString());
			try
			{
				int success = json.getInt(TAG_SUCCESS);
				if(success == 1)
				{
					listharga2 = json.getJSONArray(TAG_PENJUALAN);
					for(int i = 0; i < listharga2.length(); i++)
					{
						JSONObject c = listharga2.getJSONObject(i);
						
						String nama_barang = c.getString(TAG_NAMABARANG);
						String harga_barang = c.getString(TAG_HARGA);
						String qty = c.getString(TAG_QTY);
						String total_harga = c.getString(TAG_TOTAL);
						String nama = c.getString(TAG_NAMA);
						String jalan = c.getString(TAG_JALAN);
						String tahun = c.getString(TAG_TAHUN);
						
						HashMap<String, String> map = new HashMap<String, String>(); 
						
						map.put(TAG_NAMABARANG, nama_barang);
						map.put(TAG_HARGA, harga_barang);
						map.put(TAG_QTY, qty);
						map.put(TAG_TOTAL, total_harga);
						map.put(TAG_NAMA, nama);
						map.put(TAG_JALAN, jalan);
						map.put(TAG_TAHUN, tahun);
						listharga22.add(map);
					}
				}
				else
				{
					
				}
			}
			catch(JSONException e)
			{
				e.printStackTrace();
			}
			return null;
		}
		@Override
		protected void onPostExecute(String file_url) {
			// TODO Auto-generated method stub
			pDialog.dismiss();
			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					ListAdapter adapter = new SimpleAdapter(TahunanActivity.this, listharga22, R.layout.komponentahunan, 
							new String[] {TAG_NAMABARANG, TAG_HARGA, TAG_QTY, TAG_TOTAL, TAG_NAMA, TAG_JALAN, TAG_TAHUN}, new int[] 
									{R.id.textNamaBTAHUN, R.id.textHargaTAHUN, R.id.textQTYTAHUN, R.id.textTotalTAHUN, R.id.textNamaTAHUN, R.id.textJalanTAHUN, R.id.textTAHUNDI});
					setListAdapter(adapter);
				}
				
			});
		}
	}
	class LoadData2020 extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(TahunanActivity.this);
			pDialog.setMessage("Mohon tunggu, loading data...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			List<NameValuePair> param  = new ArrayList<NameValuePair>();
			
			JSONObject json = jParser.makeHTTPRequest(url_semua_penjualan2020, "GET", param);
			
			Log.d("Semua Daftar Penjualan", json.toString());
			try
			{
				int success = json.getInt(TAG_SUCCESS);
				if(success == 1)
				{
					listharga2 = json.getJSONArray(TAG_PENJUALAN);
					for(int i = 0; i < listharga2.length(); i++)
					{
						JSONObject c = listharga2.getJSONObject(i);
						
						String nama_barang = c.getString(TAG_NAMABARANG);
						String harga_barang = c.getString(TAG_HARGA);
						String qty = c.getString(TAG_QTY);
						String total_harga = c.getString(TAG_TOTAL);
						String nama = c.getString(TAG_NAMA);
						String jalan = c.getString(TAG_JALAN);
						String tahun = c.getString(TAG_TAHUN);
						
						HashMap<String, String> map = new HashMap<String, String>(); 
						
						map.put(TAG_NAMABARANG, nama_barang);
						map.put(TAG_HARGA, harga_barang);
						map.put(TAG_QTY, qty);
						map.put(TAG_TOTAL, total_harga);
						map.put(TAG_NAMA, nama);
						map.put(TAG_JALAN, jalan);
						map.put(TAG_TAHUN, tahun);
						listharga22.add(map);
					}
				}
				else
				{
					
				}
			}
			catch(JSONException e)
			{
				e.printStackTrace();
			}
			return null;
		}
		@Override
		protected void onPostExecute(String file_url) {
			// TODO Auto-generated method stub
			pDialog.dismiss();
			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					ListAdapter adapter = new SimpleAdapter(TahunanActivity.this, listharga22, R.layout.komponentahunan, 
							new String[] {TAG_NAMABARANG, TAG_HARGA, TAG_QTY, TAG_TOTAL, TAG_NAMA, TAG_JALAN, TAG_TAHUN}, new int[] 
									{R.id.textNamaBTAHUN, R.id.textHargaTAHUN, R.id.textQTYTAHUN, R.id.textTotalTAHUN, R.id.textNamaTAHUN, R.id.textJalanTAHUN, R.id.textTAHUNDI});
					setListAdapter(adapter);
				}
				
			});
		}
	}

}
