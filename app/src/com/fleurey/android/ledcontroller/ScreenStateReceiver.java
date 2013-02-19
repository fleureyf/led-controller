package com.fleurey.android.ledcontroller;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class ScreenStateReceiver extends BroadcastReceiver {
	
	private static final String TAG = ScreenStateReceiver.class.getSimpleName();
	
	private SharedPreferences preferences;
	private NotificationManager manager;
	
    @Override
    public void onReceive(Context context, Intent intent) { 
    	Log.d(TAG, "__ON_RECEIVE__");
    	preferences = PreferenceManager.getDefaultSharedPreferences(context);
    	manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    	if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction())) {
    		Log.d(TAG, "SCREEN_OFF__");
    		if (preferences.getBoolean(AccessibilityService.PREF_MISSED_CALL, false)) {
    			manager.cancelAll();
    			manager.notify(1000, NotificationBuilder.missedCall(context));
    		} else if (preferences.getBoolean(PowerConnectionReceiver.PREF_ON_CHARGE, false)) {
    			manager.notify(1000, NotificationBuilder.onCharge(context));
    		}
    	} 
    }
}