package com.fafa.adminb_aquaa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fafa.adminb_aquaa.DaftarPenjualanActivity.LoadSemuaDataPembeli;

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
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class TambahStockActivity extends ListActivity {
Button kembaliTambah, tambaahh;
	
	private ProgressDialog pDialog;
	JSONParser jParser = new JSONParser();
	ArrayList<HashMap<String, String>> listharga22;
	private static String url_semua_stock = "http://192.168.1.148/BAqua/getStockBarang.php";
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_LISTAQUA = "list_aqua";
	private static final String TAG_ID = "id";
	private static final String TAG_NAMABARANG = "nama_barang";
	private static final String TAG_HARGA = "harga_barang";
	private static final String TAG_STOCK = "stock_barang";
	JSONArray listharga2 = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tambah_stock);
		kembaliTambah = (Button) findViewById(R.id.btnKembaliSTOCK);
		tambaahh = (Button) findViewById(R.id.btnTambahhSTOCK);
		tambaahh.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), AddStockActivity.class);
				startActivity(i);		
				finish();

			}
		});
		kembaliTambah.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent i = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(i);		
				finish();

			}
		});
		listharga22 = new ArrayList<HashMap<String, String>>();
		new LoadSemuaDataStock().execute();
		ListView lv = getListView();
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long pid) {
				// TODO Auto-generated method stub
				String id = ((TextView) view.findViewById(R.id.textIDSTOCK)).getText().toString();
				Intent edit = new Intent(getApplicationContext(), EditStockBarangActivity.class);
				edit.putExtra(TAG_ID, id);
				startActivityForResult(edit, 100);
			}
		
		});
	}
	@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			// TODO Auto-generated method stub
			super.onActivityResult(requestCode, resultCode, data);
			if (resultCode == 100) {
				Intent intent = getIntent();
				finish();
				startActivity(intent);
			}
	}
	class LoadSemuaDataStock extends AsyncTask<String, String, String>
	{	
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(TambahStockActivity.this);
			pDialog.setMessage("Mohon tunggu, loading data...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			List<NameValuePair> param  = new ArrayList<NameValuePair>();
			
			JSONObject json = jParser.makeHTTPRequest(url_semua_stock, "GET", param);
			
			Log.d("Semua Daftar Penjualan", json.toString());
			try
			{
				int success = json.getInt(TAG_SUCCESS);
				if(success == 1)
				{
					listharga2 = json.getJSONArray(TAG_LISTAQUA);
					for(int i = 0; i < listharga2.length(); i++)
					{
						JSONObject c = listharga2.getJSONObject(i);
						
						String id = c.getString(TAG_ID);
						String nama_barang = c.getString(TAG_NAMABARANG);
						String harga_barang = c.getString(TAG_HARGA);
						String stock_barang = c.getString(TAG_STOCK);
						
						HashMap<String, String> map = new HashMap<String, String>(); 
						
						map.put(TAG_ID, id);
						map.put(TAG_NAMABARANG, nama_barang);
						map.put(TAG_HARGA, harga_barang);
						map.put(TAG_STOCK, stock_barang);
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
					ListAdapter adapter = new SimpleAdapter(TambahStockActivity.this, listharga22, R.layout.komponenstock, 
							new String[] {TAG_ID, TAG_NAMABARANG, TAG_HARGA, TAG_STOCK}, new int[] 
									{R.id.textIDSTOCK, R.id.textNamaBSTOCK, R.id.textHargaSTOCK, R.id.textSTOCKSTOCK});
					setListAdapter(adapter);
				}
			});
		}
	}
	
}
