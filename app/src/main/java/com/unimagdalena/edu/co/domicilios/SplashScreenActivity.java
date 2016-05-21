package com.unimagdalena.edu.co.domicilios;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new CargarRestaurantes().execute();
    }

    class CargarRestaurantes extends AsyncTask<String, Void, ArrayList<Restaurantes>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<Restaurantes> doInBackground(String... params) {
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Restaurantes> restaurantes) {
            super.onPostExecute(restaurantes);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                }
            }, 1000);
        }
    }
}
