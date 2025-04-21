package com.example.cookbot.Recipes;

import com.example.cookbot.R;

import java.io.Serializable;
import java.util.ArrayList;

public class Recipe implements Serializable {
    public final String title;
    public final String description;
    public final Integer imageResId;
    public ArrayList<String> steps;

    public Recipe(String title, String description){
        this.title = title;
        this.description = description;
        this.imageResId = R.drawable.cook;
    }
    public Recipe(String title, String description, int imageResId){
        this.title = title;
        this.description = description;
        this.imageResId = imageResId;
    }

    public Recipe(String title, String description, int imageResId, ArrayList<String> steps){
        this.title = title;
        this.description = description;
        this.imageResId = imageResId;
        this.steps = steps;

    }

}
