package com.fafa.adminb_aquaa;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddStockActivity extends Activity {
	private ProgressDialog pDialog;

	JSONParser jsonParser = new JSONParser();
	private static String url_tambah_stock = "http://192.168.1.148/BAqua/tambah_stock.php";
	
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_NAMABARANG = "nama_barang";
	private static final String TAG_HARGA = "harga_barang";
	private static final String TAG_STOCK = "stock_barang";
	EditText editADDnamaB, editADDharga, editADDstock;
	Button btnAddStock, btnAddKembali;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_stock);
		editADDnamaB = (EditText) findViewById(R.id.editBarangADDSTOCK);
		editADDharga = (EditText) findViewById(R.id.editHargaADDSTOCK);
		editADDstock = (EditText) findViewById(R.id.editADDSTOCK);
		
		btnAddKembali = (Button) findViewById(R.id.btnKembaliADDSTOCK);
		btnAddKembali.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), TambahStockActivity.class);
				startActivity(i);		
				finish();

			}
		});
		btnAddStock = (Button) findViewById(R.id.btnSaveADDSTOCK);
		btnAddStock.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new TambahStock().execute();
			}
		});
	}
	class TambahStock extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(AddStockActivity.this);
			pDialog.setMessage("Sedang membuat pendaftaran...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			String nama_barang = editADDnamaB.getText().toString();
			String harga_barang = editADDharga.getText().toString();
			String stock_barang = editADDstock.getText().toString();
			
			List<NameValuePair> param = new ArrayList<NameValuePair>();
			
			param.add(new BasicNameValuePair(TAG_NAMABARANG, nama_barang));
			param.add(new BasicNameValuePair(TAG_HARGA, harga_barang));
			param.add(new BasicNameValuePair(TAG_STOCK, stock_barang));
			JSONObject json = jsonParser.makeHTTPRequest(url_tambah_stock, "POST", param);
			try
			{
				int success = json.getInt(TAG_SUCCESS);
				if(success ==1)
				{
					Intent i = new Intent(getApplicationContext(), TambahStockActivity.class);
					startActivity(i);		
					finish();
				}
				else
				{
					
				}
			}
			catch(JSONException e)
			{
				
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
