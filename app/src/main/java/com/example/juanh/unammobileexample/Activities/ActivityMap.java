package com.example.juanh.unammobileexample.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.juanh.unammobileexample.Models.Coordenadas;
import com.example.juanh.unammobileexample.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ActivityMap extends AppCompatActivity
        implements OnMapReadyCallback{

    private double longitud;
    private double latitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        longitud = getIntent().getExtras().getDouble("longitud");
        latitud = getIntent().getExtras().getDouble("latitud");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng c = new LatLng(latitud, longitud);
        googleMap.addMarker(new MarkerOptions().position(c)
                .title("Marcador"));
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(c,17);
        googleMap.animateCamera(cameraUpdate);
    }
}
