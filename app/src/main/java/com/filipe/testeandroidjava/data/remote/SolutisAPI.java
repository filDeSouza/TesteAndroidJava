package com.filipe.testeandroidjava.data.remote;

import com.filipe.testeandroidjava.model.Extrato;
import com.filipe.testeandroidjava.model.ExtratoResult;
import com.filipe.testeandroidjava.model.LoginPost;

import java.lang.reflect.Array;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SolutisAPI {

    @POST("login")
    Call<LoginPost> postDadosClientes(@Body LoginPost loginPost);

    @GET("extrato")
    Observable<List<Extrato>> getDadosExtrato(@Header("token") String  token);
}
