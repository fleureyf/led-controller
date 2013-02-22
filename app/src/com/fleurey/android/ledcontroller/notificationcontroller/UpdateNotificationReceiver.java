package com.fleurey.android.ledcontroller.notificationcontroller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.fleurey.android.ledcontroller.notificationcontroller.NotificationHelper.NotificationType;
import com.fleurey.android.ledcontroller.powercontroller.CheckIsFullService;
import com.fleurey.android.ledcontroller.preferencescontroller.PreferenceKeys;

public class UpdateNotificationReceiver extends BroadcastReceiver {
	
	public static final String ACTION_ASK_FOR_UPDATE = UpdateNotificationReceiver.class.getName() + ".ACTION_ASK_FOR_UPDATE";
	
	private static final String TAG = UpdateNotificationReceiver.class.getSimpleName();
	
	private SharedPreferences preferences;
	
    @Override
    public void onReceive(Context context, Intent intent) { 
    	preferences = PreferenceManager.getDefaultSharedPreferences(context);
    	if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction())) {
    		Log.d(TAG, "__SCREEN_OFF__");
    		updateNotification(context);
    	} else if (ACTION_ASK_FOR_UPDATE.equals(intent.getAction())) {
    		Log.d(TAG, "__ACTION_ASK_FOR_UPDATE__");
    		updateNotification(context);
    	} else if (CheckIsFullService.ACTION_IS_FULL.equals(intent.getAction())) {
    		Log.d(TAG, "__ACTION_IS_FULL__");
    		if (preferences.getBoolean(PreferenceKeys.PREF_ON_CHARGE, false)) {
    			NotificationHelper.cancellAll(context);
    			NotificationHelper.notify(context, NotificationType.IS_FULL);
    		}
    	}
    }

	private void updateNotification(Context context) {
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