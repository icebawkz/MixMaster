package com.example.august.mixmaster;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.august.mixmaster.MainActivity;

/**
 * Created by Alex on 2/22/2015.
 */
public class UserPresentBroadcastReceiver extends BroadcastReceiver {

    public UserPresentBroadcastReceiver(){}

    Context mContext;
    public UserPresentBroadcastReceiver(Context mContext){
        this.mContext = mContext;
    }

    @SuppressWarnings("deprecated")
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(Intent.ACTION_USER_PRESENT)){
            NotificationManager notificationManager = (NotificationManager) context
                    .getSystemService(Context.NOTIFICATION_SERVICE);
            Notification notification = new Notification(android.R.drawable.stat_notify_more, "What are we drinking today?", System.currentTimeMillis());
            Intent notificationIntent = new Intent(context, MainActivity.class);

            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_SINGLE_TOP);

            PendingIntent Pintent = PendingIntent.getActivity(context, 0,
                    notificationIntent, 0);

            notification.setLatestEventInfo(context, "MixMaster", "What are we drinking today?", Pintent);
            notification.flags |= Notification.FLAG_AUTO_CANCEL;
            notificationManager.notify(0, notification);
        }

    }
}
