package com.example.weather;

import android.app.NotificationChannel;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.ArrayList;
import java.util.List;

public class WeatherAlerts {
    private List<String> alerts;
    private Context context;
    private static final String CHANNEL_ID = "weather_alerts_channel";

    public WeatherAlerts(Context context) {
        this.context = context;
        this.alerts = new ArrayList<>();
        createNotificationChannel();}

    public void addAlert(String alert) {
        alerts.add(alert);
        sendNotification(alert);
    }

    public List<String> getAlerts() {
        return alerts;
    }

    private void createNotificationChannel() {
        // create the NotificationChannel, but only on API 26+ because the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Weather Alerts";
            String description = "Channel for weather alerts notifications";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }}

    private void sendNotification(String alert) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_weather_alert)
                .setContentTitle("Weather Alert")
                .setContentText(alert)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify((int) System.currentTimeMillis(), builder.build()); }}
