package com.example.cookbot.Ingredients;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.cookbot.R;

import java.util.ArrayList;
import java.util.List;

public class IngredientTable {

    private final Context context;
    private final TableLayout tableLayout;
    private static boolean IsFirstRow = true;
    Cart cart;

    public IngredientTable(Context context, TableLayout tableLayout, Cart cart) {
        this.context = context;
        this.tableLayout = tableLayout;
        this.cart = cart;
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
    private void addIngredientRow(Ingredient ingredient) {
        addIngredientRow(ingredient, true);
    }

    private void addIngredientRow(Ingredient ingredient, boolean isLastRow ) {
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

        if(isLastRow){
            tableLayout.addView(row);

        }else{
            addBeforeFinalRow(row);
        }
    }

    public void addIngredients(ArrayList<Ingredient> ingredients){
        for (Ingredient ingredient: ingredients) {
            addIngredientRow(ingredient);
        }
        addFinalRow();
    }

    @SuppressLint("SetTextI18n")
    private void addFinalRow(){
        TableRow row = new TableRow(context);

        TableLayout.LayoutParams rowParams = new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(rowParams);
        rowParams.setMargins(0,100,0,0);

        Button addIngredientButton = new Button(context);
        addIngredientButton.setText("Add Ingredient");
        addIngredientButton.setGravity(Gravity.CENTER);
        addIngredientButton.setTextColor(Color.WHITE);
        addIngredientButton.setBackgroundResource(R.drawable.rounded_button);
        addIngredientButton.setTextSize(15);

        TableRow.LayoutParams buttonParams = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        buttonParams.span = 2;
        buttonParams.gravity = Gravity.CENTER_HORIZONTAL;
        addIngredientButton.setLayoutParams(buttonParams);

        addIngredientButton.setOnClickListener(v -> {
            addInputRow();
        });

        row.addView(addIngredientButton);
        tableLayout.addView(row);
    }

    @SuppressLint("SetTextI18n")
    private void addInputRow(){
        TableLayout.LayoutParams rowParams = new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT);
        rowParams.setMargins(0,50,0,0);

        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER_HORIZONTAL;

        TableRow row = new TableRow(context);
        row.setLayoutParams(rowParams);

        EditText ingredientName = new EditText(context);
        ingredientName.setHint("Enter ingredient");
        ingredientName.setGravity(Gravity.CENTER);
        ingredientName.setTextSize(15);
        ingredientName.setBackground(null);
        row.addView(ingredientName);

        Button addButton = new Button(context);
        addButton.setText("Add");
        addButton.setTextColor(Color.WHITE);
        addButton.setBackgroundColor(Color.BLACK);
        addButton.setTextSize(13);
        addButton.setGravity(Gravity.CENTER);
        addButton.setLayoutParams(params);

        addButton.setOnClickListener(v -> {
            String name = ingredientName.getText().toString().trim();
            if (!name.isEmpty()) {
                Ingredient newIngredient = new Ingredient(name, false);
                cart.addIngredient(newIngredient);
                addIngredientRow(newIngredient, false);
                tableLayout.removeView(row);
            }
        });

        row.addView(addButton);

        addBeforeFinalRow(row);
    }
    private void addBeforeFinalRow(TableRow newRow) {
        int rowCount = tableLayout.getChildCount();
        tableLayout.addView(newRow, rowCount - 1);
    }

    public void clearTable() {
        tableLayout.removeAllViews();
    }

    public int getRowCount() {
        return tableLayout.getChildCount();
    }
}
