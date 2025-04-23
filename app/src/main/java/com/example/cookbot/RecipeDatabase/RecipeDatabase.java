package com.example.cookbot.RecipeDatabase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cookbot.Recipes.Recipe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecipeDatabase {
    private Context context;

    public RecipeDatabase(Context context){
        this.context = context;
    }

    private SQLiteDatabase saladDatabase(){
        SQLiteDatabase saladDatabase = null;
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        try{
            dbHelper.createDatabase();
            saladDatabase = dbHelper.getReadableDatabase();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return saladDatabase;
    }

    private List<Recipe> transformDatabaseToRecipesList(SQLiteDatabase saladDatabase){
        if(saladDatabase == null) {
            return null;
        }
        List<Recipe> recipes = new ArrayList<>();
        Cursor cursor = saladDatabase.rawQuery("SELECT * FROM recipes_v1", null);

        if (cursor.moveToFirst()) {
            do {
                recipes.add(new Recipe(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return recipes;
    }

    public List<Recipe> getAllRecipes(){
        SQLiteDatabase saladDatabase = saladDatabase();
        return transformDatabaseToRecipesList(saladDatabase);
    }

    public static Recipe getRecipeById(List<Recipe> allRecipes, int id){
        for (Recipe recipe :
                allRecipes) {
            if (recipe.id == id) {
                return recipe;
            }
        }
        return null;
    }

}
