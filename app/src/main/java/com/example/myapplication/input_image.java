package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import java.io.ByteArrayOutputStream;

public class input_image extends AppCompatActivity {

    ImageView image;
    Button choose_btn;
    Button next_page2;
    public static final String Extra_Image="com.example.myapplication.Image";

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;
    int choose = 0;
    Intent intent2=new Intent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(!Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_image);

        image = findViewById(R.id.imageView2);
        choose_btn = findViewById(R.id.choose_btn);
        choose_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_DENIED){
                        //permission not granted, request it
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        //show popup for runtime permission
                        requestPermissions(permissions, PERMISSION_CODE);
                    }
                    else{
                            //permission already granted
                        pickImageFromGallery();

                    }
                }
                else{
                    //system is less than marshmellow
                }
            }
        });
        next_page2 = findViewById(R.id.next_page2);
        next_page2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent2.setClass(input_image.this,typename.class);
                if(choose==1){
                Bitmap im = ((BitmapDrawable)image.getDrawable()).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                im.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                intent2.putExtra(Extra_Image,byteArray);
                startActivity(intent2);
                }
            }
        });

    }


    private void pickImageFromGallery() {
        //pick image
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,IMAGE_PICK_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){

            case PERMISSION_CODE:{
                if(grantResults.length>0&& grantResults[0]==
                PackageManager.PERMISSION_GRANTED){
                    pickImageFromGallery();
                }
                else{
                    //permission denied
                    Toast.makeText(this,"Permission denied...!",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            image.setImageURI(data.getData());
            final Python py = Python.getInstance();
            Bitmap im = ((BitmapDrawable)image.getDrawable()).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            im.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            String encodedImage = android.util.Base64.encodeToString(byteArray, Base64.DEFAULT);
            PyObject pyo = py.getModule("script");
            PyObject obj = pyo.callAttr("main",encodedImage);
            String str = obj.toString();
            byte ret_im[] = android.util.Base64.decode(str,Base64.DEFAULT);
            Bitmap bmp = BitmapFactory.decodeByteArray(ret_im,0,ret_im.length);
            image.setImageBitmap(bmp);
            choose=1;
        }
    }
}
