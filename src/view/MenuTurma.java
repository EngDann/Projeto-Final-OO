package view;

import javax.swing.*;
import java.awt.*;
import app.Turma;
import crud.CrudTurma;
import excecao.CampoEmBrancoException;
import excecao.ProfessorNaoAtribuidoException;
import excecao.DisciplinaNaoAtribuidaException;

public class MenuTurma {

	public static Turma dadosNovaTurma() throws CampoEmBrancoException,
			ProfessorNaoAtribuidoException,
			DisciplinaNaoAtribuidaException {
		JPanel panel = new JPanel(new GridLayout(0, 2));

		panel.add(new JLabel("Informe o código da turma:"));
		JTextField codigoField = new JTextField();
		panel.add(codigoField);

		panel.add(new JLabel("Informe o codigo da disciplina associada a essa turma:"));
		JTextField discField = new JTextField();
		panel.add(discField);

		panel.add(new JLabel("Informe a matricula FUB do professor associado a turma:"));
		JTextField profField = new JTextField();
		panel.add(profField);

		panel.add(new JLabel("Informe o número de vagas da turma:"));
		JTextField qtdVagasField = new JTextField();
		panel.add(qtdVagasField);

		int result = JOptionPane.showConfirmDialog(null, panel, "Cadastro de Nova Turma", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		if (result == JOptionPane.OK_OPTION) {
			String codigo = codigoField.getText().trim();
			String codigoDisciplina = discField.getText().trim();
			String matriculaProfessor = profField.getText().trim();
			int qtdVagas = Integer.parseInt(qtdVagasField.getText().trim());

			if (codigo.isEmpty() || qtdVagas <= 0) {
				throw new CampoEmBrancoException("Todos os campos devem ser preenchidos corretamente.");
			}
			if (codigoDisciplina.isEmpty()) {
				throw new DisciplinaNaoAtribuidaException(
						"É obrigatorio uma disciplina para completar o cadastro.");
			}
			if (matriculaProfessor.isEmpty()) {
				throw new ProfessorNaoAtribuidoException(
						"É obrigatorio um professor associado para completar o cadastro.");
			} else {
				return new Turma(codigo, codigoDisciplina, matriculaProfessor, qtdVagas);
			}
		} else {
			return null;
		}

	}

	public static void menuTurma(CrudTurma crudTurma) throws CampoEmBrancoException,
			ProfessorNaoAtribuidoException,
			DisciplinaNaoAtribuidaException {
		String txt = "Informe a opção desejada \n"
				+ "1 - Criar turma\n"
				+ "2 - Pesquisar Turma\n"
				+ "3 - Atualizar turma\n"
				+ "4 - Associar professor\n"
				+ "5 - Adicionar aluno\n"
				+ "6 - Solicitar lista de chamada\n"
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
						try {
							Turma novaTurma = dadosNovaTurma();
							if (novaTurma != null) {
								crudTurma.cadastrarTurma(novaTurma);
								JOptionPane.showMessageDialog(null, "Turma cadastrada com sucesso!");
							} else {
								JOptionPane.showMessageDialog(null, "Cadastro de turma cancelado.");
							}
						} catch (NullPointerException e) {
							JOptionPane.showMessageDialog(null, "Algo deu errado no cadastro.", null,
									JOptionPane.ERROR_MESSAGE);
							e.printStackTrace();
						}
						break;

					case 2:
						String codigo = JOptionPane.showInputDialog("Informe o código da turma:");
						if (codigo != null && !codigo.trim().isEmpty()) {
							Turma t = crudTurma.pesquisarTurma(codigo.trim());
							if (t != null) {
								JOptionPane.showMessageDialog(null, t.toString());
							} else {
								JOptionPane.showMessageDialog(null, "Turma não encontrada.");
							}
						} else {
							throw new CampoEmBrancoException("Codigo da turma nao pode ser vazio.");
						}
						break;

					case 3:
						codigo = JOptionPane.showInputDialog("Informe o código da turma:");
						if (codigo != null && !codigo.trim().isEmpty()) {
							Turma novoCadastro = dadosNovaTurma();
							if (novoCadastro != null) {
								boolean atualizado = crudTurma.atualizarTurma(codigo.trim(), novoCadastro);
								if (atualizado) {
									JOptionPane.showMessageDialog(null, "Turma atualizada.");
								} else {
									JOptionPane.showMessageDialog(null, "Turma não encontrada.");
								}
							}
						} else {
							throw new CampoEmBrancoException("Codigo da turma nao pode ser vazio.");
						}
						break;

					case 4:
						String matFUB = JOptionPane.showInputDialog("Informe a matrícula FUB do professor:");
						codigo = JOptionPane.showInputDialog("Informe o código da turma:");
						if (matFUB != null && !matFUB.trim().isEmpty() && codigo != null && !codigo.trim().isEmpty()) {
							Turma t = crudTurma.pesquisarTurma(codigo.trim());
							if (t != null) {
								boolean adicionou = crudTurma.adicionarProfessor(matFUB.trim(), t);
								if (adicionou) {
									JOptionPane.showMessageDialog(null, "Professor designado.");
								} else {
									JOptionPane.showMessageDialog(null, "Professor não encontrado.");
								}
							} else {
								JOptionPane.showMessageDialog(null, "Turma não encontrada.");
							}
						} else {
							throw new CampoEmBrancoException("Matricula FUB e codigo da turma nao pode ser vazio.");
						}
						break;

					case 5:
						String matAluno = JOptionPane.showInputDialog("Informe a matrícula do aluno:");
						codigo = JOptionPane.showInputDialog("Informe o código da turma:");
						if (matAluno != null && !matAluno.trim().isEmpty() && codigo != null
								&& !codigo.trim().isEmpty()) {
							Turma t = crudTurma.pesquisarTurma(codigo.trim());
							if (t != null) {
								boolean adicionouAluno = crudTurma.adicionarAluno(matAluno.trim(), t);
								if (adicionouAluno) {
									JOptionPane.showMessageDialog(null, "Aluno designado.");
								} else {
									JOptionPane.showMessageDialog(null,
											"Aluno não encontrado ou sem vagas disponíveis.");
								}
							} else {
								JOptionPane.showMessageDialog(null, "Turma não encontrada.");
							}
						} else {
							throw new CampoEmBrancoException(
									"Matricula do aluno e codigo da turma nao podem ser vazios.");
						}
						break;

					case 6:
						String codigoTurma = JOptionPane.showInputDialog("Informe o codigo da turma:");
						if (codigoTurma != null) {
							crudTurma.listaDePresenca(codigoTurma);
						} else {
							throw new CampoEmBrancoException("Codigo da turma nao pode ser vazio.");
						}
						break;

					case 7:
						codigo = JOptionPane.showInputDialog("Informe o código da turma:");
						if (codigo != null && !codigo.trim().isEmpty()) {
							Turma remover = crudTurma.pesquisarTurma(codigo.trim());
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
						} else {
							throw new CampoEmBrancoException("Codigo da turma nao pode ser vazio.");
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
