package com.fafa.adminb_aquaa;

import java.util.zip.Inflater;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class LaporanActivity extends Fragment {
Button coba;
Context context;

	public static Fragment newInstance(Context context) {
	LaporanActivity f = new LaporanActivity();
	 
    return f;
}

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
	View rootView = inflater.inflate(R.layout.activity_daftar_penjualan, container, false);
   context = rootView.getContext();
  //ViewGroup root = (ViewGroup) inflater.inflate(R.DaftarPenjualanActivity.class, null);
   Intent i = new Intent(getActivity(), DaftarPenjualanActivity.class);
   startActivity(i);
 
   return rootView;
}
}

