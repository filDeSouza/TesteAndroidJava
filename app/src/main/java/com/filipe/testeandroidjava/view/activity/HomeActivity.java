package com.filipe.testeandroidjava.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.filipe.testeandroidjava.R;
import com.filipe.testeandroidjava.model.Extrato;
import com.filipe.testeandroidjava.model.LoginPost;
import com.filipe.testeandroidjava.view.adapter.RecyclerViewExtratoAdapter;
import com.filipe.testeandroidjava.view.viewmodel.ExtratoViewModel;
import com.filipe.testeandroidjava.view.viewmodel.LoginViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    public static final String CLIENTE_KEY = "cliente";
    private TextView textViewNome;
    private TextView textViewDocumento;
    private TextView textViewSaldo;
    private ImageButton imageButtonLogout;
    private ProgressBar progressBar;
    private RecyclerViewExtratoAdapter adapter;
    private List<Extrato> listaExtrato = new ArrayList<>();
    private RecyclerView recyclerView;
    private ExtratoViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.transparente));

        initViews();

        if (getIntent() != null && getIntent().getExtras() != null){
            Bundle bundle = getIntent().getExtras();
            LoginPost loginPost = bundle.getParcelable(CLIENTE_KEY);

            Log.i("LOG", "Nome dentro do getExtras: " + loginPost.getNome());



            String nome = loginPost.getNome();
            String documento = loginPost.getDocumento();
            String saldo = loginPost.getSaldo();

            textViewNome.setText(nome);
            textViewDocumento.setText(documento);
            textViewSaldo.setText("R$ " + saldo);

            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
            //setScrollView(loginPost.getToken());

            viewModel.getDadosExtrato(loginPost.getToken());

            viewModel.getListaExtrato().observe(this, resultadoLista -> {
                adapter.atualizarLista(resultadoLista);
                Log.i("LOG", "resultado do adapter: " + resultadoLista);
            });

            viewModel.getLoading().observe(this, loading -> {
                if (loading){
                    progressBar.setVisibility(View.VISIBLE);
                }else {
                    progressBar.setVisibility(View.GONE);
                }
            });

            imageButtonLogout.setOnClickListener(view -> {
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
            });

        }
    }

//    private void setScrollView(String token) {
//
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(@NonNull @NotNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//
//                GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
//
//                int totalItemCount = gridLayoutManager.getItemCount();
//                int lastVisible = gridLayoutManager.findLastVisibleItemPosition();
//                boolean ultimoItem = lastVisible + 3 >= totalItemCount;
//                if (totalItemCount > 0 && ultimoItem){
//                    viewModel.getDadosExtrato(token);
//                }
//
//
//            }
//        });
//
//    }

    private void initViews() {

        textViewNome = findViewById(R.id.textViewNome);
        textViewDocumento = findViewById(R.id.textViewDocumento);
        textViewSaldo = findViewById(R.id.textViewSaldo);
        imageButtonLogout = findViewById(R.id.imageButtonLogout);
        recyclerView = findViewById(R.id.recyclerViewExtrato);
        progressBar = findViewById(R.id.progress_bar);
        adapter = new RecyclerViewExtratoAdapter(listaExtrato);
        viewModel = ViewModelProviders.of(this).get(ExtratoViewModel.class);

    }
}