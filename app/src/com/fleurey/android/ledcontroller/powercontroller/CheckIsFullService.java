package com.fleurey.android.ledcontroller.powercontroller;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.BatteryManager;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;

import com.fleurey.android.ledcontroller.preferencescontroller.PreferenceKeys;

public class CheckIsFullService extends Service {

	public static final String ACTION_IS_FULL = CheckIsFullService.class.getName() + ".ACTION_IS_FULL";
	
	private static final String TAG = CheckIsFullService.class.getSimpleName(); 
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Intent battery = registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
		int status = battery.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		if (status == BatteryManager.BATTERY_STATUS_FULL) {
			Log.d(TAG, "Check battery: full");
			sendBroadcast(new Intent(ACTION_IS_FULL));
			preferences.edit().putBoolean(PreferenceKeys.PREF_IS_FULL, true).commit();
		} else {
			Log.d(TAG, "Check battery: NOT full");
			preferences.edit().putBoolean(PreferenceKeys.PREF_IS_FULL, false).commit();
		}
		stopSelf();
		return START_NOT_STICKY;
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}
