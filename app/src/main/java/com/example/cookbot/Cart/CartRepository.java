package com.example.cookbot.Cart;

import android.app.Application;
import androidx.lifecycle.LiveData;

import com.example.cookbot.UserDatabase.UserDataDatabase;

import java.util.List;
import java.util.concurrent.Executors;

public class CartRepository {
    private final CartDao cartDao;

    public CartRepository(Application application) {
        UserDataDatabase db = UserDataDatabase.getInstance(application);
        cartDao = db.cartDao();
    }

    public void saveCart(Cart cart) {
        Executors.newSingleThreadExecutor().execute(() -> {
            if (cart.cartId == 0) {
                long newId = cartDao.insertCart(cart);
                cart.cartId = (int) newId;
            } else {
                cartDao.updateCart(cart);
            }
        });
    }

    public LiveData<List<Cart>> getAllCarts() {
        return cartDao.getAllCartsLive();
    }
}
