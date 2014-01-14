package com.fmi.android.webservices;

import java.util.Locale;

public class Time {

	private String time;
	private long millisecondsSinceEpoch;
	private String date;
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public long getMillisecondsSinceEpoch() {
		return millisecondsSinceEpoch;
	}
	public void setMillisecondsSinceEpoch(long millisecondsSinceEpoch) {
		this.millisecondsSinceEpoch = millisecondsSinceEpoch;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		final String result = String.format(Locale.US, "%s\n%d\n%s", time, millisecondsSinceEpoch, date);
		
		return result;
	}
}
