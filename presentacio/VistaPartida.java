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
public class VistaPartida extends SuperVista{
	//principal
	private JPanel panelTauler = new JPanel();
	private JPanel panelOpcions = new JPanel();
	private JPanel panelAfegirValor = new JPanel();
	private JPanel panelAfegirCandidat = new JPanel();
	private JPanel panelBorrarCandidat = new JPanel();
	private JPanel panelPista = new JPanel();
	Color old;
	int RegionsId[][];
	int RegionsColor[][];
	CasillaCP Caselles[][];
	CasillaCP Select;
	Color colorDefecte = new Color(0,0,255);
	Color color1 = new Color(255,0,0);
	Color color2= new Color(0,255,0);
	Color color3= new Color(255,255,0);
	Color color4= new Color(255,0,255);
	Color color5= new Color(0,255,255);
	int tamany=6;
	int pulsaX=0;
	int pulsaY=0;
	boolean pulsat=false;
	boolean visit[][] = new boolean[tamany][tamany];
	boolean visit2[][] = new boolean[tamany][tamany];
	
	
	Color colorOriginal[][];
	
	//OPCIONS
	private JComboBox<String> comboboxAfegirValor = new JComboBox<String>();
	private JButton buttonAfegirValor = new JButton("A�adir");
	private JComboBox<String> comboboxAfegirCandidat = new JComboBox<String>();
	private JButton buttonAfegirCandidat = new JButton("A�adir Candidato");
	String auxAfegirCandidat = "indica el valor del candidato";
	private JComboBox<String> comboboxBorrarCandidat = new JComboBox<String>();
	private JButton buttonBorrarCandidat = new JButton("Borrar Candidato");
	private JButton buttonGuardarPartida = new JButton("GuardarPartida");
	private JButton buttonPista = new JButton("Pista");
	private JButton buttonComprovar = new JButton("Comprovar");
	String auxBorrarCandidat = "indica el valor del candidato";
	
	
	
	  public VistaPartida (CtrlPresentacio pCtrlPresentacion) {
	    System.out.println("isEventDispatchThread: " + SwingUtilities.isEventDispatchThread());
	    cp = pCtrlPresentacion;
	  }
	  
	  public void setTamany(int tam){
			 tamany=tam;
	   }
	  
	  public void llamarVista(){
		  hacerVisible();
		  inicializarComponentes();
		  repintar();
	  }
	  public void llamarVista(String[][] valor, String[][] obj, String[][] op, int[][] reg){
		  llamarVista();
		  RegionsId=reg;
		  int max=0;
		  RegionsColor= new int[tamany][tamany];
		  for(int i=0;i<reg.length;i++)for(int j=0;j<reg.length;j++){
			  if(reg[i][j]>max)max=reg[i][j];
		  }
		  for(int i=0;i<reg.length;i++)for(int j=0;j<reg.length;j++){
			  RegionsColor[i][j]=0;
		  }
		  cleanVisit(visit);
		  boolean regions[]= new boolean[max+1];
		  boolean ColorPosible[] = new boolean[6];
		  for(boolean b:ColorPosible)b=true;
		  visit[0][0]=true;
		  ponerColor(0,0,reg[0][0],1);
		  int idOld=RegionsId[0][0];
		  for(int i=0;i<tamany;i++)for(int j=0;j<tamany;j++){
			  	int idNew=RegionsId[i][j];
			  	if(idNew!=idOld){
			  		for (int k = 0; k < ColorPosible.length; k++) {
						ColorPosible[k]=true;
					}
			  		idOld=idNew;
			  	}
			  	if(visit[i][j]==false){
					ColorPosible=ColorPosible(i,j,RegionsId[i][j],ColorPosible);
					int color =0;
					boolean ok=false;
					for(int k=0;k<6 && !ok;k++){
						if(ColorPosible[k]){
							ok=true;
							color=k;
						}
					}
					ponerColor(i,j,reg[i][j],color);
				}
			}
		  for(int i=0;i<reg.length;i++)for(int j=0;j<reg.length;j++){
			  Color color = this.ChooseColor(RegionsColor[i][j]);
			  Caselles[i][j].setColorOriginal(color);
		  }
		  for(int i=0;i<reg.length;i++)for(int j=0;j<reg.length;j++){
			  CasillaCP c= Caselles[i][j];
			  c.setObjectiu(obj[i][j]);
			  c.setOperacio(op[i][j]);
			  c.setValor(valor[i][j]);
		  }
		  //llamarVista();
	  }
	  private boolean[] ColorPosible(int i, int j,int id, boolean[] colorPosible) {
		  visit[i][j]=true;
		  int a=0;
		  for(int x1=i-1;x1<=i+1;x1++)for(int y1=j-1;y1<=j+1;y1++){
				if(x1<0){}
				else if(a%2==0){}
				else if(x1>=tamany){}
				else if(y1<0){}
				else if(y1>=tamany){}
				else if(RegionsId[x1][y1]==id && visit[x1][y1]==false){
					ColorPosible(x1,y1,id,colorPosible);
				}
				else if(RegionsId[x1][y1]!=id){
					int color= RegionsColor[x1][y1];
					colorPosible[color]=false;
					colorPosible[0]=false;
				}
				a++;
			}
		 
		  return colorPosible;
	  }

	private void cleanVisit(boolean[][] visit){
			for(int i=0;i<visit.length;i++)for(int j=0;j<visit.length;j++)visit[i][j]=false;
		}
	  private void ponerColor(int x,int y,int c,int col) {
			visit[x][y]=true;
			RegionsColor[x][y]=col;
			Color color= ChooseColor(col);
			Caselles[x][y].setColorOriginal(color);
			int i=0;
			for(int x1=x-1;x1<=x+1;x1++)for(int y1=y-1;y1<=y+1;y1++){
				if(x1<0){}
				else if(i%2==0){}
				else if(x1>=tamany){}
				else if(y1<0){}
				else if(y1>=tamany){}
				else if(RegionsId[x1][y1]==c && !Caselles[x1][y1].getColorOriginal().equals(this.ChooseColor(col))){
					RegionsColor[x1][y1]=col;
					Caselles[x1][y1].setColorOriginal(color);
					ponerColor(x1,y1,c,col);
				}
				i++;
			}
		}
	  
	  public void inicializarComponentes(){
		  	inicializarMatrius();
		 	inicializar_frameVista();
		    inicializar_panelTauler();
		    anadirPanels();
		    inicializar_panelOpcions();
		    asignar_listenersComponentes(); //a hacer
		
	}
	  
	  private void anadirPanels() {
		  contentPane.setLayout(new BorderLayout());
		  contentPane.add(panelTauler,BorderLayout.WEST);
		  contentPane.add(panelOpcions,BorderLayout.EAST);
	}

	private void inicializarMatrius() {
			
			RegionsId= new int[tamany][tamany];
			for(int i=0;i<RegionsId.length;i++)for(int j=0;j<RegionsId.length;j++){
				RegionsId[i][j]=0;
			}
			visit= new boolean[tamany][tamany];
			 for(int i=0;i<tamany;i++)for(int j=0;j<tamany;j++){
				 visit[i][j]=false;
			 }
			Caselles= new CasillaCP[tamany][tamany];
		}
	  
	  private void inicializar_panelTauler() {
		    // Layout
		  colorOriginal= new Color[tamany][tamany];
		  panelTauler.setLayout(new GridLayout (tamany,tamany)); //a revisar
		    // Paneles
		  Caselles= new CasillaCP[tamany][tamany];
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
					        gr=gr*2;if(gr>255)gr=255;
					        r=r*2;if(r>255)r=255;
					        b=b*2;if(b>255)b=255;
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
			panelOpcions.setLayout(new BoxLayout(panelOpcions, BoxLayout.Y_AXIS));
		  	panelOpcions.add(panelAfegirValor);
		  	panelOpcions.add(panelAfegirCandidat);
		  	panelOpcions.add(panelBorrarCandidat);
		  	panelOpcions.add(panelPista);
		  	
		  	
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
		  	panelPista.add(buttonGuardarPartida);
		  	panelPista.add(buttonPista);
		  	panelPista.add(buttonComprovar);
		  	
		  	
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
		buttonGuardarPartida.addActionListener
	      (new ActionListener() {
		        public void actionPerformed (ActionEvent event) {
		          String texto = ((JButton) event.getSource()).getText();
		          System.out.println("Has clickado el boton con texto: " + texto);
		          actionPerformed_buttonGuardarPartida(event);
		          
		        }
		      });
		this.buttonPista.addActionListener
	      (new ActionListener() {
		        public void actionPerformed (ActionEvent event) {
		          String texto = ((JButton) event.getSource()).getText();
		          System.out.println("Has clickado el boton con texto: " + texto);
		          actionPerformed_buttonPista(event);
		          
		        }
		      });
		this.buttonComprovar.addActionListener
	      (new ActionListener() {
		        public void actionPerformed (ActionEvent event) {
		          String texto = ((JButton) event.getSource()).getText();
		          System.out.println("Has clickado el boton con texto: " + texto);
		          actionPerformed_buttonComprovar(event);
		          
		        }
		      });
	}

	protected void actionPerformed_buttonComprovar(ActionEvent event) {
		// TODO Auto-generated method stub
		
			cp.llamarComprobar(cp.comprovar());
		
	}

	protected void actionPerformed_buttonPista(ActionEvent event) {
		// TODO Auto-generated method stub
		cp.pista();
		
	}

	protected void actionPerformed_buttonGuardarPartida(ActionEvent event) {
		cp.GuardaPartida();
		this.hacerInvisible();
		cp.llamarMenu();
		
	}

	protected void actionPerformed_buttonBorrarCandidat(ActionEvent event) {
		if(pulsat==false){
			cp.llamarError("No has pulsat cap Casella");
		}
		else{
			String valor = (String) comboboxBorrarCandidat.getModel().getSelectedItem(); 
			if(!valor.equals(auxBorrarCandidat)){
				//CasillaCP b = (CasillaCP) panelTauler.getComponentAt(pulsaX, pulsaY);
				CasillaCP b=Select;
				Point p=b.getXY();
				int x=p.x;
				int y=p.y;
				int n=Integer.valueOf(valor);
				if(cp.borrarCandidat(x,y,n)){
					b.eraseCandidat(valor);	
				}
				posarComboboxCandidats(b);
			}
			else{
				cp.llamarError("No has seleccionat cap candidat");
			}
			repintar();	
		}
		
	}

	protected void actionPerformed_buttonAfegirCandidat(ActionEvent event) {
		if(pulsat==false){
			cp.llamarError("No has pulsat cap Casella");
		}
		else{
			String valor = (String) comboboxAfegirCandidat.getModel().getSelectedItem(); 
			if(!valor.equals(auxAfegirCandidat)){
				//CasillaCP b = (CasillaCP) panelTauler.getComponentAt(pulsaX, pulsaY);
				CasillaCP b=Select;
				Point p=b.getXY();
				int x=p.x;
				int y=p.y;
				int n=Integer.valueOf(valor);
				if(cp.addCandidat(x,y,n)){
					b.addCandidat(valor);
				}
				posarComboboxCandidats(b);
			}
			else{
				cp.llamarError("No has seleccionat cap candidat");
			}
			repintar();	
		}
		
		
	}

	protected void actionPerformed_buttonAfegirValor(ActionEvent event) {
		if(pulsat==false){
			cp.llamarError("No has pulsat cap Casella");
		}
		else{
			String valor = (String) comboboxAfegirValor.getModel().getSelectedItem(); 
			if(!valor.equals("Indica el valor que vols afegir")){
				//CasillaCP b = (CasillaCP) panelTauler.getComponentAt(pulsaX, pulsaY);
				CasillaCP b=Select;
				Point p=b.getXY();
				int x=p.x;
				int y=p.y;
				if(valor.equals("CAP")){
					cp.borrarValorJugar(x,y);
					b.borrarValor();
				}
				else{
					int n=Integer.valueOf(valor);
					if(cp.afegirValor(x,y,n)){
						b.setValor(valor);
						
					}
					else{
						b.setColor(Color.ORANGE);
					}
				}
				b.ReturnColorOriginal();
				buidarComboboxCandidats();
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
				  CasillaCP aux2= Select;
				  System.out.println("pulsaX:" + pulsaX+" ,pulsaY:"+pulsaY);
				  aux2.ReturnColorOriginal();
			  }
			  pulsat=true;
			  pulsaX=aux.getLocation().x;
			  pulsaY=aux.getLocation().y;
			  Select= (CasillaCP) panelTauler.getComponentAt(pulsaX,pulsaY);
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

	public void afegirValor(String[][] mv) {
		for(int i=0;i<mv.length;i++)for(int j=0;j<mv.length;j++){
			Caselles[i][j].setValor(mv[i][j]);
		}
		
	}
}