package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class main_place extends AppCompatActivity {
    public int moneycal=100;
    public int not_worktraveling=1;  //現在沒有在旅遊
    private TextView money;
    private TextView send_msg;
    public int energycal=100;
    private TextView energy;
    public int foodcal=10;
    private TextView food;
    private TextView dialogmsg;
    private byte[] byteArray;
    private ImageView im;
    private ImageView travelwork;
    private ImageButton imbutton;
//    private TextView food;
    private  ImageView dialogim;
    private Button eat;
    private Button work;
    private int secondLeft = 0;  //包含工作及旅遊及睡覺所花的時間
    private int time = 5;  //包含工作及旅遊及睡覺所花的時間
    private Button travel;
    private Button sleep;
    Random r;
    int randon=0;
    private Button shopping;
    public static final String SHARED_PREF = "shared";
    public static final String TEXT = "text";
    private String load_name;
    private int load_money;
    private int load_food;
    private int load_energy;
    private  int tw=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_place);
        travelwork = findViewById(R.id.travelwork);
        travelwork.setAlpha(0f);
        send_msg=(TextView) findViewById(R.id.send_name);
        money=findViewById(R.id.money_text);
        energy = findViewById(R.id.energy);
        food = findViewById(R.id.food_text);
        im=findViewById(R.id.show_text_imageView);
        dialogmsg = findViewById(R.id.dialogtext);
        SharedPreferences sharedpreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        if(sharedpreferences.getString(TEXT,"").trim().length()<1){
                Intent intent = getIntent();
                String message = intent.getStringExtra(typename.Extra_Message);
                byteArray = intent.getByteArrayExtra("from_typename");
                Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                im.setImageBitmap(bmp);
                send_msg.setText(message);
            switch(randon) {
                case 5:
                    dialogmsg.setText("主人早安阿");
                    break;
                case 4:
                    dialogmsg.setText("主人加油~");
                    break;
                case 3:
                    dialogmsg.setText("我的寒假不是\n我的寒假");
                    break;
                case 2:
                    dialogmsg.setText("主人我好餓");
                    break;
                case 1:
                    dialogmsg.setText("主人你的肝還好嗎");
                    break;
                default:
                    dialogmsg.setText("我覺得\n我有點可愛");
            }

        }else {
            load();
            switch(randon) {
                case 5:
                    dialogmsg.setText("主人早安阿");
                    break;
                case 4:
                    dialogmsg.setText("主人加油~");
                    break;
                case 3:
                    dialogmsg.setText("我的寒假不是\n我的寒假");
                    break;
                case 2:
                    dialogmsg.setText("主人我好餓");
                    break;
                case 1:
                    dialogmsg.setText("主人你的肝還好嗎");
                    break;
                default:
                    dialogmsg.setText("我覺得\n我有點可愛");
            }
        }

        imbutton = findViewById(R.id.imbutton);
        imbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(not_worktraveling==1){
                    if(randon<5){
                        randon++;
                    }else{
                        randon=0;
                    }
                    switch(randon) {
                        case 4:
                            dialogmsg.setText("主人加油~");
                            break;
                        case 3:
                            dialogmsg.setText("我的寒假不是\n我的寒假");
                            break;
                        case 2:
                            dialogmsg.setText("主人我好餓");
                            break;
                        case 1:
                            dialogmsg.setText("主人你的\n肝還好嗎");
                            break;
                        default:
                            dialogmsg.setText("我覺得\n我有點可愛");
                    }
                }else{

                }
            }
        });
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
                    dialogmsg.setText("主人\n我去工作拉");
                    travelwork.setAlpha(1f);
                    int imageResource = getResources().getIdentifier("@drawable/briefcase", null, getPackageName());
                    Drawable res = getResources().getDrawable(imageResource);
                    travelwork.setImageDrawable(res);
                    tw=0;
                    worktravel_timer();
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
                    dialogmsg.setText("主人\n我要出門玩拉");
                    travelwork.setAlpha(1f);
                    int imageResource = getResources().getIdentifier("@drawable/suitcase", null, getPackageName());
                    Drawable res = getResources().getDrawable(imageResource);
                    travelwork.setImageDrawable(res);
                    tw=1;
                    worktravel_timer();
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
                    onStop();
                    intent3.setClass(main_place.this, sleep.class);
                    startActivity(intent3);
                    randon=5;
                    energycal=energycal+15;
                    energy.setText(String.valueOf(energycal));
                }
            }
        });



        shopping=findViewById(R.id.shop_button);
        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStop();
                Intent intent2 = new Intent();
                intent2.setClass(main_place.this, shop.class);
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
        editor.putInt("FOOD",foodcal);
        editor.putInt("MONEY",moneycal);
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
        money.setText(String.valueOf(moneycal));
        energy.setText(String.valueOf(energycal));
        food.setText(String.valueOf(foodcal));
    }

    public void worktravel_timer(){
        not_worktraveling=0;
        ImageView im = findViewById(R.id.show_text_imageView);
        ImageView dialogim = findViewById(R.id.gifImageView);
        TextView dialogtext = findViewById(R.id.dialogtext);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time--;
                        if(time <10){
                            dialogtext.setAlpha(0f);
                            im.setAlpha(0f);    //把寵物變透明，讓使用者看不到，代表他出去工作
                            dialogim.setAlpha(0f);
                            travelwork.setAlpha(0f);
                        }
                        if (time == 0) {
                            timer.cancel();
                            im.setAlpha(1f);    //imageView.animate().alpha(1f).setDuration(1500);
                            dialogim.setAlpha(1f);
                            dialogtext.setAlpha(1f);
                            if(tw==1){
                                foodcal = foodcal + 15;
                                food.setText(String.valueOf(foodcal));
                                energycal = energycal - 10;
                                energy.setText(String.valueOf(energycal));
                                dialogmsg.setText("主人遊樂園\n好好玩喔");
                                not_worktraveling=1;
                            }else{
                                moneycal = moneycal + 15;
                                money.setText(String.valueOf(moneycal));
                                energycal = energycal - 30;
                                energy.setText(String.valueOf(energycal));
                                dialogmsg.setText("主人我被炒了\nQQ");
                                not_worktraveling=1;
                            }
                        }
                    }
                });
            }
        };
        timer.schedule(task, 1000, 1000);


        time=15;

    }

}