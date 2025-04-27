package com.example.cookbot.Cart;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CartDao {
    @Insert
    long insertCart(Cart cart);

    @Update
    void updateCart(Cart cart);

    @Query("SELECT * FROM Cart")
    LiveData<List<Cart>> getAllCartsLive();
}
