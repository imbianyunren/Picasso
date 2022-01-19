package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class main_place extends AppCompatActivity {
    public int moneycal=1000;
    public int not_worktraveling=1;  //現在沒有在旅遊
    private TextView money;
    private TextView send_msg;
    public int energycal=100;
    private TextView energy;
    public int foodcal=10;
    private TextView food;
    private byte[] byteArray;
    private ImageView im;

    private Button eat;
    private Button work;
    private int secondLeft = 0;  //包含工作及旅遊及睡覺所花的時間
    private Button travel;
    private Button sleep;
    Random r;
    int time_travel;
    private Button shopping;
    public static final String SHARED_PREF = "shared";
    public static final String TEXT = "text";
    private String load_name;
    private int load_money;
    private int load_food;
    private int load_energy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_place);

        send_msg=(TextView) findViewById(R.id.send_name);
        money=findViewById(R.id.money_text);
        energy = findViewById(R.id.energy);
        food = findViewById(R.id.food_text);
        im=findViewById(R.id.show_text_imageView);

        SharedPreferences sharedpreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        if(sharedpreferences.getString(TEXT,"").trim().length()<1){
                Intent intent = getIntent();
                String message = intent.getStringExtra(typename.Extra_Message);
                byteArray = intent.getByteArrayExtra("from_typename");
                Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                im.setImageBitmap(bmp);
                send_msg.setText(message);

        }else {
            load();
        }

        eat = findViewById(R.id.eat_button);
        eat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (not_worktraveling==1 && foodcal - 5 >= 0) {
                    foodcal = foodcal - 5;
                    food.setText(String.valueOf(foodcal));
                    energycal = energycal + 10;
                    energy.setText(String.valueOf(energycal));
                } else if(not_worktraveling==1 && foodcal<5) {
                    new AlertDialog.Builder(main_place.this)
                            .setTitle("已經沒有食物")//設定視窗標題
                            .setIcon(R.mipmap.ic_launcher)//設定對話視窗圖示
                            .setMessage("要去商店買食物給你的寵物喔")//設定顯示的文字
                            .setPositiveButton("關閉視窗", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })//設定結束的子視窗
                            .show();//呈現對話視窗
                }else{
                    new AlertDialog.Builder(main_place.this)
                            .setTitle("你的寵物出門了喔")//設定視窗標題
                            .setIcon(R.mipmap.ic_launcher)//設定對話視窗圖示
                            .setMessage("等他回來吧～")//設定顯示的文字
                            .setPositiveButton("關閉視窗", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })//設定結束的子視窗
                            .show();//呈現對話視窗
                }

                food.setText(String.valueOf(foodcal));
                energycal = energycal + 10;
                energy.setText(String.valueOf(energycal));
            }
        });

        work = findViewById(R.id.work_button);
        work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(not_worktraveling==0) {
                    new AlertDialog.Builder(main_place.this)
                            .setTitle("你的寵物出門了喔")//設定視窗標題
                            .setIcon(R.mipmap.ic_launcher)//設定對話視窗圖示
                            .setMessage("等他回來吧～")//設定顯示的文字
                            .setPositiveButton("關閉視窗", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })//設定結束的子視窗
                            .show();//呈現對話視窗
                }else if(energycal>=30) {
                    secondLeft = 5;    //先假設工作5秒
                    worktravel_timer();
                    moneycal = moneycal + 15;
                    money.setText(String.valueOf(moneycal));
                    energycal = energycal - 30;
                    energy.setText(String.valueOf(energycal));
                }else {
                    new AlertDialog.Builder(main_place.this)
                            .setTitle("寵物已經沒活力了喔")//設定視窗標題
                            .setIcon(R.mipmap.ic_launcher)//設定對話視窗圖示
                            .setMessage("請餵食他或是讓他睡覺")//設定顯示的文字
                            .setPositiveButton("關閉視窗", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })//設定結束的子視窗
                            .show();//呈現對話視窗
                }
            }
        });

        travel = findViewById(R.id.travel_button);
        travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(not_worktraveling==0) {
                    new AlertDialog.Builder(main_place.this)
                            .setTitle("你的寵物出門了喔")//設定視窗標題
                            .setIcon(R.mipmap.ic_launcher)//設定對話視窗圖示
                            .setMessage("等他回來吧～")//設定顯示的文字
                            .setPositiveButton("關閉視窗", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })//設定結束的子視窗
                            .show();//呈現對話視窗
                }else if(energycal>=10) {
                    r = new Random();
                    time_travel = r.nextInt((4 - 2) + 1) + 2;    //隨機出去兩小時到四小時
                    secondLeft = 5000;    //先假設工作5秒
                    worktravel_timer();
                    foodcal = foodcal + 15;
                    food.setText(String.valueOf(foodcal));
                    energycal = energycal - 10;
                    energy.setText(String.valueOf(energycal));
                }else {
                    new AlertDialog.Builder(main_place.this)
                            .setTitle("寵物已經沒活力了喔")//設定視窗標題
                            .setIcon(R.mipmap.ic_launcher)//設定對話視窗圖示
                            .setMessage("請餵食他或是讓他睡覺")//設定顯示的文字
                            .setPositiveButton("關閉視窗", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })//設定結束的子視窗
                            .show();//呈現對話視窗
                }
            }
        });
        sleep = findViewById(R.id.sleep_button);
        sleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(not_worktraveling==0) {
                    new AlertDialog.Builder(main_place.this)
                            .setTitle("你的寵物出門了喔")//設定視窗標題
                            .setIcon(R.mipmap.ic_launcher)//設定對話視窗圖示
                            .setMessage("等他回來吧～")//設定顯示的文字
                            .setPositiveButton("關閉視窗", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })//設定結束的子視窗
                            .show();//呈現對話視窗
                }else {
                    Intent intent3 = new Intent();
                    intent3.setClass(main_place.this, sleep.class);
                    startActivity(intent3);
                    energycal=energycal+15;
                    energy.setText(String.valueOf(energycal));
                }
            }
        });



        shopping=findViewById(R.id.shop_button);
        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent();
                intent2.setClass(main_place.this, shop.class);
                intent2.putExtra("pass_money",moneycal);
                intent2.putExtra("pass_food",foodcal);
                startActivity(intent2);
            }
        });

    }
    protected void onStop(){
        super.onStop();
        send_msg=(TextView) findViewById(R.id.send_name);
        money=findViewById(R.id.money_text);
        im=findViewById(R.id.show_text_imageView);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Bitmap store_img = ((BitmapDrawable)im.getDrawable()).getBitmap();
        store_img.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] BA = stream.toByteArray();
        String encodedImage = Base64.encodeToString(BA, Base64.DEFAULT);
        SharedPreferences sharedpreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(TEXT, send_msg.getText().toString());
        editor.putInt("MONEY",moneycal);
        editor.putInt("FOOD",foodcal);
        editor.putInt("ENERGY",energycal);
        editor.putString("image_data",encodedImage);
        editor.apply();
    }
    public void load(){
//        TextView send_msg=(TextView) findViewById(R.id.send_name);
        send_msg=(TextView) findViewById(R.id.send_name);
        money=findViewById(R.id.money_text);
        im=findViewById(R.id.show_text_imageView);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        String previouslyEncodedImage = sharedPreferences.getString("image_data", "");
        byte[] b = Base64.decode(previouslyEncodedImage, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
        im.setImageBitmap(bitmap);
        foodcal = sharedPreferences.getInt("FOOD",0);
        energycal = sharedPreferences.getInt("ENERGY",0);
        load_name = sharedPreferences.getString(TEXT,"");
        moneycal = sharedPreferences.getInt("MONEY",0);
        send_msg.setText(load_name);
        money.setText(String.valueOf(load_money));
        energy.setText(String.valueOf(energycal));
        food.setText(String.valueOf(foodcal));
    }

    public void worktravel_timer(){
        ImageView im = findViewById(R.id.show_text_imageView);
        im.setAlpha(0f);    //把寵物變透明，讓使用者看不到，代表他出去工作
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        secondLeft--;
                        if (secondLeft == 0) {
                            timer.cancel();
                            im.setAlpha(1f);    //imageView.animate().alpha(1f).setDuration(1500);
                        }
                    }
                });
            }
        };
        timer.schedule(task, 1000, 1000);
        not_worktraveling=1;

    }

}