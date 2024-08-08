import javax.swing.JOptionPane;

import crud.CrudAlunos;
import crud.CrudProfessores;
import crud.CrudDisciplina;
import crud.CrudTurma;
import view.MenuAluno;
import view.MenuDisciplina;
import view.MenuProfessor;
import view.MenuTurma;
import view.MenuPrincipal;
import view.MenuProfessor;

public class Principal {

	static CrudAlunos crudAluno;
	static CrudProfessores crudProfessor;
	static CrudTurma crudTurma;
	static CrudDisciplina crudDisciplina;

	public static void main(String[] args) {
		crudAluno = new CrudAlunos();
		crudProfessor = new CrudProfessores();
		crudDisciplina = new CrudDisciplina();
		crudTurma = new CrudTurma();

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
					MenuDisciplina.menuDisciplina(crudDisciplina);
					break;
				case 4:
					MenuTurma.menuTurma(crudTurma);
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
