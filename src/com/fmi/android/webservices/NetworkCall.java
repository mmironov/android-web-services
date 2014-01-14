package com.fmi.android.webservices;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

public class NetworkCall<ResponseType> {
	
	public static final String SERVER_URL = "http://time.jsontest.com/";
	
	public NetworkCallHandler<ResponseType> handler;
	
	public void setHandler(final NetworkCallHandler<ResponseType> handler) {
		this.handler = handler;
	}
	
	@SuppressWarnings("unchecked")
	public void getCurrentTimeFromServer() {
		
		final HttpClient httpClient = new DefaultHttpClient();
		final HttpGet httpGet = new HttpGet(SERVER_URL);
		
		try {
			final HttpResponse response = httpClient.execute(httpGet);
			
			ByteArrayOutputStream bitesOut = new ByteArrayOutputStream();
			
			response.getEntity().writeTo(bitesOut);
			
			final String responseString = new String(bitesOut.toByteArray());
			
			try {
				final JSONObject json = new JSONObject(responseString);
				
				final Time result = ObjectBuilder.parseTime(json);
				
				if (handler != null) {					
					handler.onSuccess((ResponseType)result);
				}
				
			} catch (JSONException e) {
				final Error error = new Error();
				error.code = 100;
				error.message = "JSon Error";
				
				if (handler != null) {					
					handler.onError(error);
				}
			}
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			final Error error = new Error();
			error.code = 502;
			error.message = "Network Error";
			
			if (handler != null) {				
				handler.onError(error);
			}
		}
	}
}
