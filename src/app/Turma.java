package app;

import java.util.List;

public class Turma {
    int codigo;
    Professor professor;
    List<Aluno> alunos;

    public Turma(int codigo, Professor professor, List<Aluno> alunos) {
        this.codigo = codigo;
        this.professor = professor;
        this.alunos = alunos;
    }

    public int getCodigo() {
        return codigo;
    }

    public Professor getProfessor() {
        return professor;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

}
