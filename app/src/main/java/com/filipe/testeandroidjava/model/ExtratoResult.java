package com.filipe.testeandroidjava.model;

import com.google.gson.annotations.Expose;

import java.util.List;

public class ExtratoResult {

    @Expose
    private List<Extrato> extrato;

    public List<Extrato> getExtrato() {
        return extrato;
    }

    public void setExtrato(List<Extrato> extrato) {
        this.extrato = extrato;
    }
}
