package crud;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import app.Professor;
import app.Disciplina;
import app.Aluno;
import app.Turma;

public class CrudTurma {
    private int numTurmas;
    private List<Turma> turmas;
    private CrudAlunos crudAluno;
    private CrudProfessores crudProfessor;
    private CrudDisciplina crudDisciplina;

    public CrudTurma(CrudAlunos crudAluno, CrudProfessores crudProfessor, CrudDisciplina crudDisciplina) {
        this.numTurmas = 0;
        this.turmas = new ArrayList<>();
        this.crudAluno = crudAluno;
        this.crudProfessor = crudProfessor;
        this.crudDisciplina = crudDisciplina;
    }

    public int cadastrarTurma(Turma turma) {
        boolean cadastrou = turmas.add(turma);
        if (cadastrou) {
            numTurmas = turmas.size();
        }
        return numTurmas;
    }

    public Turma pesquisarTurma(String codigo) {
        for (Turma turma : turmas) {
            if (turma.getCodigo().equalsIgnoreCase(codigo)) {
                return turma;
            }
        }
        return null;
    }

    public boolean removerTurma(Turma turma) {
        boolean removeu = turmas.remove(turma);
        if (removeu) {
            numTurmas = turmas.size();
        }
        return removeu;
    }

    public boolean atualizarTurma(String codigo, Turma novaTurma) {
        for (int i = 0; i < turmas.size(); i++) {
            if (turmas.get(i).getCodigo().equalsIgnoreCase(codigo)) {
                turmas.set(i, novaTurma);
                return true;
            }
        }
        return false;
    }

    public boolean adicionarProfessor(String matriculaFUB, Turma turma) {
        Professor p = crudProfessor.pesquisarProfessor(matriculaFUB);
        if (p != null) {
            turma.setProfessorAssociado(p);
            return true;
        }
        return false;
    }

    public boolean adicionarAluno(String matricula, Turma turma) {
        Aluno alun = crudAluno.pesquisarAluno(matricula);
        if (alun != null && turma.getQtdVagasLivres() > 0) {
            turma.getAlunosMatriculados().add(alun);
            turma.setQtdVagasLivres(turma.getQtdVagasLivres() - 1);
            return true;
        }
        return false;
    }

    public void listaDePresenca(String codigoTurma) {
        Turma t = pesquisarTurma(codigoTurma);
        String lista = "Lista de chamada: turma "+codigoTurma+"\n";
        if (t != null) {
        	for(Aluno a : t.getAlunosMatriculados()) {
        		lista += a.getNome()+" "+a.getMatricula()+"\n";
        	}
        	JOptionPane.showMessageDialog(null, lista);
        }
    }
    
    public boolean repeteCodigo(String cod){
		for(Turma t : turmas) {
			if(t.getCodigo().trim().equalsIgnoreCase(cod)) {
				return true;
			}
		}
		return false;
	}
}
