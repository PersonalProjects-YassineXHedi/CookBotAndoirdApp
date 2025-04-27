package com.example.cookbot.Recipes;

import android.annotation.SuppressLint;
import android.database.Cursor;

import com.example.cookbot.Cart.Ingredients.Ingredient;
import com.example.cookbot.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Recipe implements Serializable {
    public final int id;
    public final String title;
    public final String description;
    public List<Ingredient> ingredients;
    public List<String> steps;
    public final Integer imageResId;

    @SuppressLint("Range")
    public Recipe(Cursor cursor) {
        this.id = cursor.getInt(cursor.getColumnIndex("id"));
        this.title = cursor.getString(cursor.getColumnIndex("name"));
        this.description = cursor.getString(cursor.getColumnIndex("description"));
        this.imageResId = R.drawable.cook;
        this.ingredients = getRecipeIngredients(cursor);

        //TODO: add the steps to each recipe
        this.steps = null;
    }

    private List<String> parseJsonArray(String jsonString) {
        List<String> list = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(jsonString);
            for (int i = 0; i < array.length(); i++) {
                list.add(array.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    private List<Ingredient> getRecipeIngredients(Cursor cursor){
        List<Ingredient> ingredients = new ArrayList<>();

        @SuppressLint("Range")
        String jsonString = cursor.getString(cursor.getColumnIndex("ingredients"));

        List<String> initialList = parseJsonArray(jsonString);
        for (String ingName :
                initialList) {
            Ingredient ingredient = new Ingredient(ingName);
            ingredients.add(ingredient);
        }
        return ingredients;
    }

    public int countMatchingIngredients(List<Ingredient> availableIngredients){
        int count = 0;
        for (Ingredient availableIngredient :
                availableIngredients) {
            if(containsIngredient(availableIngredient)){
                count++;
            }
        }
        return count;
    }

    private boolean containsIngredient(Ingredient ingredient){
        for (Ingredient ing :
                ingredients) {
            if(ing.equals(ingredient)){
                return true;
            }
        }
        return false;
    }

}
