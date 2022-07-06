package com.example.bdnavigation.ui.ver.persona;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bdnavigation.R;
import com.example.bdnavigation.bd.BDControlador;
import com.example.bdnavigation.ui.ver.producto.ListaProducto;

import java.sql.Statement;
import java.util.ArrayList;

public class ModificarPersonaActivity extends AppCompatActivity {

    private TextView et_modPersona, et_modTelefono, et_modDomicilio, et_modEmail;
    private Spinner modSpinner;
    private ListaPersona listaPersona;
    private ArrayList<String> listaSPPersona;
    private ArrayList<ListaProducto> listaSPProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_persona);


        initViews();

        listaPersona = (ListaPersona) getIntent().getSerializableExtra("persona");
        et_modPersona.setText(listaPersona.getNombre());
        et_modTelefono.setText(listaPersona.getTelefono());
        et_modDomicilio.setText(listaPersona.getDomicilio());
        et_modEmail.setText(listaPersona.getEmail());

        consultarProducto();

        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout
                .simple_spinner_item, listaSPPersona);
        modSpinner.setAdapter(adaptador);

        Button btnModPersona = findViewById(R.id.btnModPersona);

        btnModPersona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listaPersona.nombre = et_modPersona.getText().toString();
                listaPersona.telefono = et_modTelefono.getText().toString();
                listaPersona.domicilio = et_modDomicilio.getText().toString();
                listaPersona.email = et_modEmail.getText().toString();
                listaPersona.producto = modSpinner.getSelectedItem().toString();
                if (!listaPersona.nombre.isEmpty() && !listaPersona.telefono.isEmpty() && !listaPersona.domicilio.isEmpty() && !listaPersona.email.isEmpty()){
                    try {
                        modificarPersona(listaPersona);

                        
                        Toast.makeText(getApplicationContext(), "CONTACTO MODIFICADO CORRECTAMENTE ", Toast.LENGTH_LONG).show();


                    } catch (Exception ex) {
                        Toast.makeText(getApplicationContext(), "Error al modificar el contacto", Toast.LENGTH_LONG).show();
                    }


                }else{
                    Toast.makeText(getApplicationContext(), "AGREGA LOS CAMPOS RESTANTES", Toast.LENGTH_LONG).show();


                }
            }
        });

    }

    private void consultarProducto() {
        BDControlador controlador = new BDControlador(this, getResources().getString(R.string.nombre_BD), null, getResources().getInteger(R.integer.version_BD));
        SQLiteDatabase BD = controlador.getReadableDatabase();

        ListaProducto producto = null;
        listaSPProducto = new ArrayList<ListaProducto>();

        Cursor cursor = BD.rawQuery("SELECT * FROM producto", null);

        while (cursor.moveToNext()) {
            producto = new ListaProducto();
            producto.setProducto(cursor.getString(1));

            listaSPProducto.add(producto);
        }
        obtenerProducto();
    }

    private void obtenerProducto() {
        listaSPPersona = new ArrayList<String>();
        for (int i = 0; i < listaSPProducto.size(); i++) {
            listaSPPersona.add(listaSPProducto.get(i).getProducto());
        }
    }

    private void initViews() {
        et_modPersona = findViewById(R.id.etModNombrePersona);
        et_modDomicilio = findViewById(R.id.etModDomicilio);
        et_modTelefono = findViewById(R.id.etModTelefono);
        et_modEmail = findViewById(R.id.etModEmail);
        modSpinner = findViewById(R.id.ModSpinner);
    }

    public void modificarPersona(ListaPersona persona){
        BDControlador controlador = new BDControlador(this, getResources().getString(R.string.nombre_BD),null,getResources().getInteger(R.integer.version_BD));
        SQLiteDatabase BD = controlador.getWritableDatabase();


       SQLiteStatement statement = BD.compileStatement("UPDATE persona SET nombre_persona=?,telefono=?,domicilio=?,email=?,productox=? WHERE id_persona ="+persona.getId()+"");

       statement.bindString(1,persona.nombre);
       statement.bindString(2,persona.telefono);
       statement.bindString(3,persona.domicilio);
       statement.bindString(4,persona.email);
       statement.bindString(5,persona.producto);
       statement.execute();

        BD.close();

    }



}