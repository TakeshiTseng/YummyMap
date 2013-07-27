package com.jertt.yummymap;

import java.util.*;
import org.json.*;
import android.content.Context;
import android.location.*;
import android.os.*;
import android.support.v4.app.Fragment;
import android.view.*;
import android.widget.*;

public class AroundFragment extends Fragment implements LocationListener {
	private SimpleAdapter adapter;
	ListView around_listview;
	ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

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
		around_listview = (ListView)rootView.findViewById(R.id.listView1);
		adapter = new SimpleAdapter(rootView.getContext(), list,
				R.layout.favorite_listview, new String[] { "top", "name",
						"people","star","photo" }, new int[] { R.id.aroundtext1, R.id.aroundtext2,
						R.id.aroundtext3 , R.id.aroundtext4 ,R.id.imageView1});
		around_listview.setAdapter(adapter);
		around_listview.setTextFilterEnabled(true); 
		
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

	class UpdateTask extends AsyncTask<Location, Void, List<HashMap<String, Object>>> {

		@Override
		protected List<HashMap<String, Object>> doInBackground(Location... params) {
			Location location = params[0];
			// search 100 meter food!
			String resString = YMapAPI.search(location.getLongitude(),
					location.getLatitude(), 100);
			list.clear();
			try {
				JSONObject res = new JSONObject(resString);
				boolean err = res.getBoolean("error");
				if (!err) {
					JSONArray arr = res.getJSONArray("data");
					for (int i = 0; i < arr.length(); i++) {
						HashMap<String, Object> item = new HashMap<String, Object>();
						JSONObject place = arr.getJSONObject(i);
						// add list element here
						item.put("top", "top "+ (i+1));
						item.put("name",place.getString("name"));
						item.put("type", place.getInt("type"));
						item.put("people", place.getJSONArray("descriptions").length()+"人來過");
						JSONObject des = (JSONObject) place.getJSONArray("descriptions").get(0);
						item.put("photo", "http://ym.takeshi.tw:8080/get_photo?pid="+des.getString("photoid"));
						item.put("star", des.getString("star")+"星");
						list.add(item);	
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return list;
		}
	
		@Override
		protected void onPostExecute(List<HashMap<String, Object>> result) {
			super.onPostExecute(result);
			adapter.notifyDataSetChanged();
		}

	}
	
	
}
