package view;

import javax.swing.*;
import java.awt.*;
import app.Professor;
import crud.CrudProfessores;

public class MenuProfessor {

	public static Professor dadosNovoProfessor() {
		JPanel panel = new JPanel(new GridLayout(0, 2));

		panel.add(new JLabel("Informe o nome do professor:"));
		JTextField nomeField = new JTextField();
		panel.add(nomeField);

		panel.add(new JLabel("Informe o CPF do professor:"));
		JTextField cpfField = new JTextField();
		panel.add(cpfField);

		panel.add(new JLabel("Informe o email do professor:"));
		JTextField emailField = new JTextField();
		panel.add(emailField);

		panel.add(new JLabel("Informe a matrícula FUB do professor:"));
		JTextField matriculaFUBField = new JTextField();
		panel.add(matriculaFUBField);

		panel.add(new JLabel("Informe a área de formação do professor:"));
		JTextField areaFormacaoField = new JTextField();
		panel.add(areaFormacaoField);

		int result;
		do {
			result = JOptionPane.showConfirmDialog(null, panel, "Cadastro de Novo Professor",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

			if (result == JOptionPane.OK_OPTION) {
				String nome = nomeField.getText().trim();
				String cpf = cpfField.getText().trim();
				String email = emailField.getText().trim();
				String matriculaFUB = matriculaFUBField.getText().trim();
				String areaFormacao = areaFormacaoField.getText().trim();

				if (nome.isEmpty() || cpf.isEmpty() || email.isEmpty() || matriculaFUB.isEmpty()
						|| areaFormacao.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos.", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} else {
					return new Professor(nome, cpf, email, matriculaFUB, areaFormacao);
				}
			} else {
				return null;
			}
		} while (result == JOptionPane.OK_OPTION);

		return null;
	}

	public static void menuProfessor(CrudProfessores crudProfessor) {
		String txt = "Informe a opção desejada \n"
				+ "1 - Cadastrar professor\n"
				+ "2 - Pesquisar professor\n"
				+ "3 - Atualizar professor\n"
				+ "4 - Remover professor\n"
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
						Professor novoProfessor = dadosNovoProfessor();
						if (novoProfessor != null) {
							crudProfessor.cadastrarProfessor(novoProfessor);
							JOptionPane.showMessageDialog(null, "Professor cadastrado com sucesso!");
						}
						break;

					case 2:
						String matriculaFUB = JOptionPane.showInputDialog("Informe a matrícula FUB do professor:");
						if (matriculaFUB != null && !matriculaFUB.trim().isEmpty()) {
							Professor p = crudProfessor.pesquisarProfessor(matriculaFUB.trim());
							if (p != null) {
								JOptionPane.showMessageDialog(null, p.toString());
							} else {
								JOptionPane.showMessageDialog(null, "Professor não encontrado.");
							}
						} else {
							JOptionPane.showMessageDialog(null, "Matrícula FUB não pode ser vazia.");
						}
						break;

					case 3:
						matriculaFUB = JOptionPane.showInputDialog("Informe a matrícula FUB do professor:");
						if (matriculaFUB != null && !matriculaFUB.trim().isEmpty()) {
							Professor novoCadastro = dadosNovoProfessor();
							if (novoCadastro != null) {
								boolean atualizado = crudProfessor.atualizarProfessor(matriculaFUB.trim(),
										novoCadastro);
								if (atualizado) {
									JOptionPane.showMessageDialog(null, "Cadastro atualizado.");
								} else {
									JOptionPane.showMessageDialog(null, "Professor não encontrado.");
								}
							}
						} else {
							JOptionPane.showMessageDialog(null, "Matrícula FUB não pode ser vazia.");
						}
						break;

					case 4:
						matriculaFUB = JOptionPane.showInputDialog("Informe a matrícula FUB do professor:");
						if (matriculaFUB != null && !matriculaFUB.trim().isEmpty()) {
							Professor remover = crudProfessor.pesquisarProfessor(matriculaFUB.trim());
							if (remover != null) {
								boolean removido = crudProfessor.removerProfessor(remover);
								if (removido) {
									JOptionPane.showMessageDialog(null, "Professor removido do cadastro.");
									System.gc();
								} else {
									JOptionPane.showMessageDialog(null, "Erro ao remover professor.");
								}
							} else {
								JOptionPane.showMessageDialog(null, "Professor não encontrado.");
							}
						} else {
							JOptionPane.showMessageDialog(null, "Matrícula FUB não pode ser vazia.");
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
