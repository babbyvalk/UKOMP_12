package com.fafa.adminb_aquaa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class BulananActivity extends ListActivity {
Spinner bulanan;
TextView bulanText, bulanCount;
Button cari, kembaliBULANAN;

private ProgressDialog pDialog;
JSONParser jParser = new JSONParser();
ArrayList<HashMap<String, String>> listharga22;
private static String url_semua_penjualan = "http://192.168.1.148/BAqua/getPenjualanJanuari.php";
private static String url_semua_penjualanFebruari = "http://192.168.1.148/BAqua/getPenjualanFebruari.php";
private static String url_semua_penjualanMaret = "http://192.168.1.148/BAqua/getPenjualanMaret.php";
private static String url_semua_penjualanApril = "http://192.168.1.148/BAqua/getPenjualanApril.php";
private static String url_semua_penjualanMei = "http://192.168.1.148/BAqua/getPenjualanMei.php";
private static String url_semua_penjualanJuni = "http://192.168.1.148/BAqua/getPenjualanJuni.php";
private static String url_semua_penjualanJuli = "http://192.168.1.148/BAqua/getPenjualanJuli.php";
private static String url_semua_penjualanAgustus = "http://192.168.1.148/BAqua/getPenjualanAgustus.php";
private static String url_semua_penjualanSeptember = "http://192.168.1.148/BAqua/getPenjualanSeptember.php";
private static String url_semua_penjualanOktober = "http://192.168.1.148/BAqua/getPenjualanOktober.php";
private static String url_semua_penjualanNovember = "http://192.168.1.148/BAqua/getPenjualanNovember.php";
private static String url_semua_penjualanDesember = "http://192.168.1.148/BAqua/getPenjualanDesember.php";

private static final String TAG_SUCCESS = "success";
private static final String TAG_PENJUALAN = "penjualan";
private static final String TAG_NAMABARANG = "nama_barang";
private static final String TAG_HARGA = "harga_barang";
private static final String TAG_QTY = "qty";
private static final String TAG_TOTAL = "total_harga";
private static final String TAG_NAMA = "nama";
private static final String TAG_JALAN = "jalan";
private static final String TAG_BULAN = "bln";
JSONArray listharga2 = null;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bulanan);
		listharga22 = new ArrayList<HashMap<String, String>>();
		bulanCount = (TextView) findViewById(R.id.textCountBulan);
		bulanan = (Spinner) findViewById(R.id.spinBulan);
		kembaliBULANAN = (Button) findViewById(R.id.btnKembaliBULANAN);
		kembaliBULANAN.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent kembali = new Intent(BulananActivity.this, MainActivity.class);
				startActivity(kembali);
			}
		});
		
		bulanText = (TextView) findViewById(R.id.textBulanan);
		List<String> bulan = new ArrayList<String>();
	    bulan.add("Januari");
	    bulan.add("Februari");
	    bulan.add("Maret");
	    bulan.add("April");
	    bulan.add("Mei");
	    bulan.add("Juni");
	    bulan.add("Juli");
	    bulan.add("Agustus");
	    bulan.add("September");
	    bulan.add("Oktober");
	    bulan.add("November");
	    bulan.add("Desember");
	    ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>
	    					(this, android.R.layout.simple_spinner_item,bulan);
	    dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    bulanan.setAdapter(dataAdapter1);
	    bulanan.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				String p = parent.getItemAtPosition(position).toString();
				bulanText.setText(p);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	    
	    cari = (Button) findViewById(R.id.btnCariBULANAN);
	    cari.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if(bulanText.getText().equals("Januari"))
				{
					bulanCount.setText("0 Data");
					listharga22.clear();
					new LoadDataJanuari().execute();
				}
				else if(bulanText.getText().equals("Februari"))
				{
					bulanCount.setText("10 Data");
					listharga22.clear();
					new LoadDataFebruari().execute();
				}
				else if(bulanText.getText().equals("Maret"))
				{
					bulanCount.setText("1 Data");
					listharga22.clear();
					new LoadDataMaret().execute();
				}
				else if(bulanText.getText().equals("April"))
				{
					bulanCount.setText("0 Data");
					listharga22.clear();
					new LoadDataApril().execute();
				}
				else if(bulanText.getText().equals("Mei"))
				{
					bulanCount.setText("0 Data");
					listharga22.clear();
					new LoadDataMei().execute();
				}
				else if(bulanText.getText().equals("Juni"))
				{
					bulanCount.setText("0 Data");
					listharga22.clear();
					new LoadDataJuni().execute();
				}
				else if(bulanText.getText().equals("Juli"))
				{
					bulanCount.setText("0 Data");
					listharga22.clear();
					new LoadDataJuli().execute();
				}
				else if(bulanText.getText().equals("Agustus"))
				{
					bulanCount.setText("0 Data");
					listharga22.clear();
					new LoadDataAgustus().execute();
				}
				else if(bulanText.getText().equals("September"))
				{
					bulanCount.setText("0 Data");
					listharga22.clear();
					new LoadDataSeptember().execute();
				}
				else if(bulanText.getText().equals("Oktober"))
				{
					bulanCount.setText("0 Data");
					listharga22.clear();
					new LoadDataOktober().execute();
				}
				else if(bulanText.getText().equals("November"))
				{
					bulanCount.setText("0 Data");
					listharga22.clear();
					new LoadDataNovember().execute();
				}
				else if(bulanText.getText().equals("Desember"))
				{
					bulanCount.setText("0 Data");
					listharga22.clear();
					new LoadDataDesember().execute();
				}
			}
		});
	   
	}
	class LoadDataJanuari extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(BulananActivity.this);
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
						String bln = c.getString(TAG_BULAN);
						
						HashMap<String, String> map = new HashMap<String, String>(); 
						
						map.put(TAG_NAMABARANG, nama_barang);
						map.put(TAG_HARGA, harga_barang);
						map.put(TAG_QTY, qty);
						map.put(TAG_TOTAL, total_harga);
						map.put(TAG_NAMA, nama);
						map.put(TAG_JALAN, jalan);
						map.put(TAG_BULAN, bln);
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
					ListAdapter adapter = new SimpleAdapter(BulananActivity.this, listharga22, R.layout.komponenbulanan, 
							new String[] {TAG_NAMABARANG, TAG_HARGA, TAG_QTY, TAG_TOTAL, TAG_NAMA, TAG_JALAN, TAG_BULAN}, new int[] 
									{R.id.textNamaBBULAN, R.id.textHargaBULAN, R.id.textQTYBULAN, R.id.textTotalBULAN, R.id.textNamaBULAN, R.id.textJalanBULAN, R.id.textBULANDI});
					setListAdapter(adapter);
				}
				
			});
		}
	}
	class LoadDataFebruari extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(BulananActivity.this);
			pDialog.setMessage("Mohon tunggu, loading data...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			List<NameValuePair> param  = new ArrayList<NameValuePair>();
			
			JSONObject json = jParser.makeHTTPRequest(url_semua_penjualanFebruari, "GET", param);
			
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
						String bln = c.getString(TAG_BULAN);
						
						HashMap<String, String> map = new HashMap<String, String>(); 
						
						map.put(TAG_NAMABARANG, nama_barang);
						map.put(TAG_HARGA, harga_barang);
						map.put(TAG_QTY, qty);
						map.put(TAG_TOTAL, total_harga);
						map.put(TAG_NAMA, nama);
						map.put(TAG_JALAN, jalan);
						map.put(TAG_BULAN, bln);
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
					ListAdapter adapter = new SimpleAdapter(BulananActivity.this, listharga22, R.layout.komponenbulanan, 
							new String[] {TAG_NAMABARANG, TAG_HARGA, TAG_QTY, TAG_TOTAL, TAG_NAMA, TAG_JALAN, TAG_BULAN}, new int[] 
									{R.id.textNamaBBULAN, R.id.textHargaBULAN, R.id.textQTYBULAN, R.id.textTotalBULAN, R.id.textNamaBULAN, R.id.textJalanBULAN, R.id.textBULANDI});
					setListAdapter(adapter);
				}
				
			});
		}
	}
	class LoadDataMaret extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(BulananActivity.this);
			pDialog.setMessage("Mohon tunggu, loading data...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			List<NameValuePair> param  = new ArrayList<NameValuePair>();
			
			JSONObject json = jParser.makeHTTPRequest(url_semua_penjualanMaret, "GET", param);
			
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
						String bln = c.getString(TAG_BULAN);
						
						HashMap<String, String> map = new HashMap<String, String>(); 
						
						map.put(TAG_NAMABARANG, nama_barang);
						map.put(TAG_HARGA, harga_barang);
						map.put(TAG_QTY, qty);
						map.put(TAG_TOTAL, total_harga);
						map.put(TAG_NAMA, nama);
						map.put(TAG_JALAN, jalan);
						map.put(TAG_BULAN, bln);
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
					ListAdapter adapter = new SimpleAdapter(BulananActivity.this, listharga22, R.layout.komponenbulanan, 
							new String[] {TAG_NAMABARANG, TAG_HARGA, TAG_QTY, TAG_TOTAL, TAG_NAMA, TAG_JALAN, TAG_BULAN}, new int[] 
									{R.id.textNamaBBULAN, R.id.textHargaBULAN, R.id.textQTYBULAN, R.id.textTotalBULAN, R.id.textNamaBULAN, R.id.textJalanBULAN, R.id.textBULANDI});
					setListAdapter(adapter);
				}
				
			});
		}
	}
	class LoadDataApril extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(BulananActivity.this);
			pDialog.setMessage("Mohon tunggu, loading data...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			List<NameValuePair> param  = new ArrayList<NameValuePair>();
			
			JSONObject json = jParser.makeHTTPRequest(url_semua_penjualanApril, "GET", param);
			
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
						String bln = c.getString(TAG_BULAN);
						
						HashMap<String, String> map = new HashMap<String, String>(); 
						
						map.put(TAG_NAMABARANG, nama_barang);
						map.put(TAG_HARGA, harga_barang);
						map.put(TAG_QTY, qty);
						map.put(TAG_TOTAL, total_harga);
						map.put(TAG_NAMA, nama);
						map.put(TAG_JALAN, jalan);
						map.put(TAG_BULAN, bln);
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
					ListAdapter adapter = new SimpleAdapter(BulananActivity.this, listharga22, R.layout.komponenbulanan, 
							new String[] {TAG_NAMABARANG, TAG_HARGA, TAG_QTY, TAG_TOTAL, TAG_NAMA, TAG_JALAN, TAG_BULAN}, new int[] 
									{R.id.textNamaBBULAN, R.id.textHargaBULAN, R.id.textQTYBULAN, R.id.textTotalBULAN, R.id.textNamaBULAN, R.id.textJalanBULAN, R.id.textBULANDI});
					setListAdapter(adapter);
				}
				
			});
		}
	}
	
	class LoadDataMei extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(BulananActivity.this);
			pDialog.setMessage("Mohon tunggu, loading data...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			List<NameValuePair> param  = new ArrayList<NameValuePair>();
			
			JSONObject json = jParser.makeHTTPRequest(url_semua_penjualanMei, "GET", param);
			
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
						String bln = c.getString(TAG_BULAN);
						
						HashMap<String, String> map = new HashMap<String, String>(); 
						
						map.put(TAG_NAMABARANG, nama_barang);
						map.put(TAG_HARGA, harga_barang);
						map.put(TAG_QTY, qty);
						map.put(TAG_TOTAL, total_harga);
						map.put(TAG_NAMA, nama);
						map.put(TAG_JALAN, jalan);
						map.put(TAG_BULAN, bln);
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
					ListAdapter adapter = new SimpleAdapter(BulananActivity.this, listharga22, R.layout.komponenbulanan, 
							new String[] {TAG_NAMABARANG, TAG_HARGA, TAG_QTY, TAG_TOTAL, TAG_NAMA, TAG_JALAN, TAG_BULAN}, new int[] 
									{R.id.textNamaBBULAN, R.id.textHargaBULAN, R.id.textQTYBULAN, R.id.textTotalBULAN, R.id.textNamaBULAN, R.id.textJalanBULAN, R.id.textBULANDI});
					setListAdapter(adapter);
				}
				
			});
		}
	}
	
	class LoadDataJuni extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(BulananActivity.this);
			pDialog.setMessage("Mohon tunggu, loading data...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			List<NameValuePair> param  = new ArrayList<NameValuePair>();
			
			JSONObject json = jParser.makeHTTPRequest(url_semua_penjualanJuni, "GET", param);
			
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
						String bln = c.getString(TAG_BULAN);
						
						HashMap<String, String> map = new HashMap<String, String>(); 
						
						map.put(TAG_NAMABARANG, nama_barang);
						map.put(TAG_HARGA, harga_barang);
						map.put(TAG_QTY, qty);
						map.put(TAG_TOTAL, total_harga);
						map.put(TAG_NAMA, nama);
						map.put(TAG_JALAN, jalan);
						map.put(TAG_BULAN, bln);
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
					ListAdapter adapter = new SimpleAdapter(BulananActivity.this, listharga22, R.layout.komponenbulanan, 
							new String[] {TAG_NAMABARANG, TAG_HARGA, TAG_QTY, TAG_TOTAL, TAG_NAMA, TAG_JALAN, TAG_BULAN}, new int[] 
									{R.id.textNamaBBULAN, R.id.textHargaBULAN, R.id.textQTYBULAN, R.id.textTotalBULAN, R.id.textNamaBULAN, R.id.textJalanBULAN, R.id.textBULANDI});
					setListAdapter(adapter);
				}
				
			});
		}
	}
	
	class LoadDataJuli extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(BulananActivity.this);
			pDialog.setMessage("Mohon tunggu, loading data...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			List<NameValuePair> param  = new ArrayList<NameValuePair>();
			
			JSONObject json = jParser.makeHTTPRequest(url_semua_penjualanJuli, "GET", param);
			
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
						String bln = c.getString(TAG_BULAN);
						
						HashMap<String, String> map = new HashMap<String, String>(); 
						
						map.put(TAG_NAMABARANG, nama_barang);
						map.put(TAG_HARGA, harga_barang);
						map.put(TAG_QTY, qty);
						map.put(TAG_TOTAL, total_harga);
						map.put(TAG_NAMA, nama);
						map.put(TAG_JALAN, jalan);
						map.put(TAG_BULAN, bln);
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
					ListAdapter adapter = new SimpleAdapter(BulananActivity.this, listharga22, R.layout.komponenbulanan, 
							new String[] {TAG_NAMABARANG, TAG_HARGA, TAG_QTY, TAG_TOTAL, TAG_NAMA, TAG_JALAN, TAG_BULAN}, new int[] 
									{R.id.textNamaBBULAN, R.id.textHargaBULAN, R.id.textQTYBULAN, R.id.textTotalBULAN, R.id.textNamaBULAN, R.id.textJalanBULAN, R.id.textBULANDI});
					setListAdapter(adapter);
				}
				
			});
		}
	}
	class LoadDataAgustus extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(BulananActivity.this);
			pDialog.setMessage("Mohon tunggu, loading data...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			List<NameValuePair> param  = new ArrayList<NameValuePair>();
			
			JSONObject json = jParser.makeHTTPRequest(url_semua_penjualanAgustus, "GET", param);
			
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
						String bln = c.getString(TAG_BULAN);
						
						HashMap<String, String> map = new HashMap<String, String>(); 
						
						map.put(TAG_NAMABARANG, nama_barang);
						map.put(TAG_HARGA, harga_barang);
						map.put(TAG_QTY, qty);
						map.put(TAG_TOTAL, total_harga);
						map.put(TAG_NAMA, nama);
						map.put(TAG_JALAN, jalan);
						map.put(TAG_BULAN, bln);
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
					ListAdapter adapter = new SimpleAdapter(BulananActivity.this, listharga22, R.layout.komponenbulanan, 
							new String[] {TAG_NAMABARANG, TAG_HARGA, TAG_QTY, TAG_TOTAL, TAG_NAMA, TAG_JALAN, TAG_BULAN}, new int[] 
									{R.id.textNamaBBULAN, R.id.textHargaBULAN, R.id.textQTYBULAN, R.id.textTotalBULAN, R.id.textNamaBULAN, R.id.textJalanBULAN, R.id.textBULANDI});
					setListAdapter(adapter);
				}
				
			});
		}
	}
	class LoadDataSeptember extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(BulananActivity.this);
			pDialog.setMessage("Mohon tunggu, loading data...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			List<NameValuePair> param  = new ArrayList<NameValuePair>();
			
			JSONObject json = jParser.makeHTTPRequest(url_semua_penjualanSeptember, "GET", param);
			
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
						String bln = c.getString(TAG_BULAN);
						
						HashMap<String, String> map = new HashMap<String, String>(); 
						
						map.put(TAG_NAMABARANG, nama_barang);
						map.put(TAG_HARGA, harga_barang);
						map.put(TAG_QTY, qty);
						map.put(TAG_TOTAL, total_harga);
						map.put(TAG_NAMA, nama);
						map.put(TAG_JALAN, jalan);
						map.put(TAG_BULAN, bln);
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
					ListAdapter adapter = new SimpleAdapter(BulananActivity.this, listharga22, R.layout.komponenbulanan, 
							new String[] {TAG_NAMABARANG, TAG_HARGA, TAG_QTY, TAG_TOTAL, TAG_NAMA, TAG_JALAN, TAG_BULAN}, new int[] 
									{R.id.textNamaBBULAN, R.id.textHargaBULAN, R.id.textQTYBULAN, R.id.textTotalBULAN, R.id.textNamaBULAN, R.id.textJalanBULAN, R.id.textBULANDI});
					setListAdapter(adapter);
				}
				
			});
		}
	}
	class LoadDataOktober extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(BulananActivity.this);
			pDialog.setMessage("Mohon tunggu, loading data...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			List<NameValuePair> param  = new ArrayList<NameValuePair>();
			
			JSONObject json = jParser.makeHTTPRequest(url_semua_penjualanOktober, "GET", param);
			
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
						String bln = c.getString(TAG_BULAN);
						
						HashMap<String, String> map = new HashMap<String, String>(); 
						
						map.put(TAG_NAMABARANG, nama_barang);
						map.put(TAG_HARGA, harga_barang);
						map.put(TAG_QTY, qty);
						map.put(TAG_TOTAL, total_harga);
						map.put(TAG_NAMA, nama);
						map.put(TAG_JALAN, jalan);
						map.put(TAG_BULAN, bln);
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
					ListAdapter adapter = new SimpleAdapter(BulananActivity.this, listharga22, R.layout.komponenbulanan, 
							new String[] {TAG_NAMABARANG, TAG_HARGA, TAG_QTY, TAG_TOTAL, TAG_NAMA, TAG_JALAN, TAG_BULAN}, new int[] 
									{R.id.textNamaBBULAN, R.id.textHargaBULAN, R.id.textQTYBULAN, R.id.textTotalBULAN, R.id.textNamaBULAN, R.id.textJalanBULAN, R.id.textBULANDI});
					setListAdapter(adapter);
				}
				
			});
		}
	}
	class LoadDataNovember extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(BulananActivity.this);
			pDialog.setMessage("Mohon tunggu, loading data...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			List<NameValuePair> param  = new ArrayList<NameValuePair>();
			
			JSONObject json = jParser.makeHTTPRequest(url_semua_penjualanNovember, "GET", param);
			
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
						String bln = c.getString(TAG_BULAN);
						
						HashMap<String, String> map = new HashMap<String, String>(); 
						
						map.put(TAG_NAMABARANG, nama_barang);
						map.put(TAG_HARGA, harga_barang);
						map.put(TAG_QTY, qty);
						map.put(TAG_TOTAL, total_harga);
						map.put(TAG_NAMA, nama);
						map.put(TAG_JALAN, jalan);
						map.put(TAG_BULAN, bln);
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
					ListAdapter adapter = new SimpleAdapter(BulananActivity.this, listharga22, R.layout.komponenbulanan, 
							new String[] {TAG_NAMABARANG, TAG_HARGA, TAG_QTY, TAG_TOTAL, TAG_NAMA, TAG_JALAN, TAG_BULAN}, new int[] 
									{R.id.textNamaBBULAN, R.id.textHargaBULAN, R.id.textQTYBULAN, R.id.textTotalBULAN, R.id.textNamaBULAN, R.id.textJalanBULAN, R.id.textBULANDI});
					setListAdapter(adapter);
				}
				
			});
		}
	}
	class LoadDataDesember extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(BulananActivity.this);
			pDialog.setMessage("Mohon tunggu, loading data...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			List<NameValuePair> param  = new ArrayList<NameValuePair>();
			
			JSONObject json = jParser.makeHTTPRequest(url_semua_penjualanDesember, "GET", param);
			
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
						String bln = c.getString(TAG_BULAN);
						
						HashMap<String, String> map = new HashMap<String, String>(); 
						
						map.put(TAG_NAMABARANG, nama_barang);
						map.put(TAG_HARGA, harga_barang);
						map.put(TAG_QTY, qty);
						map.put(TAG_TOTAL, total_harga);
						map.put(TAG_NAMA, nama);
						map.put(TAG_JALAN, jalan);
						map.put(TAG_BULAN, bln);
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
					ListAdapter adapter = new SimpleAdapter(BulananActivity.this, listharga22, R.layout.komponenbulanan, 
							new String[] {TAG_NAMABARANG, TAG_HARGA, TAG_QTY, TAG_TOTAL, TAG_NAMA, TAG_JALAN, TAG_BULAN}, new int[] 
									{R.id.textNamaBBULAN, R.id.textHargaBULAN, R.id.textQTYBULAN, R.id.textTotalBULAN, R.id.textNamaBULAN, R.id.textJalanBULAN, R.id.textBULANDI});
					setListAdapter(adapter);
				}
				
			});
		}
	}

}
