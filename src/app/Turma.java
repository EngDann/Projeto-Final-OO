package app;

import java.util.List;

public class Turma {
    String codigo;
    Disciplina disciplina;
    Professor professor;
    List<Aluno> alunos;

    public Turma(String codigo, Disciplina disciplina, Professor professor, List<Aluno> alunos) {
        this.codigo = codigo;
        this.disciplina = disciplina;
        this.professor = professor;
        this.alunos = alunos;
    }

    public String getCodigo() {
        return codigo;
    }

    public Professor getProfessor() {
        return professor;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

}
