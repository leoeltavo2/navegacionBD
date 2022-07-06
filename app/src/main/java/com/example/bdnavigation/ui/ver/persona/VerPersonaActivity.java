package com.example.bdnavigation.ui.ver.persona;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.bdnavigation.R;
import com.example.bdnavigation.bd.BDControlador;

import java.util.ArrayList;

public class VerPersonaActivity extends AppCompatActivity {

    RecyclerView recyclerVer;
    ArrayList<ListaPersona> listaPersona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_persona);

        recyclerVer = findViewById(R.id.recyclerVer);
        recyclerVer.setLayoutManager(new LinearLayoutManager(this));
        listaPersona = new ArrayList<>();
        AdaptadorPersona adapter = new AdaptadorPersona(mostrarLista());
        recyclerVer.setAdapter(adapter);



    }

    public ArrayList<ListaPersona> mostrarLista(){
        BDControlador controlador = new BDControlador(this, getResources().getString(R.string.nombre_BD),null,getResources().getInteger(R.integer.version_BD));
        SQLiteDatabase BD = controlador.getReadableDatabase();

        Cursor cursor = BD.rawQuery("SELECT * FROM persona", null);
//        Cursor cursor = BD.rawQuery("SELECT id_persona,nombre_persona,telefono,domicilio,email,producto FROM persona", null);

        ArrayList<ListaPersona> persona = new ArrayList<>();
        ListaPersona lista = null;

        if( cursor.moveToFirst() ){
            do{ //lectura de datos de un registro
                lista = new ListaPersona();
                lista.setId(cursor.getString(0));
                lista.setNombre(cursor.getString(1));
                lista.setTelefono(cursor.getString(2));
                lista.setDomicilio(cursor.getString(3));
                lista.setEmail(cursor.getString(4));
                lista.setProducto(cursor.getString(5));
                persona.add(lista);

            }while(cursor.moveToNext());
//            Toast.makeText(getContext(),lista.getEmail().toString()+"" , Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "No hay registros", Toast.LENGTH_SHORT).show();
        }
        BD.close();

        return persona;
    }

}