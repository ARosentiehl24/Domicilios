package com.unimagdalena.edu.co.domicilios;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.rafakob.drawme.DrawMeButton;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PlatoAdapter extends RecyclerView.Adapter<PlatoAdapter.ViewHolder> {

    private Activity activity;
    private ArrayList<Plato> platos;

    public PlatoAdapter(Activity activity, ArrayList<Plato> platos) {
        this.activity = activity;
        this.platos = platos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View platos = layoutInflater.inflate(R.layout.plato_item_row, parent, false);

        return new ViewHolder(platos);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Plato plato = platos.get(position);

        holder.nombrePlato.setText(plato.getNombre());
        holder.descripcionPlato.setText(plato.getDescripcion());
        holder.precioPlato.setText(String.format("$%s", plato.getPrecio()));

        holder.botonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "Click" + plato.getPrecio(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return platos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.nombre_plato)
        TextView nombrePlato;

        @Bind(R.id.descripcion_plato)
        TextView descripcionPlato;

        @Bind(R.id.precio_plato)
        TextView precioPlato;

        @Bind(R.id.botonAgregar)
        DrawMeButton botonAgregar;

        @Bind(R.id.botonQuitar)
        DrawMeButton botonQuitar;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
