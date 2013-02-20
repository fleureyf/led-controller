package com.fleurey.android.ledcontroller.preferencescontroller;


import android.content.SharedPreferences;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

public class CheckToSet {

	private static final String TAG = CheckToSet.class.getSimpleName();
	
	public static boolean checkMissedCall(SharedPreferences preferences, AccessibilityEvent event) {
		if (event.getEventType() == AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED) {
			if ("com.android.phone".equals(event.getPackageName().toString()) && event.getText().toString().startsWith("[Missed call from")) {
				Log.e(TAG, "Missed call");
				preferences.edit().putBoolean(PreferenceKeys.PREF_MISSED_CALL, true).commit();
				return true;
			}
		}
		return false;
	}
}
