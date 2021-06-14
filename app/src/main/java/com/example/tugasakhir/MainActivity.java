package com.example.tugasakhir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

//Testing, testing 123
public class MainActivity extends AppCompatActivity {
    //Deklarasi Variabel, kayak di C++ gitu
    //Bedanya, disini variabelnya adalah komponen komponen XML yang nantinya akan diubah.
    //Contoh, kalo biasanya situ belajar tentang variabel x bisa diisi dengan angka atau text
    //Di Android Studio, variabel editText dideklarasi sebagai EditText sehingga bisa menggantikan
    // komponen EditText yang ada di dalam XML.

    //Selanjutnya, ada Variabel btnAdd. Sifatnya adalah Button, jadi variabel ini bisa diganti
    //fungsinya ketika di klik.

    //Recycler View itu intinya, suatu view secara keseluruhan. Jadi seluruh konten tertentu di
    //masukan kedalam satu variabel besar bernama recyclerView.

    EditText editText; //Variabel EditText
    Button btAdd, btReset; //Variabel Button
    RecyclerView recyclerView; //Variabel RecyclerView

    //Nah, ini juga variabel. Varibel ini adalah variabel "dataList" dengan jenis data List.
    //Dalam kasus ini, data yang terdapat dalam list ini di handle oleh kelas MainData.
    List<MainData> dataList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    DBKomen database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Dibagian ini, variabel ini dihubungkan dengan komponen yang berada di XML
        editText = findViewById(R.id.edit_text);
        btAdd = findViewById(R.id.bt_add);
        btReset = findViewById(R.id.bt_reset);
        recyclerView = findViewById(R.id.recycler_view);


    }
}