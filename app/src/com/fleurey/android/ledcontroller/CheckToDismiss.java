package com.fleurey.android.ledcontroller;

import android.content.SharedPreferences;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

public class CheckToDismiss {

	private static final String TAG = CheckToDismiss.class.getSimpleName();
	
	public static boolean checkMissedCall(SharedPreferences preferences, AccessibilityEvent event) {
		if (event.getEventType() == AccessibilityEvent.TYPE_VIEW_CLICKED) {
			if ("com.android.launcher".equals(event.getPackageName().toString()) && "[Phone]".equals(event.getText().toString())) {
				Log.e(TAG, "[Check] Set missed call false");
				preferences.edit().putBoolean(PreferenceKeys.PREF_MISSED_CALL, false).commit();
				return true;
			}
		}
		return false;
	}
	
}
