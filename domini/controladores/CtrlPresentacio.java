package domini.controladores;
import java.util.ArrayList;
import java.util.HashMap;

import presentacio.CasillaCP;
import presentacio.VistaCreacio;
import presentacio.VistaEmergente;
import presentacio.VistaLogin;
import presentacio.VistaMenu;
import presentacio.VistaPartida;
import presentacio.VistaRanking;
import presentacio.VistaInici;
/**
 * 
 * @author arnau.zapata.i
 *
 */
public class CtrlPresentacio {
	int tamany=6;

	private CtrlDomini cd = new CtrlDomini(this);
	private VistaPartida vp = new VistaPartida(this);
	private VistaCreacio vc = new VistaCreacio(this);
	private VistaInici vi;
	private VistaMenu vm;//= new VistaMenu(this);
	private VistaLogin vl=new VistaLogin(this);
	private VistaEmergente ve = new VistaEmergente(this);
	private VistaRanking vr = new VistaRanking(this);
	//private HashMap<String,String> idToNom= new HashMap<String,String>();
	//private HashMap<String,String> nomToId= new HashMap<String,String>();
	/**
	 * inicialitza la interficie
	 */
	public void inicializarPresentacion() {
			//vi = new VistaInici(this);
			
		vl.llamarVista();
			//vm.llamarVista();
			//vp.llamarVista();
			//vc.llamarVista();
	}
	
	public void ini(){
		vl.llamarVista();
	}
	/**
	 * donades una matriu de caselles y de id de les regions per cada casella, si es solucio
	 * unica guarda el tauler, sino envia un misatge de error.
	 * @param caselles Matriu de caselles
	 * @param regionsId id de les regions per cada casella, si es solucio
	 */
	public void enviarTablero(CasillaCP[][] caselles, int[][] regionsId) {
		
		String valor[][] = new String[caselles.length][caselles.length];
		String obj[][] = new String[caselles.length][caselles.length];
		String op[][] = new String[caselles.length][caselles.length];
		for(int i=0;i<tamany;i++)for(int j=0;j<tamany;j++){
			CasillaCP c= caselles[i][j];
			valor[i][j]=String.valueOf(c.getValorInt());
			int num=c.getValorInt();
			//if(num>0){cd.introduirSolucioCasellaCreacio(i,j,num);}
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
			
		}
	}
	
	/**
	 * afegeix en el tauler de la capa de Domini el valor n de la Casella en la posicio x,y
	 * @param x posicio X del tauler
	 * @param y posicio Y del tauler
	 * @param n valor de la casella
	 */
	public void afegirValor(int x, int y, int n) {
		cd.introduirValorJugar(x,y,n);
	}

	/**
	 * Crida la vista VistaCreacio
	 */
	public void llamarCreacio() {
		vc = new VistaCreacio(this);
		this.setTamany(tamany);
		cd.crearTauler(tamany);
		vc.llamarVista();
	}
	/**
	 * elimina del tauler de la capa de Domini el valor de la Casella en la posicio x,y
	 * @param x posicio X del tauler
	 * @param y posicio Y del tauler
	 * @param n valor de la casella
	 */
	public void borrarValorJugar(int x, int y) {
		cd.borrarValorJugar(x,y);
		
	}
	/**
	 * fa apareixer una Finestra emergent que mostra un missatge de error
	 * @param string missatge de error que es vol introduir
	 */
	public void llamarError(String string) {
		ve.llamarVista(string);
	}
	/**
	 * Loguea l'usuari amb el Nom u y pasword p
	 * @param u Nom del usuari
	 * @param p Password del usuari
	 * @return retorna true si s'ha logueat y false el cas contrari
	 */
	public boolean Login(String u, String p) {
		return cd.Login(u,p);
		
	}
	/**
	 * fa apareixer una finestra emergent que et permet elegir quina partida vols carregar
	 */
	public void resumirPartida() {
		ve=new VistaEmergente(this);
		ve.llamarResumirPartida();
		//cd.resumirPartida();		
	}
	/**
	 * asigna el tamany del tauler a totes les Vistes y Ctrladors de la capa de presentacio que utilitzin aquesta dada
	 * @param tam tamany del tauler
	 */
	public void setTamany(int tam) {
		tamany=tam;
		vc.setTamany(tam);
		vp.setTamany(tam);
	}
	
	/**
	 * fa apareixer una finestra emergent que et permet elegir quin Kenken vols carregar
	 */
	public void llamarCarregarKenken() {
		ve=new VistaEmergente(this);
		ve.llamarCarregarKenken();
		
	}
	/**
	 * fa apareixer una finestra emergent que et permet elegir quina Partida vols carregar
	 */
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
	/**
	 * 
	 * @return arrayList amb tots els Kenkens guardatss
	 */
	public ArrayList<String> ConsultaKenkenGuardats() {
		return cd.ConsultaKenkenGuardats();
	}
	/**
	 * 
	 * @return arrayList amb tots els noms de les Partides guardades
	 */
	public ArrayList<String> ConsultaPartidesGuardades() {
		//ArrayList<String>ls= new ArrayList<String>();
		return cd.consultaPartidesGuardades();
	}
	/**
	 * guarda un nou usuari amb nom u y password p
	 * @param u nom del usuari
	 * @param p password del usuari
	 */
	public void CrearUsuari(String u, String p) {
		cd.crearUsuari(u,p);
		
	}
	/**
	 * Fa visible la vista del Menu
	 */
	public void llamarMenu() {
		vm= new VistaMenu(this);
		vm.llamarVista();
		
	}
	/**
	 * fa apareixer una partida amb el tauler carregat
	 * @param id id del tauler
	 */
	public void carregarKenkenGuardat(String id) {
		vm.hacerInvisible();
		cd.CarregarKenkenGuardat(id);
		this.setTamany(cd.getTamany());
		String[][] mv=cd.getMValors();
		String[][] obj=cd.getMObjectius();
		String[][] op=cd.getMOperacions();
		int[][] reg=cd.getMRegions();
		vp= new VistaPartida(this);
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
	/**
	 * fa apareixer una finestra emergent per elegir quina partida es vol eliminar
	 */
	public void llamarEsborrarPartida() {
		ve=new VistaEmergente(this);
		ve.llamarEsborrarPartida();
		
	}
	/**
	 * fa apareixer una nova partida amb tamany tam
	 * @param tam tamany de la partida
	 */
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
	/**
	 * si la partida no habia sigut guardada anteriorment fa parareixer una finestra emergent que et permet guardarla,
	 * si habia sigut guardada es guarda
	 */
	public void GuardaPartida() {
		if(cd.getid()==-1){
			ve=new VistaEmergente(this);
			ve.llamarGuardar();
		}
		else{
			cd.guardarPartida();
		}
	}
	/**
	 * Borra la partida amb nom s
	 * @param s Partida a borrar
	 */
	public void borrarPartida(String s) {
		cd.borrarPartida(s);
		
	}
	/**
	 * Coloca la posicio correcta de una de les caselles del tauler
	 */
	public void pista() {
		cd.pista();
		String[][] mv=cd.getMValors();
		vp.afegirValor(mv);
		
	}
	/**
	 * 
	 * @return cert si tots els valors son correctes y fals en cas contrari
	 */
	public boolean comprovar() {
		return cd.comprovar();
	}
	/**
	 * Fa apareixen una finestra emergent que et diu si el tauler es correcte o no segons el parametre comprovar
	 * @param comprovar indica si el tauler es correcte o no
	 */
	public void llamarComprobar(boolean comprovar) {
		ve=new VistaEmergente(this);
		ve.llamarComprovar(comprovar);
	}
		/**
		 * Fa apareixen una finestra emergent que et permet guardar la partida
		 */
	public void llamarGuardar() {
		ve=new VistaEmergente(this);
		ve.llamarGuardar();		
	}
	/**
	 * Sobrescriu la partida amb nom s per la partida a Guardar
	 * @param s nom de la partida a sobrescriure
	 */
	public void Sobrescriu(String s) {
		cd.Sobrescriu(s);
		
	}
	
	/**
	 * guarda una nova apartida amb nom s
	 * @param s nom de la nova partida
	 */
	public void GuardarNou(String s) {
		cd.guardarNovaPartida(s);
		
	}
	/**
	 * Fa apareixen una finestra emergent que et permet importar un tauler kenken
	 */
	public void llamarImportar() {
		ve.llamarImportar();
		
	}

	public ArrayList<String> obtener_BT() {
		return cd.obtener_BT();
		//return null;
}
/**
 * fa apareixer la VistaCreacio amb el tauler importat
 * @param s el nom del tauler importat
 */
	public void importar(String s) {
		cd.CarregarKenkenGuardat(s);
		vc= new  VistaCreacio(this);
		setTamany(cd.getTamany());
		String[][] obj=cd.getMObjectius();
		String[][] op=cd.getMOperacions();
		int[][] reg=cd.getMRegions();
		vc.llamarVista(obj,op,reg);

	}
	/**
	 * Fa apareixen una finestra emergent que t'indica que has acabat la partida
	 */
	public void llamarPartidaFi() {
		ve.llamarPartidaFi();
		cd.actualitzarRanking();
		/*if (p.getAcabada()) {
			cRank.anadir_valores_fin_partida(cUsr.getNom(), p.getPartida().getTauler().getMida(), p.getPartida().getTemps());
		}*/
		
	}
	/**
	 * 
	 * @return cert si la partida ha acabat
	 */
	public boolean partidaFi() {
		return cd.partidaFi();
	}
	/**
	 * 
	 * @return retorna el temps que ha durat el jugador en la partida
	 */
	public int getTemps() {
		return cd.getTemps();
	}
	/**
	 * fa apareixer la VistaRanking
	 */
	public void llamarRanking() {
		vr.llamarVista();
		
	}

	public ArrayList<String> obtener_BT_Tam(Integer value) {
		return cd.obtener_BT_tam(value);
	}
	/**
	 * 
	 * @return Matriu de booleans que indiquen casella per casella quina es incorrecte y quina no
	 */
	public boolean[][] getMatriuIncorrecte() {
		return cd.getMatriuIncorrecte();
	}
	/**
	 * Totes les indicacions de que el valor introduit es incorrecte (casilla en color vermell) desapareixen
	 */
	public void allCorrect() {
		vp.allCorrect();
	}
	public ArrayList<String> obtener_MS() {
		return cd.obtener_MS();
	}
	
}
