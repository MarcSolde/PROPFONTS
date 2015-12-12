package presentacio;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import domini.controladores.CtrlPresentacio;

public class VistaEmergente extends SuperVista{
	private boolean primeraVez=true;
	private JPanel panelError = new JPanel();
		private JLabel labelAlert = new JLabel("ERROR:");
		private JLabel labelError = new JLabel();
		private JButton buttonOk = new JButton("OK");
		int opcio=0;
	
	private JPanel panelCarregarKenken = new JPanel();
		private JComboBox comboboxKenkenPosible = new JComboBox();
		private JButton buttonKenkenPosible = new JButton("Carregar");
		private JButton buttonQuit = new JButton("Quit");
	
	private JPanel panelComprovar = new JPanel();
		private JLabel labelComprovar = new JLabel();
		private JButton buttonFi = new JButton("Quit");
		
	private JPanel panelGuardar = new JPanel();
		private JButton buttonSobrescriure = new JButton("Sobrescriure");
		private JButton buttonNou = new JButton("Nou");
		
	private JPanel panelNombrar = new JPanel();
		private JTextField textFieldNombrar= new JTextField("introdueix el nom que tindra la Partida");
		private JButton buttonNombrar = new JButton("Nombrar");
		
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
		  llamarVista();
		  ArrayList<String> s=cp.ConsultaKenkenGuardats();
			inicializar_panelCarregarKenken(s);
			buttonKenkenPosible.setText("Carregar");
			cambiarPanel(panelCarregarKenken);
			llamarVista();
			opcio=0;
		}
	  
	  public void llamarImportar() {
		  llamarVista();
		  ArrayList<String> s=cp.ConsultaKenkenGuardats();
			inicializar_panelCarregarKenken(s);
			buttonKenkenPosible.setText("Carregar");
			cambiarPanel(panelCarregarKenken);
			llamarVista();
			opcio=3;
		}
	  
	  public void llamarResumirPartida() {
		    llamarVista();
		  	ArrayList<String> s =cp.ConsultaPartidesGuardades();
			inicializar_panelCarregarKenken(s);
			buttonKenkenPosible.setText("Carregar");
			cambiarPanel(panelCarregarKenken);
			opcio=1;
		}
	  public void llamarEsborrarPartida() {
		  llamarVista();
		  	ArrayList<String> s =cp.ConsultaPartidesGuardades();
		  	buttonKenkenPosible.setText("Eliminar");
			inicializar_panelCarregarKenken(s);
			cambiarPanel(panelCarregarKenken);
			opcio=2;
		}
	  private void inicializar_panelCarregarKenken(ArrayList<String> ls) {
		  panelCarregarKenken.removeAll();
		  comboboxKenkenPosible= new JComboBox();
		  for(String s:ls){
			  comboboxKenkenPosible.addItem(s);
		  }
		  panelCarregarKenken.add(comboboxKenkenPosible);
		  panelCarregarKenken.add(buttonKenkenPosible);
		  panelCarregarKenken.add(buttonQuit);
		  
		  
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
			 	inicializar_panelError();
			 	 inicializar_panelNombrar();
			    inicializar_panelOpcions();
			    asignar_listenersComponentes(); //a hacer
		}
		  
		  private void inicializar_panelNombrar() {
			  panelNombrar.add(textFieldNombrar);
			  panelNombrar.add(buttonNombrar);
			
		}

		private void inicializar_panelError() {
			  panelError.add(labelAlert);
			  panelError.add(labelError);
			  panelError.add(buttonOk);
			
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
			buttonKenkenPosible.addActionListener
		      (new ActionListener() {
			        public void actionPerformed (ActionEvent event) {
			          String texto = ((JButton) event.getSource()).getText();
			          System.out.println("Has clickado el boton con texto: " + texto);
			          actionPerformed_buttonKenkenPosible(event);
			          
			        }
			 });
			buttonQuit.addActionListener
		      (new ActionListener() {
			        public void actionPerformed (ActionEvent event) {
			          String texto = ((JButton) event.getSource()).getText();
			          System.out.println("Has clickado el boton con texto: " + texto);
			          actionPerformed_buttonQuit(event);
			          
			        }
			 });
			buttonFi.addActionListener
		      (new ActionListener() {
			        public void actionPerformed (ActionEvent event) {
			          String texto = ((JButton) event.getSource()).getText();
			          System.out.println("Has clickado el boton con texto: " + texto);
			          actionPerformed_buttonFi(event);
			          
			        }
			 });
			buttonNou.addActionListener
		      (new ActionListener() {
			        public void actionPerformed (ActionEvent event) {
			          String texto = ((JButton) event.getSource()).getText();
			          System.out.println("Has clickado el boton con texto: " + texto);
			          actionPerformed_buttonNou(event);
			          
			        }
			 });
			buttonSobrescriure.addActionListener
		      (new ActionListener() {
			        public void actionPerformed (ActionEvent event) {
			          String texto = ((JButton) event.getSource()).getText();
			          System.out.println("Has clickado el boton con texto: " + texto);
			          actionPerformed_buttonSobrescriure(event);
			          
			        }
			 });
			buttonNombrar.addActionListener
		      (new ActionListener() {
			        public void actionPerformed (ActionEvent event) {
			          String texto = ((JButton) event.getSource()).getText();
			          System.out.println("Has clickado el boton con texto: " + texto);
			          actionPerformed_buttonNombrar(event);
			          
			        }
			 });
			textFieldNombrar.addMouseListener(new MouseListener() {
		        public void mouseClicked(MouseEvent e) {
		        	textFieldNombrar.setText("");
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

		protected void actionPerformed_buttonNombrar(ActionEvent event) {
			String s = this.textFieldNombrar.getText();
			if(s.equals("introdueix el nom que tindra la Partida")){
				cp.llamarError("Antes has de introducir un nombre a la Partida");
			}
			else{
				cp.GuardarNou(s);	
				this.hacerInvisible();
				cp.llamarMenu();
			}
			
		}

		protected void actionPerformed_buttonSobrescriure(ActionEvent event) {
			ArrayList<String> ls=cp.ConsultaPartidesGuardades();
			inicializar_panelSobrescriure(ls);
			cambiarPanel(panelCarregarKenken);
			
		}

		  private void inicializar_panelSobrescriure(ArrayList<String> ls) {
			  panelCarregarKenken.removeAll();
			  for(String s:ls){
				  this.comboboxKenkenPosible.addItem(s);
			  }
			  panelCarregarKenken.add(comboboxKenkenPosible);
			  panelCarregarKenken.add(buttonKenkenPosible);
			  buttonKenkenPosible.setText("Sobrescriu");
			  panelCarregarKenken.add(buttonQuit);
			  
			  
		}
		
		protected void actionPerformed_buttonNou(ActionEvent event) {
			//cp.Guarda();
			cambiarPanel(panelNombrar);
		}

		protected void actionPerformed_buttonFi(ActionEvent event) {
			this.hacerInvisible();
			
		}

		protected void actionPerformed_buttonQuit(ActionEvent event) {
			this.hacerInvisible();
			
		}

		protected void actionPerformed_buttonKenkenPosible(ActionEvent event) {
			String s=(String) this.comboboxKenkenPosible.getSelectedItem();
			this.hacerInvisible();
			if(this.buttonKenkenPosible.getText().equals("Sobrescriu")){
				cp.Sobrescriu(s);
				cp.llamarMenu();
			}
			else if(opcio==0)cp.carregarKenkenGuardat(s);
			else if(opcio==1)cp.CarregarPartida(s);
			else if(opcio==2)cp.borrarPartida(s);
			else cp.importar(s);
		}

		protected void actionPerformed_buttonOk(ActionEvent event) {
			
			 this.hacerInvisible();
			
		}

		public void llamarComprovar(boolean comprovar) {
			if(comprovar){
				labelComprovar.setText("Correcto");
			}
			else{
				labelComprovar.setText("InCorrecto");
			}
			 panelComprovar.add(labelComprovar);
			 panelComprovar.add(this.buttonFi);
			 cambiarPanel(panelComprovar);
			 llamarVista();
		}

		public void llamarGuardar() {
			panelGuardar.add(this.buttonSobrescriure);
			panelGuardar.add(this.buttonNou);
			cambiarPanel(panelGuardar);
			 llamarVista();
		}

		

		

		

		
}
