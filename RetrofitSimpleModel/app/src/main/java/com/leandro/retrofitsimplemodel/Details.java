package com.leandro.retrofitsimplemodel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

public class Details extends AppCompatActivity {

    Context context;
    ImageView imageView;
    TextView textView;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Detail Activity");



        Intent intent = getIntent();
        imageView = findViewById(R.id.image_id);
        String imageUrl = String.valueOf(intent.getStringExtra("image_detail"));
        Picasso.get().load(imageUrl).into(imageView);


        String text  = (getIntent().getExtras().getString("text_detail"));
        textView = findViewById(R.id.textView_id);
        textView.setText(getIntent().getExtras().getString("text_detail"));
        Log.i("TEXTO","Texts "+text);



        getSupportActionBar().setTitle(text);

    }
}
