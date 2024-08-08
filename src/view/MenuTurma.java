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
		String codigo;
		do {
			codigo = JOptionPane.showInputDialog("Informe o código da turma: ");
		} while (codigo == null || codigo.trim().isEmpty());
		return codigo;
	}

	private static int lerQtdVagas() {
		int qtdVagas = 0;
		do {
			String strNumVagas = JOptionPane.showInputDialog("Informe o número de vagas da turma: ");
			try {
				qtdVagas = Integer.parseInt(strNumVagas);
				if (qtdVagas <= 0) {
					JOptionPane.showMessageDialog(null, "Número de vagas deve ser maior que zero.");
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor, insira um número.");
			}
		} while (qtdVagas <= 0);
		return qtdVagas;
	}

	private static String lerMatriculaProf() {
		String matriculaFUB;
		do {
			matriculaFUB = JOptionPane.showInputDialog("Informe a matrícula FUB do professor: ");
		} while (matriculaFUB == null || matriculaFUB.trim().isEmpty());
		return matriculaFUB;
	}

	private static String lerCodigoDisciplina() {
		String codigoDisciplina;
		do {
			codigoDisciplina = JOptionPane.showInputDialog("Informe o código da disciplina: ");
		} while (codigoDisciplina == null || codigoDisciplina.trim().isEmpty());
		return codigoDisciplina;
	}

	private static String lerMatriculaAluno() {
		String matriculaAluno;
		do {
			matriculaAluno = JOptionPane.showInputDialog("Informe a matrícula do aluno: ");
		} while (matriculaAluno == null || matriculaAluno.trim().isEmpty());
		return matriculaAluno;
	}

	public static void menuTurma(CrudTurma crudTurma) {
		String txt = "Informe a opção desejada \n"
				+ "1 - Criar turma\n"
				+ "2 - Pesquisar Turma\n"
				+ "3 - Atualizar turma\n"
				+ "4 - Associar professor\n"
				+ "5 - Associar disciplina\n"
				+ "6 - Associar aluno\n"
				+ "7 - Remover turma\n"
				+ "0 - Voltar para menu anterior";

		int opcao = -1;
		do {
			String strOpcao = JOptionPane.showInputDialog(txt);
			if (strOpcao != null) {
				try {
					opcao = Integer.parseInt(strOpcao);
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
					continue;
				}

				switch (opcao) {
					case 1:
						Turma novaTurma = dadosNovaTurma();
						crudTurma.cadastrarTurma(novaTurma);
						JOptionPane.showMessageDialog(null, "Turma cadastrada com sucesso!");
						break;

					case 2:
						String codigo = lerCodigoTurma();
						Turma t = crudTurma.pesquisarTurma(codigo);
						if (t != null) {
							JOptionPane.showMessageDialog(null, t.toString());
						} else {
							JOptionPane.showMessageDialog(null, "Turma não encontrada.");
						}
						break;

					case 3:
						codigo = lerCodigoTurma();
						Turma novoCadastro = dadosNovaTurma();
						boolean atualizado = crudTurma.atualizarTurma(codigo, novoCadastro);
						if (atualizado) {
							JOptionPane.showMessageDialog(null, "Turma atualizada.");
						} else {
							JOptionPane.showMessageDialog(null, "Turma não encontrada.");
						}
						break;

					case 4:
						String matFUB = lerMatriculaProf();
						codigo = lerCodigoTurma();
						t = crudTurma.pesquisarTurma(codigo);
						if (t != null) {
							boolean adicionou = crudTurma.adicionarProfessor(matFUB, t);
							if (adicionou) {
								JOptionPane.showMessageDialog(null, "Professor designado.");
							} else {
								JOptionPane.showMessageDialog(null, "Professor não encontrado.");
							}
						} else {
							JOptionPane.showMessageDialog(null, "Turma não encontrada.");
						}
						break;

					case 5:
						String codigoDisciplina = lerCodigoDisciplina();
						codigo = lerCodigoTurma();
						t = crudTurma.pesquisarTurma(codigo);
						if (t != null) {
							boolean adicionouDisciplina = crudTurma.adicionarDisciplina(codigoDisciplina, t);
							if (adicionouDisciplina) {
								JOptionPane.showMessageDialog(null, "Disciplina designada.");
							} else {
								JOptionPane.showMessageDialog(null, "Disciplina não encontrada.");
							}
						} else {
							JOptionPane.showMessageDialog(null, "Turma não encontrada.");
						}
						break;

					case 6:
						String matAluno = lerMatriculaAluno();
						codigo = lerCodigoTurma();
						t = crudTurma.pesquisarTurma(codigo);
						if (t != null) {
							boolean adicionouAluno = crudTurma.adicionarAluno(matAluno, t);
							if (adicionouAluno) {
								JOptionPane.showMessageDialog(null, "Aluno designado.");
							} else {
								JOptionPane.showMessageDialog(null, "Aluno não encontrado ou sem vagas disponíveis.");
							}
						} else {
							JOptionPane.showMessageDialog(null, "Turma não encontrada.");
						}
						break;

					case 7:
						codigo = lerCodigoTurma();
						Turma remover = crudTurma.pesquisarTurma(codigo);
						if (remover != null) {
							boolean removido = crudTurma.removerTurma(remover);
							if (removido) {
								JOptionPane.showMessageDialog(null, "Turma removida do cadastro.");
								System.gc();
							} else {
								JOptionPane.showMessageDialog(null, "Erro ao remover turma.");
							}
						} else {
							JOptionPane.showMessageDialog(null, "Turma não encontrada.");
						}
						break;

					case 0:
						break;

					default:
						JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
						break;
				}
			} else {
				opcao = 0;
			}
		} while (opcao != 0);
	}
}
