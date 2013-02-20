package com.fleurey.android.ledcontroller.notificationcontroller;

import com.fleurey.android.ledcontroller.notificationcontroller.NotificationHelper.NotificationType;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class PreferencesChangeReceiver extends BroadcastReceiver {

	private static final String TAG = PreferencesChangeReceiver.class.getSimpleName();
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d(TAG, "__ON_RECEIVED__");
		NotificationHelper.notify(context, NotificationType.IS_FULL);
	}

}
