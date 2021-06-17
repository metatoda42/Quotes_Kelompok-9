package com.example.tugasakhir;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class QuoteDataService {
    Context context;

    public QuoteDataService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(String response1, String response2);
    }
    //Fungsi untuk mendapatkan isi quote berdasarkan ID
    public void getQuoteContent(String quoteid, VolleyResponseListener volleyResponseListener){
        String url ="https://api.quotable.io/quotes/"+quoteid;

// Request a string response from the provided URL.
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    String konten;
                    String author;
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONObject quoteContent = response.getJSONObject(null);
                            konten = quoteContent.getString("content");
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, (CharSequence) e, Toast.LENGTH_SHORT).show();
                        }
                        volleyResponseListener.onResponse(konten,author);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "FUUUUCK", Toast.LENGTH_SHORT).show();
                volleyResponseListener.onError("FUCK");
            }
        });

// Add the request to the RequestQueue.
        Simpleton.getInstance(context).addToRequestQueue(request);
    }
}
