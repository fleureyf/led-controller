package com.fleurey.android.ledcontroller.powercontroller;

import com.fleurey.android.ledcontroller.preferencescontroller.PreferenceKeys;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class PowerConnectionReceiver extends BroadcastReceiver {
		
	private SharedPreferences preferences;
	
    @Override
    public void onReceive(Context context, Intent intent) { 
    	preferences = PreferenceManager.getDefaultSharedPreferences(context);
    	Toast.makeText(context, "Intent received", Toast.LENGTH_LONG).show();
    	if (Intent.ACTION_POWER_CONNECTED.equals(intent.getAction())) {
    		Toast.makeText(context, "Connected", Toast.LENGTH_LONG).show();
    		preferences.edit().putBoolean(PreferenceKeys.PREF_ON_CHARGE, true).commit();
    	} else if (Intent.ACTION_POWER_DISCONNECTED.equals(intent.getAction())) {
    		Toast.makeText(context, "Disconnected", Toast.LENGTH_LONG).show();
    		preferences.edit().putBoolean(PreferenceKeys.PREF_ON_CHARGE, false).commit();
    	}
    }
}