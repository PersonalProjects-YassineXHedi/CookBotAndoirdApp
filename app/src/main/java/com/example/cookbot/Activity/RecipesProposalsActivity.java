package com.example.cookbot.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookbot.Cart.Cart;
import com.example.cookbot.R;
import com.example.cookbot.Recipes.Recipe;
import com.example.cookbot.Recipes.RecipeAdapter;
import com.example.cookbot.Recipes.RecipeViewModel;
import com.example.cookbot.Recipes.RecipesSearch;

import java.util.List;

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

        Cart myCart = (Cart) getIntent().getSerializableExtra("cart");
        RecipesSearch recipesSearch = new RecipesSearch(this, myCart);
        List<Recipe> myRecipes = recipesSearch.getSuggestRecipes();

        viewModel.loadRecipes(myRecipes);
    }
}
