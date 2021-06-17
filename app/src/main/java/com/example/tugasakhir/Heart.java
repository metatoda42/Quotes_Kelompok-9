package com.example.tugasakhir;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class Heart extends Fragment {
    //Disini hubungkan dengan API
    //dan database
    //intinya ini proses yang mengurus halaman home
    //semua kodenya intinya

    EditText editText;
    Button btSave,btReset;
    RecyclerView recyclerView;
    List<MainData> dataList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    DBKomen database;

    AdapterHeart adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        editText = view.findViewById(R.id.et_komQuote);
        btSave = view.findViewById(R.id.btnSave);
        btReset = view.findViewById(R.id.btnReset);
        recyclerView = view.findViewById(R.id.recyclerviewid);

        database = DBKomen.getInstance(this.getContext());

        dataList = database.mainDao().getAll();

        linearLayoutManager = new LinearLayoutManager(this.getContext());

        recyclerView.setLayoutManager((linearLayoutManager));

        adapter = new AdapterHeart(this.getContext(),dataList);
        recyclerView.setAdapter(adapter);

        btSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String sText = editText.getText().toString().trim();
                //string qid = api buat ambil quote id
                MainData data = new MainData();

                data.setKomentar(sText);
                //data.setidQuote(qid)

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
                adapter = new AdapterHeart(view.getContext(),dataList);
                recyclerView.setAdapter(adapter);

                adapter.notifyDataSetChanged();
            }
        });
        return view;
    }



}