package com.fafa.adminb_aquaa;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditStockBarangActivity extends Activity {
	String id;
	EditText barangEDITS, hargaEDITS, stockEDITS;
	Button kembaliEdit, hapusEdit, prosesEdit;
	
	private ProgressDialog pDialog;

	JSONParser jsonParser = new JSONParser();
	private static final String url_stock_details = "http://192.168.1.148/BAqua/get_stock_details.php";
	private static final String url_save_stock = "http://192.168.1.148/BAqua/update_stock.php";
	private static final String url_hapus_stock = "http://192.168.1.148/BAqua/delete_stock.php";
	
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_LISTAQUA= "list_aqua";
	private static final String TAG_ID = "id";
	private static final String TAG_NAMABARANG = "nama_barang";
	private static final String TAG_HARGA = "harga_barang";
	private static final String TAG_STOCK = "stock_barang";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_stock_barang);
		Intent i = getIntent();
		id = i.getStringExtra(TAG_ID);
		new GetDaftarStockDetails().execute();
		prosesEdit = (Button) findViewById(R.id.btnSaveEDITSTOCK);
		prosesEdit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new ProsesSaveEditStock().execute();
			}
		});
		
		hapusEdit = (Button) findViewById(R.id.btnHapusEDITSTOCK);
		hapusEdit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DeleteStock().execute();
			}
		});
		
		kembaliEdit = (Button) findViewById(R.id.btnKembaliEDITSTOCK);
		kembaliEdit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(i);		
				finish();
			}
		});
	}
	class GetDaftarStockDetails extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(EditStockBarangActivity.this);
			pDialog.setMessage("Mengambil Data...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					int success;
					try
					{
						List<NameValuePair> param = new ArrayList<NameValuePair>();
						param.add(new BasicNameValuePair("id", id));
						
						JSONObject json = jsonParser.makeHTTPRequest(url_stock_details, "GET", param);
						Log.d("Single DAFTARBUKU Details", json.toString());
						
						success = json.getInt(TAG_SUCCESS);
						if(success == 1)
						{
							JSONArray stockOBJB = json.getJSONArray(TAG_LISTAQUA); 
							
							JSONObject stock = stockOBJB.getJSONObject(0);
							barangEDITS = (EditText) findViewById(R.id.editBarangEDIT);
							hargaEDITS = (EditText) findViewById(R.id.editHargaEDIT);
							stockEDITS = (EditText) findViewById(R.id.editEditStockEDIT);
							
							barangEDITS.setText(stock.getString(TAG_NAMABARANG));
							hargaEDITS.setText(stock.getString(TAG_HARGA));
							stockEDITS.setText(stock.getString(TAG_STOCK));
							
						}
						else
						{
							
						}
					}
					catch(JSONException e)
					{
						e.printStackTrace();
					}
				}
			}).start();
			return null;
		}
		@Override
		protected void onPostExecute(String file_url) {
			// TODO Auto-generated method stub
			pDialog.dismiss();
		}
	}
	class ProsesSaveEditStock extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(EditStockBarangActivity.this);
			pDialog.setMessage("Mengambil Data...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
				String nama_barang = barangEDITS.getText().toString();
				String harga_barang = hargaEDITS.getText().toString();
				String stock_barang = stockEDITS.getText().toString();
				
				List<NameValuePair> param = new ArrayList<NameValuePair>();
				
				param.add(new BasicNameValuePair(TAG_ID, id));
				param.add(new BasicNameValuePair(TAG_NAMABARANG, nama_barang));
				param.add(new BasicNameValuePair(TAG_HARGA, harga_barang));
				param.add(new BasicNameValuePair(TAG_STOCK, stock_barang));
				JSONObject json = jsonParser.makeHTTPRequest(url_save_stock, "POST", param);
			try
			{
				int success = json.getInt(TAG_SUCCESS);
				if(success ==1)
				{
					Intent i = getIntent();
					setResult(100, i);
					finish();
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
		}
	}
	class DeleteStock extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(EditStockBarangActivity.this);
			pDialog.setMessage("Mengambil Data...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			int success;
			try
			{
				List<NameValuePair> param = new ArrayList<NameValuePair>();
				param.add(new BasicNameValuePair("id", id));

				JSONObject json = jsonParser.makeHTTPRequest(url_hapus_stock, "POST", param);

				Log.d("Hapus Data", json.toString());

				success = json.getInt(TAG_SUCCESS);
				if(success == 1)
				{
					Intent i = getIntent();
					setResult(100, i);
					finish();
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
		}
	}
}
