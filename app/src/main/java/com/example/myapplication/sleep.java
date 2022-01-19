package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class sleep extends AppCompatActivity {

    private int secondLeft = 20;  //包含睡覺所花的時間，現在是1小時
    public static final String SHARED_PREF = "shared";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);
        sleep_timer();
    }
    public void sleep_timer(){
        TextView time = findViewById(R.id.time);
        time.setText(getTimerText());
        ImageView im = findViewById(R.id.image_sleep);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        String previouslyEncodedImage = sharedPreferences.getString("image_data", "");
        byte[] b = Base64.decode(previouslyEncodedImage, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
        im.setImageBitmap(bitmap);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                 runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time.setText(getTimerText());
                        secondLeft--;

                        if (secondLeft < 0) {
                            timer.cancel();
                            Intent intent_sleep_back=new Intent(sleep.this, main_place.class);
                            startActivity(intent_sleep_back);
                        }
                    }
                });
            }
        };
        timer.schedule(task, 1000, 1000);
    }
    private String getTimerText()
    {
        int rounded = (int) Math.round(secondLeft);

        int seconds = ((rounded % 86400) % 3600) % 60;
        int minutes = ((rounded % 86400) % 3600) / 60;
        int hours = ((rounded % 86400) / 3600);

        return formatTime(seconds, minutes, hours);
    }

    private String formatTime(int seconds, int minutes, int hours)
    {
        return String.format("%02d",hours) + " : " + String.format("%02d",minutes) + " : " + String.format("%02d",seconds);
    }
}