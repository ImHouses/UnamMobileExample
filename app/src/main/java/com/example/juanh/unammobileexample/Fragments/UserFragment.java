package com.example.juanh.unammobileexample.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.example.juanh.unammobileexample.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {

    private SwitchCompat aSwitch;
    private EditText editUsuario;
    private SharedPreferences sharedPreferences;
    private TextView textViewUsuario;
    private Button button;

    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user, container, false);
        sharedPreferences = getActivity().getSharedPreferences("users", Context.MODE_PRIVATE);
        textViewUsuario = (TextView) v.findViewById(R.id.user_text);
        editUsuario = (EditText) v.findViewById(R.id.et_user);
        aSwitch = (SwitchCompat) v.findViewById(R.id.switchCompat);
        button = (Button) v.findViewById(R.id.button_user);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = editUsuario.getText().toString();
                textViewUsuario.setText(user);
                if (aSwitch.isChecked()) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("user", user);
                    editor.apply();
                }
                button.setVisibility(View.INVISIBLE);
                editUsuario.setVisibility(View.INVISIBLE);
            }
        });
        if (!sharedPreferences.contains("user")) {
            editUsuario.setVisibility(View.VISIBLE);
            aSwitch.setVisibility(View.VISIBLE);
            button.setVisibility(View.VISIBLE);
        } else {
            textViewUsuario.setText(sharedPreferences.getString("user",null));
        }
        return v;
    }
}
