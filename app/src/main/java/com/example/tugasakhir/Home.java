package com.example.tugasakhir;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

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
    Context context;
    AdapterHeart adapter;
    QuoteDataService quoteDataService = new QuoteDataService(context);
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        tvQuote = view.findViewById(R.id.tv_randQuote);
        tvAuth = view.findViewById(R.id.tv_authQuote);
        btSave = view.findViewById(R.id.btnSave);
        btReset = view.findViewById(R.id.btnReset);
        //=====================================================================
        //TOMBOL RESET DIPENCET, HASILNYA INI
       btReset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                quoteDataService.getQuoteContent(new QuoteDataService.VolleyResponseListener() {

                    @Override
                    public void onError(String message) {
                        Toast.makeText(context, "FFFFUCK", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String konten){
                        tvQuote.setText(konten);
                        Toast.makeText(context,konten,Toast.LENGTH_LONG).show();

                    }
                });

                quoteDataService.getQuoteAuthor(new QuoteDataService.VolleyResponseListener() {

                    @Override
                    public void onError(String message) {
                        Toast.makeText(context, "FFFFUCK", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String konten){
                        tvAuth.setText(konten);
                        Toast.makeText(context,konten,Toast.LENGTH_LONG).show();

                    }
                });
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



}