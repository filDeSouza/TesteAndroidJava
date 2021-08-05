package com.filipe.testeandroidjava.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Extrato implements Parcelable{

    @SerializedName("data")
    private String data;

    @SerializedName("descricao")
    private String descricao;

    @SerializedName("valor")
    private String valor;

    protected Extrato(Parcel in) {
        data = in.readString();
        descricao = in.readString();
        valor = in.readString();
    }

    public static final Creator<Extrato> CREATOR = new Creator<Extrato>() {
        @Override
        public Extrato createFromParcel(Parcel in) {
            return new Extrato(in);
        }

        @Override
        public Extrato[] newArray(int size) {
            return new Extrato[size];
        }
    };

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(data);
        parcel.writeString(descricao);
        parcel.writeString(valor);
    }
}
