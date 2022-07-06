package com.example.bdnavigation.ui.ver;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bdnavigation.R;
import com.example.bdnavigation.bd.BDControlador;
import com.example.bdnavigation.ui.agregar.persona.AgregarPersonaActivity;
import com.example.bdnavigation.ui.ver.persona.AdaptadorPersona;
import com.example.bdnavigation.ui.ver.persona.ListaPersona;
import com.example.bdnavigation.ui.ver.persona.VerPersonaActivity;
import com.example.bdnavigation.ui.ver.producto.VerProductoActivity;

import java.util.ArrayList;

public class VerFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_ver, container, false);


        Button btn_verPersona = root.findViewById(R.id.btn_verPersona);
        Button btn_verProducto = root.findViewById(R.id.btn_verProducto);

        btn_verPersona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), VerPersonaActivity.class);
                startActivity(intent);
            }
        });

        btn_verProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), VerProductoActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }




}