package view;

import javax.swing.JOptionPane;
import app.Disciplina;
import crud.CrudDisciplina;

public class MenuDisciplina {

	public static Disciplina dadosNovaDisciplina() {
		String nome = lerNome();
		String codigo = lerCodigo();
		return new Disciplina(nome, codigo);
	}

	private static String lerCodigo() {
		String codigo;
		do {
			codigo = JOptionPane.showInputDialog("Informe o codigo da disciplina: ");
		} while (codigo == null || codigo.trim().isEmpty());
		return codigo;
	}

	private static String lerNome() {
		String nome;
		do {
			nome = JOptionPane.showInputDialog("Informe o nome da disciplina: ");
		} while (nome == null || nome.trim().isEmpty());
		return nome;
	}

	public static void menuDisciplina(CrudDisciplina cadDisciplina) {
		String txt = "Informe a opção desejada \n"
				+ "1 - Cadastrar disciplina\n"
				+ "2 - Pesquisar disciplina\n"
				+ "3 - Atualizar disciplina\n"
				+ "4 - Remover disciplina\n"
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
						cadDisciplina.cadastrarDisciplina(novaDisciplina);
						JOptionPane.showMessageDialog(null, "Disciplina cadastrada com sucesso!");
						break;

					case 2:
						String codigo = lerCodigo();
						Disciplina d = cadDisciplina.pesquisarDisciplina(codigo);
						if (d != null) {
							JOptionPane.showMessageDialog(null, d.toString());
						} else {
							JOptionPane.showMessageDialog(null, "Disciplina não encontrada.");
						}
						break;

					case 3:
						codigo = lerCodigo();
						Disciplina novoCadastro = dadosNovaDisciplina();
						boolean atualizado = cadDisciplina.atualizarDisciplina(codigo, novoCadastro);
						if (atualizado) {
							JOptionPane.showMessageDialog(null, "Disciplina atualizada.");
						} else {
							JOptionPane.showMessageDialog(null, "Disciplina não encontrada.");
						}
						break;

					case 4:
						codigo = lerCodigo();
						Disciplina remover = cadDisciplina.pesquisarDisciplina(codigo);
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
