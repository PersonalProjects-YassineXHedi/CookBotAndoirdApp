package com.example.cookbot;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecipeViewModel extends ViewModel {
    private final MutableLiveData<List<Recipe>> recipes = new MutableLiveData<>();

    public LiveData<List<Recipe>> getRecipes(){
        return recipes;
    }

    public void addRecipe(Recipe recipe){
        List<Recipe> currentList = recipes.getValue();
        if(currentList == null){
            currentList = new ArrayList<>();
        }
        currentList.add(recipe);
        recipes.setValue(currentList);
    }

    public void loadRecipes() {
        recipes.setValue(Arrays.asList(
                new Recipe("Pasta", "Creamy Alfredo pasta", R.drawable.test_image_recipe),
                new Recipe("Burger", "Juicy beef burger"),
                new Recipe("Pasta", "Creamy Alfredo pasta", R.drawable.test_image_recipe),
                new Recipe("Burger", "Juicy beef burger"),
                new Recipe("Pasta", "Creamy Alfredo pasta", R.drawable.test_image_recipe),
                new Recipe("Burger", "Juicy beef burger"),
                new Recipe("Pasta", "Creamy Alfredo pasta", R.drawable.test_image_recipe),
                new Recipe("Burger", "Juicy beef burger"),
                new Recipe("Pasta", "Creamy Alfredo pasta", R.drawable.test_image_recipe),
                new Recipe("Burger", "Juicy beef burger"),
                new Recipe("Pasta", "Creamy Alfredo pasta", R.drawable.test_image_recipe),
                new Recipe("Burger", "Juicy beef burger"),
                new Recipe("Pasta", "Creamy Alfredo pasta", R.drawable.test_image_recipe),
                new Recipe("Burger", "Juicy beef burger"),new Recipe("Pasta", "Creamy Alfredo pasta", R.drawable.test_image_recipe),
                new Recipe("Burger", "Juicy beef burger")


        ));
    }
}
