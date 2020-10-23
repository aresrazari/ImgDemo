package com.marklwembe.imgdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

public class MainActivity extends AppCompatActivity {

    Button button;
    ImageView imageView;
    String server_url = "http://192.168.0.15/android.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.getImage);
        imageView = (ImageView)findViewById(R.id.img);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageRequest imageRequest = new ImageRequest(
                        server_url,
                        new Response.Listener<Bitmap>() {
                            @Override
                            public void onResponse(Bitmap response) {
                                imageView.setImageBitmap(response);
                            }
                        },
                        0,
                        0,
                        ImageView.ScaleType.CENTER_CROP,
                        null,
                        new Response.ErrorListener() {
                            @Override
                            public  void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                                error.printStackTrace();
                            }
                        }
                );

                MySingleton.getInstance(MainActivity.this).addToRequestQueue(imageRequest);

            }
        });
    }
}