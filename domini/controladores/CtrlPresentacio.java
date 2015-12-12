package domini.controladores;
import java.util.ArrayList;
import java.util.HashMap;

import presentacio.CasillaCP;
import presentacio.VistaCreacio;
import presentacio.VistaEmergente;
import presentacio.VistaLogin;
import presentacio.VistaMenu;
import presentacio.VistaPartida;
/**
 * 
 * @author arnau.zapata.i
 *
 */
public class CtrlPresentacio {
	int tamany=6;
	private CtrlDomini cd = new CtrlDomini(this);
	private VistaPartida vp= new VistaPartida(this);
	private VistaCreacio vc = new VistaCreacio(this);
	private VistaMenu vm= new VistaMenu(this);
	private VistaLogin vl=new VistaLogin(this);
	private VistaEmergente ve = new VistaEmergente(this);
	//private HashMap<String,String> idToNom= new HashMap<String,String>();
	//private HashMap<String,String> nomToId= new HashMap<String,String>();
	public void inicializarPresentacion() {

			vl.llamarVista();
			//vm.llamarVista();
			//vp.llamarVista();
			//vc.llamarVista();
	}
	public void enviarTablero(CasillaCP[][] caselles, int[][] regionsId) {
		
		String valor[][] = new String[caselles.length][caselles.length];
		String obj[][] = new String[caselles.length][caselles.length];
		String op[][] = new String[caselles.length][caselles.length];
		for(int i=0;i<tamany;i++)for(int j=0;j<tamany;j++){
			CasillaCP c= caselles[i][j];
			valor[i][j]=String.valueOf(c.getValorInt());
			int num=c.getValorInt();
			if(num>0){cd.introduirSolucioCasellaCreacio(i,j,num);}
			op[i][j]=c.getOperacio();
			obj[i][j]=c.getObjectiu();
			int id= regionsId[i][j];
			int objectiu=Integer.valueOf(c.getObjectiu());
			cd.introduirValorCasellaCreacio(i,j,num);
			cd.introduirRegioCreacio(i,j,id,op[i][j],objectiu);
		}
		if (cd.teSolucioUnica()) {
			System.out.println("te solucio unica");
			cd.GuardarTauler();
		}
		else{
			System.out.println("hi ha mes d'una solucio");
			vc.hacerVisible();
			ve.llamarVista("hi ha mes d'una solucio o els objectius son imposibles de realitzar");
			//vm.hacerVisible();
			
			//cd.GuardarTauler();
			/*vp=new VistaPartida(this);
			this.setTamany(cd.getTamany());
			vp.llamarVista(valor,obj,op,regionsId);*/
		}
	}
	public void enviarTablero(String[][] valor, String[][] obj, String[][] op, int[][] reg) {
		vp.llamarVista(valor,obj,op,reg);
	}
	
	public boolean afegirValor(int x, int y, int n) {
		return cd.introduirValorJugar(x,y,n);
	}

	
	public void llamarCreacio() {
		vc = new VistaCreacio(this);
		this.setTamany(tamany);
		cd.crearTauler(tamany);
		vc.llamarVista();
	}
	public void borrarValorJugar(int x, int y) {
		cd.borrarValorJugar(x,y);
		
	}
	public void llamarError(String string) {
		ve.llamarVista(string);
	}
	public boolean Login(String u, String p) {
		return cd.Login(u,p);
		
	}
	public void resumirPartida() {
		ve=new VistaEmergente(this);
		ve.llamarResumirPartida();
		//cd.resumirPartida();		
	}
	public void consultarRanking() {
		String s=cd.consultarRanking();
		
		
	}
	public void setTamany(int tam) {
		tamany=tam;
		vc.setTamany(tam);
		vp.setTamany(tam);
	}
	public void llamarCarregarKenken() {
		ve=new VistaEmergente(this);
		ve.llamarCarregarKenken();
		
	}
	public void CarregarPartida(String s) {
		if(!cd.CarregarPartida(s)){
			ve.llamarVista("La partida seleccionada no existeix");
		}
		else{
			vp= new VistaPartida(this);
			this.setTamany(cd.getTamany());
			String[][] mv=cd.getMValors();
			String[][] obj=cd.getMObjectius();
			String[][] op=cd.getMOperacions();
			int[][] reg=cd.getMRegions();
			vp.llamarVista(mv, obj, op, reg);
		}
		
	}
	public ArrayList<String> ConsultaKenkenGuardats() {
		return cd.ConsultaKenkenGuardats();
	}
	public ArrayList<String> ConsultaPartidesGuardades() {
		//ArrayList<String>ls= new ArrayList<String>();
		return cd.consultaPartidesGuardades();
	}
	public void CrearUsuari(String u, String p) {
		cd.crearUsuari(u,p);
		
	}
	public void llamarMenu() {
		vm= new VistaMenu(this);
		vm.llamarVista();
		
	}
	public void carregarKenkenGuardat(String id) {
		vm.hacerInvisible();
		vp= new VistaPartida(this);
		cd.CarregarKenkenGuardat(id);
		this.setTamany(cd.getTamany());
		String[][] mv=cd.getMValors();
		String[][] obj=cd.getMObjectius();
		String[][] op=cd.getMOperacions();
		int[][] reg=cd.getMRegions();
		vp.llamarVista(mv, obj, op, reg);
		//funcions necesaries per durlo a terme
		//vp.llamarVista(valor,obj,op,reg);
		//vp.llamarVista();
	}
	public void getMasResueltos() {
		cd.getMasResueltos();
		
	}
	public void getMejoresTiempos() {
		cd.getMejoresTiempos();
		
	}
	public void llamarEsborrarPartida() {
		ve=new VistaEmergente(this);
		ve.llamarEsborrarPartida();
		
	}
	public void llamarNovaPartida(int tam) {
		cd.novaPartida(tam);
		vp= new VistaPartida(this);
		setTamany(tam);
		String[][] mv=cd.getMValors();
		String[][] obj=cd.getMObjectius();
		String[][] op=cd.getMOperacions();
		int[][] reg=cd.getMRegions();
		vp.llamarVista(mv, obj, op, reg);
	}
	public void GuardaPartida() {
		if(cd.getid()==-1){
			ve=new VistaEmergente(this);
			ve.llamarGuardar();
		}
		else{
			cd.guardarPartida();
		}
	}
	public void borrarPartida(String s) {
		cd.borrarPartida(s);
		
	}
	public void pista() {
		cd.pista();
		String[][] mv=cd.getMValors();
		vp.afegirValor(mv);
		
	}
	public boolean comprovar() {
		return cd.comprovar();
	}
	public void llamarComprobar(boolean comprovar) {
		ve=new VistaEmergente(this);
		ve.llamarComprovar(comprovar);
		
	}
	public void llamarGuardar() {
		ve=new VistaEmergente(this);
		ve.llamarGuardar();		
	}
	public void Sobrescriu(String s) {
		cd.Sobrescriu(s);
		
	}
	/*public void Guarda() {
		cd.guardarPartida();
		
	}*/
	public void GuardarNou(String s) {
		if(cd.existePartida(s)){
			ve.llamarVista("Esa partida ya existe");
		}
		else cd.guardarNovaPartida(s);
		
	}
	public void llamarImportar() {
		ve.llamarImportar();
		
	}

	public ArrayList<String> obtener_BT() {
		return cd.obtener_BT();
		//return null;
}

	public void importar(String s) {
		cd.CarregarKenkenGuardat(s);
		vc= new  VistaCreacio(this);
		setTamany(tamany);
		String[][] obj=cd.getMObjectius();
		String[][] op=cd.getMOperacions();
		int[][] reg=cd.getMRegions();

		vc.llamarVista(obj,op,reg);

	}
	
}
