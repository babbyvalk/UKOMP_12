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

public class ListHargaActivity extends ListActivity{
	private ProgressDialog pDialog;
	Button kembali;
	JSONParser jParser = new JSONParser();
	ArrayList<HashMap<String, String>> listharga1;
	private static String url_semua_makanan = "http://192.168.1.148/BAqua/getAllHarga.php";
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_LIST = "list_aqua";
	private static final String TAG_PID = "id";
	private static final String TAG_MENU = "nama_barang";
	private static final String TAG_HARGA = "harga_barang";
	private static final String TAG_STOCK = "stock_barang";

	JSONArray listharga = null;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_harga);
		listharga1 = new ArrayList<HashMap<String, String>>();
		new LoadSemuaDataHarga().execute();
		kembali = (Button) findViewById(R.id.btnKembali);
		kembali.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent kembali = new Intent(ListHargaActivity.this, BerandaActivity.class);
				startActivity(kembali);
			}
		});
		ListView lv = getListView();
	}

	class LoadSemuaDataHarga extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(ListHargaActivity.this);
			pDialog.setMessage("Mohon tunggu, loading data...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			List<NameValuePair> param  = new ArrayList<NameValuePair>();
			JSONObject json = jParser.makeHTTPRequest(url_semua_makanan, "GET", param);
			Log.d("Semua Daftar Makanan", json.toString());
			try{
				int success = json.getInt(TAG_SUCCESS);
				if(success == 1)
				{
				
					listharga = json.getJSONArray(TAG_LIST);
					for(int i = 0; i < listharga.length(); i++)
					{
						JSONObject c = listharga.getJSONObject(i);
						String id = c.getString(TAG_PID);
						String nama_barang = c.getString(TAG_MENU);
						String harga_barang = c.getString(TAG_HARGA);
						String stock_barang = c.getString(TAG_STOCK);
						
						HashMap<String, String> map = new HashMap<String, String>(); 
						
						map.put(TAG_PID, id);
						map.put(TAG_MENU, nama_barang);
						map.put(TAG_HARGA, harga_barang);
						map.put(TAG_STOCK, stock_barang);
						listharga1.add(map);
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
					ListAdapter adapter = new SimpleAdapter(ListHargaActivity.this, listharga1, R.layout.manggildata, 
							new String[] {TAG_PID, TAG_MENU, TAG_HARGA, TAG_STOCK}, new int[] 
									{R.id.textID, R.id.textNama, R.id.textHarga, R.id.textStock});
					setListAdapter(adapter);
				}
			});
		}
	}
}
