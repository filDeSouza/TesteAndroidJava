package com.filipe.testeandroidjava.view.interfaces;

import com.filipe.testeandroidjava.model.LoginPost;
import android.content.Context;


public interface Comunicador {

    void recebeDados(LoginPost loginPost, Context context);

}
