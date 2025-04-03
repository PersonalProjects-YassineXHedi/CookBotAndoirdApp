package com.example.cookbot;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        ImageView imageView = findViewById(R.id.fullscreenImageView);

        String uriStr = getIntent().getStringExtra("image_uri");
        if (uriStr != null) {
            Uri uri = Uri.parse(uriStr);
            imageView.setImageURI(uri);
        }
    }
}
