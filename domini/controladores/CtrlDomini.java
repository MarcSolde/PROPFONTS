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

	public void crearTauler(int n) {
		ct=new CtrlTauler();
		ct.CrearTauler(n);
	}

	public void introduirValorCasellaCreacio(int i, int j, int valor) {
		ct.introduirValorCasella(i,j,valor);
		
	}

	public void introduirRegioCreacio(int i, int j, int id,String op,int obj) {
		ct.introduirRegio(i,j,id,op,obj);
		
	}
	public boolean teSolucioUnica() {
		return ct.teSolucioUnica();
		
	}

	public void GuardarTauler() {
		cd.escriureTauler(ct.getTauler(),1);	
	}

	public boolean introduirValorJugar(int x, int y, int n) {
		//cPar.setValor(x,y,n);
		return true;
	}

	public boolean borrarCandidatJugar(int x, int y, int n) {
		//cPar.borrarCandidat
		return true;
	}

	public boolean addCandidatJugar(int x, int y, int n) {
		//cPar.addCandidat(x,y,n);
		return true;
	}

	public void introduirSolucioCasellaCreacio(int i, int j, int valor) {
		ct.introduirSolucioCasella(i,j,valor);
		
	}

	public void borrarValorJugar(int x, int y) {
		//cPar.borrarValor(x,y);
		
	}

	public boolean Login(String u, String p) {
		if(cd.llegirUsuari(u, p)){
			cu.nouUsuari(u, p);
			return true;
		}
		return false;
	}

	public void resumirPartida() {
		
	}

	public String consultarRanking() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<String> ConsultaKenkenGuardats() {
		//return cd.consultarLlistaPartides();
		ArrayList<String> ls= new ArrayList<String>();
		ls.add("1");
		return ls;
	}

	public void CarregarPartida(String s) {
		Partida p=cd.llegirPartida(s, cu.getNom());
		ct.setTauler(p.getTauler());
	}

	public ArrayList<String> consultaPartidesGuardades() {
		ArrayList<String> ls= new ArrayList<String>();
		ls.add("1");
		return ls;
	}

	public void crearUsuari(String u, String p) {
		if(cd.escriureUsuari(u, p))cu.nouUsuari(u,p);
	}

	public void CarregarKenkenGuardat(String id) {
		ct.setTauler(cd.llegirTauler(id));
	}
	

	public void getMasResueltos() {
		cr.getMostSolved();
		
	}

	public void getMejoresTiempos() {
		cr.getBestTime();
		
	}

	public String[][] getMValors() {
		return ct.getMValors();
	}

	public String[][] getMObjectius() {
		
		return ct.getMObjectius();
	}

	public String[][] getMOperacions() {
		return ct.getMOperacions();
	}

	public int[][] getMRegions() {
		return ct.getMRegions();
	}

	public int getTamany() {
		return ct.getTamany();
	}

	public void novaPartida(int tam) {
		cPar.creaPartidaRand(tam);
		ct.setTauler(cPar.getPartida().getTauler());
		System.out.println("La mida es:" +ct.getTauler().getMida());
	}

	public void guardarPartida() {
		cPar.getPartida().setId(1);
		cd.escriurePartida(cPar.getPartida(),cu.getNom());
	}

	public void borrarPartida(String s) {
		cd.esborrarPartida(s,cu.getNom());
		
	}
}
