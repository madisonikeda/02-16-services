package edu.uw.servicedemo;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

/**
 * Created by madis on 2/16/2016.
 */
public class MusicService extends Service implements ServiceConnection {
    private MediaPlayer mediaPlayer;
    private static final String TAG = "MS";


    private int NOTIFICATION_ID = 1;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        mediaPlayer = MediaPlayer.create(this, R.raw.verdi_la_traviata_brindisi_mit);
        mediaPlayer.setOnCompletionListener(this);

        // here's an intent for you to save later
        PendingIntent pendingIntent =  PendingIntent.getActivity(getApplicationContext(),
                0, new Intent(getApplicationContext(), MainActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new NotificationCompat.Builder(this)
                .addAction(android.R.drawable.ic_media_play, "Music Player", pendingIntent)
                .setContentText("Now playing: " + "Verdi")
                .setOngoing(true)
                .build();

        // makes this a foreground service
        startForeground(NOTIFICATION_ID, notification);
        mediaPlayer.start();
        super.onStartCommand(intent, flags, startId);
        return Service.START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {

        super.onDestroy();

        stopForeground(true);
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(TAG, "Service created");
    }

    final IBinder mLocalBinder = new LocalBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mLocalBinder;
    }


    public String sayHello() {
        return "hello World";
    }

    class LocalBinder extends Binder {
        // return the current service
        public MusicService getService()  {
            return MusicService.this;
        }

    }

}
