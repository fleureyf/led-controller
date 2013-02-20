package com.fleurey.android.ledcontroller.notificationcontroller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.fleurey.android.ledcontroller.ScreenStateService;
import com.fleurey.android.ledcontroller.notificationcontroller.NotificationHelper.NotificationType;
import com.fleurey.android.ledcontroller.preferencescontroller.PreferenceKeys;

public class ScreenStateReceiver extends BroadcastReceiver {
	
	private static final String TAG = ScreenStateReceiver.class.getSimpleName();
	
	private SharedPreferences preferences;
	
    @Override
    public void onReceive(Context context, Intent intent) { 
    	preferences = PreferenceManager.getDefaultSharedPreferences(context);
    	if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction()) || ScreenStateService.ACTION_STARTED.equals(intent.getAction())) {
    		Log.d(TAG, "SCREEN_OFF__");
    		if (preferences.getBoolean(PreferenceKeys.PREF_MISSED_CALL, false)) {
    			NotificationHelper.cancellAll(context);
    			NotificationHelper.notify(context, NotificationType.MISSED_CALL);
    		} else if (preferences.getBoolean(PreferenceKeys.PREF_IS_FULL, false)) {
    			NotificationHelper.cancellAll(context);
    			NotificationHelper.notify(context, NotificationType.IS_FULL);
    		} else if (preferences.getBoolean(PreferenceKeys.PREF_ON_CHARGE, false)) {
    			NotificationHelper.cancellAll(context);
    			NotificationHelper.notify(context, NotificationType.ON_CHARGE);
    		}
    	} 
    }
}