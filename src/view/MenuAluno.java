package view;

import javax.swing.JOptionPane;
import app.Aluno;
import crud.CrudAlunos;

public class MenuAluno {

	public static Aluno dadosNovoAluno() {
		String nome = lerNome();
		String cpf = lerCPF();
		String email = lerEmail();
		String matricula = lerMatricula();
		String curso = lerCurso();
		return new Aluno(nome, cpf, email, matricula, curso);
	}

	private static String lerCurso() {
		String curso;
		do {
			curso = JOptionPane.showInputDialog("Informe o curso do aluno: ");
		} while (curso == null || curso.trim().isEmpty());
		return curso;
	}

	private static String lerEmail() {
		String email;
		do {
			email = JOptionPane.showInputDialog("Informe o email do aluno: ");
		} while (email == null || email.trim().isEmpty());
		return email;
	}

	private static String lerCPF() {
		String cpf;
		do {
			cpf = JOptionPane.showInputDialog("Informe o CPF do aluno: ");
		} while (cpf == null || cpf.trim().isEmpty());
		return cpf;
	}

	private static String lerNome() {
		String nome;
		do {
			nome = JOptionPane.showInputDialog("Informe o nome do aluno: ");
		} while (nome == null || nome.trim().isEmpty());
		return nome;
	}

	private static String lerMatricula() {
		String matricula;
		do {
			matricula = JOptionPane.showInputDialog("Informe a matricula do aluno: ");
		} while (matricula == null || matricula.trim().isEmpty());
		return matricula;
	}

	public static void menuAluno(CrudAlunos cadAluno) {
		String txt = "Informe a opção desejada \n"
				+ "1 - Cadastrar aluno\n"
				+ "2 - Pesquisar aluno\n"
				+ "3 - Atualizar aluno\n"
				+ "4 - Remover aluno\n"
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
						Aluno novoAluno = dadosNovoAluno();
						cadAluno.cadastrarAluno(novoAluno);
						JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso!");
						break;

					case 2:
						String matricula = lerMatricula();
						Aluno a = cadAluno.pesquisarAluno(matricula);
						if (a != null) {
							JOptionPane.showMessageDialog(null, a.toString());
						} else {
							JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
						}
						break;

					case 3:
						matricula = lerMatricula();
						Aluno novoCadastro = dadosNovoAluno();
						boolean atualizado = cadAluno.atualizarAluno(matricula, novoCadastro);
						if (atualizado) {
							JOptionPane.showMessageDialog(null, "Cadastro atualizado.");
						} else {
							JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
						}
						break;

					case 4:
						matricula = lerMatricula();
						Aluno remover = cadAluno.pesquisarAluno(matricula);
						if (remover != null) {
							boolean removido = cadAluno.removerAluno(remover);
							if (removido) {
								JOptionPane.showMessageDialog(null, "Aluno removido do cadastro.");
								System.gc();
							} else {
								JOptionPane.showMessageDialog(null, "Erro ao remover aluno.");
							}
						} else {
							JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
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
