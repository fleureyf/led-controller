package com.fleurey.android.ledcontroller.powercontroller;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public final class AlarmHelper {

	public static final int REFRESH_RATE = 30000; // Time in milliseconds
	
	private AlarmHelper() {}
	
	protected static void setCheckIsFullAlarm(Context context) {
		PendingIntent pIntent = PendingIntent.getService(context, 0, new Intent(context, CheckIsFullService.class), 0);
		((AlarmManager) context.getSystemService(Context.ALARM_SERVICE)).setRepeating(
				AlarmManager.RTC_WAKEUP, 
				System.currentTimeMillis(), 
				REFRESH_RATE, 
				pIntent);
	}
	
	protected static void cancelCheckIsFullAlarm(Context context) {
		PendingIntent pIntent = PendingIntent.getService(context, 0, new Intent(context, CheckIsFullService.class), 0);
		((AlarmManager) context.getSystemService(Context.ALARM_SERVICE)).cancel(pIntent);
	}
}
