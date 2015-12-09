package domini.controladores;
import java.util.ArrayList;

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
	public void inicializarPresentacion() {

			vl.llamarVista();
			//vm.llamarVista();
			//vp.llamarVista();
			//vc.llamarVista();
	}
	public void enviarTablero(CasillaCP[][] caselles, int[][] regionsId) {
		cd.crearTauler(caselles.length);
		String valor[][] = new String[caselles.length][caselles.length];
		String obj[][] = new String[caselles.length][caselles.length];
		String op[][] = new String[caselles.length][caselles.length];
		for(int i=0;i<tamany;i++)for(int j=0;j<tamany;j++){
			CasillaCP c= caselles[i][j];
			valor[i][j]=c.getValor();
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
			vc.hacerInvisible();
			vm.hacerVisible();
			cd.GuardarTauler();
			//vp.llamarVista(valor,obj,op,regionsId);
		}
	}
	public void enviarTablero(String[][] valor, String[][] obj, String[][] op, int[][] reg) {
		vp.llamarVista(valor,obj,op,reg);
	}
	
	public boolean afegirValor(int x, int y, int n) {
		return cd.introduirValorJugar(x,y,n);
	}

	public boolean borrarCandidat(int x, int y, int n) {
		return cd.borrarCandidatJugar(x,y,n);
	}

	public boolean addCandidat(int x, int y, int n) {
		return cd.addCandidatJugar(x,y,n);
	}
	public void llamarCreacio() {
		 
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
		ve.llamarCarregarKenken();
		
	}
	public void CarregarPartida(String s) {
		if(!cd.CarregarPartida(s)){
			ve.llamarVista("La partida seleccionada no existeix");
		}
		this.setTamany(cd.getTamany());
		String[][] mv=cd.getMValors();
		String[][] obj=cd.getMObjectius();
		String[][] op=cd.getMOperacions();
		int[][] reg=cd.getMRegions();
		vp.llamarVista(mv, obj, op, reg);
	}
	public ArrayList<String> ConsultaKenkenGuardats() {
		return cd.ConsultaKenkenGuardats();
	}
	public ArrayList<String> ConsultaPartidesGuardades() {
		return cd.consultaPartidesGuardades();
	}
	public void CrearUsuari(String u, String p) {
		cd.crearUsuari(u,p);
		
	}
	public void llamarMenu() {
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
		cd.guardarPartida();
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
		ve.llamarComprovar(comprovar);
		
	}
	
}
