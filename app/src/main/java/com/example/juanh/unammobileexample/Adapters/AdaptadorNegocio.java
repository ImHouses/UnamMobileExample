package com.example.juanh.unammobileexample.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.juanh.unammobileexample.Models.Negocio;
import com.example.juanh.unammobileexample.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by juanh on 08/04/2017.
 */

public class AdaptadorNegocio extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Negocio> negocios;
    private Bitmap bm;

    public AdaptadorNegocio(Context context, int layout, ArrayList<Negocio> negocios) {
        this.context = context;
        this.layout = layout;
        this.negocios = negocios;
    }

    @Override
    public int getCount() {
        return negocios.size();
    }

    @Override
    public Object getItem(int position) {
        return negocios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater;
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_negocio, parent, false);
            holder.imagen = (ImageView) convertView.findViewById(R.id.negocio_imagen);
            holder.nombre = (TextView) convertView.findViewById(R.id.negocio_nombre);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Negocio n = negocios.get(position);
        holder.nombre.setText(n.getNombre());
        Picasso.with(context)
                .load(n.getImagen())
                .into(holder.imagen);

        return convertView;
    }

    private static class ViewHolder {
        ImageView imagen;
        TextView nombre;
    }


}
