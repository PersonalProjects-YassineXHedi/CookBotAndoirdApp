package com.example.cookbot.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cookbot.Cart;
import com.example.cookbot.IngredientTable;
import com.example.cookbot.R;

import kotlin.NotImplementedError;


public class CartActivity extends AppCompatActivity {
    private Cart myCart;
    TableLayout ingredientTable;
    Button searchrecipesButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        myCart = (Cart) getIntent().getSerializableExtra("cart");
        ingredientTable = findViewById(R.id.tableLayout);
        searchrecipesButton = findViewById(R.id.searchButton);

        createDetectedIngredientsTable();

        searchrecipesButton.setOnClickListener(v -> {searchRecipes();});
    }

    private void createDetectedIngredientsTable(){
        IngredientTable table = new IngredientTable(this, ingredientTable, myCart);
        table.addIngredients(myCart.getIngredients());
    }

    private void searchRecipes(){
        Intent intent = new Intent(CartActivity.this, RecipesProposalsActivity.class);
        startActivity(intent);
    }


}

