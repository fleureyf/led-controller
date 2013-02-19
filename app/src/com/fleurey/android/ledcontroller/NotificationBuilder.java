package com.fleurey.android.ledcontroller;

import android.app.Notification;
import android.content.Context;
import android.graphics.Color;

public class NotificationBuilder {

	public static Notification onCharge(Context context) {
		Notification.Builder builder = new Notification.Builder(context);
		builder.setLights(Color.YELLOW, 1, 0);
		Notification not = builder.build();
		not.flags |= Notification.FLAG_SHOW_LIGHTS;
		return not;
	}
	
}
