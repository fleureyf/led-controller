package com.fleurey.android.ledcontroller;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.BatteryManager;
import android.widget.Toast;

public class PowerConnectionReceiver extends BroadcastReceiver {
	
    @Override
    public void onReceive(Context context, Intent intent) { 
    	Toast.makeText(context, "Intent received", Toast.LENGTH_LONG).show();
    	int status = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED)).getIntExtra(BatteryManager.EXTRA_STATUS, -1);
    	Notification.Builder builder = new Notification.Builder(context);
    	//builder.setDefaults(Notification.DEFAULT_SOUND);
    	//builder.setContentTitle("From receiver");
    	//builder.setContentText("Status: " + status);
    	//builder.setSmallIcon(R.drawable.ic_launcher);
    	builder.setOngoing(true);
    	if (status == BatteryManager.BATTERY_STATUS_FULL) {
    		Toast.makeText(context, "Full", Toast.LENGTH_LONG).show();
    		builder.setLights(Color.GREEN, 1, 0);
    	} else if (Intent.ACTION_POWER_CONNECTED.equals(intent.getAction())) {
    		Toast.makeText(context, "Connected", Toast.LENGTH_LONG).show();
    		builder.setLights(Color.YELLOW, 1, 0);
    	} else if (Intent.ACTION_POWER_DISCONNECTED.equals(intent.getAction())) {
    		Toast.makeText(context, "Disconnected", Toast.LENGTH_LONG).show();
    	}
    	Notification notification = builder.build();
    	notification.flags |= Notification.FLAG_SHOW_LIGHTS;
		((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE)).notify(1000, notification);
    }
    
    
}