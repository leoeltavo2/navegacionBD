package com.example.bdnavigation.ui.ver.persona;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bdnavigation.MainActivity;
import com.example.bdnavigation.R;
import com.example.bdnavigation.bd.BDControlador;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class AdaptadorPersona extends RecyclerView.Adapter<AdaptadorPersona.ViewHolder> {//implements View.OnClickListener {

    List<ListaPersona> lista_persona;
//    private View.OnClickListener listener;
Context context;

    public AdaptadorPersona(List<ListaPersona> lista_persona) {
        this.lista_persona = lista_persona;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ver_persona,parent,false);
//       view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//      BOTONES FLOTANTES
        FloatingActionButton fabModificarPersona = holder.itemView.findViewById(R.id.fabEditarPersona);
        FloatingActionButton fabEliminarPersona = holder.itemView.findViewById(R.id.fabEliminarPersona);

        ListaPersona persona = lista_persona.get(position);
        holder.tv_idPersona.setText(persona.getId());
        holder.tv_nombrePersona.setText(persona.nombre);
        holder.tv_telefono.setText(persona.telefono);
        holder.tv_domicilio.setText(persona.domicilio);
        holder.tv_email.setText(persona.email);
        holder.tv_producto.setText(persona.producto);

        //            CLIC MODIFICAR
        fabModificarPersona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), ModificarPersonaActivity.class);
                intent.putExtra("persona",persona);
                holder.itemView.getContext().startActivity(intent);
            }
        });

    //            CLIC ELIMINAR
            fabEliminarPersona.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

           AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext());
           builder.setTitle("ELIMINAR PERSONA").setMessage("¿DESEAS ELIMINAR A: "+ holder.tv_nombrePersona.getText()+"?").setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int which) {
                   builder.setTitle("¿SEGURO QUE DESEAS ELIMINAR A "+holder.tv_nombrePersona.getText()).setMessage("Una vez eliminado ya no se podrá recuperar").setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
//            base de datos
                   BDControlador controlador = new BDControlador(holder.itemView.getContext(), holder.itemView.getResources().getString(R.string.nombre_BD),null, holder.itemView.getResources().getInteger(R.integer.version_BD));
                   SQLiteDatabase BD = controlador.getWritableDatabase();

                   BD.execSQL("DELETE FROM persona WHERE id_persona ="+holder.tv_idPersona.getText());
                   BD.close();


                       }
                   }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {

                       }
                   });
                   builder.show();

               }
           }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int which) {

               }
           });
            builder.show();

        }
    });

    }


    @Override
    public int getItemCount() {
        return lista_persona.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_idPersona, tv_nombrePersona, tv_telefono, tv_domicilio,tv_email, tv_producto;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_idPersona = itemView.findViewById(R.id.tvIdPersona);
            tv_nombrePersona = itemView.findViewById(R.id.tvNombrePersona);
            tv_telefono = itemView.findViewById(R.id.tvTelefono);
            tv_domicilio = itemView.findViewById(R.id.tvDomicilio);
            tv_email = itemView.findViewById(R.id.tvEmail);
            tv_producto = itemView.findViewById(R.id.tvProducto);

        }
    }
}
