package app;

import java.util.List;

public class Disciplina {
    List<Turma> turmas;
    String nome;
    int codigo;

    public Disciplina(List<Turma> turmas, String nome, int codigo) {
        this.turmas = turmas;
        this.nome = nome;
        this.codigo = codigo;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public String getNome() {
        return nome;
    }

    public int getCodigo() {
        return codigo;
    }

}
