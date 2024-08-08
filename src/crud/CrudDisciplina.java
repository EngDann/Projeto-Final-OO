package crud;

import java.util.List;
import java.util.ArrayList;
import app.Disciplina;
import app.Turma;

public class CrudDisciplina {
	private int numDisc;
	private List<Disciplina> disciplinas;

	public CrudDisciplina() {
		numDisc = 0;
		disciplinas = new ArrayList<>();
	}

	public int cadastrarDisciplina(Disciplina d) {
		boolean cadastrou = disciplinas.add(d);
		if (cadastrou) {
			numDisc = disciplinas.size();
		}
		return numDisc;
	}

	public Disciplina pesquisarDisciplina(String codigo) {
		for (Disciplina d : disciplinas) {
			if (d.getCodigo().equalsIgnoreCase(codigo)) {
				return d;
			}
		}
		return null;
	}

	public boolean removerDisciplina(Disciplina d) {
		boolean removeu = disciplinas.remove(d);
		if (removeu) {
			numDisc = disciplinas.size();
		}
		return removeu;
	}

	public boolean atualizarDisciplina(String codigo, Disciplina novaDisciplina) {
		for (int i = 0; i < disciplinas.size(); i++) {
			if (disciplinas.get(i).getCodigo().equalsIgnoreCase(codigo)) {
				disciplinas.set(i, novaDisciplina);
				return true;
			}
		}
		return false;
	}

	public boolean adicionarTurma(String codigoDisciplina, Turma turma) {
		Disciplina disciplina = pesquisarDisciplina(codigoDisciplina);
		if (disciplina != null) {
			disciplina.adicionarTurma(turma);
			turma.setDisciplina(disciplina);
			return true;
		}
		return false;
	}
}
