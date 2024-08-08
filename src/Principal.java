import javax.swing.JOptionPane;

import crud.CrudAlunos;
import crud.CrudProfessores;
import view.MenuAluno;
import view.MenuPrincipal;
import view.MenuProfessor;

public class Principal {

	static CrudAlunos crudAluno;
	static CrudProfessores crudProfessor;

	public static void main(String[] args) {
		crudAluno = new CrudAlunos();
		crudProfessor = new CrudProfessores();

		int opcao = 0;

		do {
			opcao = MenuPrincipal.menuOpcoes();
			switch (opcao) {
				case 1:
					MenuAluno.menuAluno(crudAluno);
					break;
				case 2:
					MenuProfessor.menuProfessor(crudProfessor);
					break;
				case 3:
					JOptionPane.showMessageDialog(null, "Cadastro de disciplinas a ser implementado");
					break;
				case 4:
					JOptionPane.showMessageDialog(null, "Cadastro de turmas a ser implementado");
					break;
				case 0:
					break;
				default:
					JOptionPane.showMessageDialog(null, "Opcao invalida");
					opcao = -1;
					break;
			}
		} while (opcao != 0);
		return;
	}

}
