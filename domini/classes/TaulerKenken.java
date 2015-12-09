
package domini.classes;

import java.util.ArrayList;
import java.util.Random;

import domini.classes_compartides.Casilla_comp;
import domini.classes_compartides.Tablero_comp;

/**
 * Created by Anna on 12/10/2015.
 */
public class TaulerKenken extends Tablero_comp implements java.io.Serializable {

	private Casilla_comp[][] solucio;
	private ArrayList<Regio> taulerReg;
	public int[][] regions;
	private boolean taulerPle;
	private int id;

	/**
	 * Creadora de la classe TaulerKenken
	 */
	public TaulerKenken(int N) {
		super(N);
		taulerPle = false;
		taulerReg = new ArrayList<Regio>();
		solucio = new Casilla_comp[mida][mida];
		regions = new int[mida][mida];
		int i, j;
		for (i = 0; i < mida; ++i) {
			for (j = 0; j < mida; ++j) {
				solucio[i][j] = new Casilla_comp(mida, false);
				regions[i][j] = -1;
			}
		}
		id = -1;
	}
	
	/**
	 * Assigna un identificador al tauler
	 * @param id identificador del tauler
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Obte l'identificador del tauler
	 * @return identificador del tauler
	 */
	public int getId() {
		return id;
	}

	/**
	 * Posa una regio a una posicio del tauler
	 * @param x fila del tauler
	 * @param y columna del tauler
	 * @param reg regio que es vol afegir
	 */
	public void setRegioTauler(int x, int y, Regio reg) {
		if (!taulerReg.contains(reg)) {
			taulerReg.add(reg);
		}
		regions[x][y] = taulerReg.indexOf(reg);
		System.out.println("AAA pos "+x+" " + y+" se lhi ha ficat l'index " +taulerReg.indexOf(reg));
		reg.addCasella(tauler[x][y]);
	}

	/**
	 * Inicialitza i posa una regio a una posicio del tauler
	 * @param x fila del tauler
	 * @param y columna del tauler
	 * @param op operacio de la regio
	 * @param res resultat de la regio
	 * @return retorna l'identificador de la regio que hi ha a la posicio
	 * x,y amb operacio = op i resultat = res
	 */
	public int setNovaRegio(int x, int y, String op, int res) {
		Regio reg = new Regio();
		reg.setOperacio(op);
		reg.setObjectiu(res);
		taulerReg.add(reg);
		regions[x][y] = taulerReg.indexOf(reg);
		taulerReg.get(regions[x][y]).addCasella(solucio[x][y]);
		return taulerReg.indexOf(reg);
	}

	/**
	 * Posa una regio a una posicio del tauler
	 * @param x fila del tauler
	 * @param y columna del tauler
	 * @param id identificador de la regio
	 */
	public void setRegioById(int x, int y, int id) {
		regions[x][y] = id;
		taulerReg.get(id).addCasella(solucio[x][y]);
	}

	/**
	 * Obte el numero de regions del tauler
	 * @return numero de regions que te el tauler
	 */
	public int getNumRegions() {
		return taulerReg.size();
	}

	/**
	 * Imprimeix les regions del tauler
	 */
	public void imprimirReg() {
		System.out.println();
		System.out.println("MATRIU DE IDENTIFICADORS DE REGIONS: ");
		for (int i = 0; i < mida; i++) {
			for (int j = 0; j < mida; j++) {
				if (regions[i][j] >= 10)
					System.out.print(regions[i][j] + " ");
				else
					System.out.print(regions[i][j] + "  ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println(" MATRIU D'OPERACIONS: ");
		for (int i = 0; i < mida; i++) {
			for (int j = 0; j < mida; j++) {
				System.out.print(getRegio(i, j).getOperacio() + "  ");
			}
			System.out.println();
		}
		System.out.println();
		ArrayList<Boolean> usat = new ArrayList(taulerReg.size());
		for (int i = 0; i < taulerReg.size(); i++) {
			usat.add(true);
		}
		for (int i = 0; i < mida; i++) {
			for (int j = 0; j < mida; j++) {
				if (usat.get(regions[i][j])) {
					if (regions[i][j] < 10) {
						System.out.print(
								"ID: " + regions[i][j] + " " + " OBJECTIU: " + getRegio(i, j).getObjectiu() + " ");
					}

					else {
						System.out.print(
								"ID: " + regions[i][j] + " " + " OBJECTIU: " + getRegio(i, j).getObjectiu() + " ");
					}
					System.out.println();
				}
				usat.set(regions[i][j], false);
			}
			// System.out.println();
		}
		// System.out.println();

	}

	/**
	 * Obte el valor d'una casella de la solucio del tauler
	 * @param x fila del tauler
	 * @param y columna del tauler
	 * @return valor de la solucio de la casella a la posicio
	 * x,y del tauler
	 */
	public int getValorSol(int x, int y) {
		return solucio[x][y].getValor();
	}

	/**
	 * Escriu un valor a una casella de la solucio del tauler
	 * @param x fila del tauler
	 * @param y columna del tauler
	 * @param n valor entre 1 i mida del tauler
	 */
	public void setValorSol(int x, int y, int n) {
		solucio[x][y].setValor(n);

		// Comprovar que es pot afegir a aquella casella
		// return BOOLEAN
	}

	/**
	 * Esborra el valor d'una casella del tauler
	 * 
	 * @param x fila del tauler
	 * @param y columna del tauler
	 */
	public void esborrarValorTauler(int x, int y) {
		tauler[x][y].setValor(0);
	}

	/**
	 * Esborra el valor d'una casella de la solucio del tauler
	 * @param x fila del tauler
	 * @param y columna del tauler
	 */
	public void esborrarValorSolucio(int x, int y) {
		solucio[x][y].setValor(0);
	}

	
	/**
	 * Consulta la validesa d'una casella del tauler
	 * @param x fila del tauler
	 * @param y columna del tauler
	 * @return si el valor que hi ha  a la casella a
	 * la posicio x,y del tauler es correcte
	 */
	public boolean validesaCasella(int x, int y) {
		if (tauler[x][y].getValor() != solucio[x][y].getValor())
			return false;
		return true;
	}

	/**
	 * Retorna si el tauler es valid
	 * @param w valor entre 0 i 1
	 * @return si el tauler es valid o no
	 */
	public boolean taulerEsValid(int w) {
		int i, j;
		boolean total = true;
		for (i = 0; i < mida; ++i) {
			for (j = 0; j < mida; ++j) {
				if (tauler[i][j].getValor() != 0) {
					if (!validesaCasella(i, j)) {
						if (w == 3) {
							System.out.println(
								"incorrecte a : " + i + " " + j + " valor correcte : " + solucio[i][j].getValor());
						} else {
							System.out.println("incorrecte");
						}
						return false;
					}
				} else {

					total = false;
				}
			}
		}
		if (total) {
			taulerPle = true;
			w = 1;
			System.out.println("KENKEN CORRECTE!!");
		}
		return true;
	}

	/**
	 * Consulta si el tauler esta ple
	 * @return si el tauler esta ple o no
	 */
	public boolean taulerFi() {
		return taulerPle;
	}
	
	/**
	 * Escriu el valor d'una de les caselles
	 * buides del tauler
	 */
	public void pista() {
		Random rand = new Random();
		boolean pista = true;
		int it = 0;
		while (pista) {
			int x = rand.nextInt(mida);
			int y = rand.nextInt(mida);
			if (tauler[x][y].getValor() == 0) {
				tauler[x][y].setValor(solucio[x][y].getValor());
				pista = false;
			}
			
			if (it > mida * mida) {
				taulerEsValid(3);
				pista = false;
			}
			++it;
		}

	}

	/**
	 * Posa varies pistes al tauler
	 * @param n numero de pistes
	 */
	public void omplirPistes(int n) {
		for (int i = 0; i < n; ++i)
			this.pista();
	}

	/**
	 * Obte la regio que hi ha a una posicio del tauler
	 * @param x fila del tauler
	 * @param y columna del tauler
	 * @return regio que hi ha a la posicio x,y del tauler
	 */
	public Regio getRegio(int x, int y) {
		return taulerReg.get(regions[x][y]);
	}

	/**
	 * Obte una regio a partir del seu identificador
	 * @param id identificador de la regio
	 * @return regio del tauler amb identificador id
	 */
	public Regio getRegioById(int id) {
		return taulerReg.get(id);
	}

	public int getIdRegio(int x, int y) {
		return regions[x][y];
	}
	/**
	 * Imprimeix el tauler
	 */
	public void imprimir() {
		for (int i = 0; i < mida; ++i) {
			for (int j = 0; j < mida; ++j) {
				System.out.print(tauler[i][j].getValor());
				if (j == mida - 1)
					System.out.println();
			}
		}
	}

	/**
	 * Imprimeix la solucio del tauler
	 */
	public void imprimirSol() {
		for (int i = 0; i < mida; ++i) {
			for (int j = 0; j < mida; ++j) {
				System.out.print(solucio[i][j].getValor());
				if (j == mida - 1)
					System.out.println();
			}
		}
	}

	/**
	 * Obte la casella a una posicio del tauler
	 * @param x fila del tauler
	 * @param y columna del tauler
	 * @return casella a la posicio x,y del tauler
	 */
	public Casilla_comp getCasellaTauler(int x, int y) {
		return tauler[x][y];
	}
	// OPERACIONS PRIVADES
}
