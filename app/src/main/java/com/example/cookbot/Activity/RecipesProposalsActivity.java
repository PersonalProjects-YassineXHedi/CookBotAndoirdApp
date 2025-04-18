package com.example.cookbot.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookbot.R;
import com.example.cookbot.RecipeAdapter;
import com.example.cookbot.RecipeViewModel;

public class RecipesProposalsActivity extends AppCompatActivity {

    private RecipeViewModel viewModel;
    private RecipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_proposals);

        RecyclerView recyclerView = findViewById(R.id.recipeRecyclerView);
        adapter = new RecipeAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(RecipeViewModel.class);
        viewModel.getRecipes().observe(this, adapter::setRecipes);
        viewModel.loadRecipes();
    }
}
