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
public class VistaCreacio extends SuperVista{
	 private CtrlPresentacio cp;
	 
	//principal
	private JPanel panelTauler = new JPanel();
		private JPanel panelAfegirValor = new JPanel();
		private JPanel panelRegio = new JPanel();
		private JPanel panelOperacions = new JPanel();
	Color old;
	Color colorDefecte = new Color(0,0,255);
	Color color1 = new Color(255,0,0);
	Color color2= new Color(0,255,0);
	Color color3= new Color(255,255,0);
	Color color4= new Color(255,0,255);
	Color color5= new Color(0,255,255);
	boolean manteniendo=false;
	boolean maxRegioManteniendo=false;
	int tamany=6;
	int pulsaX=0;
	int pulsaY=0;
	boolean pulsat=false;
	
	int Regions[][];
	int RegionsId[][];
	CasillaCP Caselles[][];
	boolean visit[][];
	ArrayList<CasillaCP> lc= new ArrayList<CasillaCP>();
	
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
	
	
	
	
	  public VistaCreacio (CtrlPresentacio ctrlPresentacio) {
	    System.out.println("isEventDispatchThread: " + SwingUtilities.isEventDispatchThread());
	    cp = ctrlPresentacio;
	    //inicializarComponentes();
	    
	  }
	  public void llamarVista(){
		  hacerVisible();
		  repintar();
		  inicializarComponentes();
	  }
	
	  
	  public void inicializarComponentes(){
		    inicializarMatrius();
		 	inicializar_frameVista();
		 	anadirPanels();
		    inicializar_panelTauler();
		    inicializar_panelOpcions();
		    asignar_listenersComponentes(); //a hacer
	}
	  
	 public void setTamany(int tam){
		 this.tamany=tam;
	 }

	private void anadirPanels() {
		 contentPane.setLayout(new BorderLayout());
		 contentPane.add(panelTauler,BorderLayout.WEST);
		 contentPane.add(panelOpcions,BorderLayout.EAST);
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
	  
	  private void inicializar_panelTauler() {
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
		        @Override
				public void mouseEntered(MouseEvent e) {
					CasillaCP aux = (CasillaCP)e.getSource();
			       if(manteniendo){
			        	auxAfegirRegio(aux,lc.get(0));
			        }
			        else{
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
			        
				}

				@Override
				public void mouseExited(MouseEvent e) {
					CasillaCP aux = (CasillaCP)e.getSource();
					Color c2=aux.getColor();
					if(!c2.equals(new Color(0,0,0))){
						Color c= aux.getColor();
				        int gr=c.getGreen();
				        int r=c.getRed();
				        int b=c.getBlue();
				        gr=gr*2;if(gr>255)gr=255;
				        r=r*2;if(r>255)r=255;
				        b=b*2;if(b>255)b=255;
				        Color c1=new Color(r,gr,b);
				        aux.setColor(c1);
					}
			        
				}
				@Override
				public void mousePressed(MouseEvent e) {
					manteniendo=true;
					CasillaCP c=(CasillaCP) e.getSource();
					lc.add(c);
					CasillaCP aux=(CasillaCP) panelTauler.getComponentAt(pulsaX, pulsaY);
					aux.ReturnColorOriginal();
				}
					
		

				@Override
				public void mouseReleased(MouseEvent e) {
					manteniendo=false;
					maxRegioManteniendo=false;
					lc.clear();
					pulsat=false;
					
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
			CasillaCP c=Caselles[i][j];
			if(c.getColor().equals(colorDefecte)){
				cp.llamarError("Color incorrecte");
				System.out.println("hi han caselles sense Regio");
				return;
			}
			else if(c.getObjectiu().equals("")){
				cp.llamarError("hi ha caselles sense objectiu");
				System.out.println("Objectiu buit");
				return;
			}
			else if(c.getOperacio().equals("")){
				cp.llamarError("hi ha caselles sense operacio");
				System.out.println("Operacio buida");
				return;
			}
		}
		cleanVisit();
		System.out.println("hasta aqui llega");
		for(int i=0;i<tamany;i++)for(int j=0;j<tamany;j++){
			if(visit[i][j]==false){ponerID(i,j,Regions[i][j],aux);aux++;}
		}
		writeR();
		writeId();
		System.out.println("hasta aqui llega");
		
		cp.enviarTablero(Caselles,RegionsId);
	}

	


	protected void actionPerformed_buttonAfegirObjectiu(ActionEvent event) {
		if(pulsat==false){
			cp.llamarError("No has pulsat cap Casella");
		}
		else{
			CasillaCP b = (CasillaCP) panelTauler.getComponentAt(pulsaX, pulsaY);
			String valor= textfieldAfegirObjectiu.getText();
			Color aux= b.getColorOriginal();
			Point p=b.getXY();
			int x=p.x;
			int y=p.y;
			cleanVisit();
			visit[x][y]=true;
			if(!aux.equals(colorDefecte)){
				if(!b.setObjectiu(valor)){
					cp.llamarError("Sols es poden afegir numeros");
				}
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
			else{
				cp.llamarError("No es poden afegir objectius en una casella sense Regio");
			}
			pulsat=false;
			b.ReturnColorOriginal();
		}
		
	}

	
	

	protected void actionPerformed_buttonAfegirOperacio(ActionEvent event) {
		if(pulsat==false){
			cp.llamarError("No has pulsat cap Casella");
		}
		else{
			CasillaCP b = (CasillaCP) panelTauler.getComponentAt(pulsaX, pulsaY);
			String valor= (String) comboboxAfegirOperacio.getModel().getSelectedItem();
			if(valor.equals("eligeix l'operacio")){
				cp.llamarError("no has elegit cap operacio");
			}
			else{
				Color aux= b.getColorOriginal();
				Point p=b.getXY();
				cleanVisit();
				int x=p.x;
				int y=p.y;
				if(!aux.equals(colorDefecte)){
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
				else{
					cp.llamarError("No es poden afegir Operacions en una casella sense Regio");
				}
				repintar();
				pulsat=false;
				b.ReturnColorOriginal();
			}
		}
			
}

	

	protected void actionPerformed_buttonEliminarRegioCasella(ActionEvent event) {
		if(pulsat==false){
			cp.llamarError("No has pulsat cap Casella");
		}
		else{
			   CasillaCP b = (CasillaCP) panelTauler.getComponentAt(pulsaX, pulsaY);
			   if(b.getColorOriginal().equals(colorDefecte)){
				   cp.llamarError("No te cap sentit eliminar una regio si la casella no te regio");
			   }
			   Color c= colorDefecte;
			   b.setColorOriginal(c);
			   Point p=b.getXY();
			   int x=p.x;
			   int y=p.y;
			   Regions[x][y]=0;
			   pulsat=false;
			   buidarComboboxCandidats();	
		}
	}

	protected void actionPerformed_buttonEliminarRegio(ActionEvent event) {
		if(pulsat==false){
			cp.llamarError("No has pulsat cap Casella");
		}
		else{
			CasillaCP b = (CasillaCP) panelTauler.getComponentAt(pulsaX, pulsaY);
			 if(b.getColorOriginal().equals(colorDefecte)){
				   cp.llamarError("No te cap sentit eliminar una regio si la casella no te regio");
			 }
			 else{
				 Color aux = b.getColorOriginal();
					Color c= colorDefecte;
					b.setColorOriginal(c);
					Point p=b.getXY();
					int x=p.x;
					int y=p.y;
					Regions[x][y]=0;
					pulsat=false;
					cleanVisit();
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
			
		}
		
	}

	

	protected void actionPerformed_buttonAfegirRegio(ActionEvent event) {
		if(pulsat==false){
			cp.llamarError("No has pulsat cap Casella");
		}
		else{
			String color= (String) comboboxAfegirRegio.getModel().getSelectedItem();
			if(!color.equals(auxAfegirRegio1)){
				CasillaCP b = (CasillaCP) panelTauler.getComponentAt(pulsaX, pulsaY);
				Color ant = b.getColorOriginal();
					//Color c = (Color) valor;
					Color c=StringToColor(color);
					b.setColor(c);
					b.setColorOriginal(c);
					pulsat=false;
					if(maxRegions(b)){b.setColorOriginal(ant);}
					else{
						actualitzarRegio(b,b.getColorOriginal());
						cleanVisit();
						String op=getOperacioEntorn(b);
						b.setOperacio(op);
						System.out.println(op);
						if(op.equals(" ")){
							Point p=b.getXY();
							int x=p.x;
							int y=p.y;
							cleanVisit();
							visit[x][y]=true;
							if(b.getColorOriginal().equals(Caselles[x+1][y].getColorOriginal())){
								
								auxAfegirOperacio(Caselles[x+1][y],b.getColorOriginal(),op);
							}
							if(b.getColorOriginal().equals(Caselles[x-1][y].getColorOriginal())){
								auxAfegirOperacio(Caselles[x-1][y],b.getColorOriginal(),op);
							}
							if(b.getColorOriginal().equals(Caselles[x][y+1].getColorOriginal())){
								auxAfegirOperacio(Caselles[x][y+1],b.getColorOriginal(),op);
							}
							if(b.getColorOriginal().equals(Caselles[x][y-1].getColorOriginal())){
								auxAfegirOperacio(Caselles[x][y-1],b.getColorOriginal(),op);
							}
						}
						cleanVisit();
						String obj=getObjectiuEntorn(b);
						if(!b.setObjectiu(obj)){
							cp.llamarError("Sols es pot afegir numeros");
						}
						System.out.println(obj);
						if(obj.equals(" ")){
							Point p=b.getXY();
							int x=p.x;
							int y=p.y;
							cleanVisit();
							visit[x][y]=true;
							if(b.getColorOriginal().equals(Caselles[x+1][y].getColorOriginal()) && x+1<tamany)auxAfegirObjectiu(Caselles[x+1][y],b.getColorOriginal(),obj);
							if(b.getColorOriginal().equals(Caselles[x-1][y].getColorOriginal()) && x-1>=0)auxAfegirObjectiu(Caselles[x-1][y],b.getColorOriginal(),obj);
							if(b.getColorOriginal().equals(Caselles[x][y+1].getColorOriginal()) && y+1<tamany)auxAfegirObjectiu(Caselles[x][y+1],b.getColorOriginal(),obj);
							if(b.getColorOriginal().equals(Caselles[x][y-1].getColorOriginal()) && y-1>=0)auxAfegirObjectiu(Caselles[x][y-1],b.getColorOriginal(),obj);
						}
						
					
				}
			}
			else{
				cp.llamarError("No has seleccionat cap Regio");
			}
		}
		
	}
	
	
	protected void auxAfegirRegio(CasillaCP b,CasillaCP aux) {
			Color color = aux.getColorOriginal();
			Color ant = b.getColorOriginal();
			String op= b.getOperacio();
			String obj=b.getObjectiu();
			b.setColor(color);
			b.setColorOriginal(color);
			pulsat=false;
			if(maxRegions(aux)){
				b.setColorOriginal(ant);
				b.setObjectiu(obj);
				b.setOperacio(op);
				}
			else{
				Point p=b.getXY();
				int x=p.x;
				int y=p.y;
				int i=0;
				boolean find=false;
				for(int x1=x-1;x1<=x+1;x1++)for(int y1=y-1;y1<=y+1;y1++){
					if(x1<0){}
					else if(i%2==0){}
					else if(x1>=tamany){}
					else if(y1<0){}
					else if(y1>=tamany){}
					else if(Caselles[x1][y1].getColorOriginal().equals(color)){
						CasillaCP c=Caselles[x1][y1];
						if(c.getObjectiu().equals(aux.getObjectiu()) && c.getOperacio().equals(aux.getOperacio()))find=true;
					}
					i++;
				}
				if(!find){
					b.setColorOriginal(ant);
					b.setObjectiu(obj);
					b.setOperacio(op);
				}		
				actualitzarRegio(b,b.getColorOriginal());
				cleanVisit();
				op=getOperacioEntorn(b);
				b.setOperacio(op);
				System.out.println(op);
				if(op.equals(" ")){
					p=b.getXY();
					x=p.x;
					y=p.y;
					cleanVisit();
					visit[x][y]=true;
					if(b.getColorOriginal().equals(Caselles[x+1][y].getColorOriginal())){
						
						auxAfegirOperacio(Caselles[x+1][y],b.getColorOriginal(),op);
					}
					if(b.getColorOriginal().equals(Caselles[x-1][y].getColorOriginal())){
						auxAfegirOperacio(Caselles[x-1][y],b.getColorOriginal(),op);
					}
					if(b.getColorOriginal().equals(Caselles[x][y+1].getColorOriginal())){
						auxAfegirOperacio(Caselles[x][y+1],b.getColorOriginal(),op);
					}
					if(b.getColorOriginal().equals(Caselles[x][y-1].getColorOriginal())){
						auxAfegirOperacio(Caselles[x][y-1],b.getColorOriginal(),op);
					}
				}
				cleanVisit();
				obj=getObjectiuEntorn(b);
				b.setObjectiu(obj);
				System.out.println(obj);
				if(obj.equals(" ")){
					p=b.getXY();
					x=p.x;
					y=p.y;
					cleanVisit();
					visit[x][y]=true;
					if(b.getColorOriginal().equals(Caselles[x+1][y].getColorOriginal()) && x+1<tamany)auxAfegirObjectiu(Caselles[x+1][y],b.getColorOriginal(),obj);
					if(b.getColorOriginal().equals(Caselles[x-1][y].getColorOriginal()) && x-1>=0)auxAfegirObjectiu(Caselles[x-1][y],b.getColorOriginal(),obj);
					if(b.getColorOriginal().equals(Caselles[x][y+1].getColorOriginal()) && y+1<tamany)auxAfegirObjectiu(Caselles[x][y+1],b.getColorOriginal(),obj);
					if(b.getColorOriginal().equals(Caselles[x][y-1].getColorOriginal()) && y-1>=0)auxAfegirObjectiu(Caselles[x][y-1],b.getColorOriginal(),obj);
				}
				
			}
			
}

	

	

	private String getObjectiuEntorn(CasillaCP aux) {
		Point p = aux.getXY();
		int x=p.x;
		int y=p.y;
		boolean ok=false;
		String op="";
		visit[x][y]=true;
		Color c= aux.getColorOriginal();
		if(!aux.getColor().equals(colorDefecte)){
			int i=0;
			for(int x1=x-1;x1<=x+1;x1++)for(int y1=y-1;y1<=y+1;y1++){
				if(x1<0){}
				else if(i%2==0){}
				else if(x1>=tamany){}
				else if(y1<0){}
				else if(y1>=tamany){}
				else if(Caselles[x1][y1].getColorOriginal().equals(c) && visit[x1][y1] ==false){
					if(!ok && op.equals("")){
						ok=true;
						op=Caselles[x1][y1].getObjectiu();
					}
					else if(!ok && !op.equals("")){
						
						return " ";
					}
					else if(ok && !op.equals(Caselles[x1][y1].getObjectiu())){
						
						ok=false;
						return " ";
					}
				}
				i++;
			}
		}
		return op;
	}

	private String getOperacioEntorn(CasillaCP aux) {
		Point p = aux.getXY();
		int x=p.x;
		int y=p.y;
		boolean ok=false;
		String op="";
		Color c= aux.getColorOriginal();
		visit[x][y]=true;
		if(!aux.getColor().equals(colorDefecte)){
			int i=0;
			for(int x1=x-1;x1<=x+1;x1++)for(int y1=y-1;y1<=y+1;y1++){
				if(x1<0){}
				else if(i%2==0){}
				else if(x1>=tamany){}
				else if(y1<0){}
				else if(y1>=tamany){}
				else if(Caselles[x1][y1].getColorOriginal().equals(c) && !visit[x1][y1]){
					if(!ok && op.equals("")){
						ok=true;
						op=Caselles[x1][y1].getOperacio();
					}
					else if(!ok && !op.equals("")){
						return " ";
					}
					else if(ok && !op.equals(Caselles[x1][y1].getOperacio())){
						ok=false;
						return " ";
					}
				}
				i++;
			}
		}
		return op;
		
		
	}

	private boolean maxRegions(CasillaCP aux) {
		cleanVisit();
		Color c= aux.getColorOriginal();
		Point p=aux.getXY();
		int x=p.x;
		int y=p.y;
		int res=1;
		visit[x][y]=true;
		if(!aux.equals(colorDefecte)){
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
		if(maxRegioManteniendo) return true;
		if(res>4 && !manteniendo) return true;
		if(res>4 && manteniendo){
			maxRegioManteniendo=true;
			return true;
		}
		else return false;
	}

	private int auxMaxRegions(CasillaCP aux, Color c) {
		int res=1;
		Point p=aux.getXY();
		int x=p.x;
		int y=p.y;
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

	

	protected void actionPerformed_buttonAfegirValor(ActionEvent event) {
		if(pulsat==false){
			cp.llamarError("No has pulsat cap Casella");
		}
		else{
			String valor = (String) comboboxAfegirValor.getModel().getSelectedItem(); 
			if(!valor.equals("Indica el valor que vols afegir")){
				CasillaCP b = (CasillaCP) panelTauler.getComponentAt(pulsaX, pulsaY);
				b.setValor(valor);
				b.ReturnColorOriginal();
				pulsat=false;
			}
			else{
				cp.llamarError("No has seleccionat cap valor");
			}
			repintar();
		}
		
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
			  buidarComboboxCandidats();
			  posarComboboxCandidats(aux);
			  aux.setColor(c);
			 
		  }	
		  repintar();
	  }

	 
	 //FUNCIONES AUXILIARES
	 
	 private void posarComboboxCandidats(CasillaCP aux) {
			boolean[] a= aux.getCandidats();
			comboboxAfegirRegio.removeAllItems();
			comboboxAfegirRegio.removeAllItems();
			comboboxAfegirRegio.addItem(auxAfegirRegio1);
			for(int i=1;i<6;i++){
				Color c= this.ChooseColor(i);
				cleanVisit();
				Point p=aux.getXY();
				int x=p.x;
				int y=p.y;
				int res=1;
				int j=0;
				for(int x1=x-1;x1<=x+1;x1++)for(int y1=y-1;y1<=y+1;y1++){
					if(x1<0){}
					else if(j%2==0){}
					else if(x1>=tamany){}
					else if(y1<0){}
					else if(y1>=tamany){}
					else if(Caselles[x1][y1].getColorOriginal().equals(c)){
						if(visit[x1][y1]==false){
							visit[x1][y1]=true;
							res+=auxMaxRegions(Caselles[x1][y1],c);
						}
					}
					j++;
				}
				if(res<5){
					String s=ColorToString(c);
					comboboxAfegirRegio.addItem(s);
				}
			}
	 }

	

	private void buidarComboboxCandidats() {
		comboboxAfegirRegio.removeAllItems();
		comboboxAfegirRegio.removeAllItems();
		comboboxAfegirRegio.addItem(auxAfegirRegio1);
	}
	private Color ChooseColor(int i) {
		Color c = colorDefecte;
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
	private void auxAfegirObjectiu(CasillaCP b, Color aux, String valor) {
		Point p=b.getXY();
		int x=p.x;
		int y=p.y;
		if(!b.setObjectiu(valor)){
			cp.llamarError("Sols es pot afegir numeros");
		}
		int i=0;
		visit[x][y]=true;
		for(int x1=x-1;x1<=x+1;x1++)for(int y1=y-1;y1<=y+1;y1++){
			if(x1<0){}
			else if(i%2==0){}
			else if(x1>=tamany){}
			else if(y1<0){}
			else if(y1>=tamany){}
			else if(Caselles[x1][y1].getColorOriginal().equals(aux)){
				if(!Caselles[x1][y1].getObjectiu().equals(valor) && !visit[x1][y1])auxAfegirObjectiu(Caselles[x1][y1],aux,valor);
			}
			i++;
		}
		pulsat=false;
		repintar();
	}
	private void cleanVisit(){
		for(int i=0;i<visit.length;i++)for(int j=0;j<visit.length;j++)visit[i][j]=false;
	}
	private void auxEliminarRegio(CasillaCP b,Color aux) {
		Color c = colorDefecte;
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
	private void auxAfegirOperacio(CasillaCP b, Color aux, String valor) {
		Point p=b.getXY();
		int x=p.x;
		int y=p.y;
		b.setOperacio(valor);
		visit[x][y]=true;
		int i=0;
		for(int x1=x-1;x1<=x+1;x1++)for(int y1=y-1;y1<=y+1;y1++){
			if(x1<0){}
			else if(i%2==0){}
			else if(x1>=tamany){}
			else if(y1<0){}
			else if(y1>=tamany){}
			else if(Caselles[x1][y1].getColorOriginal().equals(aux)){
				if(!Caselles[x1][y1].getOperacio().equals(valor) && !visit[x1][y1])auxAfegirOperacio(Caselles[x1][y1],aux,valor);
			}
			i++;
		}
	}
	private Color StringToColor(String color) {
		if(color.equals("Rojo")) return new Color(255,0,0);
		if(color.equals("Verde")) return new Color(0,255,0);
		if(color.equals("Azul")) return new Color(0,0,255);
		if(color.equals("Amarillo")) return new Color(255,255,0);
		if(color.equals("Morado")) return new Color(255,0,255);
		if(color.equals("Cian")) return new Color(0,255,255);
		return new Color(0,0,0);
	}
	
	private String ColorToString(Color c) {
		if(c.equals(new Color(255,0,0)))return "Rojo";
		if(c.equals(new Color(0,255,0)))return "Verde";
		if(c.equals(new Color(0,0,255)))return "Azul";
		if(c.equals(new Color(255,255,0)))return "Amarillo";
		if(c.equals(new Color(255,0,255)))return "Morado";
		if(c.equals(new Color(0,255,255)))return "Cian";
		return "Negro";
	}
}