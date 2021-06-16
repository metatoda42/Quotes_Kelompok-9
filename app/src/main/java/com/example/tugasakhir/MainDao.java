package com.example.tugasakhir;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface MainDao {
    @Insert(onConflict = REPLACE)
    void insert(MainData mainData);

    @Delete
    void delete(MainData mainData);
    @Delete
    void reset(List<MainData> mainData);

    @Query("UPDATE datalokal SET komentar = :sText WHERE ID = :sID")
    void update(int sID, String sText, String qText);

    @Query("SELECT * FROM datalokal")
    List<MainData> getAll();
}
