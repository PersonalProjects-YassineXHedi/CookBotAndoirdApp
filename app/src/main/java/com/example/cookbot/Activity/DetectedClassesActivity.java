package com.example.cookbot.Activity;

import android.os.Bundle;
import android.widget.TableLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cookbot.ClassesTable;
import com.example.cookbot.R;


public class DetectedClassesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detected_classes);

        getDetectedIngredientsTable();
    }

    private void getDetectedIngredientsTable(){
        TableLayout table = findViewById(R.id.tableLayout);
        ClassesTable classesTable = new ClassesTable(this, table);


    }
}

