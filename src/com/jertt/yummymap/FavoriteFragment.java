package com.jertt.yummymap;

import java.io.File;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;

import android.R.anim;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class FavoriteFragment extends Fragment {
	ListView favorite_listview;
	private static final String[] mFoods = new String[] { "大餅包小餅", "蚵仔煎",
			"東山鴨頭", "臭豆腐", "潤餅", "豆花", "青蛙下蛋", "豬血糕", "大腸包小腸", "鹹水雞", "烤香腸",
			"車輪餅", "珍珠奶茶", "鹹酥雞", "大熱狗", "炸雞排", "山豬肉", "花生冰", "剉冰", "水果冰",
			"包心粉圓", "排骨酥", "沙茶魷魚", "章魚燒", "度小月", "aaa", "abc", "bbb", "bcd",
			"123" };

	ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	private SimpleAdapter adapter;
    YMapAPI ymap = new YMapAPI();
	public FavoriteFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.favorite_fragment, container,
				false);
		// TODO 初始化
		// TODO 用 getArguments().getxxx 來取得傳過來的參數
		favorite_listview = (ListView)rootView.findViewById(R.id.listView1);
		
		for (int i = 0; i < mFoods.length; i++) {
			HashMap<String, String> item = new HashMap<String, String>();
			item.put("food", mFoods[i]);
			list.add(item);
		}
		adapter = new SimpleAdapter(rootView.getContext(), list,
				R.layout.favorite_listview, new String[] { "food" },
				new int[] { R.id.favoritetext1});

	    favorite_listview.setAdapter(adapter);
		favorite_listview.setTextFilterEnabled(true); 

		return rootView;
	}

	public String search(String longitude, String latitude, int radius) {
		String res = null;
		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet("http://ym.takeshi.tw:8080/search?long="
					+ longitude + "&lat=" + latitude + "&radius=" + radius);
			HttpResponse response = client.execute(get);
			int len = (int) response.getEntity().getContentLength();
			byte[] responseBody = new byte[len];

			InputStream is = response.getEntity().getContent();
			is.read(responseBody, 0, len);
			res = new String(responseBody, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	public String upload(String longitude, String latitude, String name,
			String desc, String type, String photoid) {
		String res = null;
		try {
			HttpClient client = new DefaultHttpClient();
			String enc = "utf-8";
			String url = "http://ym.takeshi.tw:8080/upload?long="
					+ URLEncoder.encode(longitude, "utf-8") + "&lat="
					+ URLEncoder.encode(latitude, enc) + "&name="
					+ URLEncoder.encode(name, enc) + "&desc="
					+ URLEncoder.encode(desc, enc) + "&type="
					+ URLEncoder.encode(type, enc) + "&photoid="
					+ URLEncoder.encode(photoid, enc);

			System.out.println(url);
			HttpGet get = new HttpGet(url);

			HttpResponse response = client.execute(get);

			int len = (int) response.getEntity().getContentLength();
			byte[] responseBody = new byte[len];

			InputStream is = response.getEntity().getContent();
			is.read(responseBody, 0, len);
			res = new String(responseBody, "UTF-8");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public String uploadPhoto(File photo) {
		String res = null;
		try {
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(
					"http://ym.takeshi.tw:8080/upload_photo");

			MultipartEntity entity = new MultipartEntity();
			entity.addPart("file", new FileBody(photo));
			post.setEntity(entity);

			HttpResponse response = client.execute(post);
			int len = (int) response.getEntity().getContentLength();
			byte[] responseBody = new byte[len];

			InputStream is = response.getEntity().getContent();
			is.read(responseBody, 0, len);
			res = new String(responseBody, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
}
