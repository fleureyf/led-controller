package com.fleurey.android.ledcontroller;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

public class AccessibilityService extends android.accessibilityservice.AccessibilityService {

	public static final String PREF_MISSED_CALL = PowerConnectionReceiver.class.getName() + "_PREF_MISSED_CALL";
	public static final String PREF_MMS_RECEIVED = PowerConnectionReceiver.class.getName() + "_PREF_MMS_RECEIVED";
	
	private static final String TAG = AccessibilityService.class.getSimpleName();
	
	@Override
	protected void onServiceConnected() {
		Log.d(TAG, "__ON_SERVICE_CONNECTED__");
	}

	@Override
	public void onAccessibilityEvent(AccessibilityEvent event) {
		Log.d(TAG, "__ON_ACCESSIBILITY_EVENT__");
		Log.d(TAG, event.toString());
		Log.d(TAG, event.getPackageName().toString());
		Log.d(TAG, event.getText().toString());
		checktoCancel(event);
		if ("com.android.phone".equals(event.getPackageName().toString()) && event.getText().toString().startsWith("[Missed call from")) {
			Log.e(TAG, "Missed call");
			PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putBoolean(PREF_MISSED_CALL, true).commit();
			//((NotificationManager) getSystemService(NOTIFICATION_SERVICE)).notify(3, NotificationBuilder.missedCall(getApplicationContext()));
		}
	}

	@Override
	public void onInterrupt() {
		// TODO Auto-generated method stub
	}

	private void checktoCancel(AccessibilityEvent event) {
		if (event.getEventType() == AccessibilityEvent.TYPE_VIEW_CLICKED) {
			if ("com.android.launcher".equals(event.getPackageName().toString()) && "[Phone]".equals(event.getText().toString())) {
				Log.e(TAG, "Phone opened");
				PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putBoolean(PREF_MISSED_CALL, false).commit();
			}
		}
	}
	
}
