package domini.controladores;

import domini.classes.*;
import persistencia.CtrlData;

import java.util.*;
public class CtrlTauler {
	private TaulerKenken t;
	private kenkenSolver ks;
	public CtrlData cData;
	
	public CtrlTauler(){
		cData = new CtrlData();
	}
	
	public TaulerKenken getTauler(){
		return t;
	}
	
	public int getId(){
		return t.getId();
	}
	
	public TaulerKenken llegirTaulerFile(String id){
		t = cData.llegirTauler(id);
		return t;
	}

	public void CrearTauler(int n) {
		t=new TaulerKenken(n);
	}

	public void introduirValorCasella(int i, int j, int valor) {
		t.setValorTauler(i, j, valor);
	}

	public void introduirRegio(int i, int j, int id, String op, int obj) {
		if(t.getNumRegions()<=id || t.getNumRegions()<1){
			t.setNovaRegio(i, j, op, obj);
		}
		else{
			t.setRegioById(i, j, id);
		}
		
	}

	public boolean teSolucioUnica() {
		ks= new kenkenSolver();
		if(ks.setTaulerAndSolve(t)){
			return true;
		}
		else return false;
		
	}

	public void introduirSolucioCasella(int i, int j, int valor) {
		t.setValorSol(i, j, valor);	
	}
	
	//public TaulerKenken llegirTaulerInput
}
