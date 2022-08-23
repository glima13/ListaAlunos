package com.example.alura.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.alura.R;
import com.example.alura.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class ListaAdapter extends BaseAdapter {
    private final List<Aluno> alunos = new ArrayList<>();
    private final Context context;

    public ListaAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Aluno getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Aluno alunoDevolvido = alunos.get(position);
        View viewCriada = criaView(parent);

        vincula(alunoDevolvido, viewCriada);

        return viewCriada;
    }

    private void vincula(Aluno alunoDevolvido, View viewCriada) {
        TextView nome = viewCriada.findViewById(R.id.textView);
        nome.setText(alunoDevolvido.getNomes());

        TextView telefone = viewCriada.findViewById(R.id.textView2);
        telefone.setText(alunoDevolvido.getTelefones());
    }

    private View criaView(ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_aluno, parent,false);
    }


    public void atualiza(List<Aluno> alunos){
        this.alunos.clear();
        this.alunos.addAll(alunos);
        notifyDataSetChanged();
    }



    public void remove(Aluno aluno) {
        alunos.remove(aluno);
        notifyDataSetChanged();
    }
}
