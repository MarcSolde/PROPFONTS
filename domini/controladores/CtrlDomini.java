package domini.controladores;

import java.util.ArrayList;

import domini.classes.Partida;
import domini.classes.Ranking;
import domini.classes.TaulerKenken;
import persistencia.CtrlData;

/**
 * 
 * @author arnau.zapata.i
 *
 */
public class CtrlDomini {
	CtrlPresentacio cp;
	CtrlTauler ct = new CtrlTauler();
	CtrlPartida cPar = new CtrlPartida();
	CtrlData cd = new CtrlData();
	CtrlRanking cr = new CtrlRanking();
	CtrlUsuari cu = new CtrlUsuari();
	public CtrlDomini(CtrlPresentacio ctrlPresentacio){
		cp=ctrlPresentacio;
	}
	/**
	 * Crea un tauler Buit, sense regions
	 * @param n tamany del Tauler
	 */
	public void crearTauler(int n) {
		ct=new CtrlTauler();
		ct.CrearTauler(n);
		
	}
	/**
	 * introdueix ek valor 'valor' a la casella en la posicio i,j 
	 * @param i posicio X del tauler
	 * @param j posicio Y del tauler
	 * @param valor valor de la casella
	 */
	public void introduirValorCasellaCreacio(int i, int j, int valor) {
		ct.introduirValorCasella(i, j, valor);
		
	}
	/**
	 * afegeix una Regio a la Casella
	 * @param i posicio X del tauler
	 * @param j posicio Y del tauler
	 * @param id id de la regio
	 * @param op operacio de la regio
	 * @param obj objectiu de la regio
	 */
	public void introduirRegioCreacio(int i, int j, int id,String op,int obj) {
		ct.introduirRegio(i,j,id,op,obj);
		
	}
	/**
	 * 
	 * @return cert si el tauler te solucio unica
	 */
	public boolean teSolucioUnica() {
		return ct.teSolucioUnica();
		
	}
	/**
	 * Guarda el tauler
	 */
	public void GuardarTauler() {
		ArrayList<String> ls = cd.ListKenkens();
		int i=0;
		if(ls.isEmpty())i=1;
		else i=ls.size()+1;
		cd.escriureTauler(ct.getTauler(),i);
		cPar=new CtrlPartida();
		cPar.creaPartidaRand(ct.getTauler().getMida());
		cPar.setTauler(ct.getTauler());
	}
	/**
	 * introdueix el valor n de la casella en la posicio x,y en una partida
	 * @param x Posicio X del tauler
	 * @param y posicio Y del tauler
	 * @param n valor de la casella
	 */
	public void introduirValorJugar(int x, int y, int n) {
		cPar.afegirValor(x,y,n);
	}

/**
 * Esborra el valor de la casella en la posicio x,y
 * @param x Posicio X del tauler
 * @param y posicio Y del tauler
 */
	public void borrarValorJugar(int x, int y) {
		cPar.borrarValor(x,y);
	}
	/**
	 * Guarda nom del usuari y el password amb el cual s'ha loguejat
	 * @param u nom del usuari
	 * @param p password
	 * @return cert si ha pogut loguejarse, false en cas contrari
	 */
	public boolean Login(String u, String p) {
		if(cd.llegirUsuari(u, p)){
			cu.nouUsuari(u, p);
			return true;
		}
		return false;
	}


	/**
	 * 
	 * @return llista de id's de kenkens guardats
	 */
	public ArrayList<String> ConsultaKenkenGuardats() {
		//return cd.consultarLlistaPartides();
		ArrayList<String> ls= new ArrayList<String>();
		ls=cd.ListKenkens();
		return ls;
	}
/**
 * Carrega una partida guardada amb nom s
 * @param s nom de la Partida a Carregar
 * @return cert si ha pogut carregarla correctament, false en cas contrari
 */
	public boolean CarregarPartida(String s) {
		String id=cd.findId(s,cu.getNom());
		if(id.equals("-1")) return false;
		Partida p=cd.llegirPartida(id, cu.getNom());
		if(p.getId()==-1)return false;
		ct.setTauler(p.getTauler());
		cPar.setPartida(p);
		System.out.println("retorna true");
		 return true;
	}
/**
 * 
 * @return llista de noms de les partides guardades
 */
	public ArrayList<String> consultaPartidesGuardades() {
		ArrayList<String> ls= new ArrayList<String>();
		ls=cd.ListPartides(cu.getNom());
		return ls;
	}
	/**
	 * Crea un nou usuari amb nom u y password p
	 * @param u nom del usuari
	 * @param p password del usuari
	 */
	public void crearUsuari(String u, String p) {
		if(cd.escriureUsuari(u, p))cu.nouUsuari(u,p);
	}
/**
 * Carrega un tauler guardat amb id id
 * @param id el id del tauler que es vol carregar
 */
	public void CarregarKenkenGuardat(String id) {
		ct.setTauler(cd.llegirTauler(id));
		cPar=new CtrlPartida();
		cPar.creaPartidaRand(ct.getTauler().getMida());
		cPar.setTauler(ct.getTauler());
	}
	

	public void getMasResueltos() {
		cr.getMostSolved();
		
	}

	public void getMejoresTiempos() {
		cr.getBestTime();
		
	}
/**
 * 
 * @return Matriu amb tots els valors de les caselles
 */
	public String[][] getMValors() {
		return ct.getMValors();
	}
/**
* 
* @return Matriu amb tots els objectius de les regions en cadascuna de les posicions del tauler
*/
	public String[][] getMObjectius() {
		
		return ct.getMObjectius();
	}
/**
* 
* @return Matriu amb totes les operacions les regions en cadascuna de les posicions del tauler
*/
	public String[][] getMOperacions() {
		return ct.getMOperacions();
	}
/**
* 
* @return Matriu amb tots ids 
*/
	public int[][] getMRegions() {
		return ct.getMRegions();
	}
/**
 * 
 * @return tamany del tauler
 */
	public int getTamany() {
		return ct.getTamany();
	}
/**
 * genera una nova partida Random amb tamany tam
 * @param tam tamany del tauler
 */
	public void novaPartida(int tam) {
		cPar.creaPartidaRand(tam);
		ct.setTauler(cPar.getPartida().getTauler());
		System.out.println("La mida es:" +ct.getTauler().getMida());
	}
/**
 * Guarda la partida
 */
	public void guardarPartida() {
		cd.escriurePartida(cPar.getPartida(),cu.getNom());
	}
	/**
	 * Sobrescriu la partida actual per la partida guardada amb nom s
	 * @param s nom de la partida guardada
	 */
	public void Sobrescriu(String s) {
		String id= cd.findId(s, cu.getNom());
		cPar.getPartida().setId(Integer.valueOf(id));
		cd.escriurePartida2(cPar.getPartida(),cu.getNom(),s);
	}
/**
 * borra la partida guardada amb nom s
 * @param s nom de la partida guardada a borrar
 */
	public void borrarPartida(String s) {
		String id= cd.findId(s, cu.getNom());
		cd.esborrarPartida(id,cu.getNom());
		
	}
/**
 * dona una pista
 */
	public void pista() {
		cPar.pista();
		
	}
/**
 * 
 * @return cert si tots els valors introduits son correctes, false en cas contrari
 */
	public boolean comprovar() {
		return cPar.comprovar();
		
	}
/**
 * 
 * @return id de la partida
 */
	public int getid() {
		return cPar.getPartida().getId();
	}
/**
 * Guarda una nova partida amb nom s
 * @param s nom de la partida que es vol guardar
 */
	public void guardarNovaPartida(String s) {
		cd.escriurePartida2(cPar.getPartida(),cu.getNom(),s);
		
	}
/**
 * indica si existe la partida amb nom s
 * @param s nom de la partida 
 * @return cert si existeix, fals si no
 */
	public boolean existePartida(String s) {
		String id=cd.findId(s, cu.getNom());
		if(!id.equals("-1")) return false;
		return true;
	}
/**
 * 
 * @return cert si ha acabat, false si no
 */
	public boolean partidaFi() {
		return cPar.partidaFi();
	}

	public ArrayList<String> obtener_BT() {
		return cr.obtener_BT() ;
	}
	/**
	 * 
	 * @return retorna el temps que ha durat la partida
	 */
	public int getTemps(){
		return cPar.getTemps();
	}
/**
 * actualitza el ranking
 */
	public void actualitzarRanking() {
		int tam= this.getTamany();
		int temps = this.getTemps();
		cr.anadir_valores_fin_partida(cu.getNom(), tam, temps);
		
	}
/**
 * 
 * @return matriu que indica quina caselles tenen un valor incorrecte y quines no
 */
	public boolean[][] getMatriuIncorrecte(){
		return cPar.getMatriuIncorrecte();
	}

	public ArrayList<String> obtener_BT_tam(Integer value) {
		return cr.obtener_BT_tam(value);
		
	}

	public ArrayList<String> obtener_MS() {
		// TODO Auto-generated method stub
		return cr.obtener_MS();
	}

}
