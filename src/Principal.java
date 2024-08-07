import javax.swing.JOptionPane;

import cadastros.CadastroAlunos;
import view.MenuAluno;
import view.MenuPrincipal;

public class Principal {

	static CrudAlunos cadAluno;

	public static void main(String[] args) {
		cadAluno = new CrudAlunos();
		cadProfessor - new CrudProfessor();
		
		int opcao = 0;

		do {
			opcao = MenuPrincipal.menuOpcoes();
			switch (opcao) {
				case 1:
					MenuAluno.menuAluno(cadAluno);
					break;
				case 2:
					MenuProfessor.menuProfessor(cadProfessor);
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
