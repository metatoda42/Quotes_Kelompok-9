package com.example.tugasakhir;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
//Bismillah berubahh :D
//Testing, testing 123
//YABUE
//Tes Lagi
public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    private BottomNavigationView navbar;
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
    ImageButton btAdd, btReset; //Variabel Button
    RecyclerView recyclerView; //Variabel RecyclerView

    //Nah, ini juga variabel. Varibel ini adalah variabel "dataList" dengan jenis data List.
    //Dalam kasus ini, data yang terdapat dalam list ini di handle oleh kelas MainData.
    List<MainData> dataList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    DBKomen database;
    AdapterChan adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Dibagian ini, variabel ini dihubungkan dengan komponen yang berada di XML

        //Nanti tapi, aku mau ngurus adapter bentar...
        //JIANCUK SUSAH BANGET ANJINGGGGG!!!!







        //-------------------------------------






        //Nah, kalo ini kodingan yang akan mengurus tampilan

        navbar = findViewById(R.id.main_navbar);
        navbar.setOnNavigationItemSelectedListener(this);
        loadFragment(new Fragment());
    }

    private boolean loadFragment(Fragment fragment) {
        if(fragment != null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_frame, fragment)
                    .commit();
            return true;

        }
        return false;
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()){
            case R.id.ic_home:
                fragment=new Home();
                break;
            case R.id.ic_heart:
                fragment=new Heart();
                break;


        }

        return loadFragment(fragment);
    }
}