package crud;

import java.util.ArrayList;
import java.util.List;
import app.Aluno;

public class CrudAlunos {
	private int numAlunos;
	private List<Aluno> alunos;

	public CrudAlunos() {
		this.numAlunos = 0;
		this.alunos = new ArrayList<>();
	}

	public int cadastrarAluno(Aluno a) {
		boolean cadastrou = alunos.add(a);
		if (cadastrou) {
			numAlunos = alunos.size();
		}
		return numAlunos;
	}

	public Aluno pesquisarAluno(String matriculaAluno) {
		for (Aluno a : alunos) {
			if (a.getMatricula().equalsIgnoreCase(matriculaAluno)) {
				return a;
			}
		}
		return null;
	}

	public boolean removerAluno(Aluno a) {
		boolean removeu = alunos.remove(a);
		if (removeu) {
			numAlunos = alunos.size();
		}
		return removeu;
	}

	public boolean atualizarAluno(String matricula, Aluno a) {
		for (int i = 0; i < alunos.size(); i++) {
			if (alunos.get(i).getMatricula().equalsIgnoreCase(matricula)) {
				alunos.set(i, a);
				return true;
			}
		}
		return false;
	}
	public boolean repeteMatricula(String mat){
		for(Aluno a : alunos) {
			if(a.getMatricula().trim().equalsIgnoreCase(mat)) {
				return true;
			}
		}
		return false;
	}
}
