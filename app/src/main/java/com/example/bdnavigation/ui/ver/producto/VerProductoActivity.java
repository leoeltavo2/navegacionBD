package com.example.bdnavigation.ui.ver.producto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import com.example.bdnavigation.R;
import com.example.bdnavigation.bd.BDControlador;
import com.example.bdnavigation.ui.ver.producto.AdaptadorProducto;
import com.example.bdnavigation.ui.ver.producto.ListaProducto;

import java.util.ArrayList;

public class VerProductoActivity extends AppCompatActivity {

    RecyclerView recyclerProducto;
    ArrayList<ListaProducto> listaProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_producto);

        recyclerProducto = findViewById(R.id.recyclerProducto);
        recyclerProducto.setLayoutManager(new LinearLayoutManager(this));
        listaProducto = new ArrayList<>();
        AdaptadorProducto adapter = new AdaptadorProducto(mostrarListaProducto());
        recyclerProducto.setAdapter(adapter);
    }

    public ArrayList<ListaProducto> mostrarListaProducto(){
        BDControlador controlador = new BDControlador(this, getResources().getString(R.string.nombre_BD),null,getResources().getInteger(R.integer.version_BD));
        SQLiteDatabase BD = controlador.getReadableDatabase();

        Cursor cursor = BD.rawQuery("SELECT * FROM producto", null);

        ArrayList<ListaProducto> producto = new ArrayList<>();
        ListaProducto lista = null;

        if( cursor.moveToFirst() ){
            do{ //lectura de datos de un registro
                lista = new ListaProducto();
                lista.setId(cursor.getString(0));
                lista.setProducto(cursor.getString(1));
                lista.setTipo(cursor.getString(2));
                lista.setProveedor(cursor.getString(3));
                lista.setColor(cursor.getString(4));
                lista.setPrecio(cursor.getString(5));
                producto.add(lista);

            }while(cursor.moveToNext());
//            Toast.makeText(getContext(),lista.getEmail().toString()+"" , Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "No hay registros", Toast.LENGTH_SHORT).show();
        }
        BD.close();

        return producto;
    }
}