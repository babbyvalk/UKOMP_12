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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditDaftarActivity extends Activity {
	String id;
	EditText barangEDIT, hargaEDIT, qtyEDIT, totalEDIT, namaEDIT, jalanEDIT;
	Button kembaliEdit, hapusEdit, prosesEdit;
	
	private ProgressDialog pDialog;

	JSONParser jsonParser = new JSONParser();

	private static final String url_penjualan_details = "http://192.168.1.148/BAqua/get_penjualan_details.php";
	private static final String url_proses_pesanan = "http://192.168.1.148/BAqua/update_penjualan.php";
	private static final String url_hapus_pesanan = "http://192.168.1.148/BAqua/delete_penjualan.php";
	
	
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_PENJUALAN = "penjualan";
	private static final String TAG_ID = "id";
	private static final String TAG_NAMABARANG = "nama_barang";
	private static final String TAG_HARGA = "harga_barang";
	private static final String TAG_QTY = "qty";
	private static final String TAG_TOTAL = "total_harga";
	private static final String TAG_NAMA = "nama";
	private static final String TAG_JALAN = "jalan";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_daftar);
		Intent i = getIntent();
		id = i.getStringExtra(TAG_ID);
		new GetPenjualanDetails().execute();
		
		kembaliEdit = (Button) findViewById(R.id.btnKembaliEDIT);
		kembaliEdit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(i);		
				finish();
			}
		});
		
		prosesEdit = (Button) findViewById(R.id.btnKirimPesananEDIT);
		prosesEdit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new ProsesUpdatePesanan().execute();
			}
		});
		
		hapusEdit = (Button) findViewById(R.id.btnHapusPesanan);
		hapusEdit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DeletePenjualan().execute();
			}
		});
	}
	class GetPenjualanDetails extends AsyncTask<String, String, String> {
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(EditDaftarActivity.this);
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

						JSONObject json = jsonParser.makeHTTPRequest(url_penjualan_details, "GET", param);
						Log.d("Single pendaftaran Details", json.toString());
						success = json.getInt(TAG_SUCCESS);
						
						if(success == 1)
						{
							JSONArray penjualanOBJB = json.getJSONArray(TAG_PENJUALAN); 
																	
							JSONObject penjualan = penjualanOBJB.getJSONObject(0);
							barangEDIT = (EditText) findViewById(R.id.editBarangEDIT);
							hargaEDIT = (EditText) findViewById(R.id.editHargaEDIT);
							qtyEDIT = (EditText) findViewById(R.id.editQtyEDIT);
							totalEDIT = (EditText) findViewById(R.id.editTotalHargaEDIT);
							namaEDIT = (EditText) findViewById(R.id.editNamaEDIT);
							jalanEDIT = (EditText) findViewById(R.id.editJalanEDIT);
							
							barangEDIT.setText(penjualan.getString(TAG_NAMABARANG));
							hargaEDIT.setText(penjualan.getString(TAG_HARGA));
							qtyEDIT.setText(penjualan.getString(TAG_QTY));
							totalEDIT.setText(penjualan.getString(TAG_TOTAL));
							namaEDIT.setText(penjualan.getString(TAG_NAMA));
							jalanEDIT.setText(penjualan.getString(TAG_JALAN));
						}
						else
						{
							
						}
					}
					catch (JSONException e)
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
	
	class ProsesUpdatePesanan extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(EditDaftarActivity.this);
			pDialog.setMessage("Menyimpan data ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			List<NameValuePair> param = new ArrayList<NameValuePair>();
			param.add(new BasicNameValuePair(TAG_ID, id));
			JSONObject json = jsonParser.makeHTTPRequest(url_proses_pesanan, "POST", param);
			try
			{
				int success = json.getInt(TAG_SUCCESS);
				if (success == 1) 
				{
					// sukses di perbaharui
					Intent i = getIntent();
					// jika sukses maka kirimkan kode 100
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
	class DeletePenjualan extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(EditDaftarActivity.this);
			pDialog.setMessage("Menyimpan data ...");
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

				JSONObject json = jsonParser.makeHTTPRequest(url_hapus_pesanan, "POST", param);

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
