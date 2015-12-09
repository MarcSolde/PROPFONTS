package presentacio;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
		int opcio=0;
	
	private JPanel panelCarregarKenken = new JPanel();
		private JComboBox comboboxKenkenPosible = new JComboBox();
		private JButton buttonKenkenPosible = new JButton("Carregar");
		private JButton buttonQuit = new JButton("Quit");
	
	private JPanel panelComprovar = new JPanel();
		private JLabel labelComprovar = new JLabel();
		private JButton buttonFi = new JButton("Quit");
	
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
		  for(String s:ls){
			  this.comboboxKenkenPosible.addItem(s);
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
			    inicializar_panelOpcions();
			    asignar_listenersComponentes(); //a hacer
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
			if(opcio==0)cp.carregarKenkenGuardat(s);
			else if(opcio==1)cp.CarregarPartida(s);
			else cp.borrarPartida(s);
			
		}

		protected void actionPerformed_buttonOk(ActionEvent event) {
			 //this.desactivar();
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

		

		

		
}
