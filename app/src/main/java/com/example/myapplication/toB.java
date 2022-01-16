package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class toB extends AppCompatActivity{
    private Button btn_to_A;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("B");
        setContentView(R.layout.activity_b);
        btn_to_A=(Button) findViewById(R.id.btn_to_A);
        btn_to_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(toB.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
