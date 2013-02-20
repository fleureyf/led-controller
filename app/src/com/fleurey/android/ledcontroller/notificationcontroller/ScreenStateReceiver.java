package com.fleurey.android.ledcontroller.notificationcontroller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.fleurey.android.ledcontroller.notificationcontroller.NotificationHelper.NotificationType;
import com.fleurey.android.ledcontroller.preferencescontroller.PreferenceKeys;

public class ScreenStateReceiver extends BroadcastReceiver {
	
	private static final String TAG = ScreenStateReceiver.class.getSimpleName();
	
	private SharedPreferences preferences;
	
    @Override
    public void onReceive(Context context, Intent intent) { 
    	preferences = PreferenceManager.getDefaultSharedPreferences(context);
    	if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction())) {
    		Log.d(TAG, "SCREEN_OFF__");
    		if (preferences.getBoolean(PreferenceKeys.PREF_MISSED_CALL, false)) {
    			Log.w(TAG, "Notify: MISSED_CALL");
    			NotificationHelper.notify(context, NotificationType.MISSED_CALL);
    		} else if (preferences.getBoolean(PreferenceKeys.PREF_IS_FULL, false)) {
    			Log.w(TAG, "Notify: IS_FULL");
    			NotificationHelper.notify(context, NotificationType.IS_FULL);
    		} else if (preferences.getBoolean(PreferenceKeys.PREF_ON_CHARGE, false)) {
    			Log.w(TAG, "Notify: ON_CHARGE");
    			NotificationHelper.notify(context, NotificationType.ON_CHARGE);
    		}
    	} 
    }
}