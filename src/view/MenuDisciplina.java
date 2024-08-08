package view;

import javax.swing.JOptionPane;

import app.Disciplina;
import cadastros.CrudDisciplina;

public class MenuDisciplina {

	public static Disciplina dadosNovaDisciplina() {
		String nome = lerNome();
		String codigo = lerCodigo();
		return new Disciplina(nome, codigo);
	}

	private static String lerCodigo() {
		return JOptionPane.showInputDialog("Informe o codigo da turma: ");
	}

		private static String lerNome() {
		return JOptionPane.showInputDialog("Informe o nome da disciplina: ");
	}
		
	public static void menuDisciplina(CrudDisciplina cadDisciplina) {
		String txt = "Informe a opção desejada \n"
				+ "1 - Cadastrar disciplina\n"
				+ "2 - Pesquisar disciplina\n"
				+ "3 - Atualizar disciplina\n"
				+ "4 - Remover disciplina\n"
				+ "0 - Voltar para menu anterior";
		
		int opcao=-1;
		do {
			String strOpcao = JOptionPane.showInputDialog(txt);
			opcao = Integer.parseInt(strOpcao);

			switch (opcao) {
			case 1:
				Disciplina novaDisciplina = dadosNovaDisciplina();
				cadDisciplina.cadastrarDisciplina(novaDisciplina);
				break;
				
			case 2: 
				String codigo = lerCodigo();
				Disciplina d = cadDisciplina.pesquisarDisciplina(codigo);
				if (d != null) {
					JOptionPane.showMessageDialog(null, d.toString());
				}
				break;
				
			case 3: 
				codigo = lerCodigo();
				Disciplina novoCadastro = dadosNovaDisciplina();
				boolean atualizado = cadDisciplina.atualizarDisciplina(codigo, novoCadastro);
				if (atualizado) {
					JOptionPane.showMessageDialog(null, "Disciplina atualizada.");
				}
				break;
				
			case 4: 
				codigo = lerCodigo();
				Disciplina remover = cadDisciplina.pesquisarDisciplina(codigo);
				boolean removido = cadDisciplina.removerDisciplina(remover);
				if (removido) {
					JOptionPane.showMessageDialog(null, "Disciplina removida do catalogo.");
					System.gc();
				}

			default:
				break;
			}
		} while (opcao != 0);
		return;
	}
}
