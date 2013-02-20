package com.fleurey.android.ledcontroller;

import com.fleurey.android.ledcontroller.preferencescontroller.CheckToDismiss;
import com.fleurey.android.ledcontroller.preferencescontroller.CheckToSet;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

public class AccessibilityService extends android.accessibilityservice.AccessibilityService {
	
	private static final String TAG = AccessibilityService.class.getSimpleName();
	
	private SharedPreferences preferences;
	
	@Override
	protected void onServiceConnected() {
		Log.d(TAG, "__ON_SERVICE_CONNECTED__");
		preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
	}

	@Override
	public void onAccessibilityEvent(AccessibilityEvent event) {
		Log.d(TAG, "__ON_ACCESSIBILITY_EVENT__");
		Log.d(TAG, event.toString());
		CheckToDismiss.checkMissedCall(preferences, event);
		CheckToSet.checkMissedCall(preferences, event);
	}

	@Override
	public void onInterrupt() {
		// TODO Auto-generated method stub
	}
	
}
