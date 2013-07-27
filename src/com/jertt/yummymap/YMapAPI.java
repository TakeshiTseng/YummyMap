package com.jertt.yummymap;

import java.io.File;
import java.io.InputStream;
import java.net.URLEncoder;
import org.apache.http.HttpResponse;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.*;

public class YMapAPI {
//	static final String HOST = "http://localhost:8080";
	static final String HOST = "http://ym.takeshi.tw:8080";
	public static String search(double longitude, double latitude, int radius) {
		String res = null;
		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet(HOST + "/search?long="
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

	public static String createPlace(String name, double longitude, double latitude,
			String description, int type, String photoid, String authorId,
			int star) {

		String res = null;

		try {

			String enc = "utf-8";
			String url = HOST + "/create_place?name="
					+ URLEncoder.encode(name, enc) + "&long=" + longitude
					+ "&lat=" + latitude + "&desc="
					+ URLEncoder.encode(description, enc) + "&type=" + type
					+ "&photoid=" + photoid + "&author_id=" + authorId
					+ "&star=" + star;
			HttpClient client = new DefaultHttpClient();
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

	public static String addDescription(String placeId, String description,
			String photoId, String authorId, int star) {
		String res = null;
		try {
			String enc = "utf-8";
			String url = HOST + "/add_description?id="
					+ placeId + "&desc=" + URLEncoder.encode(description, enc)
					+ "&photoid=" + photoId + "&author_id=" + authorId
					+ "&star=" + star;
			HttpClient client = new DefaultHttpClient();
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

	public static String uploadPhoto(File photo) {
		String res = null;
		try {
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(
					HOST + "/upload_photo");

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
	
	public static byte[] downloadPhoto(String pid){
		byte[] res = null;
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(HOST + "/get_photo?pid="+pid);
		try {
			HttpResponse response = client.execute(get);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return res;
	}
	
}
