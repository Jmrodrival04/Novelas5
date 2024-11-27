package com.example.novelas5;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Novela.class}, version = 1, exportSchema = false)
public abstract class NovelaDatabase extends RoomDatabase {

    private static NovelaDatabase instance;

    public abstract NovelaDao novelaDao();

    public static synchronized NovelaDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            NovelaDatabase.class, "novela_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
