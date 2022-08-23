package com.example.alura.ui.activity;

import static com.example.alura.ui.activity.ListaActivity.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.alura.R;
import com.example.alura.dao.AlunoDAO;
import com.example.alura.model.Aluno;

public class FormularioActivity extends AppCompatActivity implements ConstantesActivities {


    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    AlunoDAO dao = new AlunoDAO();
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

//---------------------------------------------------------------------------------------------------//


        inicializacaoDosCampos();
        carregaAluno();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_salvar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.salvar){
            finalizaFormulario();
            Toast.makeText(this,"Aluno salvo",Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.nome);
        campoTelefone = findViewById(R.id.telefone);
        campoEmail = findViewById(R.id.email);
    }

    private void finalizaFormulario() {
        preencheAluno();
        if (aluno.temIdValido()) {
            dao.edita(aluno);
        }else{
            dao.salva(aluno);
        }
        finish();
    }


    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        aluno.setNomes(nome);
        aluno.setEmails(email);
        aluno.setTelefones(telefone);
    }


    private void carregaAluno() {
        Intent dados = getIntent();
        if(dados.hasExtra(CHAVE_ALUNO)){
            setTitle(NOME_APPBAR_EDITA);

            aluno = (Aluno) dados.getSerializableExtra(CHAVE_ALUNO);
            campoNome.setText(aluno.getNomes());
            campoTelefone.setText(aluno.getTelefones());
            campoEmail.setText(aluno.getEmails());
        }else{
            setTitle(NOME_APPBAR_NOVO);
            aluno = new Aluno();
        }
    }


}