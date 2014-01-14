package com.fmi.android.webservices;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView timeTextView;
	
	private ProgressDialog progressDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		timeTextView = (TextView) findViewById(R.id.timeTextView);
		
		progressDialog = new ProgressDialog(this);
		progressDialog.setMessage("Getting time from server...");
		
		final Button callWebServiceButton = (Button) findViewById(R.id.triggerWebServiceButton);
		callWebServiceButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final GetCurrentTimeFromServerToDisplay call = new GetCurrentTimeFromServerToDisplay();
				
				progressDialog.show();
				
				call.execute(null);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private class GetCurrentTimeFromServerToDisplay extends GetCurrentTimeFromServer {

		@Override
		public void onSuccess(Time response) {
			timeTextView.setText(response.toString());
			progressDialog.hide();
		}

		@Override
		public void onError(Error error) {
			timeTextView.setText(error.message);
			progressDialog.hide();
		}
		
	}

}