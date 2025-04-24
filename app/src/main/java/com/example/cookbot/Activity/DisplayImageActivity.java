package com.example.cookbot.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cookbot.Model.BoundingBox;
import com.example.cookbot.Model.Detector;
import com.example.cookbot.R;

import java.io.Serializable;
import java.util.List;

public class DisplayImageActivity extends AppCompatActivity {
    Button scanButton;
    Button retakePictureButton;
    Bitmap imageBitmap;
    ImageView imageView;
    String imageUri;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_image);
        scanButton = findViewById(R.id.scanButton);
        retakePictureButton = findViewById(R.id.retakePictureButton);

        context = this;

        imageUri = getIntent().getStringExtra("image_uri");
        imageView = findViewById(R.id.imageView);
        try{
            if(imageUri != null){
                Uri uri = Uri.parse(imageUri);
                imageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                imageView.setImageBitmap(imageBitmap);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        scanButton.setOnClickListener(v -> scanImage());
        retakePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void scanImage(){
        Detector detector = new Detector(
                this,
                "best_float32.tflite",
                "labels.txt",
                new Detector.DetectorListener() {
                    @Override
                    public void onEmptyDetect() {
                        new AlertDialog.Builder(context)
                                .setTitle("Oops! No Ingredients Found")
                                .setMessage("It looks like we couldn't spot any ingredients in your photo.")
                                .setPositiveButton("Try Again", (dialog, which) -> {
                                    Intent intent = new Intent(context, MainActivity.class);
                                    context.startActivity(intent);
                                })
                                .show();
                    }

                    @Override
                    public void onDetect(List<BoundingBox> boundingBoxes) {
                        Intent intent = new Intent(DisplayImageActivity.this, DetectedImageActivity.class);
                        intent.putExtra("boxes", (Serializable) boundingBoxes);
                        intent.putExtra("image_uri", imageUri);
                        startActivity(intent);
                    }

                }
        );
        detector.setup();
        detector.detect(imageBitmap);

    }

}
