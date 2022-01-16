package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class typename extends AppCompatActivity{
    ImageView im;
    public static final String Extra_Message="com.example.myapplication.Message";

    private Button next;
    Intent intent = new Intent();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.typename);
        im = findViewById(R.id.typename_imageView);
        Bundle bundle = getIntent().getExtras();
            byte[] byteArray = bundle.getByteArray(input_image.Extra_Image);
            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            im.setImageBitmap(bmp);
            send_text(byteArray);
    }

    public void send_text(byte[] byteArray){
        intent.setClass(typename.this, main_place.class);
        next=(Button) findViewById(R.id.next_page);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText en_text = (EditText) findViewById(R.id.input_name);
                String message=en_text.getText().toString();
                intent.putExtra(Extra_Message,message);
                intent.putExtra("from_typename",byteArray);
                if(en_text.getText().length()>0)
                startActivity(intent);
            }
        });
    }
}

