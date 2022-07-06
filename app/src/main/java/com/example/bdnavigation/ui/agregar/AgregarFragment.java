package com.example.bdnavigation.ui.agregar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.bdnavigation.R;
import com.example.bdnavigation.ui.agregar.persona.AgregarPersonaActivity;
import com.example.bdnavigation.ui.agregar.producto.AgregarProductoActivity;

public class AgregarFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_agregar, container, false);

        Button btnAgregarPersona = root.findViewById(R.id.btn_agregarPersona);
        Button btnAgregarProducto = root.findViewById(R.id.btn_agregarProducto);


        btnAgregarPersona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AgregarPersonaActivity.class);
                startActivity(intent);
            }
        });

        btnAgregarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AgregarProductoActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }
}