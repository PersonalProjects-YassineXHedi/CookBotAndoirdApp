package com.example.cookbot.Recipes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookbot.R;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeViewHolder> {

    private final Context context;
    private List<Recipe> recipes = new ArrayList<>();

    public RecipeAdapter(Context context) {
        this.context = context;
    }

    public void setRecipes(List<Recipe> newList) {
        this.recipes = newList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recipe, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        holder.bind(recipes.get(position), context);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }
}

