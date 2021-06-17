package com.example.tugasakhir;

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

import java.util.ArrayList;
import java.util.List;

public class Heart extends Fragment {
    EditText editText;
    Button btAdd;
    RecyclerView recyclerView;
    List<MainData> dataList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    DBKomen database;
    Context context;
    AdapterHeart adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        editText = view.findViewById(R.id.edit_text);
        btAdd = view.findViewById(R.id.bt_add);
    //    btReset = findViewById(R.id.bt_reset);
        recyclerView = view.findViewById(R.id.recyclerviewid);
        database = DBKomen.getInstance(this.getContext());

        dataList = database.mainDao().getAll();

        linearLayoutManager = new LinearLayoutManager(context);

        adapter = new AdapterHeart(getContext(),dataList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager((linearLayoutManager));


        btAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
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