package excecao;

import javax.swing.JOptionPane;

public class DisciplinaNaoAtribuidaException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5425457815561952569L;

	public DisciplinaNaoAtribuidaException(String msg) {
		super(msg);
		JOptionPane.showMessageDialog(null, msg, this.getClass().getCanonicalName(), JOptionPane.ERROR_MESSAGE);	
	}
}