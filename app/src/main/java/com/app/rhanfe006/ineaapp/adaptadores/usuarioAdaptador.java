package com.app.rhanfe006.ineaapp.adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.rhanfe006.ineaapp.usuarios;
import com.app.rhanfe006.ineaapp.R;
import com.app.rhanfe006.ineaapp.usuarios;

import java.util.List;

/**
 * Created by rhanfe006 on 28/04/2015.
 */
public class usuarioAdaptador extends RecyclerView.Adapter<usuarioAdaptador.usuarioViewHolder> {

    private List<usuarios> items;

    public static class usuarioViewHolder extends RecyclerView.ViewHolder {

        public TextView id_persona;
        public TextView nombre;
        public TextView apellidos;
        public TextView tipo;
        public TextView nivel;
        public TextView modulo;

        public TextView fecha;

        public usuarioViewHolder(View v) {
            super(v);
            id_persona = (TextView) v.findViewById(R.id.id_persona);
            nombre= (TextView) v.findViewById(R.id.nommbre_c);
            apellidos = (TextView) v.findViewById(R.id.apellidos_c);
            tipo = (TextView) v.findViewById(R.id.tipo_c);
            nivel = (TextView) v.findViewById(R.id.nivel_c);
            modulo = (TextView) v.findViewById(R.id.modulo_c);
            fecha = (TextView)v.findViewById(R.id.fecha1_c);


        }
    }

    public usuarioAdaptador(List<usuarios> items) {
        this.items = items;
    }

    public int getItemCount() {
        return items.size();
    }

    @Override
    public usuarioViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.usuarios_card, viewGroup, false);
        return new usuarioViewHolder(v);
    }

    @Override
    public void onBindViewHolder(usuarioViewHolder usuarioViewHolder, int i) {
        usuarioViewHolder.id_persona.setText(String.valueOf(items.get(i).getIdusuario()));
        usuarioViewHolder.nombre.setText(items.get(i).getNombres());
        usuarioViewHolder.apellidos.setText(items.get(i).getApellidos());
        usuarioViewHolder.tipo.setText(items.get(i).getActividades());
        usuarioViewHolder.nivel.setText(items.get(i).getNivel());
        usuarioViewHolder.modulo.setText(items.get(i).getModulo());
        usuarioViewHolder.fecha.setText("Fceha: " + items.get(i).getFecha());

    }

}
