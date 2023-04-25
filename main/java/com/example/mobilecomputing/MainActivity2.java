package com.example.mobilecomputing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity2 extends AppCompatActivity {

    String category;
    byte[] photByteArray, photByteArray1, photByteArray2, photByteArray3, photByteArray4;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bundle extras = getIntent().getExtras();
        photByteArray = extras.getByteArray("picture");
        photByteArray1 = extras.getByteArray("picture1");
        photByteArray2 = extras.getByteArray("picture2");
        photByteArray3 = extras.getByteArray("picture3");
        photByteArray4 = extras.getByteArray("picture4");
        Bitmap photo = BitmapFactory.decodeByteArray(photByteArray, 0, photByteArray.length);
        ImageView capturedImage = (ImageView) findViewById(R.id.imageView);
        capturedImage.setImageBitmap(photo);
        /*
        Spinner categoryDropDown=findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> dropDownAdapter=ArrayAdapter.createFromResource(this, R.array.categories, android.R.layout.simple_spinner_item);
        dropDownAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        categoryDropDown.setAdapter(dropDownAdapter);
        categoryDropDown.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                category = parent.getItemAtPosition(pos).toString();
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        */
        Button uploadPhoto=(Button) findViewById(R.id.button);
        uploadPhoto.setOnClickListener(new  View.OnClickListener(){
            public void onClick(View view){
                postRequest();
            }
        });
    }

    String[] result = {"", "", "", ""};

    private void postRequest() {
        try{
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            while(result[0].length()==0) {
                OkHttpClient httpHelper1 = new OkHttpClient();
                String url1 = "http://129.219.8.135:" + 5000 + "/";
                MultipartBody.Builder photoObject1 = new MultipartBody.Builder().setType(MultipartBody.FORM);
                photoObject1.addFormDataPart("image", "image1" + ".jpg", RequestBody.create(MediaType.parse("image/*jpg"), photByteArray1));
                photoObject1.addFormDataPart("image1", "image1" + ".jpg", RequestBody.create(MediaType.parse("image/*jpg"), photByteArray1));
                photoObject1.addFormDataPart("Connection", "close");
                RequestBody requestObject1 = photoObject1.build();
                Request flaskRequest1 = new Request.Builder().post(requestObject1).url(url1).build();
                try{
                    Response response1 = httpHelper1.newCall(flaskRequest1).execute();
                    result[0] = response1.body().string();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            while(result[1].length()==0) {
                OkHttpClient httpHelper2 = new OkHttpClient();
                String url2 = "http://129.219.8.135:" + 5000 + "/";
                MultipartBody.Builder photoObject2 = new MultipartBody.Builder().setType(MultipartBody.FORM);
                photoObject2.addFormDataPart("image", "image2" + ".jpg", RequestBody.create(MediaType.parse("image/*jpg"), photByteArray2));
                photoObject2.addFormDataPart("image1", "image2" + ".jpg", RequestBody.create(MediaType.parse("image/*jpg"), photByteArray2));
                photoObject2.addFormDataPart("Connection", "close");
                RequestBody requestObject2 = photoObject2.build();
                Request flaskRequest2 = new Request.Builder().post(requestObject2).url(url2).build();
                try{
                    Response response2 = httpHelper2.newCall(flaskRequest2).execute();
                    result[1] = response2.body().string();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            while(result[2].length()==0) {
                OkHttpClient httpHelper3 = new OkHttpClient();
                String url3 = "http://129.219.8.135:" + 5000 + "/";
                MultipartBody.Builder photoObject3 = new MultipartBody.Builder().setType(MultipartBody.FORM);
                photoObject3.addFormDataPart("image", "image3" + ".jpg", RequestBody.create(MediaType.parse("image/*jpg"), photByteArray3));
                photoObject3.addFormDataPart("image1", "image3" + ".jpg", RequestBody.create(MediaType.parse("image/*jpg"), photByteArray3));
                photoObject3.addFormDataPart("Connection", "close");
                RequestBody requestObject3 = photoObject3.build();
                Request flaskRequest3 = new Request.Builder().post(requestObject3).url(url3).build();
                try{
                    Response response3 = httpHelper3.newCall(flaskRequest3).execute();
                    result[2] = response3.body().string();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            while(result[3].length()==0) {
                OkHttpClient httpHelper4 = new OkHttpClient();
                String url4 = "http://129.219.8.135:" + 5000 + "/";
                MultipartBody.Builder photoObject4 = new MultipartBody.Builder().setType(MultipartBody.FORM);
                photoObject4.addFormDataPart("image", "image4" + ".jpg", RequestBody.create(MediaType.parse("image/*jpg"), photByteArray4));
                photoObject4.addFormDataPart("image1", "image4" + ".jpg", RequestBody.create(MediaType.parse("image/*jpg"), photByteArray4));
                photoObject4.addFormDataPart("Connection", "close");
                RequestBody requestObject4 = photoObject4.build();
                Request flaskRequest4 = new Request.Builder().post(requestObject4).url(url4).build();
                try{
                    Response response4 = httpHelper4.newCall(flaskRequest4).execute();
                    result[3] = response4.body().string();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(result[0].length()!=0 && result[1].length()!=0 && result[2].length()!=0 && result[3].length()!=0){
                saveImages();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveImages() {
        Map<Integer, Integer> map = new HashMap<>();
        int digit = 0, maxCount = 0;
        for (int i = 0; i < result.length; i++) {
            String[] res = result[i].split(",");
            int predictedDigit = Integer.parseInt(res[0]);
            map.put(predictedDigit, map.getOrDefault(predictedDigit, 0) + 1);
            if (map.get(predictedDigit) > maxCount) {
                digit = predictedDigit;
                maxCount = map.get(predictedDigit);
            }
        }

        String url = "http://10.0.2.2:" + 5000 + "/";
        MultipartBody.Builder photoObject = new MultipartBody.Builder().setType(MultipartBody.FORM);
        photoObject.addFormDataPart("image1", "image1" + ".jpg", RequestBody.create(MediaType.parse("image/*jpg"), photByteArray1));
        photoObject.addFormDataPart("image2", "image2" + ".jpg", RequestBody.create(MediaType.parse("image/*jpg"), photByteArray2));
        photoObject.addFormDataPart("image3", "image3" + ".jpg", RequestBody.create(MediaType.parse("image/*jpg"), photByteArray3));
        photoObject.addFormDataPart("image4", "image4" + ".jpg", RequestBody.create(MediaType.parse("image/*jpg"), photByteArray4));
        photoObject.addFormDataPart("predictedResult", String.valueOf(digit));
        RequestBody requestObject = photoObject.build();
        OkHttpClient httpHelper = new OkHttpClient();
        Request flaskRequest = new Request.Builder().post(requestObject).url(url).build();
        httpHelper.newCall(flaskRequest).enqueue(new Callback() {
            public void onResponse(Call call, final Response response) throws IOException {
                Intent refreshPage = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(refreshPage);
            }

            public void onFailure(final Call call, final IOException e) {
            }
        });
    }
}