package com.example.bdnavigation.ui.agregar.producto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bdnavigation.R;
import com.example.bdnavigation.bd.BDControlador;

public class AgregarProductoActivity extends AppCompatActivity {

    private EditText et_nombreProducto, et_tipo, et_proveedor , et_color, et_precio ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto);

        et_nombreProducto = findViewById(R.id.etNombreProducto);
        et_tipo = findViewById(R.id.etTipo);
        et_proveedor = findViewById(R.id.etProveedor);
        et_color = findViewById(R.id.etColor);
        et_precio = findViewById(R.id.etPrecio);


        Button btn_añadirProducto = findViewById(R.id.btnAddProducto);
        btn_añadirProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistrarProducto(et_nombreProducto.getText().toString(),et_tipo.getText().toString(),et_proveedor.getText().toString(),et_color.getText().toString(),et_tipo.getText().toString());
            }
        });
    }

    public void RegistrarProducto(String producto, String tipo, String proveedor, String color, String precio){
        BDControlador controlador = new BDControlador(this, getResources().getString(R.string.nombre_BD),null,getResources().getInteger(R.integer.version_BD));
        SQLiteDatabase BD = controlador.getWritableDatabase();

        producto = et_nombreProducto.getText().toString();
        tipo = et_tipo.getText().toString();
        proveedor = et_proveedor.getText().toString();
        color = et_color.getText().toString();
        precio = et_precio.getText().toString();

        if (!producto.isEmpty() && !tipo.isEmpty() && !proveedor.isEmpty() && !color.isEmpty() && !precio.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("nombre_producto", producto);
            registro.put("tipo", tipo);
            registro.put("proveedor", proveedor);
            registro.put("color", color);
            registro.put("precio", precio);

            BD.insert("producto",null, registro);
            BD.close();
            et_nombreProducto.setText("");
            et_tipo.setText("");
            et_proveedor.setText("");
            et_color.setText("");
            et_precio.setText("");

            Toast.makeText(this,"DATOS GUARDADOS CON EXITO!!", Toast.LENGTH_LONG).show();
        }
        else Toast.makeText(this,"POR FAVOR LLENA TODOS LOS CAMPOS",Toast.LENGTH_LONG).show();

    }
}