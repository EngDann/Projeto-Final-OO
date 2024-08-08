package crud;

import java.util.List;
import java.util.ArrayList;

import app.Professor;

public class CrudProfessores {
	int numProf;
	private List<Professor> professores;

	public CrudProfessores() {
		numProf = 0;
		professores = new ArrayList<Professor>();
	}

	public int cadastrarProfessor(Professor p) {
		boolean cadastrou = professores.add(p);
		if (cadastrou) {
			numProf = professores.size();
		}
		return numProf;
	}

	public Professor pesquisarProfessor(String matriculaFUB) {
		Professor pesquisar = null;
		for (Professor p : professores) {
			if (p.getMatriculaFUB().equalsIgnoreCase(matriculaFUB)) {
				return p;
			}
		}
		return pesquisar;
	}

	public boolean removerProfessor(Professor p) {
		boolean removeu = false;
		if (professores.contains(p)) {
			removeu = professores.remove(p);
			numProf = professores.size();
		}
		return removeu;
	}

	public boolean atualizarProfessor(String matriculaFUB, Professor p) {
		for (Professor atualizar : professores) {
			if (atualizar.getMatriculaFUB().equalsIgnoreCase(matriculaFUB)) {
				atualizar = p;
				return true;
			}
		}
		return false;
	}
}
