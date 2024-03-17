package com.example.wsafety;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.github.tbouron.shakedetector.library.ShakeDetector;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.OnTokenCanceledListener;
import com.google.android.gms.tasks.Task;

public class ServiceMine extends Service {

    MediaPlayer mediaplayer;
    boolean isRunning = false;

    FusedLocationProviderClient fusedLocationClient;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    SmsManager manager = SmsManager.getDefault();
    String myLocation;
    @Override
    public void onCreate() {
        super.onCreate();

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            // Logic to handle location object
                            location.getAltitude();
                            location.getLongitude();
                            Log.i("latitude", String.valueOf(location.getLatitude()));
                            myLocation = "http://maps.google.com/maps?q=loc:"+location.getLatitude() +","+location.getLongitude();
                        }else {

                            myLocation = "Unable to Find Location :(";
                        }
                    }
                });


        ShakeDetector.create(this, () -> {

            //if you want to play siren sound you can do it here
            //just create music player and play here
            //before playing sound please set volume to max
            mediaplayer= MediaPlayer.create(getApplicationContext(),R.raw.vippolicesiren);
            mediaplayer.start();

            SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
            String ENUM = sharedPreferences.getString("ENUM","NONE");
            String ENUM2 = sharedPreferences.getString("ENUM2","NONE");
            String pol = sharedPreferences.getString("pol","NONE");
            if(!ENUM.equalsIgnoreCase("NONE")){
                manager.sendTextMessage(ENUM,null,"Im in Trouble!\nSending My Location :\n"+myLocation,null,null);

            }
            if(!pol.equalsIgnoreCase("NONE")){
                manager.sendTextMessage(pol,null,"Im in Trouble!\nSending My Location :\n"+myLocation,null,null);

            }
            if(!ENUM2.equalsIgnoreCase("NONE")){
                manager.sendTextMessage(ENUM2,null,"Im in Trouble!\nSending My Location :\n"+myLocation,null,null);

            }

        });

    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {





            if (intent.getAction().equalsIgnoreCase("STOP")) {
                if(isRunning) {
                    this.stopForeground(true);


                    this.stopSelf();
                }
            } else {


                Intent notificationIntent = new Intent(this, MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    NotificationChannel channel = new NotificationChannel("MYID", "CHANNELFOREGROUND", NotificationManager.IMPORTANCE_DEFAULT);

                    NotificationManager m = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    m.createNotificationChannel(channel);

                    Notification notification = new Notification.Builder(this, "MYID")
                            .setContentTitle("Women Safety")
                            .setContentText("Shake Device to Send SOS")
                            .setSmallIcon(R.drawable.girl_vector)
                            .setContentIntent(pendingIntent)
                            .build();
                    this.startForeground(115, notification);
                    isRunning = true;
                    return START_NOT_STICKY;
                }
            }

        return super.onStartCommand(intent,flags,startId);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
