package com.filipe.testeandroidjava.view.viewmodel;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.filipe.testeandroidjava.data.remote.RetrofitService;
import com.filipe.testeandroidjava.model.Extrato;
import com.filipe.testeandroidjava.repository.DadosRepository;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.prefs.Preferences;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ExtratoViewModel extends AndroidViewModel {

    private CompositeDisposable disposable = new CompositeDisposable();
    private DadosRepository repository = new DadosRepository();
    private MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private static RetrofitService retrofitService = new RetrofitService();
    private Preferences prefs;
    private Context context;
    private MutableLiveData<List<Extrato>> listaExtrato = new MutableLiveData<>();


    public ExtratoViewModel(@NonNull @NotNull Application application) {
        super(application);
    }

    public LiveData<List<Extrato>> getListaExtrato() {
        return this.listaExtrato;
    }

    public LiveData<Boolean> getLoading() {
        return this.loading;
    }

    public void getDadosExtrato(String token){

        disposable.add(
                repository.getDadosExtrato(token)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable1 -> loading.setValue(true))
                        .doOnTerminate(() -> loading.setValue(false))
                        .subscribe(response -> {

                            listaExtrato.setValue(response);
/*                    for (int valor = 1; valor <= 1118; valor++){
                        getDetalhePokemon(valor);
                    }*/
                            Log.i("LOG", "API: " + response );

                        }, throwable -> {
                            Log.i("LOG", "Error: " + throwable.getMessage());
                        })

        );

//        call.enqueue(new Callback<List<Extrato>>() {
//            @Override
//            public void onResponse(Call<List<Extrato>> call, Response<List<Extrato>> response) {
//
//                if (!response.isSuccessful()){
//                    Log.i("LOG", "API: Problema" + response.code());
//                    return;
//                }else {
//                    Log.i("LOG", "API: Sucesso" + response.code());
//                    lista.setExtrato(response.body());
//                    Log.i("LOG", "API: Sucesso" + response.body().get(0).getData());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Extrato>> call, Throwable t) {
//                Log.i("LOG", "API problema: " + call.toString() + "API problema: " + t.getMessage());
//            }
//        });

    }
}
