package com.fmi.android.webservices;

public interface NetworkCallHandler<ResponseType> {

	public void onSuccess(final ResponseType response);
	public void onError(final Error error);
}
