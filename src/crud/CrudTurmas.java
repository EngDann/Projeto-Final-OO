package cadastros;
//FALTA SISTEMA DE CHAMADAS
import java.util.LinkedList;
import java.util.List;

import app.Professor;
import app.Disciplina;
import app.Aluno;
import app.Turma;

public class CrudTurma {
	int numTurmas;
	private List<Turma> turmas;
	
	private static CadastroAluno cadAluno;
	private static CadastroProfessor cadProfessor;
	private static CrudDisciplina cadDisciplina;
	
	public CrudTurma(){
		numTurmas = 0;
		turmas = new LinkedList<Turma>();
	}
	
	public int cadastrarTurma(Turma t) {
		boolean cadastrou = turmas.add(t);
		if(cadastrou) {
			numTurmas = turmas.size();
		}
			return numTurmas;
	}
	
	public Turma pesquisarTurma(String codigo) {
		Turma pesquisar = null;
		 for(Turma t : turmas) {
			 if (t.getCodigo().equalsIgnoreCase(codigo)) {
				 return t;
			 }
		 }
		 return pesquisar;
	}
	
	public boolean removerTurma(Turma t) {
		boolean removeu = false;
		if(turmas.contains(t)) {
			removeu = turmas.remove(t);
			numTurmas = turmas.size();
		}
		return removeu;
	}
	
	public boolean atualizarTurma(String codigo, Turma t) {
		for(Turma atualizar : turmas) {
			if(atualizar.getCodigo().equalsIgnoreCase(codigo)) {
				atualizar = t;
				return true;
			}
		}
		return false;
	}
	
	public boolean adicionarProfessor(String matriculaFUB, Turma t) {
		Professor p = cadProfessor.pesquisarProfessor(matriculaFUB);
		if(p != null) {
			t.setProfessor(p);
			return true;
		}
		return false;
	}
	
	public boolean adicionarDisciplina(String codigoDisciplina, Turma t) {
		Disciplina d = cadDisciplina.pesquisarDisciplina(codigoDisciplina);
		if(d != null) {
			t.setDisciplina(d);
			return true;
		}
		return false;
	}
}
