package com.unimagdalena.edu.co.domicilios;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.rafakob.drawme.DrawMeButton;
import com.rafakob.drawme.DrawMeTextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RestauranteActivity extends AppCompatActivity {

    public static Restaurante restaurante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurante);

        restaurante = (Restaurante) getIntent().getSerializableExtra("restaurante");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        ViewPager mViewPager = (ViewPager) findViewById(R.id.container);
        assert mViewPager != null;
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        assert tabLayout != null;
        tabLayout.setupWithViewPager(mViewPager);
    }

    public static class PlatoFragment extends Fragment {

        @Bind(R.id.recyclerView)
        RecyclerView recyclerView;

        private DrawMeTextView totalItems;
        private PlatoAdapter platoAdapter;
        private TextView totalPrice;

        public PlatoFragment() {
        }

        public static PlatoFragment newInstance() {
            return new PlatoFragment();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_plato, container, false);
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            ButterKnife.bind(this, view);

            TextView nombreRestaurante = (TextView) view.findViewById(R.id.nombre_restaurante);
            nombreRestaurante.setText(restaurante.getNombre());

            ImageView logoRestaurante = (ImageView) view.findViewById(R.id.logo_restaurante);
            Picasso.with(getActivity()).load(restaurante.getImagen()).into(logoRestaurante);

            RatingBar rating = (RatingBar) view.findViewById(R.id.rating);
            rating.setRating(restaurante.getCalificacion());

            TextView metodosPago = (TextView) view.findViewById(R.id.metodos_pago);

            TextView pedidoMinimo = (TextView) view.findViewById(R.id.pedido_minimo);
            pedidoMinimo.setText(String.format("$%s", restaurante.getPrecioMinimo()));

            TextView costoEnvio = (TextView) view.findViewById(R.id.costo_envio);
            costoEnvio.setText(String.format("$%s", restaurante.getPrecioEnvio()));

            TextView tiempoEntrega = (TextView) view.findViewById(R.id.tiempo_entrega);
            tiempoEntrega.setText(String.format(Locale.US, "%d Minutos", restaurante.getTiempoEntrega()));

            LinearLayout verOrden = (LinearLayout) view.findViewById(R.id.ver_orden);
            verOrden.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), OrdenActivity.class);

                    startActivity(intent);

                    getActivity().overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_top);
                }
            });

            totalPrice = (TextView) view.findViewById(R.id.total_price);
            totalPrice.setText("$0");

            totalItems = (DrawMeTextView) view.findViewById(R.id.total_items);

            ArrayList<Plato> platos = restaurante.getMenu();

            platoAdapter = new PlatoAdapter(getActivity(), platos);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setHasFixedSize(true);
            recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), null, false, true));
            recyclerView.setAdapter(platoAdapter);
        }
    }

    public static class ComentarioFragment extends Fragment {

        @Bind(R.id.recyclerView)
        RecyclerView recyclerView;

        private ComentarioAdapter comentarioAdapter;

        public ComentarioFragment() {
        }

        public static ComentarioFragment newInstance() {
            return new ComentarioFragment();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_comentario, container, false);
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            ButterKnife.bind(this, view);

            ArrayList<Comentario> comentarios = restaurante.getComentarios();

            comentarioAdapter = new ComentarioAdapter(getActivity(), comentarios);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setHasFixedSize(true);
            recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), null, false, true));
            recyclerView.setAdapter(comentarioAdapter);
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return PlatoFragment.newInstance();
                case 1:
                    return ComentarioFragment.newInstance();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Menu";
                case 1:
                    return "Comentarios";
            }
            return null;
        }
    }
}
