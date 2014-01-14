package com.fmi.android.webservices;


public abstract class AsynchronousNetworkCall<RequestType, ResponseType> {

	public abstract void execute(RequestType request);
	public abstract void onSuccess(final ResponseType response);
	public abstract void onError(final Error error);
}
