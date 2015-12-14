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
	public void setTauler(TaulerKenken tau){
		t=tau;
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
		cData.guardarTauler(t);
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
	
	public String[][] getMValors(){
		int tam=t.getMida();
		String[][] mv = new String[tam][tam];
		for(int i=0;i<tam;i++)for(int j=0;j<tam;j++){
			mv[i][j]=String.valueOf(t.getValorTauler(i,j));
		}
		writeM(mv);
		return mv;
	}

	private void writeM(String[][] mv) {
		int tam=t.getMida();
		for(int i=0;i<tam;i++){
			System.out.println();
			for(int j=0;j<tam;j++){
				System.out.print(mv[i][j] + " ");
			}
			
		}
		System.out.println();
		
	}
	private void writeMInt(int[][] mv) {
		int tam=t.getMida();
		for(int i=0;i<tam;i++){
			System.out.println();
			for(int j=0;j<tam;j++){
				System.out.print(mv[i][j] + " ");
			}
			
		}
		System.out.println();
	}

	public String[][] getMObjectius() {
		int tam=t.getMida();
		String[][] mv = new String[tam][tam];
		for(int i=0;i<tam;i++)for(int j=0;j<tam;j++){
			mv[i][j]=String.valueOf(t.getRegio(i,j).getObjectiu());
		}
		writeM(mv);
		return mv;

	}

	public String[][] getMOperacions() {
		int tam=t.getMida();
		String[][] mv = new String[tam][tam];
		for(int i=0;i<tam;i++)for(int j=0;j<tam;j++){
			mv[i][j]=t.getRegio(i,j).getOperacio();
		}
		writeM(mv);
		return mv;

	}

	public int[][] getMRegions() {
		int tam=t.getMida();
		int[][] mv = new int[tam][tam];
		for(int i=0;i<tam;i++)for(int j=0;j<tam;j++){
			mv[i][j]=t.getIdRegio(i, j);
		}
		writeMInt(mv);
		return mv;

	}

	

	public int getTamany() {
		return t.getMida();
	}
	//public TaulerKenken llegirTaulerInput


}
