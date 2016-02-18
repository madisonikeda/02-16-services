package edu.uw.servicedemo;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "**MAIN**";
    // instead of sound pool
    private MediaPlayer mediaPlayer;
//
//    @Override
//    public void onStart(Intent intent, int startId) {
//        super.onStart(intent, startId);
//        Intent intent = new Intent(MainActivity.this, MusicService.class);
//        bindService(intent, this, Context.BIND_AUTO_CREATE);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //when "Start" button is pressed
    public void handleStart(View v){
        Log.i(TAG, "Start pressed");
        Intent intent = new Intent(MainActivity.this, MusicService.class);
        startService(intent);
        //finish();

        mediaPlayer = MediaPlayer.create(this, R.raw.verdi_la_traviata_brindisi_mit);
        mediaPlayer.start();

    }

    //when "Stop" button is pressed
    public void handleStop(View v){
        Log.i(TAG, "Stop pressed");
        Intent intent = new Intent(MainActivity.this, MusicService.class);
        stopService(intent);

        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }
}


