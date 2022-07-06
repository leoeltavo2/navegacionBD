package com.example.bdnavigation.ui.ver.producto;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bdnavigation.R;
import com.example.bdnavigation.bd.BDControlador;
import com.example.bdnavigation.ui.ver.producto.AdaptadorProducto;
import com.example.bdnavigation.ui.ver.producto.ModificarProductoActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class AdaptadorProducto extends RecyclerView.Adapter<AdaptadorProducto.ViewHolder> {

    List<ListaProducto> lista_producto;

    public AdaptadorProducto(List<ListaProducto> lista_producto) {
        this.lista_producto = lista_producto;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ver_producto,parent,false);
        return new AdaptadorProducto.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //      BOTONES FLOTANTES
        FloatingActionButton fabModificarProducto = holder.itemView.findViewById(R.id.fabEditarProducto);
        FloatingActionButton fabEliminarProducto = holder.itemView.findViewById(R.id.fabEliminarProducto);

        ListaProducto producto = lista_producto.get(position);
        holder.tv_idProducto.setText(producto.getId());
        holder.tv_nombreProducto.setText(producto.getProducto());
        holder.tv_tipo.setText(producto.getTipo());
        holder.tv_proveedor.setText(producto.getProveedor());
        holder.tv_color.setText(producto.getColor());
        holder.tv_precio.setText(producto.getPrecio());

        //            CLIC MODIFICAR
        fabModificarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), ModificarProductoActivity.class);
                intent.putExtra("producto",producto);
                holder.itemView.getContext().startActivity(intent);
            }
        });

        //            CLIC ELIMINAR
        fabEliminarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext());
                builder.setTitle("ELIMINAR PERSONA").setMessage("¿DESEAS ELIMINAR A: "+ holder.tv_nombreProducto.getText()+"?").setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        builder.setTitle("¿SEGURO QUE DESEAS ELIMINAR A "+holder.tv_nombreProducto.getText()).setMessage("Una vez eliminado ya no se podrá recuperar").setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//            base de datos
                                BDControlador controlador = new BDControlador(holder.itemView.getContext(), holder.itemView.getResources().getString(R.string.nombre_BD),null, holder.itemView.getResources().getInteger(R.integer.version_BD));
                                SQLiteDatabase BD = controlador.getWritableDatabase();

                                BD.execSQL("DELETE FROM producto WHERE id_producto ="+holder.tv_idProducto.getText());
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
        return lista_producto.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_idProducto, tv_nombreProducto, tv_tipo, tv_proveedor, tv_color, tv_precio;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_idProducto = itemView.findViewById(R.id.tvIdProducto);
            tv_nombreProducto = itemView.findViewById(R.id.tvNombreProducto);
            tv_tipo = itemView.findViewById(R.id.tvTipo);
            tv_proveedor = itemView.findViewById(R.id.tvProveedor);
            tv_color = itemView.findViewById(R.id.tvColor);
            tv_precio = itemView.findViewById(R.id.tvPrecio);
        }
    }
}
