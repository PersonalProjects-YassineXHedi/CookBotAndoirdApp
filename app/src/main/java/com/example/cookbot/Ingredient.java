package com.example.cookbot;

public class Ingredient {
    private String name;
    private boolean isSelected;

    public Ingredient(String name, boolean isSelected) {
        this.name = name;
        this.isSelected = isSelected;
    }
    public Ingredient(String name) {
        this.name = name;
        this.isSelected = true;
    }

    public String getName() { return name; }
    public boolean isSelected() { return isSelected; }
    public void setSelected(boolean selected) { isSelected = selected; }
}
