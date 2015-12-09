package presentacio;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import domini.controladores.CtrlPresentacio;

public class SuperVista{
	
		 protected CtrlPresentacio cp;
		 protected JPanel panelOpcions = new JPanel();
		 protected JFrame frameVista = new JFrame("Vista Principal");
		 protected JPanel contentPane = new JPanel();
	
			public void repintar(){
				 frameVista.pack();
			     frameVista.repaint();
			}
		  
		  public void llamarVista(){
			  hacerVisible();
			  repintar();
		  }
		  
		  public void hacerInvisible() {
			    System.out.println("isEventDispatchThread: " + SwingUtilities.isEventDispatchThread());
			    frameVista.pack();
			    frameVista.setVisible(false);
			  }
		  
		  public void hacerVisible() {
		    System.out.println("isEventDispatchThread: " + SwingUtilities.isEventDispatchThread());
		    frameVista.pack();
		    frameVista.setVisible(true);
		  }

		  public void activar() {
		    frameVista.setEnabled(true);
		  }

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
