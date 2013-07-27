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

public class MapsFragment extends Fragment {
	static final LatLng NKUT = new LatLng(23.979548, 120.696745);
	private GoogleMap map;
	SupportMapFragment mMapFragment;

	public MapsFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.map_fragment, container,
				false);

		map = ((SupportMapFragment) getActivity().getSupportFragmentManager()
				.findFragmentById(R.id.map)).getMap();

		mMapFragment = SupportMapFragment.newInstance();
		FragmentTransaction fragmentTransaction = getFragmentManager()
				.beginTransaction();
		fragmentTransaction.add(R.id.map, mMapFragment);
		fragmentTransaction.commit();

		Marker nkut = map.addMarker(new MarkerOptions().position(NKUT)
				.title("南開科技大學").snippet("數位生活創意系"));
		
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(NKUT, 16));

		
		return rootView;

		// TODO 初始化
		// TODO 用 getArguments().getxxx 來取得傳過來的參數
	}
}
