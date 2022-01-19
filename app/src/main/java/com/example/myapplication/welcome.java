package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
public class welcome extends AppCompatActivity{
    private Button start;
    public static final String SHARED_PREF = "shared";
    public static final String TEXT = "text";
    private String load_name;
    private TextView msg;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        start = (Button) findViewById(R.id.start);
        msg=(TextView) findViewById(R.id.textView2);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
                if(sharedPreferences.getString(TEXT,"").trim().length()<1){
                    intent.setClass(welcome.this, input_image.class);
                    startActivity(intent);
                }
                else{
                    intent.setClass(welcome.this, main_place.class);
                    startActivity(intent);
                }
            }
        });
    }
}
