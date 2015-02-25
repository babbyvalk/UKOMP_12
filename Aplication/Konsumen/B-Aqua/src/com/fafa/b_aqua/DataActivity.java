package com.fafa.b_aqua;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class DataActivity extends ListActivity {
	Button kembali;
	
	private ProgressDialog pDialog;
	JSONParser jParser = new JSONParser();
	ArrayList<HashMap<String, String>> listharga22;
	private static String url_semua_penjualan = "http://192.168.1.148/BAqua/getPenjualan.php";
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_PENJUALAN = "penjualan";
	private static final String TAG_ID = "id";
	private static final String TAG_NAMABARANG = "nama_barang";
	private static final String TAG_HARGA = "harga_barang";
	private static final String TAG_QTY = "qty";
	private static final String TAG_TOTAL = "total_harga";
	private static final String TAG_NAMA = "nama";
	private static final String TAG_JALAN = "jalan";
	private static final String TAG_RT = "rt";
	private static final String TAG_RW = "rw";
	private static final String TAG_NORUMAH = "no_rumah";
	private static final String TAG_KETERANGAN = "keterangan";
	
	
	JSONArray listharga2 = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_data);
		listharga22 = new ArrayList<HashMap<String, String>>();
		
		kembali = (Button) findViewById(R.id.btnKembali2);
		
		new LoadSemuaDataPembeli().execute();
		
		kembali.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), BerandaActivity.class);
				startActivity(i);
				
				finish();	
			}
		});
		ListView lv = getListView();
	}
	class LoadSemuaDataPembeli extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(DataActivity.this);
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
						
						String id = c.getString(TAG_ID);
						String nama_barang = c.getString(TAG_NAMABARANG);
						String harga_barang = c.getString(TAG_HARGA);
						String qty = c.getString(TAG_QTY);
						String total_harga = c.getString(TAG_TOTAL);
						String nama = c.getString(TAG_NAMA);
						String jalan = c.getString(TAG_JALAN);
						String rt = c.getString(TAG_RT);
						String rw = c.getString(TAG_RW);
						String no_rumah = c.getString(TAG_NORUMAH);
						String keterangan = c.getString(TAG_KETERANGAN);
						
						HashMap<String, String> map = new HashMap<String, String>(); 
						
						map.put(TAG_ID, id);
						map.put(TAG_NAMABARANG, nama_barang);
						map.put(TAG_HARGA, harga_barang);
						map.put(TAG_QTY, qty);
						map.put(TAG_TOTAL, total_harga);
						map.put(TAG_NAMA, nama);
						map.put(TAG_JALAN, jalan);
						map.put(TAG_RT, rt);
						map.put(TAG_RW, rw);
						map.put(TAG_NORUMAH, no_rumah);
						map.put(TAG_KETERANGAN, keterangan);
						
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
					ListAdapter adapter = new SimpleAdapter(DataActivity.this, listharga22, R.layout.manggilkomponendata, 
							new String[] {TAG_ID, TAG_NAMABARANG, TAG_HARGA, TAG_QTY, TAG_TOTAL, TAG_NAMA, TAG_JALAN, TAG_RT, TAG_RW, TAG_NORUMAH, TAG_KETERANGAN}, new int[] 
									{R.id.textIDDATA, R.id.textNamaBDATA, R.id.textHargaDATA, R.id.textQTYDATA, R.id.textTotalDATA, R.id.textNamaDATA, R.id.textJalanDATA, R.id.textRTDATA, R.id.textRWDATA, R.id.textNODATA, R.id.textKetDATA});
					setListAdapter(adapter);
				}
			});
		}
	}
}
