package domini.controladores;
import java.util.*;

import domini.*;
import domini.classes.Partida;
import persistencia.CtrlData;

/**
 * 
 * @author pau
 *
 */
public class CtrlPartida{
	private Partida p;
	private int temps; 
	private int tamany;
	private boolean partida_acabada; 
	private CtrlTauler cTauler;
	private Scanner in;
	private static String usuari;
	
	
	/**
	 * creadora per defecte
	 */
	public CtrlPartida(){
		in = new Scanner(System.in);
	}
	/**
	 * 
	 * @param u asigna lusuari del ctrlpartida u
	 */
	public void assignarUser(String u){
		usuari = u;
	}
	/**
	 * 
	 * @param id - id de la partida
	 * @param usr - nom del usuari
	 */
	public void carregaPartida(String id, String usr){
		CtrlData cData = new CtrlData();
		p = cData.llegirPartida(id,usr);
	}
	/**
	 * 
	 * @param ken
	 */
	public void creaPartidaBD(String ken){
		CtrlData cData = new CtrlData();
		p = new Partida(cData.llegirTauler(ken));
	}
	
	public void creaPartidaUser(int tamany){
		p = new Partida(tamany);
		p.montar();
		kenkenSolver s = new kenkenSolver();
		if(s.setTaulerAndSolve(p.getTauler())){
			System.out.println("TAULER VALID");
			System.out.println("1: Guardar a la BD");
			System.out.println("2: Jugarlo");
			switch(in.nextInt()){
			case 1: CtrlData cdata = new CtrlData();
			boolean res = cdata.escriurePartida(p, usuari);
			if (!res) System.out.println("La partida no s'ha pogut guardar correctament");
			break;
			case 2: inGame();
			break;	
			}
		 
			
		}
		else System.out.println("TAULER INCORRECTE");
		
	}
	
	/** pre:	partida no inicialitzada
	 * post:	crea partida per jugar
	*/ 
	public void creaPartidaRand(int tamany){
		p = new Partida(tamany);
		Gen g = new Gen(p.getTauler());
		
		kenkenSolver s = new kenkenSolver();
		boolean stop = false;
		
		//kenkenSolver s1 = new kenkenSolver();
		//s1.setTaulerAndSolve(p1.getTauler());
		while(!stop){
			 p = new Partida(tamany);
			 
			 g = new Gen(p.getTauler());
			stop = s.setTaulerAndSolve(p.getTauler());
		}
		//p.getTauler().imprimirSol();
		System.out.println();
		System.out.println("TAULER : ");
		p.getTauler().imprimir();
		p.getTauler().imprimirReg();
	}
	/**
	 * 
	 * @return retorna la partida del controlador
	 */
	public Partida getPartida(){
		return p;
	}
	/**
	 * 
	 * @return retorna el boolea que indica si la partida s'ha acabat o no
	 */
	public Boolean getAcabada() {
		return partida_acabada;
	}
	/**
	 * funcio que inicialitza el joc
	 */
	public void inGame(){
		p.Start();
		boolean b = false;
		while(!b){
			System.out.println("Selecciona accio :");
			System.out.println("1: introduir valor de la pos x y ");
			System.out.println("2: imprimir tauler per pantalla ");
			System.out.println("3: comprovar el kenken");
			System.out.println("4: demanar una ajuda");
			System.out.println("8: guardar partida");
			System.out.println("9: QUIT");

			switch(in.nextInt()){
			case 1:
				System.out.println("introdueix coordenades x y, y el valor a colocar");
				int x = in.nextInt();
				int y = in.nextInt();
				if(x>p.getTauler().getMida() || y>p.getTauler().getMida()){
					System.out.println("POSICIO INCORRECTE <FORA DE RANG>");
				}
				else{
					p.getTauler().setValorTauler(x,y , in.nextInt());
				}
				break;
			case 2:
		
				System.out.println();
				System.out.println("TAULER : ");
				Partida.getTauler().imprimir();
				Partida.getTauler().imprimirReg();
				break;
			case 3:
				int i = 0;
				if(!p.comprovar(i));
				else if(p.partidaFi()){
					b = true;
					System.out.println("Sortint de la partida...");
					partida_acabada = true;
					
				}
				else{
					System.out.println("correcte de moment");
				}
				break;
			case 4: 
				p.getTauler().pista();
				p.getTauler().imprimir();
				break;
			case 8:
				CtrlData cdata = new CtrlData();
				boolean res = cdata.escriurePartida(p, usuari);
				if (!res){
					System.out.println("La partida no s'ha pogut guardar correctament");
					b = false;
					}
					else b = true;
				break;
			default: b = true;
			break;
			}
		}
	}
	

}



