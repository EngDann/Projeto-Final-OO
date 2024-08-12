import javax.swing.JOptionPane;
import crud.CrudAlunos;
import crud.CrudProfessores;
import crud.CrudDisciplina;
import crud.CrudTurma;
import excecao.CampoEmBrancoException;
import excecao.DisciplinaNaoAtribuidaException;
import excecao.ProfessorNaoAtribuidoException;
import view.MenuAluno;
import view.MenuDisciplina;
import view.MenuProfessor;
import view.MenuTurma;
import view.MenuPrincipal;

public class Principal {

	static CrudAlunos crudAluno;
	static CrudProfessores crudProfessor;
	static CrudDisciplina crudDisciplina;
	static CrudTurma crudTurma;

	public static void main(String[] args) {
		crudAluno = new CrudAlunos();
		crudProfessor = new CrudProfessores();
		crudDisciplina = new CrudDisciplina();
		crudTurma = new CrudTurma(crudAluno, crudProfessor, crudDisciplina);

		int opcao = 0;

		do {
			opcao = MenuPrincipal.menuOpcoes();
			switch (opcao) {
				case 1:
					try {
						MenuAluno.menuAluno(crudAluno);
					} catch (CampoEmBrancoException e) {
						e.printStackTrace();
					}
					break;
				case 2:
					try {
						MenuProfessor.menuProfessor(crudProfessor);
					} catch (CampoEmBrancoException e) {
						e.printStackTrace();
					}
					break;
				case 3:
					try {
						MenuDisciplina.menuDisciplina(crudDisciplina, crudTurma);
					} catch (CampoEmBrancoException e) {
						e.printStackTrace();
					}
					break;
				case 4:
					try {
						MenuTurma.menuTurma(crudTurma, crudDisciplina, crudProfessor);
					} catch (CampoEmBrancoException e) {
						e.printStackTrace();
					} catch (DisciplinaNaoAtribuidaException e) {
						e.printStackTrace();
					} catch (ProfessorNaoAtribuidoException e) {
						e.printStackTrace();
					}
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
