package com.filipe.testeandroidjava.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.filipe.testeandroidjava.R;
import com.filipe.testeandroidjava.model.LoginPost;
import com.filipe.testeandroidjava.repository.DadosRepository;
import com.filipe.testeandroidjava.util.AppUtils;
import com.filipe.testeandroidjava.view.interfaces.Comunicador;
import com.filipe.testeandroidjava.view.viewmodel.LoginViewModel;

import java.util.prefs.Preferences;

public class LoginActivity extends AppCompatActivity implements Comunicador {

    public static final String CLIENTE_KEY = "cliente";
    private EditText editTextusuario;
    private EditText editTextSenha;
    private Button botaoLogin;
    private AppUtils appUtils = new AppUtils();
    private String textoUsuario;
    private String textoSenha;
    private TextView textoMsgErro;
    private Preferences prefs;
    private LoginPost loginPost = new LoginPost();
    private DadosRepository repository = new DadosRepository();
    private SharedPreferences sharedPref;
    private String usuarioSalvo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPref = getSharedPreferences("myPref", MODE_PRIVATE);

        usuarioSalvo = sharedPref.getString("user_id", "");

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.transparente));

        initViews();

        botaoLogin.setOnClickListener(view -> {
            textoUsuario = editTextusuario.getText().toString();
            textoSenha = editTextSenha.getText().toString();

            if (usuarioSalvo.isEmpty()){
                sharedPref.edit().putString("user_id", editTextusuario.getText().toString());
                Log.i("LOG", "Usuario salvo: " + usuarioSalvo);
            }

            if (textoUsuario.equals("") || textoSenha.equals("")){
                textoMsgErro.setVisibility(View.VISIBLE);
                editTextusuario.setBackground(getDrawable(R.drawable.edit_text_border));
                Log.i("LOG", "Caiu no if 2");
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(textoUsuario).matches() && appUtils.validaCPF(textoUsuario) == false){
                Log.i("LOG", "Caiu no if 4");
            }
            if (!appUtils.validarSenha(textoSenha.trim())){
                Log.i("LOG", "Caiu no if 5");
            }
            else {
                textoMsgErro.setVisibility(View.INVISIBLE);

//                TesteViewModel testeViewModel = new TesteViewModel(this);
//                testeViewModel.getData(textoUsuario, textoSenha);
//
//                Intent intent = new Intent(this, HomeActivity.class);
//                Bundle bundle = new Bundle();
//                intent.putExtras(bundle);
//                startActivity(intent);


            }
            LoginViewModel loginViewModel = new LoginViewModel(LoginActivity.this);
            loginViewModel.getData(textoUsuario, textoSenha);


//            Intent intent = new Intent(this, HomeActivity.class);
//            Bundle bundle = new Bundle();
//            //bundle.putParcelable("cliente", );
//            intent.putExtras(bundle);
//            startActivity(intent);

        });

    }

    private void initViews() {

        editTextusuario = findViewById(R.id.editTextUsuario);
        Log.i("LOG", "Usuario salvo: " + usuarioSalvo);

        if (!usuarioSalvo.isEmpty()){
            editTextusuario.setText(usuarioSalvo);
        }
        editTextSenha = findViewById(R.id.editTextSenha);
        botaoLogin = findViewById(R.id.botaoLogin);
        textoMsgErro = findViewById(R.id.textViewMsgErro);

    }

    public void recebeDados(LoginPost loginPost, Context context) {

        //LoginPost retorno = testeViewModel.getData(textoUsuario, textoSenha);
        Log.i("LOG", "Valor do nome: " + loginPost.getNome());
        Intent intent = new Intent(context, HomeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("cliente", loginPost);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}