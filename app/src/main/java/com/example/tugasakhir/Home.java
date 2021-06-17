package com.example.tugasakhir;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
    Button btSave,btReset;
    RecyclerView recyclerView;
    List<MainData> dataList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    DBKomen database;

    AdapterHome adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        editText = view.findViewById(R.id.et_komQuote);
        quoteid =
        btSave = view.findViewById(R.id.btnSave);
        btReset = view.findViewById(R.id.btnReset);
        tvQuote = view.findViewById(R.id.tv_randQuote);
        tvAuth = view.findViewById(R.id.tv_authQuote);
        database = DBKomen.getInstance(this.getContext());

        dataList = database.mainDao().getAll();

        linearLayoutManager = new LinearLayoutManager(this.getContext());

        recyclerView.setLayoutManager((linearLayoutManager));

        adapter = new AdapterHome(this.getContext(),dataList);
        recyclerView.setAdapter(adapter);


//        MainData d = dataList.get(holder.getAdapterPosition());
//        //REST API DSB DISINI AJA DULU
        btSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String sText = editText.getText().toString().trim();
                String qText = QuoteDataService.getQuoteContent
                //string qid = (api buat ambil quote id)
                    MainData data = new MainData();

                    data.setKomentar(sText);
                    data.setIdQuote(qText);







                    database.mainDao().insert(data);

                    editText.setText("");

                    dataList.clear();
                    dataList.addAll(database.mainDao().getAll());
                    adapter.notifyDataSetChanged();


            }
        });
        btReset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                adapter = new AdapterHome(view.getContext(),dataList);
                recyclerView.setAdapter(adapter);

                adapter.notifyDataSetChanged();
            }
        });
        return view;
    }



}