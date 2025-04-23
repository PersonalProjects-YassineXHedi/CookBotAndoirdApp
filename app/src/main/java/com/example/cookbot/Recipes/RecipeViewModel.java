package com.example.cookbot.Recipes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cookbot.R;

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

    public void loadRecipes(List<Recipe> suggestedRecipes) {
        for (Recipe recipe :
                suggestedRecipes) {
            addRecipe(recipe);
        }
    }


}
