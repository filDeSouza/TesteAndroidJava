package com.filipe.testeandroidjava.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.filipe.testeandroidjava.R;
import com.filipe.testeandroidjava.model.Extrato;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RecyclerViewExtratoAdapter extends RecyclerView.Adapter<RecyclerViewExtratoAdapter.ViewHolder> {

    private List<Extrato> listaExtrato;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    String formattedDate = "2021-05-05T16:15:10Z";
    Date conversorData = new Date();

    public RecyclerViewExtratoAdapter(List<Extrato> listaExtrato) {
        this.listaExtrato = listaExtrato;
        Log.i("LOG", "tamanho da lista dentro do RecyclerViewExtratoAdapter: " + listaExtrato.size());
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_extrato, parent, false);
        Log.i("LOG", "tamanho da lista dentro do onCreateViewHolder: " + listaExtrato.size());
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Extrato itemExtrato = listaExtrato.get(position);
        holder.onBind(itemExtrato);
        Log.i("LOG", "tamanho da lista dentro do onBindViewHolder: " + listaExtrato.size());

    }

    @Override
    public int getItemCount() {
        Log.i("LOG", "Tamanho da lista no getItemCount: " + listaExtrato.size());
        return listaExtrato.size();
    }

    public void atualizarLista(List<Extrato> novalista){

        if (this.listaExtrato.isEmpty()){
            this.listaExtrato = novalista;
        }else{
            this.listaExtrato.addAll(novalista);
        }
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewTipo;
        private TextView textViewData;
        private TextView textViewDescricao;
        private TextView textViewValor;
        private String valor;
        private float valorEmFloat;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            textViewTipo = itemView.findViewById(R.id.textItemTipo);
            textViewData = itemView.findViewById(R.id.textItemData);
            textViewDescricao = itemView.findViewById(R.id.textItemDescricao);
            textViewValor = itemView.findViewById(R.id.textItemValor);
        }

        public void onBind(Extrato extrato){
            valor = extrato.getValor();
            valorEmFloat = Float.parseFloat(valor);
            if (valorEmFloat < 0){
                textViewTipo.setText("Débito");
                textViewValor.setTextColor(Color.rgb(250, 3, 33));
            }else if (valorEmFloat > 0){
                textViewValor.setTextColor(Color.rgb(17, 209, 11));
                textViewTipo.setText("Crédito");
            }

            try {
                String subString = extrato.getData().substring(0, 10);
                Log.i("LOG", "Data formatada: " + subString);
                conversorData = format.parse(subString);
                SimpleDateFormat sdfnewformat = new SimpleDateFormat("dd/MM/yyyy");
                String finalDateString = sdfnewformat.format(conversorData);
                textViewData.setText(finalDateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            textViewDescricao.setText(extrato.getDescricao());
            //valor = extrato.getValor();
            textViewValor.setText("R$ " + extrato.getValor());

            Log.i("LOG", "API: " + extrato.getDescricao());
        }
    }
}
