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
		ct.introduirValorCasella(i, j, valor);
		
	}

	public void introduirRegioCreacio(int i, int j, int id,String op,int obj) {
		ct.introduirRegio(i,j,id,op,obj);
		
	}
	public boolean teSolucioUnica() {
		return ct.teSolucioUnica();
		
	}

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

	public boolean introduirValorJugar(int x, int y, int n) {
		cPar.afegirValor(x,y,n);
		return true;
	}

	public void introduirSolucioCasellaCreacio(int i, int j, int valor) {
		ct.introduirSolucioCasella(i,j,valor);
	}

	public void borrarValorJugar(int x, int y) {
		cPar.borrarValor(x,y);
	}

	public boolean Login(String u, String p) {
		if(cd.llegirUsuari(u, p)){
			cu.nouUsuari(u, p);
			return true;
		}
		return false;
	}

	public String consultarRanking() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<String> ConsultaKenkenGuardats() {
		//return cd.consultarLlistaPartides();
		ArrayList<String> ls= new ArrayList<String>();
		ls=cd.ListKenkens();
		return ls;
	}

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

	public ArrayList<String> consultaPartidesGuardades() {
		ArrayList<String> ls= new ArrayList<String>();
		ls=cd.ListPartides(cu.getNom());
		return ls;
	}

	public void crearUsuari(String u, String p) {
		if(cd.escriureUsuari(u, p))cu.nouUsuari(u,p);
	}

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
		cd.escriurePartida(cPar.getPartida(),cu.getNom());
	}
	public void Sobrescriu(String s) {
		String id= cd.findId(s, cu.getNom());
		cPar.getPartida().setId(Integer.valueOf(id));
		cd.escriurePartida2(cPar.getPartida(),cu.getNom(),s);
	}

	public void borrarPartida(String s) {
		String id= cd.findId(s, cu.getNom());
		cd.esborrarPartida(id,cu.getNom());
		
	}

	public void pista() {
		cPar.pista();
		
	}

	public boolean comprovar() {
		return cPar.comprovar();
		
	}

	public int getid() {
		return cPar.getPartida().getId();
	}

	public void guardarNovaPartida(String s) {
		cd.escriurePartida2(cPar.getPartida(),cu.getNom(),s);
		
	}

	public boolean existePartida(String s) {
		String id=cd.findId(s, cu.getNom());
		if(!id.equals("-1")) return false;
		return true;
	}

	public boolean partidaFi() {
		return cPar.partidaFi();
	}

	public ArrayList<String> obtener_BT() {
		return cr.obtener_BT() ;
	}
	
	public int getTemps(){
		return cPar.getTemps();
	}

	public String getNom() {
		return cu.getNom();
		
	}

	public void actualitzarRanking() {
		int tam= this.getTamany();
		int temps = this.getTemps();
		cr.anadir_valores_fin_partida(cu.getNom(), tam, temps);
		
		
	}

}
