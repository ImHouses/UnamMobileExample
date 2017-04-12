package com.example.juanh.unammobileexample.Fragments;


import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.juanh.unammobileexample.Adapters.AdaptadorPost;
import com.example.juanh.unammobileexample.Models.Post;
import com.example.juanh.unammobileexample.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends Fragment {

    ProgressDialog progressDialog;
    ArrayList<Post> posts;
    LinkedList<Bitmap> bitmaps;

    public FeedFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_feed, container, false);
        // Inflate the layout for this fragment
        buildFeed();
        return vista;
    }

    private void buildFeed() {
        posts = new ArrayList<>();
        getDataAsync();
    }

    private void getDataAsync() {
        new DataAsyncTask().execute();
    }

    private class DataAsyncTask extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setView(getView());
            progressDialog.setMessage("Cargando...");
            progressDialog.show();
        }

        protected String  doInBackground(String... strings) {
            String j = "";
            try {
                URL datosFeed = new URL("http://www.mocky.io/v2/58e12be02500006c06633072");
                BufferedReader br = new BufferedReader(new InputStreamReader(datosFeed.openStream()));
                String s = "";
                while ((s = br.readLine()) != null) {
                    j += s;
                }
                br.close();
            } catch(MalformedURLException mue) {
                mue.printStackTrace();
            } catch(IOException ioe) {
                ioe.printStackTrace();
            }
            return j;
        }

        protected void onPostExecute(String result) {
            try {
                JSONObject json = new JSONObject(result);
                populatePosts(json.getJSONArray("timeline"));
            } catch(JSONException je) {
                je.printStackTrace();
            }
            AdaptadorPost adaptador = new AdaptadorPost(getContext(), posts);
            ListView lista = (ListView) getView().findViewById(R.id.lista_feed);
            lista.setAdapter(adaptador);
            progressDialog.dismiss();
        }
    }
    private void populatePosts(JSONArray jsonArray) {
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject p = jsonArray.getJSONObject(i);
                String postuser = p.getString("user");
                String titulo = p.getString("content_title").trim();
                String contenido = p.getString("content_text").trim();
                String imagen = p.getString("image").trim();
                posts.add(new Post(postuser, titulo, contenido, imagen));
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }

}
