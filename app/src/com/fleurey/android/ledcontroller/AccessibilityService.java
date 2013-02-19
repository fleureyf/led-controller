package com.fleurey.android.ledcontroller;

import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

public class AccessibilityService extends android.accessibilityservice.AccessibilityService {

	private static final String TAG = AccessibilityService.class.getSimpleName();
	
	@Override
	protected void onServiceConnected() {
		Log.d(TAG, "__ON_SERVICE_CONNECTED__");
	}

	@Override
	public void onAccessibilityEvent(AccessibilityEvent event) {
		Log.d(TAG, "__ON_ACCESSIBILITY_EVENT__");
	}

	@Override
	public void onInterrupt() {
		// TODO Auto-generated method stub
	}

}
