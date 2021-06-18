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

    public QuoteDataService(Context ctx) {
        this.context = ctx;
    }

    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(String konten);
    }
    //Fungsi untuk mendapatkan random quote
    public void getQuoteContent(final VolleyResponseListener volleyResponseListener){
        String url ="https://api.quotable.io/random/";

        // Request a string response from the provided URL.
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    String konten;
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONObject quoteContent = response;
                            konten = quoteContent.getString("content");
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                        }
                        volleyResponseListener.onResponse(konten);
                    }
                }, error -> {
                    Toast.makeText(context, "FUUUUCK", Toast.LENGTH_SHORT).show();
                    volleyResponseListener.onError("FUCK");
                });

// Add the request to the RequestQueue.
        Simpleton.getInstance(context).addToRequestQueue(request);
    }



    public void getQuoteAuthor(VolleyResponseListener volleyResponseListener){
        String url ="https://api.quotable.io/random/";
        // Request a string response from the provided URL.
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    String author;
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONObject quoteContent = response;
                            author = quoteContent.getString("author");
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                        }
                        volleyResponseListener.onResponse(author);
                    }
                }, error -> {
                    Toast.makeText(context, "FUUUUCK", Toast.LENGTH_SHORT).show();
                    volleyResponseListener.onError("FUCK");
                });

// Add the request to the RequestQueue.
        Simpleton.getInstance(context).addToRequestQueue(request);
    }
}
