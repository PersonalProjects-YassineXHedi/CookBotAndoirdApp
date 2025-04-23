package com.example.cookbot.Ingredients;

import java.io.Serializable;
import java.util.Objects;

public class Ingredient implements Serializable {
    private String name;
    private boolean isSelected;

    public Ingredient(String name, boolean isSelected) {
        this.name = name;
        this.isSelected = isSelected;
    }
    public Ingredient(String name) {
        this.name = name;
        this.isSelected = false;
    }

    public boolean Equals(Ingredient other){
        if(!Objects.equals(name, other.name)){return false;}
        return true;
    }

    public String getName() { return name; }
    public boolean isSelected() { return isSelected; }
    public void setSelected(boolean selected) { isSelected = selected; }
}
