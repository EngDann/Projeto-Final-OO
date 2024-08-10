package view;

import javax.swing.*;
import java.awt.*;
import app.Aluno;
import crud.CrudAlunos;

public class MenuAluno {

	public static Aluno dadosNovoAluno() throws CampoEmBrancoException {
		JPanel panel = new JPanel(new GridLayout(0, 2));

		panel.add(new JLabel("Informe o nome do aluno:"));
		JTextField nomeField = new JTextField();
		panel.add(nomeField);

		panel.add(new JLabel("Informe o CPF do aluno:"));
		JTextField cpfField = new JTextField();
		panel.add(cpfField);

		panel.add(new JLabel("Informe o email do aluno:"));
		JTextField emailField = new JTextField();
		panel.add(emailField);

		panel.add(new JLabel("Informe a matricula do aluno:"));
		JTextField matriculaField = new JTextField();
		panel.add(matriculaField);

		panel.add(new JLabel("Informe o curso do aluno:"));
		JTextField cursoField = new JTextField();
		panel.add(cursoField);

		int result;
		do {
			result = JOptionPane.showConfirmDialog(null, panel, "Cadastro de Novo Aluno", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE);

			if (result == JOptionPane.OK_OPTION) {
				String nome = nomeField.getText().trim();
				String cpf = cpfField.getText().trim();
				String email = emailField.getText().trim();
				String matricula = matriculaField.getText().trim();
				String curso = cursoField.getText().trim();

				if (nome.isEmpty() || cpf.isEmpty() || email.isEmpty() || matricula.isEmpty() || curso.isEmpty()) {
					throw new CampoEmBrancoException("Todos os campos devem ser preenchidos.");
				} else {
					return new Aluno(nome, cpf, email, matricula, curso);
				}
			} else {
				return null;
			}
		} while (result == JOptionPane.OK_OPTION);
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
						try{
							Aluno novoAluno = dadosNovoAluno();
							cadAluno.cadastrarAluno(novoAluno);
							JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso!");
						} catch(CampoEmBrancoException e){
							continue;
						}
						break;

					case 2:
						String matricula = JOptionPane.showInputDialog("Informe a matricula do aluno:");
						if (matricula != null && !matricula.trim().isEmpty()) {
							Aluno a = cadAluno.pesquisarAluno(matricula.trim());
							if (a != null) {
								JOptionPane.showMessageDialog(null, a.toString());
							} else {
								JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
							}
						} else {
							JOptionPane.showMessageDialog(null, "Matricula não pode ser vazia.");
						}
						break;

					case 3:
						try{
							matricula = JOptionPane.showInputDialog("Informe a matricula do aluno:");
						
						if (matricula != null && !matricula.trim().isEmpty()) {
							Aluno novoCadastro = dadosNovoAluno();
							if (novoCadastro != null) {
								boolean atualizado = cadAluno.atualizarAluno(matricula.trim(), novoCadastro);
								if (atualizado) {
									JOptionPane.showMessageDialog(null, "Cadastro atualizado.");
								} else {
									JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
								}
							}
						} else {
							JOptionPane.showMessageDialog(null, "Matricula não pode ser vazia.");
						}
						} catch(CampoEmBrancoException e) {
						continue;	
						}
						break;

					case 4:
						matricula = JOptionPane.showInputDialog("Informe a matricula do aluno:");
						if (matricula != null && !matricula.trim().isEmpty()) {
							Aluno remover = cadAluno.pesquisarAluno(matricula.trim());
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
						} else {
							JOptionPane.showMessageDialog(null, "Matricula não pode ser vazia.");
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
