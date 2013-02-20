package com.fleurey.android.ledcontroller.powercontroller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class PowerConnectionReceiver extends BroadcastReceiver {
	
	public static final String PREF_ON_CHARGE = PowerConnectionReceiver.class.getName() + "_PREF_ON_CHARGE";
	public static final String PREF_IS_FULL = PowerConnectionReceiver.class.getName() + "_PREF_IS_FULL";
	
	private SharedPreferences preferences;
	
    @Override
    public void onReceive(Context context, Intent intent) { 
    	preferences = PreferenceManager.getDefaultSharedPreferences(context);
    	Toast.makeText(context, "Intent received", Toast.LENGTH_LONG).show();
    	if (Intent.ACTION_POWER_CONNECTED.equals(intent.getAction())) {
    		Toast.makeText(context, "Connected", Toast.LENGTH_LONG).show();
    		preferences.edit().putBoolean(PREF_ON_CHARGE, true).commit();
    	} else if (Intent.ACTION_POWER_DISCONNECTED.equals(intent.getAction())) {
    		Toast.makeText(context, "Disconnected", Toast.LENGTH_LONG).show();
    		preferences.edit().putBoolean(PREF_ON_CHARGE, false).commit();
    	}
    }
}