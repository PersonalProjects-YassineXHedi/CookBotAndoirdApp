package com.example.cookbot.Recipes;

import android.content.Context;

import com.example.cookbot.Ingredients.Cart;
import com.example.cookbot.Ingredients.Ingredient;
import com.example.cookbot.RecipeDatabase.RecipeDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class RecipesSearch {
    Cart cart;
    List<Recipe> allRecipes;
    private final int minMatchPercentage = 80;

    public RecipesSearch(Context context, Cart cart){
        this.cart = cart;

        RecipeDatabase recipeDatabase = new RecipeDatabase(context);
        allRecipes = recipeDatabase.getAllRecipes();
    }

    public List<Recipe> getSuggestRecipes(){
        List<Recipe> matchedRecipes = new ArrayList<>();
        List<Ingredient> cartIngredients = cart.getIngredients();

        List<Integer> listIds = getSortedMatchingRecipeIdList(cartIngredients);
        for (Integer id :
                listIds) {
            matchedRecipes.add(RecipeDatabase.getRecipeById(allRecipes,id));
        }
        return matchedRecipes;
    }

    private List<Integer> getSortedMatchingRecipeIdList(List<Ingredient> cartIngredients){
        Map<Integer, Integer> recipeIdAndCountDict = new HashMap<>();
        float minCount = getMinMatchCount();
        for (Recipe recipe : allRecipes) {
            int count = recipe.countMatchingIngredients(cartIngredients);
            if(count < minCount){
                break;
            }
            recipeIdAndCountDict.put(recipe.id, count);
        }
        return recipeIdAndCountDict.entrySet()
                .stream()
                .sorted((a, b) -> b.getValue() - a.getValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private int getMinMatchCount(){
        int totalCount = cart.getTotalIngredientsCount();
        return Math.round(totalCount * (minMatchPercentage / 100f));
    }
}
