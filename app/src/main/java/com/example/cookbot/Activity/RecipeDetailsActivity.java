package com.example.cookbot.Activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cookbot.R;
import com.example.cookbot.Recipes.Recipe;

public class RecipeDetailsActivity extends AppCompatActivity {
    Recipe myRecipe;
    TextView name;
    ImageView image;
    TextView description;
    int stepCounter = 1;
    String stepText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        myRecipe = (Recipe) getIntent().getSerializableExtra("recipe");
        name = findViewById(R.id.name1);
        name.setText(myRecipe.title);
        image = findViewById(R.id.recipeImage);
        image.setImageResource(myRecipe.imageResId);
        description = findViewById(R.id.descriptionText);
        description.setText(myRecipe.description);

        /*
        for (String step : myRecipe.steps) {
            stepText += (stepCounter + "/ " + step);
            stepCounter++;

        }*/





    }


}


