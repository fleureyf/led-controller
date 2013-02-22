package com.fleurey.android.ledcontroller.powercontroller;

import com.fleurey.android.ledcontroller.BackgroundService;
import com.fleurey.android.ledcontroller.notificationcontroller.UpdateNotificationReceiver;
import com.fleurey.android.ledcontroller.preferencescontroller.PreferenceKeys;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.BatteryManager;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

public class PowerConnectionReceiver extends BroadcastReceiver {
		
	private static final String TAG = PowerConnectionReceiver.class.getSimpleName();
	
	private SharedPreferences preferences;
	
    @Override
    public void onReceive(Context context, Intent intent) { 
    	preferences = PreferenceManager.getDefaultSharedPreferences(context);
    	Toast.makeText(context, "Intent received", Toast.LENGTH_LONG).show();
    	if (BackgroundService.ACTION_STARTED.equals(intent.getAction())) {
    		Log.d(TAG, "__ACTION_STARTED__");
    		initializePreferences(context);
    		context.sendBroadcast(new Intent(UpdateNotificationReceiver.ACTION_ASK_FOR_UPDATE));
    	}
    	if (Intent.ACTION_POWER_CONNECTED.equals(intent.getAction())) {
    		Toast.makeText(context, "Connected", Toast.LENGTH_LONG).show();
    		preferences.edit().putBoolean(PreferenceKeys.PREF_ON_CHARGE, true).commit();
    		AlarmHelper.setCheckIsFullAlarm(context);
    	} else if (Intent.ACTION_POWER_DISCONNECTED.equals(intent.getAction())) {
    		Toast.makeText(context, "Disconnected", Toast.LENGTH_LONG).show();
    		preferences.edit().putBoolean(PreferenceKeys.PREF_ON_CHARGE, false).commit();
    		AlarmHelper.cancelCheckIsFullAlarm(context);
    	}
    }

	private void initializePreferences(Context context) {
		Intent battery = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
		int status = battery.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
		if (status == BatteryManager.BATTERY_STATUS_CHARGING) {
			preferences.edit().putBoolean(PreferenceKeys.PREF_ON_CHARGE, true).commit();
		} else {
			preferences.edit().putBoolean(PreferenceKeys.PREF_ON_CHARGE, false).commit();
		}
		if (status == BatteryManager.BATTERY_STATUS_FULL) {
			preferences.edit().putBoolean(PreferenceKeys.PREF_IS_FULL, true).commit();
		} else {
			preferences.edit().putBoolean(PreferenceKeys.PREF_IS_FULL, false).commit();
		}
	}
}