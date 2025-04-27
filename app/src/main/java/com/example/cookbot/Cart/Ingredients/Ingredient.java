package com.example.cookbot.Cart.Ingredients;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Ingredient that = (Ingredient) obj;
        return Objects.equals(name, that.name);
    }

    public String getName() { return name; }
    public boolean isSelected() { return isSelected; }
    public void setSelected(boolean selected) { isSelected = selected; }
}
