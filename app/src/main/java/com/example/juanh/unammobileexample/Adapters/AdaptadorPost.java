package com.example.juanh.unammobileexample.Adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.juanh.unammobileexample.Models.Post;
import com.example.juanh.unammobileexample.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by juanh on 08/04/2017.
 */

public class AdaptadorPost extends BaseAdapter {

    private Context context;
    private ArrayList<Post> posts;


    public AdaptadorPost(Context context, ArrayList<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int position) {
        return posts.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_post, parent, false);
            holder.titulo = (TextView) convertView.findViewById(R.id.post_titulo);
            holder.usuario = (TextView) convertView.findViewById(R.id.post_user);
            holder.texto = (TextView) convertView.findViewById(R.id.post_texto);
            holder.imagen = (ImageView) convertView.findViewById(R.id.post_imagen);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Post p = posts.get(position);
        holder.titulo.setText(p.getTitulo());
        holder.usuario.setText(p.getUserpost());
        holder.texto.setText(p.getTexto());
        if (!TextUtils.isEmpty(p.getImagen())) {
            Picasso.with(context)
                .load(p.getImagen())
                .into(holder.imagen);
        } else {
            holder.imagen.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }

    private static class ViewHolder {
        ImageView imagen;
        TextView usuario;
        TextView titulo;
        TextView texto;
    }




}
