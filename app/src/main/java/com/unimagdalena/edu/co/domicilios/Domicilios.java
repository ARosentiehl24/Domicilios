package com.unimagdalena.edu.co.domicilios;

import android.app.Application;

public class Domicilios extends Application {

    private Domicilios domicilios;

    public Domicilios getInstance() {
        return domicilios;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        domicilios = this;
    }
}
