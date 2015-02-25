package com.fafa.adminb_aquaa;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PenjualanActivity extends Fragment{ 
	private PenjualanActivity Fragment;
	public static Fragment newInstance(Context context) {
		PenjualanActivity f = new PenjualanActivity();
		 
	    return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
	    ViewGroup root = (ViewGroup) inflater.inflate(R.layout.activity_laporan_penjualan, null);
	    Intent i = new Intent(getActivity(), TabLaporanActivity.class);
	    startActivity(i);
	    return root;
	}

}
