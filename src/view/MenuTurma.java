package view;

import javax.swing.JOptionPane;

import app.Turma;
import crud.CrudTurma;

public class MenuTurma {

	public static Turma dadosNovaTurma() {
		String codigo = lerCodigoTurma();
		int qtdVagas = lerQtdVagas();
		return new Turma(codigo, qtdVagas);
	}

	private static String lerCodigoTurma() {
		return JOptionPane.showInputDialog("Informe o codigo da turma: ");
	}

	private static int lerQtdVagas() {
		String strNumVagas = JOptionPane.showInputDialog("Informe o numero de vagas da turma: ");
		return Integer.parseInt(strNumVagas);
	}

	private static String lerMatriculaProf() {
		return JOptionPane.showInputDialog("Informe a matricula FUB do professor: ");
	}

	private static String lerCodigoDisciplina() {
		return JOptionPane.showInputDialog("Informe o codigo da disciplina: ");
	}

	public static void menuTurma(CrudTurma cadTurma) {
		String txt = "Informe a opção desejada \n"
				+ "1 - Criar turma\n"
				+ "2 - Pesquisar Turma\n"
				+ "3 - Atualizar turma\n"
				+ "4 - Associar professor\n"
				+ "5 - Associar disciplina\n"
				+ "6 - Remover turma\n"
				+ "0 - Voltar para menu anterior";

		int opcao = -1;
		do {
			String strOpcao = JOptionPane.showInputDialog(txt);
			opcao = Integer.parseInt(strOpcao);

			switch (opcao) {
				case 1:
					Turma novaTurma = dadosNovaTurma();
					cadTurma.cadastrarTurma(novaTurma);
					break;

				case 2:
					String codigo = lerCodigoTurma();
					Turma t = cadTurma.pesquisarTurma(codigo);
					if (t != null)
						JOptionPane.showMessageDialog(null, t.toString());
					break;

				case 3:
					codigo = lerCodigoTurma();
					Turma novoCadastro = dadosNovaTurma();
					boolean atualizado = cadTurma.atualizarTurma(codigo, novoCadastro);
					if (atualizado) {
						JOptionPane.showMessageDialog(null, "Turma atualizada.");
					}
					break;

				case 4:
					String matFUB = lerMatriculaProf();
					codigo = lerCodigoTurma();
					t = cadTurma.pesquisarTurma(codigo);
					boolean adicionou = cadTurma.adicionarProfessor(matFUB, t);
					if (adicionou) {
						JOptionPane.showMessageDialog(null, "Professor designado.");
					}
					break;

				case 5:
					String codigoDisciplina = lerCodigoDisciplina();
					codigo = lerCodigoTurma();
					t = cadTurma.pesquisarTurma(codigo);
					adicionou = cadTurma.adicionarDisciplina(codigoDisciplina, t);
					if (adicionou) {
						JOptionPane.showMessageDialog(null, "Disciplina designada.");
					}
				case 6:
					codigo = lerCodigoTurma();
					Turma remover = cadTurma.pesquisarTurma(codigo);
					boolean removido = cadTurma.removerTurma(remover);
					if (removido) {
						JOptionPane.showMessageDialog(null, "Turma removida do cadastro");
						System.gc();
					}

				default:
					break;
			}
		} while (opcao != 0);
		return;
	}
}
