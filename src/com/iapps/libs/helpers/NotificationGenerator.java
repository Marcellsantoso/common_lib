package com.iapps.libs.helpers;

import org.joda.time.DateTime;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.iapps.common_library.R;

public abstract class NotificationGenerator {
	Context ctx;
	Class<?> cls;

	public NotificationGenerator(Context ctx, Class<?> cls) {
		this.ctx = ctx;
		this.cls = cls;
	}

	@SuppressWarnings("deprecation")
	public Notification build() {
		NotificationManager notificationManager = (NotificationManager) ctx
				.getSystemService(Context.NOTIFICATION_SERVICE);
		int currentapiVersion = android.os.Build.VERSION.SDK_INT;
		Notification notification;

		Intent intent = new Intent(ctx, cls);
		PendingIntent contentIntent = PendingIntent.getActivity(ctx, 0,
				intent, 0);

		if (currentapiVersion < android.os.Build.VERSION_CODES.HONEYCOMB) {
			notification = new Notification(icon(), content(), when());
			notification.setLatestEventInfo(ctx, title(), title(),
					contentIntent);
			notification.flags = Notification.FLAG_AUTO_CANCEL;
			notificationManager.notify((int) when(), notification);

		}
		else {
			NotificationCompat.Builder builder = new NotificationCompat.Builder(
					ctx);
			builder = builder.setContentIntent(contentIntent)
					.setSmallIcon(icon()).setTicker(title()).setWhen(when())
					.setAutoCancel(true).setContentTitle(title())
					.setContentText(content());
			if (currentapiVersion >= android.os.Build.VERSION_CODES.LOLLIPOP && color() > 0)
				builder.setColor(ctx.getResources().getColor(color()));

			if (currentapiVersion >= android.os.Build.VERSION_CODES.LOLLIPOP)
				builder.setSmallIcon(iconLollipop());

			notification = builder.build();
			notificationManager.notify((int) when(), notification);
		}

		return notification;
	}

	public String title() {
		return ctx.getString(R.string.app_name);
	}

	public int icon() {
		return R.drawable.ic_launcher;
	}

	public int iconLollipop() {
		return R.drawable.ic_launcher;
	}

	public abstract String content();

	public long when() {
		return DateTime.now().getMillis();
	}

	public int color() {
		return 0;
	}

}
