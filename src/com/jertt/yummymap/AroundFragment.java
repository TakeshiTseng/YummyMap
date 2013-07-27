package com.jertt.yummymap;

import java.util.List;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListAdapter;



public class AroundFragment extends Fragment implements LocationListener{
	public AroundFragment() {
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.around_fragment,
				container, false);
		
		LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
		List<String> providers = locationManager.getAllProviders();
		for(String provider : providers){
			locationManager.requestLocationUpdates(provider, 0, 10, this);
		}
		
		// TODO 初始化
		// TODO 用 getArguments().getxxx 來取得傳過來的參數
		
		

		return rootView;
	}

	@Override
	public void onLocationChanged(Location location) {
		
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	
	
	class UpdateTask extends AsyncTask<Location, Void, List<String>>{

		@Override
		protected List<String> doInBackground(Location... params) {
			return null;
			
		}
		
		@Override
		protected void onPostExecute(List<String> result) {
			super.onPostExecute(result);
			
		}
		
	}
}
