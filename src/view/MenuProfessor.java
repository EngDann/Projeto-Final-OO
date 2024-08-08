package view;

import javax.swing.JOptionPane;
import app.Professor;
import crud.CrudProfessores;

public class MenuProfessor {

	public static Professor dadosNovoProfessor() {
		String nome = lerNome();
		String cpf = lerCPF();
		String email = lerEmail();
		String matriculaFUB = lerMatriculaFUB();
		String areaFormacao = lerAreaFormacao();
		return new Professor(nome, cpf, email, matriculaFUB, areaFormacao);
	}

	private static String lerAreaFormacao() {
		String areaFormacao;
		do {
			areaFormacao = JOptionPane.showInputDialog("Informe a área de formação do professor: ");
		} while (areaFormacao == null || areaFormacao.trim().isEmpty());
		return areaFormacao;
	}

	private static String lerEmail() {
		String email;
		do {
			email = JOptionPane.showInputDialog("Informe o email do professor: ");
		} while (email == null || email.trim().isEmpty());
		return email;
	}

	private static String lerCPF() {
		String cpf;
		do {
			cpf = JOptionPane.showInputDialog("Informe o CPF do professor: ");
		} while (cpf == null || cpf.trim().isEmpty());
		return cpf;
	}

	private static String lerNome() {
		String nome;
		do {
			nome = JOptionPane.showInputDialog("Informe o nome do professor: ");
		} while (nome == null || nome.trim().isEmpty());
		return nome;
	}

	private static String lerMatriculaFUB() {
		String matriculaFUB;
		do {
			matriculaFUB = JOptionPane.showInputDialog("Informe a matrícula FUB do professor: ");
		} while (matriculaFUB == null || matriculaFUB.trim().isEmpty());
		return matriculaFUB;
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
						crudProfessor.cadastrarProfessor(novoProfessor);
						JOptionPane.showMessageDialog(null, "Professor cadastrado com sucesso!");
						break;

					case 2:
						String matriculaFUB = lerMatriculaFUB();
						Professor p = crudProfessor.pesquisarProfessor(matriculaFUB);
						if (p != null) {
							JOptionPane.showMessageDialog(null, p.toString());
						} else {
							JOptionPane.showMessageDialog(null, "Professor não encontrado.");
						}
						break;

					case 3:
						matriculaFUB = lerMatriculaFUB();
						Professor novoCadastro = dadosNovoProfessor();
						boolean atualizado = crudProfessor.atualizarProfessor(matriculaFUB, novoCadastro);
						if (atualizado) {
							JOptionPane.showMessageDialog(null, "Cadastro atualizado.");
						} else {
							JOptionPane.showMessageDialog(null, "Professor não encontrado.");
						}
						break;

					case 4:
						matriculaFUB = lerMatriculaFUB();
						Professor remover = crudProfessor.pesquisarProfessor(matriculaFUB);
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
