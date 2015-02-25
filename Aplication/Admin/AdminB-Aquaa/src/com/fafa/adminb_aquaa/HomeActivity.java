package com.fafa.adminb_aquaa;

import android.R.color;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeActivity extends Fragment{
	Button coba;
	public static Fragment newInstance(Context context) {
		HomeActivity f = new HomeActivity();
		 
	    return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
	    ViewGroup root = (ViewGroup) inflater.inflate(R.layout.activity_beranda, null);
	    return root;
	}
	
}
