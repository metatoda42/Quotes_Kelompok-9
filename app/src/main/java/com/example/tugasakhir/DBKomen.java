package com.example.tugasakhir;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//Disini kita bikin Databasenya
//Pertama masukin komponen database. Dalam kasus ini, kita ambil tabel dari MainData.class

@Database(entities = {MainData.class},version = 1, exportSchema = false)
//Oke, baru sekarang bikin databasenya
public abstract class DBKomen extends RoomDatabase {
    //Variabel untuk database aku namakan database, biar gak lupa
    //Hanya digunakan disini kok, jadi gak perlu takut ketuker atau apa...
    private static DBKomen database;

    //Komen Database adalah nama databasenya
    private static String DATABASE_NAME = "Komen Database";

    //Nah ini adalah instansi database. Terus terang, gua gak ngerti semuanya, cuma beberapa
    public synchronized static DBKomen getInstance(Context context){
        if(database==null){//Ini buat ngecek, ada gak databasenya dalam memori smartphone.
            //Kalo belum ada databasenya, bakalan dibuatin kedalam memori smartphone
            //dengan fungsi databaseBuilder. Abis itu return database.
            database = Room.databaseBuilder(context.getApplicationContext()
                    ,DBKomen.class,DATABASE_NAME).
                    allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }
    public abstract MainDao mainDao();


}
