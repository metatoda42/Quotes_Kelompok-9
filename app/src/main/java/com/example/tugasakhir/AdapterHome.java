package com.example.tugasakhir;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;
import java.util.List;

//Ini Adapter Chan, dia adalah perantara 85% kode dalam aplikasi ini.
// Tolong jangan diganggu.

public class AdapterHome extends RecyclerView.Adapter<AdapterHome.ViewHolder> {
    //Deklarasi class yang akan diurus oleh AdapterHome dan direpresentasikan sebagai variabel
    private List<MainData> dataList;
    private Context context;
    private DBKomen database;

    //Ini semacam perintah buat Adapter chan untuk selalu mengecek ulang dataset apabila terjadi
    //perubahan dalam database DBKomen

    public AdapterHome(Context context, List<MainData> dataList){
        this.context = context;
        this.dataList = dataList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override

    //Ini adalah perintah untuk Adapter Chan untuk memunculkan rv_row di dalam fragment_list
    //setelah selesai mengecek ulang data set dalam DBKomen
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_home,parent,false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        MainData data = dataList.get(position);

        database = DBKomen.getInstance(context);

        holder.et_komQuote.setText(data.getKomentar());
        holder.tv_authQuote.setText(data.getIdQuote());



        holder.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainData d = dataList.get(holder.getAdapterPosition());

                int sID= d.getID();
                String qText = d.getIdQuote();
                EditText editText = v.findViewById(R.id.et_komQuote);


                String uText = editText.getText().toString().trim();

                database.mainDao().update(sID,uText,qText);

                dataList.clear();
                dataList.addAll(database.mainDao().getAll());
                notifyDataSetChanged();
            }
        });

        holder.btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                notifyDataSetChanged();



            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton btnReset, btnSave;
        EditText et_komQuote;
        TextView tv_randQuote, tv_authQuote;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            et_komQuote = itemView.findViewById(R.id.et_komQuote);
            tv_randQuote = itemView.findViewById(R.id.tv_randQuote);
            tv_authQuote = itemView.findViewById(R.id.tv_authQuote);
            btnReset = itemView.findViewById(R.id.btnReset);
            btnSave = itemView.findViewById(R.id.btnSave);

        }
    }
}
