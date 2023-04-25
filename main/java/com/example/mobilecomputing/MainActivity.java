package com.example.mobilecomputing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button yesButton=(Button) findViewById(R.id.button1);
        yesButton.setOnClickListener(new  View.OnClickListener(){
            public void onClick(View view){
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 1);
            }
        });

        Button noButton=(Button) findViewById(R.id.button2);
        noButton.setOnClickListener(new  View.OnClickListener(){
            public void onClick(View view){
                finish();
                System.exit(0);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap capturedPhoto = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        capturedPhoto.compress(Bitmap.CompressFormat.PNG, 100, byteStream);
        byte[] photoByteArray = byteStream.toByteArray();
        Intent nextPage = new Intent(MainActivity.this, MainActivity2.class);
        nextPage.putExtra("picture", photoByteArray);
        startActivity(nextPage);

        Bitmap[] images = new Bitmap[4];
        int width=capturedPhoto.getWidth(), height=capturedPhoto.getHeight();
        images[0] = Bitmap.createBitmap(capturedPhoto, 0, 0, width/2 , height/2);
        images[1] = Bitmap.createBitmap(capturedPhoto, width/2, 0, width/2, height/2);
        images[2] = Bitmap.createBitmap(capturedPhoto,0, height/2, width/2,height/2);
        images[3] = Bitmap.createBitmap(capturedPhoto, width/2, height/2, width/2, height/2);

        ByteArrayOutputStream byteStream1 = new ByteArrayOutputStream();
        images[0].compress(Bitmap.CompressFormat.PNG, 100, byteStream1);
        byte[] photoByteArray1 = byteStream1.toByteArray();
        nextPage.putExtra("picture1", photoByteArray1);

        ByteArrayOutputStream byteStream2 = new ByteArrayOutputStream();
        images[1].compress(Bitmap.CompressFormat.PNG, 100, byteStream2);
        byte[] photoByteArray2 = byteStream2.toByteArray();
        nextPage.putExtra("picture2", photoByteArray2);

        ByteArrayOutputStream byteStream3 = new ByteArrayOutputStream();
        images[2].compress(Bitmap.CompressFormat.PNG, 100, byteStream3);
        byte[] photoByteArray3 = byteStream3.toByteArray();
        nextPage.putExtra("picture3", photoByteArray3);

        ByteArrayOutputStream byteStream4 = new ByteArrayOutputStream();
        images[3].compress(Bitmap.CompressFormat.PNG, 100, byteStream4);
        byte[] photoByteArray4 = byteStream4.toByteArray();
        nextPage.putExtra("picture4", photoByteArray4);

        startActivity(nextPage);
    }
}