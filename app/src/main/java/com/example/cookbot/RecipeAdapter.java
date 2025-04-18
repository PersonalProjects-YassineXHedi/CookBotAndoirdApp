package com.example.cookbot;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private final Context context;
    private List<Recipe> recipes = new ArrayList<>();

    public RecipeAdapter(Context context) {
        this.context = context;
    }

    public void setRecipes(List<Recipe> newList) {
        this.recipes = newList;
        notifyDataSetChanged();
    }

    static class RecipeViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        ImageView imageView;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.recipeTitle);
            title = itemView.findViewById(R.id.recipeDescription);
            imageView = itemView.findViewById(R.id.recipeImage);
        }

        public void bind(Recipe recipe, Context context) {
            title.setText(recipe.title);
            description.setText(recipe.description);
            if(recipe.imageResId != null){
                imageView.setImageResource(recipe.imageResId);
            }else{
                imageView.setImageResource(R.drawable.ic_launcher_foreground);
            }

//            itemView.setOnClickListener(v -> {
//                // Logic for recipe component on click
//            });
        }
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

