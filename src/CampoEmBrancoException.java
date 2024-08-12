package excecao;

import javax.swing.JOptionPane;

public class CampoEmBrancoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2882194849285807761L;

	public CampoEmBrancoException(String msg) {
		super(msg);
		JOptionPane.showMessageDialog(null, msg, this.getClass().getCanonicalName(), JOptionPane.ERROR_MESSAGE);
	}
	
}
