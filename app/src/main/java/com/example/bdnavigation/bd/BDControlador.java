package com.example.bdnavigation.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDControlador extends SQLiteOpenHelper {

    public BDControlador(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
//        venta
        db.execSQL("CREATE TABLE producto(id_producto INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,nombre_producto TEXT NOT NULL, tipo TEXT NOT NULL, proveedor TEXT NOT NULL, color TEXT NOT NULL, precio REAL NOT NULL)");
        db.execSQL("CREATE TABLE persona(id_persona INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nombre_persona TEXT NOT NULL, telefono TEXT NOT NULL, domicilio TEXT NOT NULL, email TEXT NOT NULL, productox Integer, FOREIGN KEY(productox) REFERENCES producto(id_producto) )");

        db.execSQL("INSERT INTO producto VALUES(NULL,'martillo','herramienta','foxes','rojo','32')");
//        db.execSQL("INSERT INTO persona VALUES(NULL,'martin','3434','foxes','martin@gmail.com','martillo')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE persona");
        db.execSQL("DROP TABLE producto");
        onCreate(db);
    }


}
