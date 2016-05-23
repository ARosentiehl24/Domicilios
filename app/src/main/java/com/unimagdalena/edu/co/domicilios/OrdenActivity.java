package com.unimagdalena.edu.co.domicilios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class OrdenActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.confirmar_pedido)
    CardView confirmarPedido;

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orden);
        ButterKnife.bind(this);

        ImageView imageView = (ImageView) toolbar.findViewById(R.id.button_expander);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        confirmarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrdenActivity.this, ConfirmarPedidoActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_bottom);
        finish();
    }
}
