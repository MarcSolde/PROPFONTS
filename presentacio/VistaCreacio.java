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

import controladores.dominio.CtrlPresentacio;

import presentacio.CasillaCP;

//import wiki2.CtrlPresentacion;

/**
 * 
 * @author arnau.zapata.i
 *
 */
public class VistaCreacio {
	 private CtrlPresentacio cp;
	//principal
	private JFrame frameVista = new JFrame("Vista Principal");
	private JPanel panelTauler = new JPanel();
	private JPanel panelOpcions = new JPanel();
		private JPanel panelAfegirValor = new JPanel();
		private JPanel panelRegio = new JPanel();
		private JPanel panelOperacions = new JPanel();
	Color old;
	Color color1 = new Color(255,0,0);
	Color color2= new Color(0,255,0);
	Color color3= new Color(255,255,0);
	Color color4= new Color(255,0,255);
	Color color5= new Color(0,255,255);
	
	int tamany=7;
	int pulsaX=0;
	int pulsaY=0;
	boolean pulsat=false;
	
	int Regions[][];
	int RegionsId[][];
	CasillaCP Caselles[][];
	boolean visit[][];
	
	//OPCIONS
	@SuppressWarnings("rawtypes")
	private JComboBox comboboxAfegirValor = new JComboBox();
	private JButton buttonAfegirValor = new JButton("Añadir");
	private JComboBox comboboxAfegirRegio = new JComboBox();
	private JButton buttonAfegirRegio = new JButton("Afegir Regio");
	private JButton buttonEliminarRegio = new JButton("Eliminar Region");
	private JButton buttonEliminarRegioCasella = new JButton("Eliminar Regio Casella");
	private JComboBox comboboxAfegirOperacio = new JComboBox();
	private JButton buttonAfegirOperacio = new JButton("Afegir Operacio");
	private JTextField textfieldAfegirObjectiu = new JTextField("  ");
	private JButton buttonAfegirObjectiu = new JButton("Afegir Objectiu");
	private JButton buttonGuardar = new JButton("Guardar Tauler");
	
	String auxAfegirRegio1 = "indica regio a afegir";
	String auxAfegirRegio2 = "nova";
	
	
	
	
	  public VistaCreacio (CtrlPresentacio ctrlPresentacio) {
	    System.out.println("isEventDispatchThread: " + SwingUtilities.isEventDispatchThread());
	    cp = ctrlPresentacio;
	    inicializarComponentes();
	    
	  }
	  
	  public void llamarVista(){
		  hacerVisible();
		  repintar();
	  }
	  
	  public void inicializarComponentes(){
		    inicializarMatrius();
		 	inicializar_frameVista();
		    inicializar_panelTauler();
		    inicializar_panelOpcions();
		    asignar_listenersComponentes(); //a hacer
		
	}
	  
	 

	private void inicializarMatrius() {
		Regions= new int[tamany][tamany];
		RegionsId= new int[tamany][tamany];
		for(int i=0;i<RegionsId.length;i++)for(int j=0;j<RegionsId.length;j++){
			RegionsId[i][j]=0;
		}
		visit= new boolean[tamany][tamany];
		for(int i=0;i<Regions.length;i++)for(int j=0;j<Regions.length;j++){
			Regions[i][j]=0;
		}
		 for(int i=0;i<tamany;i++)for(int j=0;j<tamany;j++){
			 visit[i][j]=false;
		 }
		Caselles= new CasillaCP[tamany][tamany];
	}

	private void inicializar_frameVista() {
		    // Tamanyo
			
		    frameVista.setMinimumSize(new Dimension(950,400));
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
		  
		  panelTauler.setLayout(new GridLayout (tamany,tamany)); 
		  for(int i=0;i<tamany;i++)for(int j=0;j<tamany;j++){
			 CasillaCP c = new CasillaCP(tamany,i,j);
			 Caselles[i][j]=c;
			 Graphics g = c.getGraphics();
			 c.paintComponents(g);
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
		  	panelOpcions.add(panelRegio,BorderLayout.CENTER);
		  	panelOpcions.add(panelOperacions,BorderLayout.SOUTH);
		  	
		  	comboboxAfegirValor.addItem("Indica el valor que vols afegir");
		  	comboboxAfegirValor.addItem("CAP");
		  	comboboxAfegirRegio.addItem(auxAfegirRegio1);
		  	comboboxAfegirRegio.addItem(auxAfegirRegio2);
		  	for(int i=0;i<tamany;i++){
		  		int aux=i+1;
		  		String b;
		  		b=String.valueOf(aux);
		  		comboboxAfegirValor.addItem(b);
		  		
		  	}
		  	panelAfegirValor.add(comboboxAfegirValor);
		  	panelAfegirValor.add(buttonAfegirValor);
		  	panelRegio.add(comboboxAfegirRegio);
		  	panelRegio.add(buttonAfegirRegio);
		  	panelRegio.add(buttonEliminarRegioCasella);
		  	panelRegio.add(buttonEliminarRegio);
		  	panelOperacions.add(comboboxAfegirOperacio);
		  	comboboxAfegirOperacio.addItem("eligeix l'operacio");
		  	comboboxAfegirOperacio.addItem("CAP");
		  	comboboxAfegirOperacio.addItem("+");
		  	comboboxAfegirOperacio.addItem("-");
		  	comboboxAfegirOperacio.addItem("*");
		  	comboboxAfegirOperacio.addItem("/");
		  	panelOperacions.add(buttonAfegirOperacio);
		  	panelOperacions.add(textfieldAfegirObjectiu);
		  	panelOperacions.add(buttonAfegirObjectiu);
		  	panelOperacions.add(buttonGuardar);
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
		buttonAfegirRegio.addActionListener
	      (new ActionListener() {
		        public void actionPerformed (ActionEvent event) {
		          String texto = ((JButton) event.getSource()).getText();
		          System.out.println("Has clickado el boton con texto: " + texto);
		          actionPerformed_buttonAfegirRegio(event);
		          
		        }
		      });
		buttonEliminarRegio.addActionListener
	      (new ActionListener() {
		        public void actionPerformed (ActionEvent event) {
		          String texto = ((JButton) event.getSource()).getText();
		          System.out.println("Has clickado el boton con texto: " + texto);
		          actionPerformed_buttonEliminarRegio(event);
		          
		        }
		      });
		buttonEliminarRegioCasella.addActionListener
	      (new ActionListener() {
		        public void actionPerformed (ActionEvent event) {
		          String texto = ((JButton) event.getSource()).getText();
		          System.out.println("Has clickado el boton con texto: " + texto);
		          actionPerformed_buttonEliminarRegioCasella(event);
		          
		        }
		      });
		buttonAfegirOperacio.addActionListener
	      (new ActionListener() {
		        public void actionPerformed (ActionEvent event) {
		          String texto = ((JButton) event.getSource()).getText();
		          System.out.println("Has clickado el boton con texto: " + texto);
		          actionPerformed_buttonAfegirOperacio(event);
		          
		        }
		      });
		buttonAfegirObjectiu.addActionListener
	      (new ActionListener() {
		        public void actionPerformed (ActionEvent event) {
		          String texto = ((JButton) event.getSource()).getText();
		          System.out.println("Has clickado el boton con texto: " + texto);
		          actionPerformed_buttonAfegirObjectiu(event);
		          
		        }
		});
		buttonGuardar.addActionListener
	      (new ActionListener() {
		        public void actionPerformed (ActionEvent event) {
		          String texto = ((JButton) event.getSource()).getText();
		          System.out.println("Has clickado el boton con texto: " + texto);
		          actionPerformed_buttonGuardar(event);
		          
		        }
		});
	}

	protected void actionPerformed_buttonGuardar(ActionEvent event) {
		int aux=0;
		for(int i=0;i<tamany;i++)for(int j=0;j<tamany;j++){
			visit[i][j]=false;
		}
		
		for(int i=0;i<tamany;i++)for(int j=0;j<tamany;j++){
			if(visit[i][j]==false){ponerID(i,j,Regions[i][j],aux);aux++;}
		}
		//writeR();
		//writeId();
		cp.enviarTablero(Caselles,RegionsId);
	}

	private void writeId() {
		int j=0;
		for(int i=0;i<tamany;i++){
			System.out.println();
			for(j=0;j<tamany;j++){
				String s=String.valueOf(RegionsId[i][j]);
				System.out.print(s+" ");	
			}	
		}
		
	}

	private void writeR() {
		int j=0;
		for(int i=0;i<tamany;i++){
			System.out.println();
			for(j=0;j<tamany;j++){
				String s=String.valueOf(Regions[i][j]);
				System.out.print(s+" ");	
			}	
		}
		
	}

	private void ponerID(int x,int y,int c,int id) {
		visit[x][y]=true;
		RegionsId[x][y]=id;
		int i=0;
		for(int x1=x-1;x1<=x+1;x1++)for(int y1=y-1;y1<=y+1;y1++){
			if(x1<0){}
			else if(i%2==0){}
			else if(x1>=tamany){}
			else if(y1<0){}
			else if(y1>=tamany){}
			else if(Regions[x1][y1]==c && visit[x1][y1]==false){
				ponerID(x1,y1,c,id);
			}
			i++;
		}
	}

	protected void actionPerformed_buttonAfegirObjectiu(ActionEvent event) {
		CasillaCP b = (CasillaCP) panelTauler.getComponentAt(pulsaX, pulsaY);
		String valor= textfieldAfegirObjectiu.getText();
		Color aux= b.getColorOriginal();
		Point p=b.getXY();
		int x=p.x;
		int y=p.y;
		if(!aux.equals(new Color(0,0,255))){
			b.setObjectiu(valor);
			int i=0;
			for(int x1=x-1;x1<=x+1;x1++)for(int y1=y-1;y1<=y+1;y1++){
				if(x1<0){}
				else if(i%2==0){}
				else if(x1>=tamany){}
				else if(y1<0){}
				else if(y1>=tamany){}
				else if(Caselles[x1][y1].getColorOriginal().equals(aux)){
					auxAfegirObjectiu(Caselles[x1][y1],aux,valor);
				}
				i++;
			}
		}
		pulsat=false;
		b.ReturnColorOriginal();
	}

	private void auxAfegirObjectiu(CasillaCP b, Color aux, String valor) {
		Point p=b.getXY();
		int x=p.x;
		int y=p.y;
		b.setObjectiu(valor);
		int i=0;
		for(int x1=x-1;x1<=x+1;x1++)for(int y1=y-1;y1<=y+1;y1++){
			if(x1<0){}
			else if(i%2==0){}
			else if(x1>=tamany){}
			else if(y1<0){}
			else if(y1>=tamany){}
			else if(Caselles[x1][y1].getColorOriginal().equals(aux)){
				if(!Caselles[x1][y1].getObjectiu().equals(valor))auxAfegirObjectiu(Caselles[x1][y1],aux,valor);
			}
			i++;
		}
		pulsat=false;
		repintar();
	}

	protected void actionPerformed_buttonAfegirOperacio(ActionEvent event) {
		CasillaCP b = (CasillaCP) panelTauler.getComponentAt(pulsaX, pulsaY);
		String valor= (String) comboboxAfegirOperacio.getModel().getSelectedItem();
		Color aux= b.getColorOriginal();
		Point p=b.getXY();
		int x=p.x;
		int y=p.y;
		if(!aux.equals(new Color(0,0,255))){
			b.setOperacio(valor);
			int i=0;
			for(int x1=x-1;x1<=x+1;x1++)for(int y1=y-1;y1<=y+1;y1++){
				if(x1<0){}
				else if(i%2==0){}
				else if(x1>=tamany){}
				else if(y1<0){}
				else if(y1>=tamany){}
				else if(Caselles[x1][y1].getColorOriginal().equals(aux)){
					auxAfegirOperacio(Caselles[x1][y1],aux,valor);
				}
				i++;
			}
		}
		repintar();
		pulsat=false;
		b.ReturnColorOriginal();
	}

	private void auxAfegirOperacio(CasillaCP b, Color aux, String valor) {
		Point p=b.getXY();
		int x=p.x;
		int y=p.y;
		b.setOperacio(valor);
		int i=0;
		for(int x1=x-1;x1<=x+1;x1++)for(int y1=y-1;y1<=y+1;y1++){
			if(x1<0){}
			else if(i%2==0){}
			else if(x1>=tamany){}
			else if(y1<0){}
			else if(y1>=tamany){}
			else if(Caselles[x1][y1].getColorOriginal().equals(aux)){
				if(!Caselles[x1][y1].getOperacio().equals(valor))auxAfegirOperacio(Caselles[x1][y1],aux,valor);
			}
			i++;
		}
	}

	protected void actionPerformed_buttonEliminarRegioCasella(ActionEvent event) {
		CasillaCP b = (CasillaCP) panelTauler.getComponentAt(pulsaX, pulsaY);
		   Color c= new Color(0,0,255);
		   b.setColorOriginal(c);
		   Point p=b.getXY();
		   int x=p.x;
		   int y=p.y;
		   Regions[x][y]=0;
		   pulsat=false;
		   buidarComboboxCandidats();	
	}

	protected void actionPerformed_buttonEliminarRegio(ActionEvent event) {
		CasillaCP b = (CasillaCP) panelTauler.getComponentAt(pulsaX, pulsaY);
		Color aux = b.getColorOriginal();
		Color c= new Color(0,0,255);
		b.setColorOriginal(c);
		Point p=b.getXY();
		int x=p.x;
		int y=p.y;
		Regions[x][y]=0;
		pulsat=false;
		buidarComboboxCandidats();
		int i=0;
		for(int x1=x-1;x1<=x+1;x1++)for(int y1=y-1;y1<=y+1;y1++){
			if(x1<0){}
			else if(i%2==0){}
			else if(x1>=tamany){}
			else if(y1<0){}
			else if(y1>=tamany){}
			else if(Caselles[x1][y1].getColorOriginal().equals(aux)){
				Caselles[x1][y1].setColorOriginal(c);
				auxEliminarRegio(Caselles[x1][y1],aux);
			}
			i++;
		}
	}

	private void auxEliminarRegio(CasillaCP b,Color aux) {
		Color c = new Color(0,0,255);
		Point p=b.getXY();
		int x=p.x;
		int y=p.y;
		Regions[x][y]=0;
		pulsat=false;
		buidarComboboxCandidats();
		int i=0;
		for(int x1=x-1;x1<=x+1;x1++)for(int y1=y-1;y1<=y+1;y1++){
			if(x1<0){}
			else if(i%2==0){}
			else if(x1>=tamany){}
			else if(y1<0){}
			else if(y1>=tamany){}
			else if(Caselles[x1][y1].getColorOriginal().equals(aux)){
				Caselles[x1][y1].setColorOriginal(c);
				auxEliminarRegio(Caselles[x1][y1],aux);
			}
			++i;
		}
		
	}

	protected void actionPerformed_buttonAfegirRegio(ActionEvent event) {
		Object valor= comboboxAfegirRegio.getModel().getSelectedItem();
		if(pulsat && !valor.equals(auxAfegirRegio1)){
			CasillaCP b = (CasillaCP) panelTauler.getComponentAt(pulsaX, pulsaY);
			Color ant = b.getColorOriginal();
			if(valor.equals(auxAfegirRegio2)){
				boolean[] aux=comprovarColor(b);
				
					b.AfegirRegio(aux);
					Color c = b.getColorOriginal();
					actualitzarRegio(b,c);
					pulsat=false;
				
			}
			else{
				Color c = (Color) valor;
				b.setColor(c);
				b.setColorOriginal(c);
				pulsat=false;
				if(maxRegions(b)){b.setColorOriginal(ant);}
				actualitzarRegio(b,b.getColorOriginal());
			}
		}
	}

	

	private boolean maxRegions(CasillaCP aux) {
		 for(int i=0;i<tamany;i++)for(int j=0;j<tamany;j++){
			 visit[i][j]=false;
		 }
		Color c= aux.getColorOriginal();
		Point p=aux.getXY();
		int x=p.x;
		int y=p.y;
		int res=1;
		visit[x][y]=true;
		if(!aux.equals(new Color(0,0,255))){
			int i=0;
			for(int x1=x-1;x1<=x+1;x1++)for(int y1=y-1;y1<=y+1;y1++){
				if(x1<0){}
				else if(i%2==0){}
				else if(x1>=tamany){}
				else if(y1<0){}
				else if(y1>=tamany){}
				else if(Caselles[x1][y1].getColorOriginal().equals(c)){
					if(visit[x1][y1]==false){
						visit[x1][y1]=true;
						res+=auxMaxRegions(Caselles[x1][y1],c);
					}
				}
				i++;
			}
		}
		pulsat=false;
		if(res<5) return false;
		else return true;
	}

	private int auxMaxRegions(CasillaCP aux, Color c) {
		int res=1;
		Point p=aux.getXY();
		int x=p.x;
		int y=p.y;
		/*if(Caselles[x][y].getColorOriginal().equals(c) && visit[x][y]==false){
			res=1;
		}*/
		int i=0;
		for(int x1=x-1;x1<=x+1;x1++)for(int y1=y-1;y1<=y+1;y1++){
			if(x1<0){}
			else if(i%2==0){}
			else if(x1>=tamany){}
			else if(y1<0){}
			else if(y1>=tamany){}
			else if(Caselles[x1][y1].getColorOriginal().equals(c)){
				if(visit[x1][y1]==false){
					visit[x1][y1]=true;
					res+=auxMaxRegions(Caselles[x1][y1],c);
				}
			}
			i++;
		}
		return res;
	}

	private boolean[] comprovarColor(CasillaCP b) {
		Point p =b.getXY();
		boolean[] cols= new boolean[6];
		for(int i=0;i<6;i++)cols[i]=false;
		int x=p.x;
		int y=p.y;
		int i=0;
		for(int x1=x-1;x1<=x+1;x1++)for(int y1=y-1;y1<=y+1;y1++){
			if(x1<0){}
			else if(i%2==0){}
			else if(x1>=tamany){}
			else if(y1<0){}
			else if(y1>=tamany){}
			else{
				cols[Regions[x1][y1]]=true;
			}
			i++;
		}
		return cols;
	}

	

	@SuppressWarnings("deprecation")
	protected void actionPerformed_buttonAfegirValor(ActionEvent event) {
		String valor = (String) comboboxAfegirValor.getModel().getSelectedItem(); 
		if(pulsat && !valor.equals("Indica el valor que vols afegir")){
			CasillaCP b = (CasillaCP) panelTauler.getComponentAt(pulsaX, pulsaY);
			b.setValor(valor);
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
			  buidarComboboxCandidats();
			  posarComboboxCandidats(aux);
		  }	
		  repintar();
	  }

	 private void posarComboboxCandidats(CasillaCP aux) {
			boolean[] a= aux.getCandidats();
			comboboxAfegirRegio.removeAllItems();
			comboboxAfegirRegio.removeAllItems();
			comboboxAfegirRegio.addItem(auxAfegirRegio1);
			comboboxAfegirRegio.addItem(auxAfegirRegio2);
			int i=1;
			boolean[] b = comprovarColor(aux);
			for(i=1;i<b.length;i++){
				if(b[i]==true){
					comboboxAfegirRegio.addItem(ChooseColor(i));
				}
			}
			//if(i==b.length)comboboxAfegirRegio.addItem(ChooseColor(0));
	 }

	private void buidarComboboxCandidats() {
		comboboxAfegirRegio.removeAllItems();
		comboboxAfegirRegio.removeAllItems();
		comboboxAfegirRegio.addItem(auxAfegirRegio1);
		comboboxAfegirRegio.addItem(auxAfegirRegio2);
	}
	private Color ChooseColor(int i) {
		Color c = new Color(0,0,255);
		switch(i){
			case 1: return color1;
			case 2: return color2;
			case 3: return color3;
			case 4: return color4;
			case 5: return color5;
			default:return c;
		}
	}
	private void actualitzarRegio(CasillaCP cas,Color c) {
			int i=0;
			if(c.equals(color1))i=1;
			else if(c.equals(color2))i=2;
			else if(c.equals(color3))i=3;
			else if(c.equals(color4))i=4;
			else if(c.equals(color5))i=5;
			Point p=cas.getXY();
			int x=p.x;
			int y=p.y;
			Regions[x][y]=i;
	}
	
}