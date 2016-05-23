package com.unimagdalena.edu.co.domicilios;

import java.io.Serializable;

public enum TipoPago implements Serializable {
    EFECTIVO("Efectivo"),
    CREDITO("Credito"),
    DEBITO("Debito");

    private String tipoPago;

    TipoPago(String tipoPAgo) {
        this.tipoPago = tipoPAgo;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }
}
