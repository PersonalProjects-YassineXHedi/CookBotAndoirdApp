package com.example.cookbot.Recipes;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cookbot.Activity.RecipeDetailsActivity;
import com.example.cookbot.R;

class RecipeViewHolder extends RecyclerView.ViewHolder {
    TextView title;
    TextView description;
    ImageView imageView;

    public RecipeViewHolder(View itemView) {
        super(itemView);
        description = itemView.findViewById(R.id.recipeDescription);
        title = itemView.findViewById(R.id.recipeTitle);
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

        itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, RecipeDetailsActivity.class);
            intent.putExtra("recipe", recipe);
            context.startActivity(intent);
            // Logic for recipe component on click
        });
    }
}
