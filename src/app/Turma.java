package app;

import java.util.List;

public class Turma {
    // FALTA IMPLEMENTAR O SISTEMA DE LISTA DE ALUNOS E CHAMADA
    private String codigo;
    private int qtdVagas,
            qtdVagasLivres;

    private Professor professorAssociado;
    private List<Aluno> alunosMatriculados;
    private Disciplina disciplina;

    public Turma(String codigo, int qtdVagas) {

    }

    public final String getCodigo() {
        return codigo;
    }

    public final int getQtdVagas() {
        return qtdVagas;
    }

    public final int getQtdVagasLivres() {
        return qtdVagasLivres;
    }

    public final Professor getProfessor() {
        return professorAssociado;
    }

    public final List<Aluno> getAlunosMatriculados() {
        return alunosMatriculados;
    }

    public final Disciplina getDisciplina() {
        return disciplina;
    }

    public final String toString() {
        String resposta = "DISCIPLINA: " + disciplina.getCodigo() + " " + disciplina.getNome() + "\n";
        resposta += "CODIGO: " + codigo + "\n";
        resposta += "QUANTIDADE DE VAGAS: " + qtdVagas + "\n";
        resposta += "NUMERO DE VAGAS LIVRES: " + qtdVagasLivres + "\n";
        resposta += "PROFESSOR: " + professorAssociado.getNome() + "\n";
        return resposta;
    }
}
