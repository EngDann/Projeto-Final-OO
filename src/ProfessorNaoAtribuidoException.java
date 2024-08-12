package excecao;

import javax.swing.JOptionPane;

public class ProfessorNaoAtribuidoException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7058407270076507777L;

	public ProfessorNaoAtribuidoException(String msg) {
		super(msg);
		JOptionPane.showMessageDialog(null, msg, this.getClass().getCanonicalName(), JOptionPane.ERROR_MESSAGE);
	}
}
