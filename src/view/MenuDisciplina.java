package view;

import javax.swing.*;
import java.awt.*;
import app.Disciplina;
import app.Turma;
import crud.CrudDisciplina;
import crud.CrudTurma;
import excecao.CampoEmBrancoException;

public class MenuDisciplina {

	static CrudTurma cadTurma;

	public static Disciplina dadosNovaDisciplina() throws CampoEmBrancoException {
		JPanel panel = new JPanel(new GridLayout(0, 2));

		panel.add(new JLabel("Informe o nome da disciplina:"));
		JTextField nomeField = new JTextField();
		panel.add(nomeField);

		panel.add(new JLabel("Informe o código da disciplina:"));
		JTextField codigoField = new JTextField();
		panel.add(codigoField);

		int result = JOptionPane.showConfirmDialog(null, panel, "Cadastro de Nova Disciplina",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		if (result == JOptionPane.OK_OPTION) {
			String nome = nomeField.getText().trim();
			String codigo = codigoField.getText().trim();

			if (nome.isEmpty() || codigo.isEmpty()) {
				throw new CampoEmBrancoException("Todos os campos devem ser preenchidos.");
			} else {
				return new Disciplina(nome, codigo);
			}
		} else {
			return null;
		}
	}

	public static void menuDisciplina(CrudDisciplina cadDisciplina, CrudTurma cadTurma) throws CampoEmBrancoException {
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
						try {
							Disciplina novaDisciplina = dadosNovaDisciplina();
							if (novaDisciplina != null) {
								cadDisciplina.cadastrarDisciplina(novaDisciplina);
								JOptionPane.showMessageDialog(null, "Disciplina cadastrada com sucesso!");
							} else {
								JOptionPane.showMessageDialog(null, "Cadastro de disciplina cancelado.");

							}
						} catch (NullPointerException e) {
							JOptionPane.showMessageDialog(null, "Algo deu errado no cadastro.", null,
									JOptionPane.ERROR_MESSAGE);
							e.printStackTrace();
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
							throw new CampoEmBrancoException("Codigo da disciplina nao pode ser vazio.");
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
							throw new CampoEmBrancoException("Codigo da disciplina nao pode ser vazio.");
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
							throw new CampoEmBrancoException("Codigo da disciplina nao pode ser vazio.");
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
							throw new CampoEmBrancoException("Codigo da disciplina nao pode ser vazio.");
						}
						break;

					case 6:
						codigo = JOptionPane.showInputDialog("Informe o código da disciplina:");
						if (codigo != null && !codigo.trim().isEmpty()) {
							Disciplina disciplina = cadDisciplina.pesquisarDisciplina(codigo.trim());
							if (disciplina != null) {
								String codigoTurma = JOptionPane.showInputDialog("Informe o código da turma: ");
								if (codigoTurma != null) {
									Turma t = cadTurma.pesquisarTurma(codigoTurma.trim());
									boolean adicionou = cadDisciplina.adicionarTurma(codigo, t);
									if (adicionou) {
										JOptionPane.showMessageDialog(null, "Turma adicionada.");
									} else {
										JOptionPane.showMessageDialog(null, "Erro ao adicionar turma.");
									}
								} else {
									throw new CampoEmBrancoException("Codigo da turma nao pode ser vazio.");
								}
							} else {
								JOptionPane.showMessageDialog(null, "Disciplina não encontrada.");
							}
						} else {
							throw new CampoEmBrancoException("Codigo da disciplina nao pode ser vazio.");
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
