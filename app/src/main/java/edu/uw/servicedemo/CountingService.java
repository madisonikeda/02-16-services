package edu.uw.servicedemo;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by madis on 2/16/2016.
 */
public class CountingService extends IntentService {
    private static final String TAG = "INTENT";

    public CountingService() {
        // the string passed is just a debugging string
        super("CountingService");
    }

    // handles the intents coming in one at a time
    @Override
    protected void onHandleIntent(Intent intent) {
        Log.v(TAG, "Handling intent");
        for (int i = 0; i < 10; i++) {
//            if (i == 3) {
//                stopSelf();
//            }
            Log.v(TAG, "" + i);
            try {
                Thread.sleep(5000);
                
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(TAG, "service created");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "service destroyed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v(TAG, "Received intent");
        return super.onStartCommand(intent, flags, startId);
    }
}
