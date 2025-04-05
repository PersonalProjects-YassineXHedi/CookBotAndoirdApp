package com.example.cookbot;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ClassesTable {

    private final Context context;
    private final TableLayout tableLayout;
    private static boolean IsFirstRow = true;

    public ClassesTable(Context context, TableLayout tableLayout) {
        this.context = context;
        this.tableLayout = tableLayout;
    }

    private void setupTable() {
        tableLayout.removeAllViews(); // Clear previous content
        TableRow headerRow = new TableRow(context);
        List<String> headers = new ArrayList<String>();
        headers.add("Ingredients");
        headers.add("Added to Cart");
        for (String header : headers) {
            TextView textView = new TextView(context);
            textView.setText(header);
            textView.setPadding(100,30,100,30);
            textView.setTypeface(null, Typeface.BOLD);
            textView.setTextSize(20);
            headerRow.addView(textView);
        }

        tableLayout.addView(headerRow);
    }

    public void addRow(Ingredient ingredient) {
        if(IsFirstRow) {
            setupTable();
            IsFirstRow = false;
        }
        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER_HORIZONTAL;

        TableRow row = new TableRow(context);

        TextView classText = new TextView(context);
        classText.setText(ingredient.getName());
        classText.setGravity(Gravity.CENTER);
        classText.setTextSize(15);
        row.addView(classText);

        CheckBox checkBox = new CheckBox(context);
        checkBox.setChecked(ingredient.isSelected());
        checkBox.setGravity(Gravity.CENTER);
        checkBox.setLayoutParams(params);
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            ingredient.setSelected(isChecked);
        });

        row.addView(checkBox);

        tableLayout.addView(row);
    }

    public void clearTable() {
        tableLayout.removeAllViews();
    }

    public int getRowCount() {
        return tableLayout.getChildCount();
    }
}
