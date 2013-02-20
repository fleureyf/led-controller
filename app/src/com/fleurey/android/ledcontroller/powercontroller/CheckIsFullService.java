package com.fleurey.android.ledcontroller.powercontroller;

import com.fleurey.android.ledcontroller.preferencescontroller.PreferenceKeys;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.BatteryManager;
import android.os.IBinder;
import android.preference.PreferenceManager;

public class CheckIsFullService extends Service {

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Intent battery = registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
		int status = battery.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()); 
		if (status == BatteryManager.BATTERY_STATUS_FULL) {
			preferences.edit().putBoolean(PreferenceKeys.PREF_IS_FULL, true);
		} else {
			preferences.edit().putBoolean(PreferenceKeys.PREF_IS_FULL, false);
		}
		stopSelf();
		return START_NOT_STICKY;
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}
