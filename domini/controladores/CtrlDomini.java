package domini.controladores;

import persistencia.CtrlData;

/**
 * 
 * @author arnau.zapata.i
 *
 */
public class CtrlDomini {
	CtrlPresentacio cp;
	CtrlTauler ct;
	CtrlPartida cPar = new CtrlPartida();
	CtrlData cd = new CtrlData();
	public CtrlDomini(){
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
		cd.escriureTauler(ct.getTauler(),ct.getId());	
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
		return cd.llegirUsuari(u, p);
		//return false;
	}

	public void resumirPartida() {
		
	}

	public String consultarRanking() {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] ConsultaKenkenGuardats() {
		//return cd.consultarLlistaPartides();
		return null;
	}

	public void CarregarPartida(String s) {
		//cd.llegirPartida(id, s);
	}
}
