package com.example.juanh.unammobileexample.Adapters;

import android.content.Context;
import android.media.Image;
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
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.item_post, null);
        Post p = posts.get(position);
        ((TextView) v.findViewById(R.id.post_titulo)).setText(p.getTitulo());
        ((TextView) v.findViewById(R.id.post_texto)).setText(p.getTexto());
        ((TextView) v.findViewById(R.id.post_user)).setText(p.getUserpost());
        ImageView i = (ImageView) v.findViewById(R.id.post_imagen);
        i.setVisibility(View.INVISIBLE);
        if (!TextUtils.isEmpty(p.getImagen())) {
            i.setVisibility(View.VISIBLE);
            Picasso.with(context)
                    .load(p.getImagen())
                    .into(i);
        }
        return v;
    }

}
