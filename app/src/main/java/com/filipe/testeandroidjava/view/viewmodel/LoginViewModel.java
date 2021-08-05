package com.filipe.testeandroidjava.view.viewmodel;

import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.filipe.testeandroidjava.data.remote.RetrofitService;
import com.filipe.testeandroidjava.model.Extrato;
import com.filipe.testeandroidjava.model.ExtratoResult;
import com.filipe.testeandroidjava.model.LoginPost;
import com.filipe.testeandroidjava.data.remote.SolutisAPI;
import com.filipe.testeandroidjava.repository.DadosRepository;
import com.filipe.testeandroidjava.view.activity.LoginActivity;
import com.google.gson.Gson;

import java.util.List;
import java.util.prefs.Preferences;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.content.Context;


public class LoginViewModel extends Observable {

    private CompositeDisposable disposable = new CompositeDisposable();
    private DadosRepository repository = new DadosRepository();
    private MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private static RetrofitService retrofitService = new RetrofitService();
    private Preferences prefs;
    private Context context;
    private MutableLiveData<List<Extrato>> listaExtrato = new MutableLiveData<>();

    public LoginViewModel(Context contextRecebido)
    {
        this.context = contextRecebido;
    }

    public LiveData<List<Extrato>> getExtratoConstrutor(){
        return this.listaExtrato;
    }

    public void getData(String usuario, String senha){

        SolutisAPI solutisAPI = retrofitService.getRetrofit().create(SolutisAPI.class);
        LoginPost loginPost = new LoginPost(usuario, senha);
        Call<LoginPost> call = solutisAPI.postDadosClientes(loginPost);

        call.enqueue(new Callback<LoginPost>() {
            @Override
            public void onResponse(Call<LoginPost> call, Response<LoginPost> response) {

                if (!response.isSuccessful()){
                    Log.i("LOG", "API: Problema" + response.code());
                    return;
                }else {
                    LoginPost loginPost1 = response.body();
                        //prefs.put("nome", response.body().getUsuario());
                        loginPost.setDocumento(response.body().getDocumento());
                        loginPost.setNome(response.body().getNome());
                        loginPost.setSaldo(response.body().getSaldo());
                        loginPost.setSenha(response.body().getSenha());
                        loginPost.setToken(response.body().getToken());
                        loginPost.setUsuario(response.body().getUsuario());
                        Log.i("LOG", "API: Sucesso" + response.body().getNome());
                        LoginActivity loginActivity = new LoginActivity();
                        loginActivity.recebeDados(response.body(), context);
                }

            }

            @Override
            public void onFailure(Call<LoginPost> call, Throwable t) {
                Log.i("LOG", "API problema: " + call.toString() + "API problema: " + t.getMessage());
            }
        });


    }

    public LiveData<Boolean> getLoading(){
        return this.loading;
    }



    @Override
    protected void subscribeActual(Observer observer) {

    }
}
