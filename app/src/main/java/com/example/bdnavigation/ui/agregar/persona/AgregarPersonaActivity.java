package com.example.bdnavigation.ui.agregar.persona;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.ContentValues;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bdnavigation.R;
import com.example.bdnavigation.bd.BDControlador;
import com.example.bdnavigation.ui.ver.persona.ListaPersona;
import com.example.bdnavigation.ui.ver.producto.ListaProducto;

import java.util.ArrayList;

public class AgregarPersonaActivity extends AppCompatActivity {

    private EditText et_nombrePersona, et_telefono, et_domicilio, et_email ;
    Spinner spPersona;
    private ArrayList<String> listaSPPersona;
    private ArrayList<ListaProducto> listaSPProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_persona);

        et_nombrePersona = findViewById(R.id.etNombrePersona);
        et_telefono = findViewById(R.id.etTelefono);
        et_domicilio = findViewById(R.id.etDomicilio);
        et_email = findViewById(R.id.etEmail);
        spPersona = findViewById(R.id.spinner);

        consultarProducto();

        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout
        .simple_spinner_item,listaSPPersona);
        spPersona.setAdapter(adaptador);

        Button btn_añadirPersona = findViewById(R.id.btnAddPersona);
        btn_añadirPersona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistrarPersona(et_nombrePersona.getText().toString(),et_telefono.getText().toString(),et_domicilio.getText().toString(),et_email.getText().toString(), spPersona.getSelectedItem().toString());
            }
        });
    }

    private void consultarProducto() {
        BDControlador controlador = new BDControlador(this, getResources().getString(R.string.nombre_BD),null,getResources().getInteger(R.integer.version_BD));
        SQLiteDatabase BD = controlador.getReadableDatabase();

        ListaProducto producto = null;
        listaSPProducto = new ArrayList<ListaProducto>();

        Cursor cursor = BD.rawQuery("SELECT * FROM producto", null);

        while(cursor.moveToNext()){
            producto = new ListaProducto();
            producto.setProducto(cursor.getString(1));

            listaSPProducto.add(producto);
        }
        obtenerProducto();
    }

    private void obtenerProducto() {
        listaSPPersona = new ArrayList<String>();
//        listaSPPersona.add("seleccione");
        for (int i=0;i<listaSPProducto.size();i++){
            listaSPPersona.add(listaSPProducto.get(i).getProducto());
        }
    }

    public void RegistrarPersona(String persona, String telefono, String domicilio, String email, String spinnerProducto){
        BDControlador controlador = new BDControlador(this, getResources().getString(R.string.nombre_BD),null,getResources().getInteger(R.integer.version_BD));
        SQLiteDatabase BD = controlador.getWritableDatabase();

         persona = et_nombrePersona.getText().toString();
         telefono = et_telefono.getText().toString();
         domicilio = et_domicilio.getText().toString();
         email = et_email.getText().toString();
         spinnerProducto = spPersona.getSelectedItem().toString();

        if (!persona.isEmpty() && !telefono.isEmpty() && !domicilio.isEmpty() && !email.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("nombre_persona", persona);
            registro.put("telefono", telefono);
            registro.put("domicilio", domicilio);
            registro.put("email", email);
            registro.put("productox", spinnerProducto);

            BD.insert("persona",null, registro);
            BD.close();
            et_nombrePersona.setText("");
            et_telefono.setText("");
            et_domicilio.setText("");
            et_email.setText("");

            Toast.makeText(this,"DATOS GUARDADOS CON EXITO!!", Toast.LENGTH_LONG).show();
        }
        else Toast.makeText(this,"POR FAVOR LLENA TODOS LOS CAMPOS",Toast.LENGTH_LONG).show();

    }


}