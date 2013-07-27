package com.jertt.yummymap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;
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
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

public class AroundFragment extends Fragment implements LocationListener {
	
	
	public AroundFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.around_fragment, container,
				false);

		LocationManager locationManager = (LocationManager) getActivity()
				.getSystemService(Context.LOCATION_SERVICE);
		List<String> providers = locationManager.getAllProviders();
		for (String provider : providers) {
			locationManager.requestLocationUpdates(provider, 0, 10, this);
		}

		// TODO 初始化
		// TODO 用 getArguments().getxxx 來取得傳過來的參數
		return rootView;
	}

	@Override
	public void onLocationChanged(Location location) {
		new UpdateTask().execute(location);

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

	class UpdateTask extends AsyncTask<Location, Void, List<String>> {

		@Override
		protected List<String> doInBackground(Location... params) {
			Location location = params[0];
			List<HashMap<String, String>> names = new ArrayList<HashMap<String, String>>();

			// search 100 meter food!
			String resString = YMapAPI.search(location.getLongitude(),
					location.getLatitude(), 100);

			try {
				JSONObject res = new JSONObject(resString);
				boolean err = res.getBoolean("error");
				if (!err) {
					JSONArray arr = res.getJSONArray("data");
					for (int i = 0; i < arr.length(); i++) {
						JSONObject place = arr.getJSONObject(i);
						// add list element here
						
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(List<String> result) {
			super.onPostExecute(result);
			
			

		}

	}
	
	
}
