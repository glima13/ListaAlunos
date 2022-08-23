package com.example.alura;

import android.app.Application;

import com.example.alura.dao.AlunoDAO;
import com.example.alura.model.Aluno;

public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        AlunoDAO dao = new AlunoDAO();
        dao.salva(new Aluno("Guilherme","31983637997","guilherme_edson@hotmail.com"));
        dao.salva(new Aluno("Isabella","31983637996","isabella@hotmail.com"));
        super.onCreate();
    }
}
