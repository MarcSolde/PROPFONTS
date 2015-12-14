package presentacio;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import domini.controladores.CtrlPresentacio;

public class SuperVista extends JFrame{
	
		 protected CtrlPresentacio cp;
		 protected JPanel panelOpcions = new JPanel();
		 protected JFrame frameVista = new JFrame("Vista Principal");
		 protected JPanel contentPane = new JPanel();
	
		 	/**
		 	 * actualitza la vista amb els canvis fets a l'interficie
		 	 */
			public void repintar(){
				 frameVista.pack();
			     frameVista.repaint();
			}
		  /**
		   * fa visible la vista, l'actualitza els cambis y asigna els components
		   */
		  public void llamarVista(){
			  hacerVisible();
			  repintar();
			  inicializarComponentes();
		  }
		  
		  protected void inicializarComponentes() {}

		/**
		 * fa invisible la vista
		 */
		public void hacerInvisible() {
			    System.out.println("isEventDispatchThread: " + SwingUtilities.isEventDispatchThread());
			    frameVista.pack();
			    frameVista.setVisible(false);
			  }
		/**
		 * fa visible la vista
		 */
		  public void hacerVisible() {
		    System.out.println("isEventDispatchThread: " + SwingUtilities.isEventDispatchThread());
		    frameVista.pack();
		    frameVista.setVisible(true);
		  }
		  /**
		   * activa la vista
		   */
		  public void activar() {
		    frameVista.setEnabled(true);
		  }
		  /**
		   * desactiva la vista
		   */
		  public void desactivar() {
		    frameVista.setEnabled(false);
		  }


			protected void inicializar_frameVista() {
				    frameVista.setMinimumSize(new Dimension(1080,400));
				    frameVista.setPreferredSize(frameVista.getMinimumSize());
				    frameVista.setResizable(false);
				    // Posicion y operaciones por defecto
				    frameVista.setLocationRelativeTo(null);
				    frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				    contentPane = (JPanel) frameVista.getContentPane();
			}
}
