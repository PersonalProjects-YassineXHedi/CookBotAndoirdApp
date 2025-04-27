package com.example.cookbot.Cart;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CartViewModel extends AndroidViewModel {
    private final CartRepository repository;
    private LiveData<List<Cart>> allCarts;

    public CartViewModel(@NonNull Application application) {
        super(application);
        repository = new CartRepository(application);
        allCarts = repository.getAllCarts();
    }

    public LiveData<List<Cart>> getAllCarts() {
        return allCarts;
    }

    public void insertCart(Cart cart) {
        repository.saveCart(cart);
    }
}
