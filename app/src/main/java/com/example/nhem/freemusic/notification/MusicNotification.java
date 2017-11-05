package com.example.nhem.freemusic.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.example.nhem.freemusic.R;
import com.example.nhem.freemusic.activities.MainActivity;
import com.example.nhem.freemusic.database.TopSongModel;
import com.example.nhem.freemusic.utils.MusicHandle;
import com.squareup.picasso.Picasso;

/**
 * Created by NHEM on 05/11/2017.
 */

public class MusicNotification {
    public static final int NOTI_ID = 0;
    private static RemoteViews remoteViews;
    public static NotificationManager notificationManager;
    private static NotificationCompat.Builder builder;

    public static void setupNotification(Context context, TopSongModel topSongModel) {
        remoteViews = new RemoteViews(context.getPackageName(),
                R.layout.layout_notification);

        remoteViews.setTextViewText(R.id.tv_noti_song_name, topSongModel.getSongName());
        remoteViews.setTextViewText(R.id.tv_noti_singer, topSongModel.getArtistName());

        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContent(remoteViews)
                .setOngoing(true)
                .setContentIntent(pendingIntent);

        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTI_ID, builder.build());

        Picasso.with(context).load(topSongModel.getSmallImage())
                .into(remoteViews, R.id.iv_noti_song, NOTI_ID, builder.build());


        setOnClickPlayPause(context);

    }

    private static void setOnClickPlayPause(Context context) {
        Intent intent = new Intent(context, MusicService.class);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        remoteViews.setOnClickPendingIntent(R.id.iv_noti_play, pendingIntent);
    }

    public static void updateNotification() {
        if (MusicHandle.hybridMediaPlayer.isPlaying()) {
            remoteViews.setImageViewResource(R.id.iv_noti_play, R.drawable.ic_pause_black_24dp);
            builder.setOngoing(true);
        } else {
            remoteViews.setImageViewResource(R.id.iv_noti_play, R.drawable.ic_play_arrow_black_24dp);
            builder.setOngoing(false);
        }
        notificationManager.notify(0, builder.build());
    }
}
