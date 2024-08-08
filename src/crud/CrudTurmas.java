package crud;

import java.util.List;
import java.util.LinkedList;

import app.Disciplina;
//FALTA SISTEMA DE CHAMADAS
public class CrudDisciplina {
	int numDisc;
	private List<Disciplina> disciplinas;
	
	public CrudDisciplina(){
		numDisc = 0;
		disciplinas = new LinkedList<Disciplina>();
	}
	
	public int cadastrarDisciplina(Disciplina d) {
		boolean cadastrou = disciplinas.add(d);
		if(cadastrou) {
			numDisc = disciplinas.size();
		}
			return numDisc;
	}
	
	public Disciplina pesquisarDisciplina(String codigo) {
		Disciplina pesquisar = null;
		 for(Disciplina d : disciplinas) {
			 if (d.getCodigo().equalsIgnoreCase(codigo)) {
				 return pesquisar;
			 }
		 }
		 return pesquisar;
	}
	
	public boolean removerDisciplina(Disciplina d) {
		boolean removeu = false;
		if(disciplinas.contains(d)) {
			removeu = disciplinas.remove(d);
			numDisc = disciplinas.size();
		}
		return removeu;
	}
	
	public boolean atualizarDisciplina(String codigo, Disciplina d) {
		for(Disciplina atualizar : disciplinas) {
			if(atualizar.getCodigo().equalsIgnoreCase(codigo)) {
				atualizar = d;
				return true;
			}
		}
		return false;
	}
}
