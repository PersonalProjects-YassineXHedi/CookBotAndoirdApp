package com.example.cookbot.Activity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cookbot.BoundingBox;
import com.example.cookbot.Detector;
import com.example.cookbot.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DetectedImageActivity extends AppCompatActivity {
    Bitmap imageBitmap;
    ImageView imageView;
    Button viewIngredientsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detected_image);

        imageView = findViewById(R.id.imageView);
        viewIngredientsButton = findViewById(R.id.viewIngredientButton);

        displayDetectedBoxesOnImage();

        viewIngredientsButton.setOnClickListener(v -> viewIngredientTable());
    }

    private void viewIngredientTable(){

    }

    private void displayDetectedBoxesOnImage(){
        @SuppressWarnings("unchecked")
        ArrayList<BoundingBox> boxes = (ArrayList<BoundingBox>) getIntent().getSerializableExtra("boxes");

        String uriString = getIntent().getStringExtra("image_uri");
        try{
            if(uriString != null){
                Uri imageUri = Uri.parse(uriString);
                imageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        if(imageBitmap != null && boxes != null){
            runOnUiThread(() -> {
                Bitmap updatedBitmap = drawBoundingBoxes(imageBitmap, boxes);
                imageView.setImageBitmap(updatedBitmap);
            });
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


}