package com.fmi.android.webservices;

import org.json.JSONException;
import org.json.JSONObject;

public class ObjectBuilder {

	private static final String KEY_TIME = "time";
	private static final String KEY_MILLISECONDS_SINCE_EPOCH = "milliseconds_since_epoch";
	private static final String KEY_DATE = "date";
	
	public static Time parseTime(JSONObject json) {
		Time time = new Time();
		
		try {
			time.setTime(json.getString(KEY_TIME));
			time.setMillisecondsSinceEpoch(json.getLong(KEY_MILLISECONDS_SINCE_EPOCH));
			time.setDate(json.getString(KEY_DATE));
			
			return time;
		} catch (JSONException e) {
			return null;
		}
	}
}
