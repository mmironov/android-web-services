package com.fmi.android.webservices;

import android.os.AsyncTask;

public abstract class GetCurrentTimeFromServer extends AsynchronousNetworkCall<Void, Time> {

	@Override
	public void execute(Void request) {
		final CurrentTimeAsyncTask task = new CurrentTimeAsyncTask();
		task.execute();
	}
	
	private class CurrentTimeAsyncTask extends AsyncTask<Void, Void, Void> implements NetworkCallHandler<Time> {

		private Error error = null;
		private Time response = null;
		
		@Override
		protected Void doInBackground(Void... params) {
			final NetworkCall<Time> call = new NetworkCall<Time>();
			call.setHandler(this);
			call.getCurrentTimeFromServer();
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			
			if (response != null) {
				GetCurrentTimeFromServer.this.onSuccess(response);
			} else if (error != null) {
				GetCurrentTimeFromServer.this.onError(error);
			}
		}

		@Override
		public void onSuccess(Time response) {
			this.response = response;
		}

		@Override
		public void onError(Error error) {
			this.error = error;
		}
	}
}
