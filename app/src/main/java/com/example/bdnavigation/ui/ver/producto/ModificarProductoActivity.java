package com.example.bdnavigation.ui.ver.producto;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bdnavigation.R;
import com.example.bdnavigation.bd.BDControlador;

public class ModificarProductoActivity extends AppCompatActivity {

    private TextView et_modProducto, et_modTipo, et_modProveedor, et_modColor, et_modPrecio;
    private ListaProducto listaProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_producto);

        initViews();

        listaProducto = (ListaProducto) getIntent().getSerializableExtra("producto");
        et_modProducto.setText(listaProducto.getProducto());
        et_modTipo.setText(listaProducto.getTipo());
        et_modProveedor.setText(listaProducto.getProveedor());
        et_modColor.setText(listaProducto.getColor());
        et_modPrecio.setText(listaProducto.getPrecio());

        Button btnModProducto = findViewById(R.id.btnModificarProducto);

        btnModProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listaProducto.producto = et_modProducto.getText().toString();
                listaProducto.tipo = et_modTipo.getText().toString();
                listaProducto.proveedor = et_modProveedor.getText().toString();
                listaProducto.color = et_modColor.getText().toString();
                listaProducto.precio = et_modPrecio.getText().toString();
                if (!listaProducto.producto.isEmpty() && !listaProducto.tipo.isEmpty() && !listaProducto.proveedor.isEmpty() && !listaProducto.color.isEmpty() && !listaProducto.precio.isEmpty()){
                    try {
                        modificarProducto(listaProducto);
                        Toast.makeText(getApplicationContext(), "PRODUCTO MODIFICDO CORRECTAMENTE", Toast.LENGTH_LONG).show();
//                        startActivity(new Intent(getApplicationContext(),VerProductoActivity.class));
//                        finish();
                    } catch (Exception ex) {
                        Toast.makeText(getApplicationContext(), "Error al modificar el producto", Toast.LENGTH_LONG).show();
                    }


                }else{
                    Toast.makeText(getApplicationContext(), "AGREGA LOS CAMPOS RESTANTES", Toast.LENGTH_LONG).show();


                }
            }
        });
    }

    private void initViews() {
        et_modProducto = findViewById(R.id.etModNombreProducto);
        et_modTipo = findViewById(R.id.etModTipo);
        et_modProveedor = findViewById(R.id.etModProveedor);
        et_modColor = findViewById(R.id.etModColor);
        et_modPrecio = findViewById(R.id.etModPrecio);
    }

    public void modificarProducto(ListaProducto producto){
        BDControlador controlador = new BDControlador(this, getResources().getString(R.string.nombre_BD),null,getResources().getInteger(R.integer.version_BD));
        SQLiteDatabase BD = controlador.getWritableDatabase();


        SQLiteStatement statement = BD.compileStatement("UPDATE producto SET nombre_producto=?,tipo=?,proveedor=?,color=?,precio=? WHERE id_producto ="+producto.getId()+"");

        statement.bindString(1,producto.producto);
        statement.bindString(2,producto.tipo);
        statement.bindString(3,producto.proveedor);
        statement.bindString(4,producto.color);
        statement.bindString(5, producto.precio);
        statement.execute();

        BD.close();

    }


}