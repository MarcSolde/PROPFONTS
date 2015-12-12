package presentacio;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
	private JPanel panelLogin = new JPanel();
		private JTextField textUsuari = new JTextField("Usuari");
		private JTextField textPassword = new JTextField("Password");
		private JButton buttonOk = new JButton();
		
	private JPanel panelElegir = new JPanel();
		private JButton buttonLogin = new JButton("Login");
		private JButton buttonCrear = new JButton("Nou Usuari");
	
		int option=0;
	
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
		    contentPane.add(panelOpcions);
	}

	  
		  
		  public void inicializarComponentes(){
			 	inicializar_frameVista();
			    inicializar_panelOpcions();
			    asignar_listenersComponentes(); //a hacer
			
		}
		  
		  
		  

		private void inicializar_panelOpcions() {
			panelLogin.add(textUsuari);
			panelLogin.add(textPassword);
			panelLogin.add(buttonOk);
			panelElegir.add(buttonLogin);
			panelElegir.add(buttonCrear);
			
			panelOpcions.add(panelElegir);

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
			buttonOk.addActionListener
		      (new ActionListener() {
			        public void actionPerformed (ActionEvent event) {
			          String texto = ((JButton) event.getSource()).getText();
			          System.out.println("Has clickado el boton con texto: " + texto);
			          actionPerformed_buttonOk(event);
			          
			        }
			 });
			textUsuari.addMouseListener(new MouseListener() {
		        public void mouseClicked(MouseEvent e) {
		        	textUsuari.setText("");
		        }

				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			 });
			textPassword.addMouseListener(new MouseListener() {
		        public void mouseClicked(MouseEvent e) {
		        	textPassword.setText("");
		        }

				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			 });
		}
		
		protected void actionPerformed_buttonOk(ActionEvent event) {
			String u = textUsuari.getText();
			String p = textPassword.getText();
			if(option==1){
				
				if(!cp.Login(u,p)){
					cp.CrearUsuari(u,p);
					this.hacerInvisible();
					cp.llamarMenu();
				}
				else{
					cp.llamarError("l'Usuari ya existeix");
				}
			}
			else{

				if(cp.Login(u,p)){
					this.hacerInvisible();
					cp.llamarMenu();
				}
				else{
					cp.llamarError("Usuari y/o password incorrectes");
				}
			}
		}

		private void cambiarPanel(JPanel p) {
			panelOpcions.removeAll();
			panelOpcions.add(p);
			repintar();
			
		}

		protected void actionPerformed_buttonCrear(ActionEvent event) {
			buttonOk.setText("Crear");
			cambiarPanel(panelLogin);
			
			option=1;
			
			
					
		}

		protected void actionPerformed_buttonLogin(ActionEvent event) {
			buttonOk.setText("Login");
			cambiarPanel(panelLogin);
			option=0;
			
			
			
			
		}
}
