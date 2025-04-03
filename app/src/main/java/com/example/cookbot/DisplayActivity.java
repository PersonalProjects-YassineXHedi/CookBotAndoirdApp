package com.example.cookbot;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        String uriString = getIntent().getStringExtra("image_uri");
        ImageView imageView = findViewById(R.id.fullscreenImageView);

        if (uriString != null) {
            Uri imageUri = Uri.parse(uriString);
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);

                Detector detector = new Detector(
                        this,
                        "best_float32.tflite",
                        "labels.txt",
                        new Detector.DetectorListener() {
                            @Override
                            public void onEmptyDetect() {
                                imageView.setImageBitmap(bitmap);
                            }

                            @Override
                            public void onDetect(List<BoundingBox> boundingBoxes) {
                                displayDetectedClasses(boundingBoxes);
                                runOnUiThread(() -> {
                                    Bitmap updatedBitmap = drawBoundingBoxes(bitmap, boundingBoxes);
                                    imageView.setImageBitmap(updatedBitmap);
                                });
                            }

                        }
                );
                detector.setup();
                detector.detect(bitmap);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Bitmap drawBoundingBoxes(Bitmap bitmap, List<BoundingBox> boxes) {
        Bitmap mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(mutableBitmap);

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8f);

        Paint textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(60f);
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);

        for (BoundingBox box : boxes) {
            RectF rect = new RectF(
                    box.x1 * mutableBitmap.getWidth(),
                    box.y1 * mutableBitmap.getHeight(),
                    box.x2 * mutableBitmap.getWidth(),
                    box.y2 * mutableBitmap.getHeight()
            );
            canvas.drawRect(rect, paint);
            String displayedBoxText = box.clsName + " {" + box.cnf + "}";
            canvas.drawText(displayedBoxText, rect.left, rect.top, textPaint);
        }

        return mutableBitmap;
    }

    private void displayDetectedClasses(List<BoundingBox> boundingBoxes){
        Map<String, Integer> classesAndCount = Detector.getDetectedClassesAndCount(boundingBoxes);

    }


}
