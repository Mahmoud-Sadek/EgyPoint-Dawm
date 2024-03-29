package com.sadek.dawemapp.utils;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.sadek.dawemapp.R;


/**
 * Created by Mahmoud Sadek on 11/10/2018.
 */

public class NotificationHelper extends ContextWrapper {

    public static final String CHANNEL_ID="com.sadek.dawemapp";
    public static final String CHANNEL_NAME="notification";

    public NotificationManager notificationManager;


    public NotificationHelper(Context base) {
        super(base);

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){   //only working when api is 26 or higher
            createChannel();

        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {

        NotificationChannel notificationChannel=new NotificationChannel(CHANNEL_ID,CHANNEL_NAME,NotificationManager.IMPORTANCE_DEFAULT);

        notificationChannel.enableLights(false);
        notificationChannel.enableVibration(true);
        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);


        getManager().createNotificationChannel(notificationChannel);


    }

    public NotificationManager getManager() {
        if(notificationManager==null)
            notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        return  notificationManager;

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public  Notification.Builder getNotificationChannel(String title, String body, PendingIntent contentIntent, Uri soundUri)

    {
        return new Notification.Builder(getApplicationContext(),CHANNEL_ID)
                .setContentIntent(contentIntent)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setSound(soundUri)
                .setAutoCancel(false);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public  Notification.Builder getNotificationChannel(String title, String body, Uri soundUri)

    {
        return new Notification.Builder(getApplicationContext(),CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setSound(soundUri)
                .setAutoCancel(false);
    }
}