package com.example.tugasakhir;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment {
    //Disini hubungkan dengan API
    //dan database
    //intinya ini proses yang mengurus halaman home
    //semua kodenya intinya

    EditText editText;
    TextView tvQuote, tvAuth;
    ImageButton btSave,btReset;
    List<MainData> dataList = new ArrayList<>();
    DBKomen database;
    AdapterHeart adapter;
    QuoteDataService quoteDataService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        tvQuote = view.findViewById(R.id.tv_randQuote);
        tvAuth = view.findViewById(R.id.tv_authQuote);
        btSave = view.findViewById(R.id.btnSave);
        btReset = view.findViewById(R.id.btnReset);
        editText = view.findViewById(R.id.et_komQuote);
        database = DBKomen.getInstance(this.getContext());
        adapter = new AdapterHeart(getContext(),dataList);
        quoteDataService = new QuoteDataService(getContext());
        getQuote();
        //=====================================================================
        //TOMBOL RESET DIPENCET, HASILNYA INI
       btReset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getQuote();
            }
        });

       btSave.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                String sText = editText.getText().toString().trim();

               if(!sText.equals("")){
                   MainData data = new MainData();

                   data.setKomentar(sText);

                   database.mainDao().insert(data);

                   editText.setText("");

                   dataList.clear();
                   dataList.addAll(database.mainDao().getAll());
                   adapter.notifyDataSetChanged();
               }
           }
       });
        return view;
    }

    public void getQuote(){
        quoteDataService.getQuoteContent(new QuoteDataService.VolleyResponseListener() {

            @Override
            public void onError(String message) {
                Toast.makeText(getActivity(), "FFFFUCK", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String konten){
                tvQuote.setText(konten);

            }
        });

        quoteDataService.getQuoteAuthor(new QuoteDataService.VolleyResponseListener() {

            @Override
            public void onError(String message) {
                Toast.makeText(getActivity(), "FFFFUCK", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String konten){
                tvAuth.setText(konten);

            }
        });
    }



}