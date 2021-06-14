package com.example.tugasakhir;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//Ok, disini sudah mulai agak rumit.
//Intinya, Main Data adalah data yagn terdapat pada Database
//Nah, tableName adalah nama dari table
//Dalam kasus ini aku cuma bikin satu table bernama "Data Lokal", yang satunya lagi nanti akan
//diurus sama API karena databasenya berasal dari API

@Entity(tableName = "Data Lokal")

public class MainData {
    //Bikin tabel!
    @PrimaryKey(autoGenerate=true) private int ID;
    @ColumnInfo (name = "komentar") private String komentar;
    @ColumnInfo(name = "idQuote") private String idQuote;

    //Selanjutnya adalah Getter dan Setter
    //Pada dasarnya, Getter dan Setter adalah fungsi yang akan kita gunakan untuk melakukan
    //CRUD pada tabel yang tersedia


    public int getID() {//Ini Variabel untuk mendapatkan ID atau Primary Key
        return ID;
    }

    public void setID(int ID) {//Ini Variabel untuk mengubah ID atau Primary Key
        this.ID = ID;
    }

    public String getKomentar() {//Ini Variabel untuk mendapatkan komentar
        return komentar;
    }

    public void setKomentar(String komentar) {//Ini Variabel untuk mengubah komentar
        this.komentar = komentar;
    }

    public String getIdQuote() {//sama kayak yang diatas, mager gua...
        return idQuote;
    }

    public void setIdQuote(String idQuote) {
        this.idQuote = idQuote;
    }
}
