package com.example.zman.Backend;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Build;

import com.example.zman.R;

public class Globals {
    public static MediaPlayer mPlayer;

    public static void makeNotification(Context context, Intent intent, String notiTitle, String notiText)
    {
        NotificationManager mNotificationManager;
        String id = "my_channel_01";
        int importance = NotificationManager.IMPORTANCE_LOW;
        NotificationChannel mChannel;

        //notification channel
        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {

            mChannel = new NotificationChannel(id, "channel Name",
                    importance);
            mChannel.enableLights(true);
            mNotificationManager.createNotificationChannel(mChannel);
        }

        PendingIntent contentIntent = PendingIntent.getActivity(context,
                0, intent, PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        Notification.Builder builder = new Notification.Builder(context);

        builder.setContentIntent(contentIntent);
        builder.setSmallIcon(R.drawable.chad);
        builder.setContentTitle(notiTitle);
        builder.setContentText(notiText);
        builder.setDefaults(Notification.DEFAULT_SOUND);
        builder.setWhen(System.currentTimeMillis());
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.chad));
        builder.setDefaults(Notification.DEFAULT_LIGHTS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            builder.setChannelId(id); // Channel

        Notification noti = builder.build();

        mNotificationManager.notify(1, noti);
    }
}
