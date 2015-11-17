
package domini.classes;
import java.util.Scanner;

/**
 * 
 * @author pau
 *
 */
public class Partida{

	private int temps;
	private static int tamany;
	private boolean partida_acabada;
	private static TaulerKenken t;
	private int id;
	long tStart;
	double elapsedSeconds;
	
	/**
	 * 
	 * @param i - tamany del tauler que es vol crear 
	 */
	public Partida(int i){
		ini(i);
	}
	/**
	 * funcio que conta el temps en el moment en que s'inicialitza
	 */
	public void Start(){
		tStart = System.currentTimeMillis();
	}
	/**
	 * 
	 * @param t -creadora de tauler a partir dun tauler
	 */
	public Partida(TaulerKenken t){
		this.t = t;
		tamany = t.getMida();
		temps = 0;
		tStart = System.currentTimeMillis();
		partida_acabada = t.taulerFi();
		id = -1;
	}
	/**
	 * 
	 * @param i - tamany del tauler, lassigna al seu tauler 
	 */
	public void ini(int i){
		temps = 0;
		t = new TaulerKenken(i);
		tStart = System.currentTimeMillis();
		partida_acabada = t.taulerFi();
		id = -1;
	}
	/**
	 * afegeix una pista al tauler
	 */
	public void pista(){
		t.pista();
	}
	/**
	 * imprimeix les regions de tauler
	 */
	public void imprimirReg(){
		t.imprimirReg();
	}
	/**
	 * 
	 * @return tauler de la partida
	 */
	public static TaulerKenken getTauler(){
		return t;
	}
	
	
	/**
	 * 
	 * @param i
	 * @return comprova el tauler si es o no valid, a mes si es valid i veu que el tauler esta ple acaba la partida
	 */
	public boolean comprovar(int i){
		if(t.taulerEsValid( i)){
			partida_acabada = t.taulerFi();
			return true;
		}
		return false;
		}
	
	/**
	 * 
	 * @return bolea per comprovar si la partida ha acabat
	 */
	public boolean partidaFi(){
		return partida_acabada;
	}
	/**
	 * 
	 * @param b - boolea que indica si la partida ha acabat
	 */
	public void partidaFi(boolean b) {
		partida_acabada = b;
	}
	/**
	 * 
	 * @param t - temps de la partida
	 */
	public void setTemps(int t){
		temps = t;
	}
	/**
	 * monta un kenken definit per lusuari
	 */
	public static void montar(){
			int count = 0;
			Scanner in = new Scanner(System.in);
			tamany = getTauler().getMida();
			for (int i = 0; i < tamany; ++i) {
				for (int j = 0; j <tamany; ++j){
					System.out.println("digues num regio (>0), a la posicio "+i+" "+j+": ");
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
		}

	/**
	 * 
	 * @return temps de la partida
	 */
	public int getTemps(){
		long tEnd = System.currentTimeMillis();
		long tDelta = tEnd - tStart;
		elapsedSeconds = tDelta / 1000.0;
		int sec = 0;
		int min = 0; 
		int h = 0;
		sec = (int)elapsedSeconds;
		int temp = sec;
		min = sec/60;
		h = min/60;
		sec = sec%60;
		System.out.println("temps de partida "+ sec + " segons, " + min +" minut/s, i " + h+ " hora/es");
		return temp;
	}
	/**
	 * 
	 * @param id - identificador de partida
	 */
	public void setId (int id) {
		this.id = id;
	}
	/**
	 * 
	 * @return l'identificador de partida
	 */
	public int getId () {
		return id;
	}

	}
