package com.filipe.testeandroidjava.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

public class LoginPost implements Parcelable {

    @SerializedName("username")
    private String usuario;

    @SerializedName("password")
    private String senha;

    @SerializedName("nome")
    private String nome;

    @SerializedName("documento")
    private String documento;

    @SerializedName("saldo")
    private String saldo;

    @SerializedName("token")
    private String token;

    public LoginPost(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }


    public LoginPost() {
    }

    protected LoginPost(Parcel in) {
        nome = in.readString();
        token = in.readString();
        documento = in.readString();
        saldo = in.readString();
        senha = in.readString();
        usuario = in.readString();

    }

    public static final Creator<LoginPost> CREATOR = new Creator<LoginPost>() {
        @Override
        public LoginPost createFromParcel(Parcel in) {
            return new LoginPost(in);
        }

        @Override
        public LoginPost[] newArray(int size) {
            return new LoginPost[size];
        }
    };

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(nome);
        parcel.writeString(token);
        parcel.writeString(documento);
        parcel.writeString(saldo);
        parcel.writeString(senha);
        parcel.writeString(usuario);
        Log.i("LOG", "Valor do nome dentro do writeToParcel: " + nome);



    }
}
