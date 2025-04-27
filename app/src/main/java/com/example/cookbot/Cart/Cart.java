package com.example.cookbot.Cart;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.cookbot.Cart.Ingredients.Ingredient;
import com.example.cookbot.UserDatabase.Converter.IngredientConverter;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Entity
@TypeConverters(IngredientConverter.class)
public class Cart implements Serializable {
    private static final String FILENAME_FORMAT = "yyyy-MM-dd_HH:mm:ss";

    @PrimaryKey(autoGenerate = true)
    public int cartId;

    private List<Ingredient> ingredients;
    private String cartName;

    public Cart(List<Ingredient> ingredients){
        this.ingredients = ingredients;
        this.cartName  = new SimpleDateFormat(FILENAME_FORMAT, Locale.getDefault())
                .format(new Date());
    }

    public void addIngredient(Ingredient ingredient){
        ingredients.add(ingredient);
    }

    public void removeIngredient(Ingredient ingredient){
        ingredients.remove(ingredient);
    }

    public List<Ingredient> getIngredients(){
        return ingredients;
    }

    public boolean containsIngredient(Ingredient ingredient){
        for (Ingredient ing :
                ingredients) {
            if(ing.equals(ingredient)){
                return true;
            }
        }
        return false;
    }

    public int getTotalIngredientsCount(){
        return ingredients.size();
    }

    public String getCartName() {
        return cartName;
    }
    public void setCartName(String cartName) {
        this.cartName = cartName;
    }
}
