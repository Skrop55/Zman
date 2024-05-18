package com.example.zman.Backend;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.example.zman.MainActivity;

public class AlarmService extends Service {
    public AlarmService() {} //constructor

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyService", "OnCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) { //notification
        Log.d("MyService", "onStartCommand");
        int retVal = super.onStartCommand(intent, flags, startId);
        Toast.makeText(this, "Service starting", Toast.LENGTH_SHORT).show();
        String notiTitle="nice to have you with us!";
        String notiText="Click here to open the user database";
        Intent intent2 = new Intent(this, MainActivity.class);
        Globals.makeNotification(this, intent2, notiTitle, notiText);
        return retVal;
    }

    @Override
    public void onDestroy() {
        Log.d("MyService", "onDestroy");
        super.onDestroy();
    }
}