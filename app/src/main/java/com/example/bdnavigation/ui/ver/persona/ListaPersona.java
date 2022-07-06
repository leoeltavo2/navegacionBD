package com.example.bdnavigation.ui.ver.persona;


import java.io.Serializable;

public class ListaPersona implements Serializable {
        String id;
        String nombre;
        String telefono;
        String domicilio;
        String email;
        String producto;

//        public ListaPersona(Integer id, String nombre, String telefono, String domicilio, String email, String producto) {
//                this.id = id;
//                this.nombre = nombre;
//                this.telefono = telefono;
//                this.domicilio = domicilio;
//                this.email = email;
//                this.producto = producto;
//        }

        public String getId() {
                return id;
        }

        public String getNombre() {
                return nombre;
        }

        public String getTelefono() {
                return telefono;
        }

        public String getDomicilio() {
                return domicilio;
        }

        public String getEmail() {
                return email;
        }

        public String getProducto() {
                return producto;
        }

        public void setId(String id) {
                this.id = id;
        }

        public void setNombre(String nombre) {
                this.nombre = nombre;
        }

        public void setTelefono(String telefono) {
                this.telefono = telefono;
        }

        public void setDomicilio(String domicilio) {
                this.domicilio = domicilio;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public void setProducto(String producto) {
                this.producto = producto;
        }
}
