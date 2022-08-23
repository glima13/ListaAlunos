package com.example.alura.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.alura.R;
import com.example.alura.dao.AlunoDAO;
import com.example.alura.model.Aluno;
import com.example.alura.ui.adapter.ListaAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListaActivity extends AppCompatActivity implements ConstantesActivities {


    private final AlunoDAO dao = new AlunoDAO();
    private ListaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    //---------------------------------------------------------------------------------------------------//


        botaoAdicionaAluno();
        configuraLista();
    }

    //---------------------------------------------------------------------------------------------------//

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_remover, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull final MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.item1) {
            new AlertDialog.Builder(this)
                    .setTitle("Removendo o aluno")
                    .setMessage("Tem certeza que deseja apagar o aluno?")
                    .setPositiveButton("Sim", (dialog, which) -> {
                        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                        Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
                        remove(alunoEscolhido);
                    })
                    .setNegativeButton("Não", null)
                    .show();
        }
        return super.onContextItemSelected(item);
    }

    //---------------------------------------------------------------------------------------------------//



    //---------------------------------------------------------------------------------------------------//
    @Override
    protected void onResume() {
        super.onResume();
        //atualiza alunos
        adapter.atualiza(dao.todos());
    }

    private void botaoAdicionaAluno() {
        FloatingActionButton botaoMais = findViewById(R.id.floatingActionButton);

        botaoMais.setOnClickListener(v -> startActivity(new Intent(ListaActivity.this, FormularioActivity.class)));
    }

    private void configuraLista() {
        ListView listaDeAlunos = findViewById(R.id.list);
        configuraAdapter(listaDeAlunos);
        configuraListenerClickPorItem(listaDeAlunos);
        registerForContextMenu(listaDeAlunos);
    }

    private void remove(Aluno aluno){
        dao.remove(aluno);
        adapter.remove(aluno);
    }
    //---------------------------------------------------------------------------------------------------//

    private void configuraAdapter(ListView listaDeAlunos) {

        adapter = new ListaAdapter(this);
        listaDeAlunos.setAdapter(adapter);
    }

    private void configuraListenerClickPorItem(ListView listaDeAlunos) {
        listaDeAlunos.setOnItemClickListener((adapterView, view, position, id) -> {

            Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(position);
            abreFormularioParaEditarAluno(alunoEscolhido);
        });
    }

    private void abreFormularioParaEditarAluno(Aluno aluno) {
        Intent vaiParaFormularioActivity = new Intent(ListaActivity.this,FormularioActivity.class);
        vaiParaFormularioActivity.putExtra(CHAVE_ALUNO, aluno);
        startActivity(vaiParaFormularioActivity);
    }


}