package com.example.juanh.unammobileexample.Fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.juanh.unammobileexample.Activities.ActivityMap;
import com.example.juanh.unammobileexample.Activities.MainActivity;
import com.example.juanh.unammobileexample.Adapters.AdaptadorNegocio;
import com.example.juanh.unammobileexample.Adapters.AdaptadorPost;
import com.example.juanh.unammobileexample.Models.Negocio;
import com.example.juanh.unammobileexample.Models.Post;
import com.example.juanh.unammobileexample.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NegociosFragment extends Fragment {

    private ArrayList<Negocio> negocios;

    private HashMap<Integer, Bitmap> bitmapsHash;

    private ProgressDialog progressDialog;

    private ListView lista;

    private View rootView;

    public NegociosFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_negocios, container, false);
        buildUI();
        getDataAsync();
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getContext(), ActivityMap.class);
                i.putExtra("latitud", negocios.get(position).getCoordenadas().getLatitud());
                i.putExtra("longitud", negocios.get(position).getCoordenadas().getLongitud());
                startActivity(i);
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }

    private void buildUI() {
        negocios = new ArrayList<>();
        AdaptadorNegocio adaptador =
                new AdaptadorNegocio(getContext(), R.layout.item_negocio, negocios);
        lista = (ListView) rootView.findViewById(R.id.lista_negocios);
        lista.setAdapter(adaptador);
        lista.invalidateViews();
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
                URL datosFeed = new URL("http://www.mocky.io/v2/58e12f07250000ca06633073");
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
                populateNegocios(json.getJSONArray("business_data"));
            } catch(JSONException je) {
                je.printStackTrace();
            }
            lista.invalidateViews();
            progressDialog.dismiss();
        }
    }


    private void populateNegocios(JSONArray jsonArray) {
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject n = jsonArray.getJSONObject(i);
                String nombre = n.getString("name");
                String imagen = n.getString("image").trim();
                double latitud = n.getDouble("lat");
                double longitud = n.getDouble("lon");
                negocios.add(new Negocio(nombre, imagen, latitud, longitud));
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }

}
