package presentacio;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import domini.controladores.CtrlPresentacio;

/**
 * 
 * @author arnau.zapata.i
 *
 */
public class VistaLogin extends SuperVista{
	private JPanel panelTamany = new JPanel();
	private JTextField textUsuari = new JTextField("Usuari");
	private JTextField textPassword = new JTextField("Password");
	private JButton buttonLogin = new JButton("Login");
	private JButton buttonCrear = new JButton("Nou Usuari");
	
	private JPanel panelDadesTauler = new JPanel();
	
	  public VistaLogin(CtrlPresentacio pCtrlPresentacion) {
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

	  
		  
		  public void inicializarComponentes(){
			 	inicializar_frameVista();
			 	contentPane.add(panelOpcions);
			    inicializar_panelOpcions();
			    asignar_listenersComponentes(); //a hacer
			
		}
		  
		  
		  

		private void inicializar_panelOpcions() {
				panelOpcions.add(textUsuari);
				panelOpcions.add(textPassword);
				panelOpcions.add(buttonLogin);
				panelOpcions.add(buttonCrear);
			  }
		
		private void asignar_listenersComponentes() {
			buttonLogin.addActionListener
		      (new ActionListener() {
		        public void actionPerformed (ActionEvent event) {
		          String texto = ((JButton) event.getSource()).getText();
		          System.out.println("Has clickado el boton con texto: " + texto);
		          actionPerformed_buttonLogin(event);
		          
		        }
		      });
			buttonCrear.addActionListener
		      (new ActionListener() {
			        public void actionPerformed (ActionEvent event) {
			          String texto = ((JButton) event.getSource()).getText();
			          System.out.println("Has clickado el boton con texto: " + texto);
			          actionPerformed_buttonCrear(event);
			          
			        }
			 });
			
		}

		protected void actionPerformed_buttonCrear(ActionEvent event) {
			String u = textUsuari.getText();
			String p = textPassword.getText();
			cp.CrearUsuari(u,p);
			/*if(cp.Login(u,p)){
				this.hacerInvisible();
			}
			else{
				cp.llamarError("Usuari y/o password incorrectes");
			}*/
					
		}

		protected void actionPerformed_buttonLogin(ActionEvent event) {
			 //this.desactivar();
			String u = textUsuari.getText();
			String p = textPassword.getText();
			if(cp.Login(u,p)){
				this.hacerInvisible();
				cp.llamarMenu();
			}
			else{
				cp.llamarError("Usuari y/o password incorrectes");
			}
			
		}
}
