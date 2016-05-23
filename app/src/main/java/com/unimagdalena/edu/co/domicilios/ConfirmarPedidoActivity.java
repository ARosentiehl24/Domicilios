package com.unimagdalena.edu.co.domicilios;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ConfirmarPedidoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_pedido);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
