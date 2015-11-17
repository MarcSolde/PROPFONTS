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
import presentacio.CasillaCP;


//import wiki2.CtrlPresentacion;

/**
 * 
 * @author arnau.zapata.i
 *
 */
public class VistaPartida {
	 private CtrlPresentacio cp;
	//principal
	private JFrame frameVista = new JFrame("Vista Principal");
	private JPanel panelTauler = new JPanel();
	private JPanel panelOpcions = new JPanel();
	private JPanel panelAfegirValor = new JPanel();
	private JPanel panelAfegirCandidat = new JPanel();
	private JPanel panelBorrarCandidat = new JPanel();
	Color old;
	
	int tamany=7;
	int pulsaX=0;
	int pulsaY=0;
	boolean pulsat=false;
	
	Color colorOriginal[][];
	
	//OPCIONS
	@SuppressWarnings("rawtypes")
	private JComboBox comboboxAfegirValor = new JComboBox();
	private JButton buttonAfegirValor = new JButton("Añadir");
	private JComboBox comboboxAfegirCandidat = new JComboBox();
	private JButton buttonAfegirCandidat = new JButton("Añadir Candidato");
	String auxAfegirCandidat = "indica el valor del candidato";
	private JComboBox comboboxBorrarCandidat = new JComboBox();
	private JButton buttonBorrarCandidat = new JButton("Borrar Candidato");
	String auxBorrarCandidat = "indica el valor del candidato";
	
	
	
	  public VistaPartida (CtrlPresentacio pCtrlPresentacion) {
	    System.out.println("isEventDispatchThread: " + SwingUtilities.isEventDispatchThread());
	   cp = pCtrlPresentacion;
	    inicializarComponentes();
	    
	  }
	  
	  public void llamarVista(){
		  hacerVisible();
		  repintar();
	  }
	  
	  public void inicializarComponentes(){
		 	inicializar_frameVista();
		    inicializar_panelTauler();
		    inicializar_panelOpcions();
		    asignar_listenersComponentes(); //a hacer
		
	}
	  
	 

	private void inicializar_frameVista() {
		    // Tamanyo
			
		    frameVista.setMinimumSize(new Dimension(700,400));
		    frameVista.setPreferredSize(frameVista.getMinimumSize());
		    frameVista.setResizable(false);
		    // Posicion y operaciones por defecto
		    frameVista.setLocationRelativeTo(null);
		    frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    JPanel contentPane = (JPanel) frameVista.getContentPane();
		    contentPane.setLayout(new BorderLayout());
		    contentPane.add(panelTauler,BorderLayout.WEST);
		    contentPane.add(panelOpcions,BorderLayout.EAST);
		  }
	  
	  private void inicializar_panelTauler() {
		    // Layout
		  colorOriginal= new Color[tamany][tamany];
		  panelTauler.setLayout(new GridLayout (tamany,tamany)); //a revisar
		    // Paneles
		  
		  for(int i=0;i<tamany;i++)for(int j=0;j<tamany;j++){
			 CasillaCP c = new CasillaCP(tamany,i,j);
			 Graphics g = c.getGraphics();
			 c.paintComponents(g);
			 colorOriginal[i][j]=c.getColorOriginal();
			 c.addMouseListener(new MouseListener() {
		        public void mouseClicked(MouseEvent e) {
		          CasillaCP aux = (CasillaCP)e.getSource();
		          
		          int x=aux.getLocation().x;
		          int y=aux.getLocation().y;
		          System.out.println("En la casilla que esta en la posicion: x="+x+ ",y="+y);
		          actionPerformed_CasillaPulsada(e);
		          
		        }
		        public void mouseEntered(MouseEvent e) {
					CasillaCP aux = (CasillaCP)e.getSource();
			        Color c= aux.getColor();
			        int gr=c.getGreen();
			        int r=c.getRed();
			        int b=c.getBlue();
			        gr=gr/2;
			        r=r/2;
			        b=b/2;
			        Color c1=new Color(r,gr,b);
			        aux.setColor(c1);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					CasillaCP aux = (CasillaCP)e.getSource();
					Color c2=aux.getColor();
					if(c2.equals(new Color(0,0,0))){}
					else{
						Color c= aux.getColor();
				        int gr=c.getGreen();
				        int r=c.getRed();
				        int b=c.getBlue();
				        gr=gr*2;
				        r=r*2;
				        b=b*2;
				        Color c1=new Color(r,gr,b);
				        aux.setColor(c1);
					}
			        
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
		      });
			 panelTauler.add(c); 
			 
		  }
	  }
	  
	 

	private void inicializar_panelOpcions() {
		  	panelOpcions.setLayout(new BorderLayout());
		  	panelOpcions.add(panelAfegirValor,BorderLayout.NORTH);
		  	panelOpcions.add(panelAfegirCandidat,BorderLayout.CENTER);
		  	panelOpcions.add(panelBorrarCandidat,BorderLayout.SOUTH);
		  	
		  	comboboxAfegirValor.addItem("Indica el valor que vols afegir");
		  	comboboxAfegirValor.addItem("CAP");
		  	comboboxAfegirCandidat.addItem(auxAfegirCandidat);
		  	comboboxBorrarCandidat.addItem(auxBorrarCandidat);
		  	for(int i=0;i<tamany;i++){
		  		int aux=i+1;
		  		String b;
		  		b=String.valueOf(aux);
		  		comboboxAfegirValor.addItem(b);
		  		
		  	}
		  	panelAfegirValor.add(comboboxAfegirValor);
		  	panelAfegirValor.add(buttonAfegirValor);
		  	panelAfegirCandidat.add(comboboxAfegirCandidat);
		  	panelAfegirCandidat.add(buttonAfegirCandidat);
		  	panelBorrarCandidat.add(comboboxBorrarCandidat);
		  	panelBorrarCandidat.add(buttonBorrarCandidat);
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
	 
		/*public void cambiarModificarRelacionUtilidades(JPanel p){
			panelUtilizacionABM.remove(panelModificarRelacionUtilidades);
			panelModificarRelacionUtilidades.setLayout(p.getLayout());
			panelModificarRelacionUtilidades = p;
			panelUtilizacionABM.add(panelModificarRelacionUtilidades,BorderLayout.SOUTH);
			cambiarUtilizacionABM(panelModificarRelacion);
		}*/
		public void repintar(){
			 frameVista.pack();
		     frameVista.repaint();
		}
		
	
	private void asignar_listenersComponentes() { //a hacer

	    // Listeners para los botones
			//Componentes de PanelBotones
		buttonAfegirValor.addActionListener
	      (new ActionListener() {
	        public void actionPerformed (ActionEvent event) {
	          String texto = ((JButton) event.getSource()).getText();
	          System.out.println("Has clickado el boton con texto: " + texto);
	          actionPerformed_buttonAfegirValor(event);
	          
	        }
	      });
		buttonAfegirCandidat.addActionListener
	      (new ActionListener() {
		        public void actionPerformed (ActionEvent event) {
		          String texto = ((JButton) event.getSource()).getText();
		          System.out.println("Has clickado el boton con texto: " + texto);
		          actionPerformed_buttonAfegirCandidat(event);
		          
		        }
		      });
		buttonBorrarCandidat.addActionListener
	      (new ActionListener() {
		        public void actionPerformed (ActionEvent event) {
		          String texto = ((JButton) event.getSource()).getText();
		          System.out.println("Has clickado el boton con texto: " + texto);
		          actionPerformed_buttonBorrarCandidat(event);
		          
		        }
		      });
	}

	protected void actionPerformed_buttonBorrarCandidat(ActionEvent event) {
		String valor = (String) comboboxBorrarCandidat.getModel().getSelectedItem(); 
		if(pulsat && !valor.equals(auxBorrarCandidat)){
			CasillaCP b = (CasillaCP) panelTauler.getComponentAt(pulsaX, pulsaY);
			Point p=b.getXY();
			int x=p.x;
			int y=p.y;
			int n=Integer.valueOf(valor);
			if(cp.borrarCandidat(x,y,n)){
				b.eraseCandidat(valor);	
			}
			b.ReturnColorOriginal();
			pulsat=false;
			buidarComboboxCandidats();
		}
		repintar();	
	}

	protected void actionPerformed_buttonAfegirCandidat(ActionEvent event) {
		String valor = (String) comboboxAfegirCandidat.getModel().getSelectedItem(); 
		if(pulsat && !valor.equals(auxAfegirCandidat)){
			CasillaCP b = (CasillaCP) panelTauler.getComponentAt(pulsaX, pulsaY);
			Point p=b.getXY();
			int x=p.x;
			int y=p.y;
			int n=Integer.valueOf(valor);
			if(cp.addCandidat(x,y,n)){
				b.addCandidat(valor);
			}
			b.ReturnColorOriginal();
			pulsat=false;
			buidarComboboxCandidats();
		}
		repintar();	
		
	}

	@SuppressWarnings("deprecation")
	protected void actionPerformed_buttonAfegirValor(ActionEvent event) {
		String valor = (String) comboboxAfegirValor.getModel().getSelectedItem(); 
		if(pulsat && !valor.equals("Indica el valor que vols afegir")){
			CasillaCP b = (CasillaCP) panelTauler.getComponentAt(pulsaX, pulsaY);
			Point p=b.getXY();
			int x=p.x;
			int y=p.y;
			int n=Integer.valueOf(valor);
			if(cp.afegirValor(x,y,n)){
				b.setValor(valor);
			}
			else{
				b.setColor(Color.ORANGE);
			}
			b.ReturnColorOriginal();
			pulsat=false;
		}
		repintar();
	}
	
	
	 protected void actionPerformed_CasillaPulsada(MouseEvent e) {
		  CasillaCP aux = (CasillaCP)e.getSource();
		  int x=aux.getLocation().x;
		  int y=aux.getLocation().y;
		  if(pulsat && pulsaX==x && pulsaY==y){
			  pulsat=false;
			  aux.ReturnColorOriginal();
			  buidarComboboxCandidats();
		  }
		  else{
			  if(pulsat){
				  CasillaCP aux2= (CasillaCP) panelTauler.getComponentAt(pulsaX,pulsaY);
				  System.out.println("pulsaX:" + pulsaX+" ,pulsaY:"+pulsaY);
				  aux2.ReturnColorOriginal();
			  }
			  pulsat=true;
			  pulsaX=aux.getLocation().x;
			  pulsaY=aux.getLocation().y;
			  Color c = new Color(0,0,0);
			  aux.setColor(c);
			  posarComboboxCandidats(aux);
		  }	
		  repintar();
	  }

	private void posarComboboxCandidats(CasillaCP aux) {
		boolean[] a= aux.getCandidats();
		comboboxAfegirCandidat.removeAllItems();
		comboboxBorrarCandidat.removeAllItems();
		comboboxAfegirCandidat.addItem(auxAfegirCandidat);
		comboboxBorrarCandidat.addItem(auxBorrarCandidat);
		for(int i=0;i<a.length;i++){
			if(a[i]==false)comboboxAfegirCandidat.addItem(String.valueOf(i+1));
			else comboboxBorrarCandidat.addItem(String.valueOf(i+1));
		}
	}

	private void buidarComboboxCandidats() {
		comboboxAfegirCandidat.removeAllItems();
		comboboxBorrarCandidat.removeAllItems();
		comboboxAfegirCandidat.addItem(auxAfegirCandidat);
		comboboxBorrarCandidat.addItem(auxBorrarCandidat);
	}
	
}