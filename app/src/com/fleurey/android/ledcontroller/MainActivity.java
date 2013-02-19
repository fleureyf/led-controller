package com.fleurey.android.ledcontroller;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("DEBUG", "__ON_CREATE__");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		startService(new Intent(getApplicationContext(), ScreenStateService.class));
		Notification notification = testNotification();
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		notification.flags |= Notification.FLAG_SHOW_LIGHTS;
		notification.defaults |= Notification.DEFAULT_VIBRATE;
		manager.notify(1, notification);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	private Notification testNotification() {
		Notification.Builder builder = new Notification.Builder(getApplicationContext());
//		builder.setLights(Color.MAGENTA, 1, 0);
		builder.setContentTitle("Test notif");
		builder.setContentText("Test");
		builder.setSmallIcon(R.drawable.ic_launcher);
		return builder.build();
	}

}
