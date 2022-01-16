package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class main_place extends AppCompatActivity {
    public int moneycal=1000;
    private TextView money;
    private Button eat;
    public static final String SHARED_PREF = "shared";
    public static final String TEXT = "text";

    private String load_name;
    private int load_money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_place);
        ImageView im;
        im = findViewById(R.id.show_text_imageView);
        Intent intent = getIntent();
        String message = intent.getStringExtra(typename.Extra_Message);
        TextView send_msg=(TextView) findViewById(R.id.send_name);
        byte[] byteArray = intent.getByteArrayExtra("from_typename");

        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        im.setImageBitmap(bmp);
        send_msg.setText(message);

        money = findViewById(R.id.money_text);
        eat = findViewById(R.id.eat_button);
        eat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moneycal=moneycal-5;
                money.setText(String.valueOf(moneycal));
            }
        });

        SharedPreferences sharedpreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(TEXT, send_msg.getText().toString());
        editor.putInt("MONEY",moneycal);
        editor.apply();

//        //update();
//        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
//        load_name = sharedPreferences.getString(TEXT,"");
//        load_money = sharedPreferences.getInt("MONEY",0);
//        send_msg.setText(load_name);
//        money.setText(load_money);
    }
    //private void update(){}
}