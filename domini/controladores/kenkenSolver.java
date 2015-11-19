package domini.controladores;
import java.util.*;

import domini.classes.*;




/**
 * @author Marc Soldevilla
 * Clase que resol Kenken via KenKen
 */
public class kenkenSolver {
	
	//private static kenken_only_Suma ken; //aliasing del ken orig
	private static Scanner in;
	private static TaulerKenken t;
	private static int[][] temp;

	//private ArrayList<Regio> kenZ;	//aliasing del vector de zones orig
	/**
	 * Creadora de la classe buida 
	 */
	public kenkenSolver()  {
		
	}

	
	public static void Montar(){
		in = new Scanner(System.in);
		System.out.println("CHIVATO");
		System.out.println("digues tamany tauler ");
		int N = in.nextInt();
		t = new TaulerKenken(N);
		temp = new int[N][N];
		int count = 0;
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j){
				System.out.println("digues num regio a la posicio "+i+" "+j+": ");
				System.out.println("El numero de regions es: "+count);
				int rg = in.nextInt();
				if (rg > count) {
					Regio r = new Regio();
					System.out.println("digues l'objectiu i la operacio: ");
					int obj = in.nextInt();
					r.setObjectiu(obj);
					String s = in.next();
					r.setOperacio(s);
					//r.addCasella(t.getCasellaTauler(i, j));
					t.setRegioTauler(i, j, r);
					++count;
				}
				else {
					t.setRegioTauler(i, j, t.getRegioById(rg-1));
				}
			}
		}
		t.imprimirReg();
		int sol = 0;
		sol = backtracking2(0, 0, 0, t.getMida());
		System.out.println("sol : "+sol);
		if (sol == 1)System.out.println("TRUE");
		else System.out.println("false");
	}
	
	/**
	 * @param i Posicio de la matriu
	 * @param j idem
	 * @param sol numero de solucions obtingudes
	 * @param n Tamany del KenKen
	 * @return Retorna el numero de solucions (0, 1, >=2)
	 */
	public static int backtracking2(int i, int j, int sol, int n) {
		if (i == n) {
	        i = 0;
	        if (++j == n) {
	        	for(int x = 0; x<t.getMida(); x++){
	        		for(int y = 0; y<t.getMida(); y++){
	        			temp[x][y] = t.getValorTauler(x,y);
	        		}
	        	}
	        	return sol+1;
	        }
	    }

	    for (int num = 0; num < t.getMida() && sol < 2; ++num) {
	        if (viable(i, j, num+1)) {
	        	t.setValorTauler(i, j, num+1); 
				t.getRegio(i, j).setResultat(num+1);
				

				sol = backtracking2(i+1,j,sol, n);
				t.setValorTauler(i, j, 0);
				t.getRegio(i, j).unsetResultat(num+1); 
		    	 	
				
	        }
	    }
	    
	    return sol;
	}
	
	
	public boolean setTaulerAndSolve(TaulerKenken t) {
		this.t = t;
		int sol = 0;
		temp = new int[t.getMida()][t.getMida()]; 
		for (int i = 0; i < t.getMida();  ++i) {
			for (int j = 0; j < t.getMida(); ++j) {
				t.setValorTauler(i, j, 0);
				
			}
		}
		sol = backtracking2(0, 0, 0, t.getMida());
		if (sol == 1){
			for(int x = 0; x<t.getMida(); x++){
				for(int y = 0; y<t.getMida(); y++){
					t.setValorSol(x,y,temp[x][y]);
					
				}
			}
			return true;
		}
		else {
			System.out.print("Tiene "+sol+" soluciones");
			return false;
		}
	}
	
	/**
	 * @param i Posicio de la matriu
	 * @param j idem
	 * @param num numero a introduir al KenKen
	 * @return retorna cert si és un numero viable
	 */
	private static boolean viable (int i, int j, int num) {
		if (vertical(j, num) && horitzontal(i,num)) { 
			return checkZone(i, j, num);
		}
		else return false;
	}
	/**
	 * @param i Posicio de la fila
	 * @param num numero a colocar
	 * @return Diu si es pot cololocar mirant si ja esta colocat el num a la fila
	 */
	private static boolean horitzontal(int i, int num) {
		for(int x = 0; x< t.getMida(); x++){
			if(t.getValorTauler(i, x) == num) return false;
		}
		return true;
	}


	/**
	 * @param j Posicio de la columna
	 * @param num numero a cololocar
	 * @return  Diu si es pot cololocar mirant si ja esta colocat el num a la fila
	 */
	private static boolean vertical(int j, int num) {
		for(int x = 0; x< t.getMida(); x++){
			if(t.getValorTauler(x, j) == num) return false;
		}
		return true;
	}


	/**
	 * @param i Posicio de fila
	 * @param j Posicio de columna
	 * @param num numero a colocar
	 * @return Diu si es pot cololocar el numero a la regio sense crear conflicte.
	 */
	private static boolean checkZone (int i,int j, int num) {
		Regio res = t.getRegio(i, j); //Aliasing nena! Also, esta accedint a la mateixa @Mem que el ken.getCasilla(i, j).getZone() o sigui que cuidado 
		if(res.nearFull()) { 
			switch(res.getOperacio()) { //la funcion getOP devuelve la operacion que se debe hacer en la zona
				case "+": 
						if (num + res.getResultat() == res.getObjectiu() ) return true;
						else return false;
				case "*":
						if (num * res.getResultat() == res.getObjectiu()) return true;
						else return false;
				case "-":
						if (num - res.getResultat() == res.getObjectiu()) return true;
						else if (res.getResultat() - num == res.getObjectiu())return true;
						else return false;
				case "/":
						if (num / res.getResultat() == res.getObjectiu()) return true;
						else if (res.getResultat() / num == res.getObjectiu())return true;
						else return false;
				case "": 
						if (num == res.getObjectiu()) return true;
						else return false;
				default: System.out.println("operacio incorrecte, les operacions poden ser + - * /");
			}
		}
		else {
			switch(res.getOperacio()) {
				case "+": 
						if (num + res.getResultat() < res.getObjectiu()) return true; 
						else return false;
				case "*": 
						if (res.getObjectiu()%num == 0) return true; 
						else return false;
				case "-": 
						return true;
				case "/": 
						return true;
				case "": 
						System.out.println("Chivato de cas impossible");
						break;
				default: System.out.println("operacio incorrecte, les operacions poden ser + - * / "+res.getOperacio());
			}
		}
		System.out.println("Creacio erronea, sortint...");
		return false;
	}
/*	private static void setCandidats(int i, int j, int num, int n, boolean b) {
		if (b) {
				for (int k = 0; k < n; ++k) {
					if (k != i) t.esborrarCandidatTauler(k, j, num); //Funcion de clase casilla, marca en el vector de candidatos de la casilla que num no es candidato
				//	System.out.println("["+k+"]["+j+"] num="+(num+1)+": "+t.getCandidatsTauler(k, j)[num]);

				}
				for (int m = 0; m < n; ++m) {
					if (m != j) t.esborrarCandidatTauler(i, m, num); //Funcion de clase casilla, marca en el vector de candidatos de la casilla que num no es candidato
				//	System.out.println("["+i+"]["+m+"] num="+(num+1)+":"+t.getCandidatsTauler(i, m)[num]);

				}
		//ken.getCasilla(i, j).getZone().setCandidatsZona(i, j) //Esto no se implementa por el momento
		}
		else {
			for (int k = 0; k < n; ++k) {
				if (k != i) t.setCandidatTauler(k, j, num); //Funcion de clase casilla, marca en el vector de candidatos de la casilla que num no es candidato
			//	System.out.println("["+k+"]["+j+"] num="+(num+1)+": "+t.getCandidatsTauler(k, j)[num]);
			}
			for (int m = 0; m < n; ++m) {
				if (m != j) t.setCandidatTauler(i, m, num); //Funcion de clase casilla, marca en el vector de candidatos de la casilla que num no es candidato
			//	System.out.println("["+i+"]["+m+"]num="+(num+1)+":"+t.getCandidatsTauler(i, m)[num]);
			}	
		}
	}*/
		
}
