import javax.swing.JOptionPane;

import cadastros.CadastroAluno;
import cadastros.CadastroProfessor;
import cadastros.CrudDisciplina;
import cadastros.CrudTurma;
import view.MenuAluno;
import view.MenuDisciplina;
import view.MenuProfessor;
import view.MenuTurma;
import view.MenuPrincipal;

public class Principal {

	static CadastroAluno cadAluno;
	static CadastroProfessor cadProfessor;
	static CrudTurma cadTurma;
	static CrudDisciplina cadDisciplina;
	
	public static void main(String[] args) {
		cadAluno = new CadastroAluno();
		cadProfessor = new CadastroProfessor();
		cadDisciplina = new CrudDisciplina();
		cadTurma = new CrudTurma();
		
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
					MenuDisciplina.menuDisciplina(cadDisciplina);
				break;
				case 4: 
					MenuTurma.menuTurma(cadTurma);
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
