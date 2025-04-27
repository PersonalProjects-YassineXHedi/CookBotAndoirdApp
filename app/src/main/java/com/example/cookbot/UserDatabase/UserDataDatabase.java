package com.example.cookbot.UserDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.cookbot.Cart.Cart;
import com.example.cookbot.Cart.CartDao;
import com.example.cookbot.UserDatabase.Converter.IngredientConverter;

@Database(entities = {Cart.class}, version = 1, exportSchema = false)
@TypeConverters({IngredientConverter.class})
public abstract class UserDataDatabase extends RoomDatabase {
    private static volatile UserDataDatabase INSTANCE;

    public abstract CartDao cartDao();

    public static UserDataDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (UserDataDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            UserDataDatabase.class,
                            "user_data.db"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }
}
