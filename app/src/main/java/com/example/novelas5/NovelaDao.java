package com.example.novelas5;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NovelaDao {
    @Insert
    void insert(Novela novela);

    @Update
    void update(Novela novela);

    @Delete
    void delete(Novela novela);

    @Query("SELECT * FROM novelas ORDER BY titulo ASC")
    LiveData<List<Novela>> getAllNovelas();
}
