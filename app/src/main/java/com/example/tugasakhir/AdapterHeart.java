package com.example.tugasakhir;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//Ini Adapter Chan, dia adalah perantara 85% kode dalam aplikasi ini.
// Tolong jangan diganggu.

public class AdapterHeart extends RecyclerView.Adapter<AdapterHeart.ViewHolder> {
    //Deklarasi class yang akan diurus oleh AdapterHome dan direpresentasikan sebagai variabel
    private List<MainData> dataList;
    private Context context;
    private DBKomen database;

    //Ini semacam perintah buat Adapter chan untuk selalu mengecek ulang dataset apabila terjadi
    //perubahan dalam database DBKomen

    public AdapterHeart(Context context, List<MainData> dataList){
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
                .inflate(R.layout.rv_row,parent,false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        MainData data = dataList.get(position);

        database = DBKomen.getInstance(context);

        holder.textView.setText(data.getIdQuote());

        holder.btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainData d = dataList.get(holder.getAdapterPosition());

                int sID= d.getID();
                String qText = d.getIdQuote();

                String sText = d.getKomentar();

                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_update);

                int width = WindowManager.LayoutParams.MATCH_PARENT;

                int height = WindowManager.LayoutParams. WRAP_CONTENT;

                dialog.getWindow().setLayout(width,height);

                dialog.show();

                EditText editText = dialog.findViewById(R.id.edit_text);
                Button btUpdate = dialog.findViewById(R.id.bt_update);

                editText.setText(sText);

                btUpdate.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){

                        dialog.dismiss();

                        String uText = editText.getText().toString().trim();

                        database.mainDao().update(sID,uText,qText);

                        dataList.clear();
                        dataList.addAll(database.mainDao().getAll());
                        notifyDataSetChanged();

                    }
                });
            }
        });

        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                MainData d = dataList.get(holder.getAdapterPosition());

                database.mainDao().delete(d);

                int position = holder.getAdapterPosition();
                dataList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,dataList.size());



            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView btEdit, btDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
