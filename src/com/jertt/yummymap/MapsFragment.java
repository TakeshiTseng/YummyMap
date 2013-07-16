package com.jertt.yummymap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends Fragment{
	static final LatLng NKUT = new LatLng(23.979548, 120.696745);
	private GoogleMap map;
	SupportMapFragment mMapFragment;
	public MapsFragment() {
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.map_fragment,container, false);
		
		map = ((SupportMapFragment)getActivity().getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
	
		 mMapFragment = SupportMapFragment.newInstance();
		 FragmentTransaction fragmentTransaction =
		         getFragmentManager().beginTransaction();
		 fragmentTransaction.add(R.id.map,mMapFragment);
		 fragmentTransaction.commit();
		
			Marker nkut = map.addMarker(new MarkerOptions().position(NKUT).title("南開科技大學").snippet("數位生活創意系"));
			map.moveCamera(CameraUpdateFactory.newLatLngZoom(NKUT, 16));
			  
//		WebView webView = (WebView) rootView.findViewById(R.id.webView1);
//		webView.getSettings().setJavaScriptEnabled(true);
//		webView.setWebViewClient(new WebViewClient());
//		
//		webView.loadUrl("https://google-developers.appspot.com/maps/documentation/javascript/examples/full/map-geolocation?hl=zh-tw");
//		webView.loadUrl("http://192.241.139.195/elmerlu/test.html");
		

		return rootView;
		

		// TODO 初始化
		// TODO 用 getArguments().getxxx 來取得傳過來的參數
	}
	
	
//@Override
//	public void onResume() {
//		// TODO Auto-generated method stub
//		super.onResume();
//		setUpMapIfNeeded();
//	}
//private void setUpMapIfNeeded() {
//    // Do a null check to confirm that we have not already instantiated the map.
//    if (map == null) {
//        // Try to obtain the map from the SupportMapFragment.
//        map = ((SupportMapFragment) getFragmentManager().findFragmentById(R.id.map))
//                .getMap();
//        // Check if we were successful in obtaining the map.
//        if (map != null) {
//            setUpMap();
//        }
//    }
//}
//private void setUpMap() {
//    map.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
//}
}
