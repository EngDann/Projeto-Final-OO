package view;

import javax.swing.*;
import java.awt.*;
import app.Disciplina;
import app.Turma;
import crud.CrudDisciplina;
import crud.CrudTurma;

public class MenuDisciplina {

	public static Disciplina dadosNovaDisciplina() {
		JPanel panel = new JPanel(new GridLayout(0, 2));

		panel.add(new JLabel("Informe o nome da disciplina:"));
		JTextField nomeField = new JTextField();
		panel.add(nomeField);

		panel.add(new JLabel("Informe o código da disciplina:"));
		JTextField codigoField = new JTextField();
		panel.add(codigoField);

		int result;
		do {
			result = JOptionPane.showConfirmDialog(null, panel, "Cadastro de Nova Disciplina",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

			if (result == JOptionPane.OK_OPTION) {
				String nome = nomeField.getText().trim();
				String codigo = codigoField.getText().trim();

				if (nome.isEmpty() || codigo.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos.", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} else {
					return new Disciplina(nome, codigo);
				}
			} else {
				return null;
			}
		} while (result == JOptionPane.OK_OPTION);

		return null;
	}

	private static Turma dadosNovaTurma() {
		JPanel panel = new JPanel(new GridLayout(0, 2));

		panel.add(new JLabel("Informe o código da turma:"));
		JTextField codigoField = new JTextField();
		panel.add(codigoField);

		panel.add(new JLabel("Informe o número de vagas da turma:"));
		JTextField qtdVagasField = new JTextField();
		panel.add(qtdVagasField);

		int result;
		do {
			result = JOptionPane.showConfirmDialog(null, panel, "Cadastro de Nova Turma", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE);

			if (result == JOptionPane.OK_OPTION) {
				String codigo = codigoField.getText().trim();
				int qtdVagas;
				try {
					qtdVagas = Integer.parseInt(qtdVagasField.getText().trim());
					if (codigo.isEmpty() || qtdVagas <= 0) {
						JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos corretamente.",
								"Erro", JOptionPane.ERROR_MESSAGE);
					} else {
						return new Turma(codigo, qtdVagas);
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Número de vagas deve ser um número válido.", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				return null;
			}
		} while (result == JOptionPane.OK_OPTION);

		return null;
	}

	public static void menuDisciplina(CrudDisciplina cadDisciplina, CrudTurma cadTurma) {
		String txt = "Informe a opção desejada \n"
				+ "1 - Cadastrar disciplina\n"
				+ "2 - Pesquisar disciplina\n"
				+ "3 - Atualizar disciplina\n"
				+ "4 - Remover disciplina\n"
				+ "5 - Listar turmas da disciplina\n"
				+ "6 - Adicionar turma à disciplina\n"
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
						Disciplina novaDisciplina = dadosNovaDisciplina();
						if (novaDisciplina != null) {
							cadDisciplina.cadastrarDisciplina(novaDisciplina);
							JOptionPane.showMessageDialog(null, "Disciplina cadastrada com sucesso!");
						}
						break;

					case 2:
						String codigo = JOptionPane.showInputDialog("Informe o código da disciplina:");
						if (codigo != null && !codigo.trim().isEmpty()) {
							Disciplina d = cadDisciplina.pesquisarDisciplina(codigo.trim());
							if (d != null) {
								JOptionPane.showMessageDialog(null, d.toString());
							} else {
								JOptionPane.showMessageDialog(null, "Disciplina não encontrada.");
							}
						} else {
							JOptionPane.showMessageDialog(null, "Código da disciplina não pode ser vazio.");
						}
						break;

					case 3:
						codigo = JOptionPane.showInputDialog("Informe o código da disciplina:");
						if (codigo != null && !codigo.trim().isEmpty()) {
							Disciplina novoCadastro = dadosNovaDisciplina();
							if (novoCadastro != null) {
								boolean atualizado = cadDisciplina.atualizarDisciplina(codigo.trim(), novoCadastro);
								if (atualizado) {
									JOptionPane.showMessageDialog(null, "Disciplina atualizada.");
								} else {
									JOptionPane.showMessageDialog(null, "Disciplina não encontrada.");
								}
							}
						} else {
							JOptionPane.showMessageDialog(null, "Código da disciplina não pode ser vazio.");
						}
						break;

					case 4:
						codigo = JOptionPane.showInputDialog("Informe o código da disciplina:");
						if (codigo != null && !codigo.trim().isEmpty()) {
							Disciplina remover = cadDisciplina.pesquisarDisciplina(codigo.trim());
							if (remover != null) {
								boolean removido = cadDisciplina.removerDisciplina(remover);
								if (removido) {
									JOptionPane.showMessageDialog(null, "Disciplina removida do catálogo.");
								} else {
									JOptionPane.showMessageDialog(null, "Erro ao remover disciplina.");
								}
							} else {
								JOptionPane.showMessageDialog(null, "Disciplina não encontrada.");
							}
						} else {
							JOptionPane.showMessageDialog(null, "Código da disciplina não pode ser vazio.");
						}
						break;

					case 5:
						codigo = JOptionPane.showInputDialog("Informe o código da disciplina:");
						if (codigo != null && !codigo.trim().isEmpty()) {
							Disciplina disciplina = cadDisciplina.pesquisarDisciplina(codigo.trim());
							if (disciplina != null) {
								StringBuilder turmasStr = new StringBuilder();
								for (Turma turma : disciplina.getTurmas()) {
									turmasStr.append(turma.toString()).append("\n");
								}
								JOptionPane.showMessageDialog(null, turmasStr.toString());
							} else {
								JOptionPane.showMessageDialog(null, "Disciplina não encontrada.");
							}
						} else {
							JOptionPane.showMessageDialog(null, "Código da disciplina não pode ser vazio.");
						}
						break;

					case 6:
						codigo = JOptionPane.showInputDialog("Informe o código da disciplina:");
						if (codigo != null && !codigo.trim().isEmpty()) {
							Disciplina disciplina = cadDisciplina.pesquisarDisciplina(codigo.trim());
							if (disciplina != null) {
								Turma novaTurma = dadosNovaTurma();
								if (novaTurma != null) {
									cadTurma.cadastrarTurma(novaTurma); // Primeiro, adiciona à lista geral de turmas
									boolean adicionou = cadDisciplina.adicionarTurma(codigo.trim(), novaTurma);
									if (adicionou) {
										JOptionPane.showMessageDialog(null, "Turma adicionada à disciplina.");
									} else {
										JOptionPane.showMessageDialog(null, "Erro ao adicionar turma à disciplina.");
									}
								}
							} else {
								JOptionPane.showMessageDialog(null, "Disciplina não encontrada.");
							}
						} else {
							JOptionPane.showMessageDialog(null, "Código da disciplina não pode ser vazio.");
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
