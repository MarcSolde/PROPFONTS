package presentacio;

import domini.controladores.CtrlPresentacio;

/**
 * 
 * @author arnau.zapata.i
 *
 */
public class mainCP {
public static void main (String[] args) {
	javax.swing.SwingUtilities.invokeLater (
	new Runnable() {
		public void run() {
			CtrlPresentacio myCtrlPresentacio = new CtrlPresentacio();
			myCtrlPresentacio.inicializarPresentacion();
		}});
	}
}
