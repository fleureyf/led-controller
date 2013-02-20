package com.fleurey.android.ledcontroller;

import com.fleurey.android.ledcontroller.notificationcontroller.ScreenStateReceiver;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class ScreenStateService extends Service {

	public final static String ACTION_STARTED = ScreenStateService.class.getName() + ".ACTION_STARTED"; 
	
	private final static String TAG = ScreenStateService.class.getSimpleName();
	
	private ScreenStateReceiver receiver = new ScreenStateReceiver();
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(TAG, "__ON_START__");
		registerReceiver(receiver, new IntentFilter(ACTION_STARTED));
		registerReceiver(receiver, new IntentFilter(Intent.ACTION_SCREEN_OFF));
		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		Log.d(TAG, "__ON_DESTROY__");
		super.onDestroy();
		unregisterReceiver(receiver);
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}
