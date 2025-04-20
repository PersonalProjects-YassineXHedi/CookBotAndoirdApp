package com.example.cookbot.Recipes;

public class Recipe {
    public final String title;
    public final String description;
    public final Integer imageResId;
    private String steps;

    public Recipe(String title, String description){
        this.title = title;
        this.description = description;
        this.imageResId = null;
    }
    public Recipe(String title, String description, int imageResId){
        this.title = title;
        this.description = description;
        this.imageResId = imageResId;
    }
}
