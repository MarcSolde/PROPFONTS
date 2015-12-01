package presentacio;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import domini.controladores.CtrlPresentacio;

public class VistaEmergente extends SuperVista{
	private JPanel panelError = new JPanel();
		private JLabel labelAlert = new JLabel("ERROR:");
		private JLabel labelError = new JLabel();
		private JButton buttonOk = new JButton("OK");
	
	private JPanel panelCarregarKenken = new JPanel();
		
	
	private JPanel panelDadesTauler = new JPanel();
	
	  public VistaEmergente(CtrlPresentacio pCtrlPresentacion) {
		    System.out.println("isEventDispatchThread: " + SwingUtilities.isEventDispatchThread());
		    cp = pCtrlPresentacion;
		    inicializarComponentes();
		    
		  }
	  
	  protected void inicializar_frameVista() {
		    frameVista.setMinimumSize(new Dimension(500,100));
		    frameVista.setPreferredSize(frameVista.getMinimumSize());
		    frameVista.setResizable(false);
		    // Posicion y operaciones por defecto
		    frameVista.setLocationRelativeTo(null);
		    frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    contentPane = (JPanel) frameVista.getContentPane();
	}

	  public void llamarVista(String s){
		  hacerVisible();
		  cambiarPanel(panelError);
		  labelError.setText(s);
		  repintar();
	  }
	  public void llamarCarregarKenken() {
			String s[]=cp.ConsultaKenkenGuardats();
			inicializar_panelCarregarKenken(s);
			cambiarPanel(panelCarregarKenken);
			
		}
	  private void inicializar_panelCarregarKenken(String[] ls) {
		  panelCarregarKenken.removeAll();
		  for(String s:ls){
			  JButton b = new JButton(s);
			  b.addActionListener
		      (new ActionListener() {
		        public void actionPerformed (ActionEvent event) {
		          String texto = ((JButton) event.getSource()).getText();
		          System.out.println("Has clickado el boton con texto: " + texto);
		          actionPerformed_buttonKenkenGuardat(event);
		          
		        }
		      });
		  }
	}

	protected void actionPerformed_buttonKenkenGuardat(ActionEvent event) {
		JButton b= (JButton)event.getSource();
		String s=b.getText();
		cp.CarregarPartida(s);
		
	}

	private void cambiarPanel(JPanel p) {
			panelOpcions.removeAll();
			panelOpcions.add(p);
			repintar();
			
		}
		  
		  public void inicializarComponentes(){
			 	inicializar_frameVista();
			 	contentPane.add(panelOpcions);
			    inicializar_panelOpcions();
			    inicializar_panelError();
			    asignar_listenersComponentes(); //a hacer
			
		}
		  
		  private void inicializar_panelError() {
			  	panelOpcions.add(labelAlert);
				panelOpcions.add(labelError);
				panelOpcions.add(buttonOk);
			
		}

		private void inicializar_panelOpcions() {
				panelOpcions.add(panelError);
			  }
		
		private void asignar_listenersComponentes() {
			buttonOk.addActionListener
		      (new ActionListener() {
		        public void actionPerformed (ActionEvent event) {
		          String texto = ((JButton) event.getSource()).getText();
		          System.out.println("Has clickado el boton con texto: " + texto);
		          actionPerformed_buttonOk(event);
		          
		        }
		      });
			
		}

		protected void actionPerformed_buttonOk(ActionEvent event) {
			 //this.desactivar();
			 this.hacerInvisible();
			
		}

		
}
