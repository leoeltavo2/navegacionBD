package com.example.bdnavigation.ui.ver.producto;

import java.io.Serializable;

public class ListaProducto implements Serializable {
    String id;
    String producto;
    String tipo;
    String proveedor;
    String color;
    String precio;

    public ListaProducto(String id, String producto, String tipo, String proveedor, String color, String precio) {
        this.id = id;
        this.producto = producto;
        this.tipo = tipo;
        this.proveedor = proveedor;
        this.color = color;
        this.precio = precio;
    }
    public ListaProducto(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
