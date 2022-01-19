package com.example.myapplication;

import static com.example.myapplication.main_place.SHARED_PREF;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class shop extends AppCompatActivity {

    private int money_pass=100;
    private TextView money;
    public int food_pass=100;
    private TextView food;

    public static final String passmoney ="pass_back_money";
    public static final String passfood ="pass_back_food";
    public static final String TEXT = "text";

    private Button feed;
    private Button can;
    private Button meat;
    private Button hotpod;
    private Button cake;
    private Button hamburger;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        //Intent intent = new Intent();
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        money_pass = sharedPreferences.getInt("MONEY",0);
        food_pass =sharedPreferences.getInt("FOOD",0);
        money = findViewById(R.id.money_text);
        food = findViewById(R.id.food_money);

        money.setText(String.valueOf(money_pass));
        food.setText(String.valueOf(food_pass));

        feed = findViewById(R.id.button1);
        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(money_pass-5 >= 0) {
                    Toast.makeText(getApplicationContext(),"Thank you for purchasing a feed!",Toast.LENGTH_LONG).show();
                    money_pass = money_pass - 5;
                    money.setText(String.valueOf(money_pass));
                    food_pass=food_pass+5;
                    food.setText(String.valueOf(food_pass));
                }
                else{
                    Toast.makeText(getApplicationContext(),"沒錢瞜，還不去工作!",Toast.LENGTH_LONG).show();
                }
            }
        });
        meat = findViewById(R.id.button2);
        meat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(money_pass-12 >= 0) {
                    Toast.makeText(getApplicationContext(),"Thank you for purchasing a feed!",Toast.LENGTH_LONG).show();
                    money_pass = money_pass - 12;
                    money.setText(String.valueOf(money_pass));
                    food_pass=food_pass+5;
                    food.setText(String.valueOf(food_pass));
                }
                else{
                    Toast.makeText(getApplicationContext(),"沒錢瞜，還不去工作!",Toast.LENGTH_LONG).show();
                }

            }
        });
        hotpod = findViewById(R.id.button3);
        hotpod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(money_pass-12 >= 0) {
                    Toast.makeText(getApplicationContext(),"Thank you for purchasing a feed!",Toast.LENGTH_LONG).show();
                    money_pass = money_pass - 12;
                    money.setText(String.valueOf(money_pass));
                    food_pass=food_pass+15;
                    food.setText(String.valueOf(food_pass));
                }
                else{
                    Toast.makeText(getApplicationContext(),"沒錢瞜，還不去工作!",Toast.LENGTH_LONG).show();
                }
            }
        });
        cake = findViewById(R.id.button4);
        cake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(money_pass-15 >= 0) {
                    Toast.makeText(getApplicationContext(),"Thank you for purchasing a feed!",Toast.LENGTH_LONG).show();
                    money_pass = money_pass - 15;
                    money.setText(String.valueOf(money_pass));
                    food_pass=food_pass+20;
                    food.setText(String.valueOf(food_pass));
                }
                else{
                    Toast.makeText(getApplicationContext(),"沒錢瞜，還不去工作!",Toast.LENGTH_LONG).show();
                }

            }
        });
        hamburger = findViewById(R.id.button5);
        hamburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(money_pass-20 >= 0) {
                    Toast.makeText(getApplicationContext(),"Thank you for purchasing a feed!",Toast.LENGTH_LONG).show();
                    money_pass = money_pass - 20;
                    money.setText(String.valueOf(money_pass));
                    food_pass=food_pass+28;
                    food.setText(String.valueOf(food_pass));
                }
                else{
                    Toast.makeText(getApplicationContext(),"沒錢瞜，還不去工作!",Toast.LENGTH_LONG).show();
                }
            }
        });
        can = findViewById(R.id.button6);
        can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(money_pass-25 >= 0) {
                    Toast.makeText(getApplicationContext(),"Thank you for purchasing a feed!",Toast.LENGTH_LONG).show();
                    money_pass = money_pass - 25;
                    money.setText(String.valueOf(money_pass));
                    food_pass=food_pass+35;
                    food.setText(String.valueOf(food_pass));
                }
                else{
                    Toast.makeText(getApplicationContext(),"沒錢瞜，還不去工作!",Toast.LENGTH_LONG).show();
                }
            }
        });
        back = findViewById(R.id.back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedpreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putInt("MONEY",money_pass);
                editor.putInt("FOOD",food_pass);
                editor.apply();
                Intent intent=new Intent(shop.this, main_place.class);
                startActivity(intent);
            }
        });
    }
}