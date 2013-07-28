package com.jertt.yummymap;

import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.NYXDigital.NiceSupportMapFragment;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends Fragment {
	static final LatLng NKUT = new LatLng(23.979548, 120.696745);
	private GoogleMap map;
	NiceSupportMapFragment mMapFragment;
	Location myLocation;
	public MapsFragment() {
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View v = inflater.inflate(R.layout.map_fragment,null);

			mMapFragment = ((com.NYXDigital.NiceSupportMapFragment)getActivity().getSupportFragmentManager().findFragmentById(R.id.map));
			map = mMapFragment.getMap();
			map.setOnMyLocationChangeListener(new OnMyLocationChangeListener() {
				
				@Override
				public void onMyLocationChange(Location location) {
					// TODO Auto-generated method stub
					myLocation=location;
					map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(myLocation.getLatitude(),myLocation.getLongitude()),16));
				}
			});
			FragmentTransaction fragmentTransaction = getChildFragmentManager()
					.beginTransaction();
			fragmentTransaction.add(R.id.map, mMapFragment);
			fragmentTransaction.commit();
			

		return v;
	}
	private class DataDownloadTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}
