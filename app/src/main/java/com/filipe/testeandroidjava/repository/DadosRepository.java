package com.filipe.testeandroidjava.repository;

import com.filipe.testeandroidjava.model.Extrato;
import com.filipe.testeandroidjava.model.ExtratoResult;

import static com.filipe.testeandroidjava.data.remote.RetrofitService.getApiService;
import java.util.List;
import io.reactivex.Observable;
import retrofit2.Call;

public class DadosRepository {

    public Observable<List<Extrato>> getDadosExtrato(String token){
        return getApiService().getDadosExtrato(token);
    }

}
