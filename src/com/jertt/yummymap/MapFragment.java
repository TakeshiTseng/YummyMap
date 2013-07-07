package com.jertt.yummymap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebViewFragment;

public class MapFragment extends Fragment{
	public MapFragment() {
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.map_fragment,container, false);
		
		WebView webView = (WebView) rootView.findViewById(R.id.webView1);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebViewClient(new WebViewClient());
		
		webView.loadUrl("http://198.199.107.149/jackwu/YMap/DOCTYPE.html");
		
		return rootView;
		

		// TODO 初始化
		// TODO 用 getArguments().getxxx 來取得傳過來的參數
	}
}
