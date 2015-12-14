package presentacio;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import domini.controladores.CtrlPresentacio;


/**
 * 
 * @author arnau.zapata.i
 *
 */
public class VistaMenu extends SuperVista{
	//principal
	
	private JPanel panelDadesTauler = new JPanel();
	
	//OPCIONS
	private JLabel labelOpcions = new JLabel("elije: Jugar una partida o Crear un tablero");
	private JButton buttonPartida = new JButton("Jugar");
	private JButton buttonCreacio = new JButton("Crear Tablero");
	private int opcio=0;
	
	private JPanel panelElegir = new JPanel();
		private JButton buttonNovaPartida = new JButton("Nova Partida");
		private JButton buttonResumirPartida = new JButton("Resumir Partida");
		private JButton buttonConsultarRanking = new JButton("Consultar Ranking");
		private JButton buttonEsborrarPartida = new JButton("esborrar Partida guardada");
		private JButton buttonSortir = new JButton("Sortir");
	 
	private JPanel panelElegirKenken = new JPanel();
		private JButton buttonFerKenken = new JButton("Fer Kenken desde 0");
		private JButton buttonCarregarKenken = new JButton("Triar kenken guardat");
		private JButton buttonJugarKenken = new JButton("jugar un kenken");
		private JButton buttonTornarMenu = new JButton("Tornar al menu");
	
	private JPanel panelTamany = new JPanel();
		private JComboBox<String> comboboxTamany = new JComboBox<String>();
		private String StringCombo = "elije tamaNo";
		private JButton buttonValidar = new JButton("Seleccionar tamaño");
		private JButton buttonTornarTamany = new JButton("Tornar al menu");
		
	/*private JPanel panelRanking = new JPanel();
		private JLabel labelRanking = new JLabel("¿Que desea Consultar?");
		private JButton buttonMejoresTiempos = new JButton("los mejores tiempos");
		private JButton buttonMasResueltos = new JButton("Los mas resueltos");
		private JButton buttonTornarRanking = new JButton("Tornar al menu");*/
		
	 /**
	   * fa visible la vista y asigna tots els elements interns d'aquesta
	   */
	  public VistaMenu(CtrlPresentacio pCtrlPresentacion) {
	    System.out.println("isEventDispatchThread: " + SwingUtilities.isEventDispatchThread());
	    cp = pCtrlPresentacion;
	    inicializarComponentes();
	    
	  }
	  
	  protected void inicializarComponentes(){
		 	inicializar_frameVista();
		 	contentPane.add(panelOpcions);
		    inicializar_panelOpcions();
		    inicializar_panelElegir();
		    inicializar_panelElegirKenken();
		    inicializar_panelTamany();
		    //inicializar_panelRanking();
		    asignar_listenersComponentes(); //a hacer
		
	}
	  

	private void inicializar_panelElegir() {
		panelElegir.setLayout(new FlowLayout());
		panelElegir.add(buttonNovaPartida);
		panelElegir.add(buttonResumirPartida);
		panelElegir.add(buttonConsultarRanking);
		panelElegir.add(buttonEsborrarPartida);
		panelElegir.add(buttonSortir);
	}

	private void inicializar_panelElegirKenken() {
		panelElegirKenken.setLayout(new FlowLayout());
		panelElegirKenken.add(buttonJugarKenken);
		panelElegirKenken.add(buttonCarregarKenken);
		panelElegirKenken.add(buttonFerKenken);
		panelElegirKenken.add(buttonTornarMenu);
		
		
		
	}

	private void inicializar_panelOpcions() {
		panelOpcions.add(panelElegir);
	}
	
	private void inicializar_panelTamany() {
		comboboxTamany.addItem(StringCombo);
		comboboxTamany.addItem("3");
		comboboxTamany.addItem("4");
		comboboxTamany.addItem("5");
		comboboxTamany.addItem("6");
		comboboxTamany.addItem("7");
		comboboxTamany.addItem("8");
		comboboxTamany.addItem("9");		
		
		panelTamany.add(comboboxTamany);
		panelTamany.add(buttonValidar);
		panelTamany.add(buttonTornarTamany);
		
	}
	 
		
	
	private void asignar_listenersComponentes() { //a hacer

	    // Listeners para los botones
			//Componentes de PanelBotones
		
		buttonCreacio.addActionListener
	      (new ActionListener() {
		        public void actionPerformed (ActionEvent event) {
		          String texto = ((JButton) event.getSource()).getText();
		          System.out.println("Has clickado el boton con texto: " + texto);
		          actionPerformed_buttonCreacio(event);
		          
		        }
		      });
		buttonNovaPartida.addActionListener
	      (new ActionListener() {
		        public void actionPerformed (ActionEvent event) {
		          String texto = ((JButton) event.getSource()).getText();
		          System.out.println("Has clickado el boton con texto: " + texto);
		          actionPerformed_buttonNovaPartida(event);
		          
		        }
		      });
		buttonResumirPartida.addActionListener
	      (new ActionListener() {
		        public void actionPerformed (ActionEvent event) {
		          String texto = ((JButton) event.getSource()).getText();
		          System.out.println("Has clickado el boton con texto: " + texto);
		          actionPerformed_buttonResumirPartida(event);
		          
		        }
		      });
		buttonConsultarRanking.addActionListener
	      (new ActionListener() {
		        public void actionPerformed (ActionEvent event) {
		          String texto = ((JButton) event.getSource()).getText();
		          System.out.println("Has clickado el boton con texto: " + texto);
		          actionPerformed_buttonConsultarRanking(event);
		          
		        }
		      });
		buttonEsborrarPartida.addActionListener
	      (new ActionListener() {
		        public void actionPerformed (ActionEvent event) {
		          String texto = ((JButton) event.getSource()).getText();
		          System.out.println("Has clickado el boton con texto: " + texto);
		          actionPerformed_buttonEsborrarPartida(event);
		          
		        }
		 });
		buttonFerKenken.addActionListener
	      (new ActionListener() {
		        public void actionPerformed (ActionEvent event) {
		          String texto = ((JButton) event.getSource()).getText();
		          System.out.println("Has clickado el boton con texto: " + texto);
		          actionPerformed_buttonFerKenken(event);
		          
		        }
		 });
		buttonCarregarKenken.addActionListener
	      (new ActionListener() {
		        public void actionPerformed (ActionEvent event) {
		          String texto = ((JButton) event.getSource()).getText();
		          System.out.println("Has clickado el boton con texto: " + texto);
		          actionPerformed_buttonCarregarKenken(event);
		          
		        }
		 });
		buttonJugarKenken.addActionListener
	      (new ActionListener() {
		        public void actionPerformed (ActionEvent event) {
		          String texto = ((JButton) event.getSource()).getText();
		          System.out.println("Has clickado el boton con texto: " + texto);
		          actionPerformed_buttonJugarKenken(event);
		          
		        }
		 });
		buttonTornarMenu.addActionListener
	      (new ActionListener() {
		        public void actionPerformed (ActionEvent event) {
		          String texto = ((JButton) event.getSource()).getText();
		          System.out.println("Has clickado el boton con texto: " + texto);
		          actionPerformed_buttonTornarMenu(event);
		          
		        }
		 });
		buttonTornarTamany.addActionListener
	      (new ActionListener() {
		        public void actionPerformed (ActionEvent event) {
		          String texto = ((JButton) event.getSource()).getText();
		          System.out.println("Has clickado el boton con texto: " + texto);
		          actionPerformed_buttonTornarElegirKenken(event);
		          
		        }
		 });
		buttonValidar.addActionListener
	      (new ActionListener() {
		        public void actionPerformed (ActionEvent event) {
		          String texto = ((JButton) event.getSource()).getText();
		          System.out.println("Has clickado el boton con texto: " + texto);
		          actionPerformed_buttonValidar(event);  
		        }
		 });
		/*buttonTornarRanking.addActionListener
	      (new ActionListener() {
		        public void actionPerformed (ActionEvent event) {
		          String texto = ((JButton) event.getSource()).getText();
		          System.out.println("Has clickado el boton con texto: " + texto);
		          actionPerformed_buttonTornarRanking(event);  
		        }
		 });
		buttonMejoresTiempos.addActionListener
	      (new ActionListener() {
		        public void actionPerformed (ActionEvent event) {
		          String texto = ((JButton) event.getSource()).getText();
		          System.out.println("Has clickado el boton con texto: " + texto);
		          actionPerformed_buttonMejoresTiempos(event);  
		        }
		 });
		buttonMasResueltos.addActionListener
	      (new ActionListener() {
		        public void actionPerformed (ActionEvent event) {
		          String texto = ((JButton) event.getSource()).getText();
		          System.out.println("Has clickado el boton con texto: " + texto);
		          actionPerformed_buttonMasResueltos(event);  
		        }
		 });*/
		buttonSortir.addActionListener
	      (new ActionListener() {
		        public void actionPerformed (ActionEvent event) {
		          String texto = ((JButton) event.getSource()).getText();
		          System.out.println("Has clickado el boton con texto: " + texto);
		          actionPerformed_buttonSortir(event);  
		        }
		 });
	}

	

	protected void actionPerformed_buttonSortir(ActionEvent event) {
		this.hacerInvisible();
		
	}

	protected void actionPerformed_buttonEsborrarPartida(ActionEvent event) {
		cp.llamarEsborrarPartida();
		
	}

	protected void actionPerformed_buttonMasResueltos(ActionEvent event) {
		//cp.getMasResueltos();
		cp.llamarRanking();
		
	}

	protected void actionPerformed_buttonMejoresTiempos(ActionEvent event) {
		//cp.getMejoresTiempos();
		cp.llamarRanking();
		
	}

	protected void actionPerformed_buttonJugarKenken(ActionEvent event) {
		cambiarPanel(panelTamany);
		opcio=1;
		
	}

	protected void actionPerformed_buttonTornarRanking(ActionEvent event) {
		cambiarPanel(panelElegir);
		
	}

	protected void actionPerformed_buttonCarregarKenken(ActionEvent event) {
		// TODO Auto-generated method stub
		cp.llamarCarregarKenken();
	}

	protected void actionPerformed_buttonTornarElegirKenken(ActionEvent event) {
		cambiarPanel(panelElegirKenken);
		
	}

	protected void actionPerformed_buttonFerKenken(ActionEvent event) {
		cambiarPanel(panelTamany);
		opcio=0;
	}

	protected void actionPerformed_buttonTornarMenu(ActionEvent event) {
		cambiarPanel(panelElegir);
	}

	protected void actionPerformed_buttonConsultarRanking(ActionEvent event) {
		cp.llamarRanking();
	}

	protected void actionPerformed_buttonNovaPartida(ActionEvent event) {
		cambiarPanel(panelElegirKenken);
	}
	private void actionPerformed_buttonResumirPartida(ActionEvent event) {
		cp.resumirPartida();					
	}

	private void cambiarPanel(JPanel p) {
		panelOpcions.removeAll();
		panelOpcions.add(p);
		repintar();
		
	}

	protected void actionPerformed_buttonValidar(ActionEvent event) {
		String valor = (String) this.comboboxTamany.getModel().getSelectedItem(); 
		if(!valor.equals(StringCombo)){
			this.hacerInvisible();
			int tam = Integer.valueOf(valor);
			cp.setTamany(tam);
			if(opcio==0){cp.llamarCreacio();}
			else if (opcio==1){
				cp.llamarNovaPartida(tam);
			}
		}
		else{
			cp.llamarError("No has seleccionado ningun tamano");
		}
		
	}

	protected void actionPerformed_buttonCreacio(ActionEvent event) {
		desactivar();
		this.hacerInvisible();
		cp.llamarCreacio();
		//opcio=1;
		//cambiarPanelTamany();
	}

	private void cambiarPanelTamany() {
		panelOpcions.remove(buttonCreacio);
		panelOpcions.remove(buttonPartida);
		panelOpcions.remove(labelOpcions);
		panelOpcions.add(comboboxTamany);
		panelOpcions.add(buttonValidar);
		repintar();
	}

	

	
	
}