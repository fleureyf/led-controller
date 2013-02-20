package com.fleurey.android.ledcontroller.notificationcontroller;

import android.app.NotificationManager;
import android.content.Context;

public class NotificationHelper {

	public static enum NotificationType {
		MISSED_CALL, 
		SMS, 
		MMS, 
		ON_CHARGE, 
		IS_FULL;
	}
	
	public static void notify(Context context, NotificationType type) {
		NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		switch (type) {
		case MISSED_CALL:
			manager.notify(type.ordinal(), NotificationBuilder.missedCall(context));
			break;
		case ON_CHARGE:
			manager.notify(type.ordinal(), NotificationBuilder.onCharge(context));
			break;
		case IS_FULL:
			manager.notify(type.ordinal(), NotificationBuilder.isFull(context));
			break;
		default:
			break;
		}
	}
	
	public static void cancel(Context context, NotificationType type) {
		NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		manager.cancel(type.ordinal());
	}
}
