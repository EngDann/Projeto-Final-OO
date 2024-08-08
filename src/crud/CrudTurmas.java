package crud;

import java.util.ArrayList;
import java.util.List;

import app.Turma;
import app.Turma;

public class CrudTurmas {
    private int numTurmas;
    private List<Turma> turmas;

    public CrudTurmas() {
        numTurmas = 0;
        turmas = new ArrayList<Turma>();
    }

    public int cadastrarTurma(Turma a) {
        boolean cadastrou = turmas.add(a);
        if (cadastrou) {
            numTurmas = turmas.size();
        }
        return numTurmas;
    }

    public Turma pesquisarTurma(String codigoTurma) {
        for (Turma a : turmas) {
            if (a.getCodigo().equalsIgnoreCase(codigoTurma)) {
                return a;
            }
        }
        return null;
    }

    public boolean removerTurma(Turma a) {
        boolean removeu = false;
        if (turmas.contains(a)) {
            removeu = turmas.remove(a);
        }
        return removeu;
    }

    public boolean atualizarTurma(String matricula, Turma a) {
        boolean resposta = false;
        Turma remover = pesquisarTurma(matricula);
        if (remover != null) {
            turmas.remove(remover);
            resposta = turmas.add(a);
        }
        return resposta;
    }
}
