package com.fafa.b_aqua;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class PenjualanActivity extends Activity {
	//TextView Untuk Input ke database
	TextView namaBarang, hargaBarang, QTY, totalProses, textTGL, textBLN, textTHN, textSTK, textUpdate, textWKT, textKTR;
	EditText namaPembeli, jalanPembeli, rtPembeli, rwPembeli, noPembeli;
	Button prosesTotal, pesanBRNG, btnBatal;
	Spinner tgl, bln, thn;
	private ProgressDialog pDialog;
	JSONParser jParser = new JSONParser();
	protected int angka1, angka2, angka3, angkahasil, angkahasil2, angkaupdate;
	private static String url_updateStock = "http://192.168.1.148/BAqua/update_fieldstock.php";
	private static String url_pesan = "http://192.168.1.148/BAqua/pesan_barang.php";
	private static final String TAG_SUCCESS = "success";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_penjualan);
		
	namaBarang = (TextView) findViewById(R.id.textNamaBarang);
	hargaBarang = (TextView) findViewById(R.id.textHargaBarang);
	QTY = (TextView) findViewById(R.id.textQTY);
	prosesTotal = (Button) findViewById(R.id.btnProsesTotal);
	totalProses = (TextView) findViewById(R.id.textTotal);
	namaPembeli = (EditText) findViewById(R.id.editNama);
	jalanPembeli = (EditText) findViewById(R.id.editJalan);
	rtPembeli = (EditText) findViewById(R.id.editRT);
	rwPembeli = (EditText) findViewById(R.id.editRW);
	noPembeli = (EditText) findViewById(R.id.editNO);
	tgl = (Spinner) findViewById(R.id.spinTanggal);
	bln = (Spinner) findViewById(R.id.spinBulan);
	thn = (Spinner) findViewById(R.id.spinTahun);
	textTGL = (TextView) findViewById(R.id.textTanggal);
	textBLN = (TextView) findViewById(R.id.textBulan);
	textTHN = (TextView) findViewById(R.id.textTahun);
	textSTK = (TextView) findViewById(R.id.textStock);
	textUpdate = (TextView) findViewById(R.id.textUpdateStock);
	pesanBRNG = (Button) findViewById(R.id.btnPesanBarang);
	textWKT = (TextView) findViewById(R.id.textWaktu);
	textKTR = (TextView) findViewById(R.id.textKeterangan);
	btnBatal = (Button) findViewById(R.id.btnBatalkan);
	
	
	btnBatal.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent penjualan = new Intent(PenjualanActivity.this, BerandaActivity.class);
			startActivity(penjualan);	
		}
	});
	pesanBRNG.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			new UpdateStock().execute();
			new beliBarang().execute();
		}
	});
	List<String> list = new ArrayList<String>();
    list.add("1");
    list.add("2");
    list.add("3");
    list.add("4");
    list.add("5");
    list.add("5");
    list.add("6");
    list.add("7");
    list.add("8");
    list.add("9");
    list.add("10");
    list.add("11");
    list.add("12");
    list.add("13");
    list.add("14");
    list.add("15");
    list.add("16");
    list.add("17");
    list.add("18");
    list.add("19");
    list.add("20");
    list.add("21");
    list.add("22");
    list.add("23");
    list.add("24");
    list.add("25");
    list.add("26");
    list.add("27");
    list.add("28");
    list.add("29");
    list.add("30");
    list.add("31");
    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                 (this, android.R.layout.simple_spinner_item,list);
    dataAdapter.setDropDownViewResource
                 (android.R.layout.simple_spinner_dropdown_item);
    tgl.setAdapter(dataAdapter);
    tgl.setOnItemSelectedListener(new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View arg1, int position,
				long arg3) {
			// TODO Auto-generated method stub
				String p = parent.getItemAtPosition(position).toString();
				textTGL.setText(p);
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
	});
	
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
    bln.setAdapter(dataAdapter1);
    bln.setOnItemSelectedListener(new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View arg1, int position,
				long arg3) {
			// TODO Auto-generated method stub
			String p = parent.getItemAtPosition(position).toString();
			textBLN.setText(p);
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
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
	thn.setAdapter(dataAdapter2);
	thn.setOnItemSelectedListener(new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View arg1, int position,
				long arg3) {
			// TODO Auto-generated method stub
			String p = parent.getItemAtPosition(position).toString();
			textTHN.setText(p);
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
	});
	
	if (android.os.Build.VERSION.SDK_INT > 9) {
		 StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		 StrictMode.setThreadPolicy(policy);
		 }
	final Spinner spinBarang = (Spinner) findViewById(R.id.spinNamaBarang);
	final Spinner spinQty = (Spinner) findViewById(R.id.spinQTY);
	
	String url = "http://192.168.1.148/BAqua/barang/getBarang.php";
	String url_qty = "http://192.168.1.148/BAqua/qty/getQTY.php";

	try{
		JSONArray data = new JSONArray(getJSONUrl(url));
		JSONArray data2 = new JSONArray(getJSONUrl(url_qty));
		
		Log.d("Create Response", data.toString());
		
		final ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
		final ArrayList<HashMap<String, String>> MyArrList2 = new ArrayList<HashMap<String, String>>();
		
		HashMap<String, String> map;
		HashMap<String, String> map2;
		
		for(int i = 0; i < data.length(); i++)
		{
			JSONObject c = data.getJSONObject(i);
			map = new HashMap<String, String>();
			map.put("nama_barang", c.getString("nama_barang"));
			map.put("harga_barang", c.getString("harga_barang"));
			map.put("stock_barang", c.getString("stock_barang"));
			MyArrList.add(map);
		}
		for(int i2 = 0; i2 < data2.length(); i2++)
		{
			JSONObject c2 = data2.getJSONObject(i2);
			map2 = new HashMap<String, String>();
			map2.put("pid", c2.getString("pid"));
			map2.put("qty", c2.getString("qty"));
			MyArrList2.add(map2);
		}
		SimpleAdapter sAdap;
		sAdap = new SimpleAdapter(PenjualanActivity.this, MyArrList, R.layout.manggilkomponen,
				new String[] {"nama_barang"}, new int[] {R.id.textNamaB});
		spinBarang.setAdapter(sAdap);
		SimpleAdapter tAdap;
		tAdap = new SimpleAdapter(PenjualanActivity.this, MyArrList2, R.layout.manggilkomponen,
				new String[] {"qty"}, new int[] {R.id.textPCS});
		spinQty.setAdapter(tAdap);
		
		
		spinBarang.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View selectedItemView,
					int position, long id) {
				// TODO Auto-generated method stub
				String cNama = MyArrList.get(position).get("nama_barang").toString();
				String cHarga = MyArrList.get(position).get("harga_barang").toString();
				String cStock = MyArrList.get(position).get("stock_barang").toString();
				namaBarang.setText(cNama);
				hargaBarang.setText(cHarga);
				textSTK.setText(cStock);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		
		});
		
		spinQty.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View selectedItemView,
					int position, long id) {
				// TODO Auto-generated method stub
				String cID = MyArrList2.get(position).get("pid").toString();
				QTY.setText(cID);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	catch(JSONException e)
	{
		e.printStackTrace();
	}
	prosesTotal.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
		angka1 = Integer.parseInt(hargaBarang.getText().toString());
		angka2 = Integer.parseInt(QTY.getText().toString());
		angka3 = Integer.parseInt(textSTK.getText().toString());
		switch (v.getId()) {
		case R.id.btnProsesTotal:
			angkahasil = angka1*angka2;
			angkaupdate = angka3-angka2;
			totalProses.setText(Integer.toString(angkahasil));
			textUpdate.setText(Integer.toString(angkaupdate));
			break;
		default:
		}
		}
	});
		
	}
	public String getJSONUrl(String url) {
		 StringBuilder str = new StringBuilder();
		 HttpClient client = new DefaultHttpClient();
		 HttpGet httpGet = new HttpGet(url);
		 try {
		 HttpResponse response = client.execute(httpGet);
		 StatusLine statusLine = response.getStatusLine();
		 int statusCode = statusLine.getStatusCode();
		 if (statusCode == 200) { // Download OK
		 HttpEntity entity = response.getEntity();
		 InputStream content = entity.getContent();
		 BufferedReader reader = new BufferedReader(new InputStreamReader(content));
		 String line;
		 while ((line = reader.readLine()) != null) {
		 str.append(line);
		 }
		 } else {
		 Log.e("Log", "Failed to download result..");
		 }
		 } catch (ClientProtocolException e) {
		 e.printStackTrace();
		 } catch (IOException e) {
		 e.printStackTrace();
		 }
		 
		 return str.toString();
		 }
	class UpdateStock extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(PenjualanActivity.this);
			pDialog.setMessage("Sedang membuat pendaftaran...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			
			String nama_barang = namaBarang.getText().toString();
			String stock_barang = textUpdate.getText().toString();
			
			List<NameValuePair> param = new ArrayList<NameValuePair>();
	
			param.add(new BasicNameValuePair("nama_barang", nama_barang));
			param.add(new BasicNameValuePair("stock_barang", stock_barang));
			
			JSONObject json = jParser.makeHTTPRequest(url_updateStock, "POST", param);
			Log.d("Create Response", json.toString());
			
			try{
				int success = json.getInt(TAG_SUCCESS);
				if(success == 1)
				{
					Intent penjualan = new Intent(PenjualanActivity.this, PenjualanActivity.class);
					startActivity(penjualan);
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
	class beliBarang extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(PenjualanActivity.this);
			pDialog.setMessage("Sedang membuat pendaftaran...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			String nama_barang = namaBarang.getText().toString();
			String harga_barang = hargaBarang.getText().toString();
			String qty = QTY.getText().toString();
			String total_harga = totalProses.getText().toString();
			String nama = namaPembeli.getText().toString();
			String jalan = jalanPembeli.getText().toString();
			String rt = rtPembeli.getText().toString();
			String rw = rwPembeli.getText().toString();
			String no_rumah = noPembeli.getText().toString();
			String tgl = textTGL.getText().toString();
			String bln = textBLN.getText().toString();
			String tahun = textTHN.getText().toString();
			List<NameValuePair> param = new ArrayList<NameValuePair>();

			param.add(new BasicNameValuePair("nama_barang", nama_barang));
			param.add(new BasicNameValuePair("harga_barang", harga_barang));
			param.add(new BasicNameValuePair("qty", qty));
			param.add(new BasicNameValuePair("total_harga", total_harga));
			param.add(new BasicNameValuePair("nama", nama));
			param.add(new BasicNameValuePair("jalan", jalan));
			param.add(new BasicNameValuePair("rt", rt));
			param.add(new BasicNameValuePair("rw", rw));
			param.add(new BasicNameValuePair("no_rumah", no_rumah));
			param.add(new BasicNameValuePair("tgl", tgl));
			param.add(new BasicNameValuePair("bln", bln));
			param.add(new BasicNameValuePair("tahun", tahun));
			
			JSONObject json = jParser.makeHTTPRequest(url_pesan, "POST", param);
			Log.d("Create Response", json.toString());
			try{
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) 
				{
					Intent penjualan = new Intent(PenjualanActivity.this, PenjualanActivity.class);
					startActivity(penjualan);
				}
				else
				{
					
				}

			}
			catch (JSONException e)
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