package com.example.cookbot.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.cookbot.Cart.Cart;
import com.example.cookbot.Cart.CartViewModel;
import com.example.cookbot.Cart.Ingredients.IngredientTable;
import com.example.cookbot.R;
import com.google.android.material.appbar.MaterialToolbar;


public class CartActivity extends AppCompatActivity {
    private Cart myCart;
    TableLayout ingredientTable;
    Button searchrecipesButton;
    MaterialToolbar toolbar;
    CartViewModel viewModel;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        viewModel = new CartViewModel(getApplication());

        myCart = (Cart) getIntent().getSerializableExtra("cart");
        ingredientTable = findViewById(R.id.tableLayout);
        searchrecipesButton = findViewById(R.id.searchButton);
        toolbar = findViewById(R.id.cartToolbar);
        context = this;

        DisplayCartToolBar();

        createDetectedIngredientsTable();

        searchrecipesButton.setOnClickListener(v -> {searchRecipes();});
    }

    private void DisplayCartToolBar(){
        EditText cartNameEditText = new EditText(this);
        cartNameEditText.setText(myCart.getCartName());
        cartNameEditText.setTextColor(Color.WHITE);
        cartNameEditText.setBackground(null);
        cartNameEditText.setEnabled(false);
        cartNameEditText.setTextSize(20);

        Toolbar.LayoutParams layoutParams = new Toolbar.LayoutParams(
                Toolbar.LayoutParams.WRAP_CONTENT,
                Toolbar.LayoutParams.WRAP_CONTENT
        );
        layoutParams.gravity = Gravity.CENTER_VERTICAL;
        cartNameEditText.setLayoutParams(layoutParams);

        toolbar.addView(cartNameEditText);

        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_save_cart) {
                viewModel.insertCart(myCart);
                return true;
            }
            if (item.getItemId() == R.id.action_edit_cart_name) {
                cartNameEditText.setEnabled(true);
                cartNameEditText.requestFocus();
                cartNameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (!hasFocus) {
                            myCart.setCartName(cartNameEditText.getText().toString());
                            cartNameEditText.setEnabled(false);
                        }
                    }
                });
                return true;
            }
            return false;
        });
    }

    private void createDetectedIngredientsTable(){
        IngredientTable table = new IngredientTable(this, ingredientTable, myCart);
        table.addIngredients(myCart.getIngredients());
    }

    private void searchRecipes(){
        Intent intent = new Intent(CartActivity.this, RecipesProposalsActivity.class);
        intent.putExtra("cart", myCart);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart_menu, menu);
        return true;
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }


}

