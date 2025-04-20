package com.example.cookbot.Ingredients;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Cart implements Serializable {
    private static final String FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss";

    private ArrayList<Ingredient> ingredients;
    private String cartName;

    public Cart(ArrayList<Ingredient> ingredients){
        this.ingredients = ingredients;
        cartName  = new SimpleDateFormat(FILENAME_FORMAT, Locale.getDefault())
                .format(new Date());
    }

    public void addIngredient(Ingredient ingredient){
        ingredients.add(ingredient);
    }

    public void removeIngredient(Ingredient ingredient){
        ingredients.remove(ingredient);
    }

    public ArrayList<Ingredient> getIngredients(){
        return ingredients;
    }
}
