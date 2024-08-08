package app;

import java.util.ArrayList;
import java.util.List;

public class Disciplina {
    private String nome;
    private String codigo;
    private List<Turma> turmas;

    public Disciplina(String nome, String codigo) {
        this.nome = nome;
        this.codigo = codigo;
        turmas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    @Override
    public String toString() {
        String resposta = "DISCIPLINA: " + nome + "\n" + "CODIGO DA DISCIPLINA: " + codigo + "\n";
        return resposta;
    }
}
