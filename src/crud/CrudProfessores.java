package crud;

import java.util.List;
import java.util.ArrayList;
import app.Professor;

public class CrudProfessores {
	private int numProf;
	private List<Professor> professores;

	public CrudProfessores() {
		this.numProf = 0;
		this.professores = new ArrayList<>();
	}

	public int cadastrarProfessor(Professor p) {
		boolean cadastrou = professores.add(p);
		if (cadastrou) {
			numProf = professores.size();
		}
		return numProf;
	}

	public Professor pesquisarProfessor(String matriculaFUB) {
		for (Professor p : professores) {
			if (p.getMatriculaFUB().equalsIgnoreCase(matriculaFUB)) {
				return p;
			}
		}
		return null;
	}

	public boolean removerProfessor(Professor p) {
		boolean removeu = professores.remove(p);
		if (removeu) {
			numProf = professores.size();
		}
		return removeu;
	}

	public boolean atualizarProfessor(String matriculaFUB, Professor novoProfessor) {
		for (int i = 0; i < professores.size(); i++) {
			if (professores.get(i).getMatriculaFUB().equalsIgnoreCase(matriculaFUB)) {
				professores.set(i, novoProfessor);
				return true;
			}
		}
		return false;
	}
}
