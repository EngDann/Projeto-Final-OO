package app;

import java.util.ArrayList;
import java.util.List;
import crud.CrudDisciplina;
import crud.CrudProfessores;

public class Turma {
    private String codigo;
    private int qtdVagas;
    private int qtdVagasLivres;
    private List<Aluno> alunosMatriculados;
    private Professor professorAssociado;
    private Disciplina disciplina;

    public Turma(String codigo, String disciplina, String matProf, int qtdVagas, CrudDisciplina cadDisciplina,
            CrudProfessores cadProfessor) {
        this.codigo = codigo;
        this.disciplina = cadDisciplina.pesquisarDisciplina(disciplina);
        this.professorAssociado = cadProfessor.pesquisarProfessor(matProf);
        this.qtdVagas = qtdVagas;
        this.qtdVagasLivres = qtdVagas;
        this.alunosMatriculados = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public int getQtdVagas() {
        return qtdVagas;
    }

    public int getQtdVagasLivres() {
        return qtdVagasLivres;
    }

    public Professor getProfessorAssociado() {
        return professorAssociado;
    }

    public List<Aluno> getAlunosMatriculados() {
        return alunosMatriculados;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setQtdVagas(int qtdVagas) {
        this.qtdVagas = qtdVagas;
    }

    public void setQtdVagasLivres(int qtdVagasLivres) {
        this.qtdVagasLivres = qtdVagasLivres;
    }

    public void setProfessorAssociado(Professor professorAssociado) {
        this.professorAssociado = professorAssociado;
    }

    public void setAlunosMatriculados(List<Aluno> alunosMatriculados) {
        this.alunosMatriculados = alunosMatriculados;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    @Override
    public String toString() {
        String resposta = "DISCIPLINA: "
                + (disciplina != null ? disciplina.getCodigo() + " (" + disciplina.getNome() : "N/A") + ")\n";
        resposta += "CODIGO: " + codigo + "\n";
        resposta += "QUANTIDADE DE VAGAS: " + qtdVagas + "\n";
        resposta += "NUMERO DE VAGAS LIVRES: " + qtdVagasLivres + "\n";
        resposta += "PROFESSOR: " + (professorAssociado != null ? professorAssociado.getNome() : "N/A") + "\n";
        return resposta;
    }
}
