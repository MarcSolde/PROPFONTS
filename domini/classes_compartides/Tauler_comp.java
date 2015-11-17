package domini.classes_compartides;

import domini.classes_compartides.Casilla_comp;

/**
 * @author Anna Mascaro
 *
 */
public class Tauler_comp {
	
	protected Casilla_comp[][] tauler;
	protected int mida;
	
	
	/**
	 * Creadora, crea un tauler buit, de mida N
	 * @param N mida del tauler
	 */
	public Tauler_comp(int N)
	{
		tauler = new Casilla_comp[N][N];
		int i,j;
		for (i = 0; i < N; ++i) {
			for (j = 0; j < N; ++j) {
				tauler[i][j] = new Casilla_comp(N, false);
			}
		}
		mida = N;
	}
	
	/**
	 * Obte la mida del tauler
	 * @return mida del tauler
	 */
	public int getMida()
	{
		return mida;
	}
	
	/**
	 * Obte el valor d'una casella del tauler
	 * @param x fila del tauler
	 * @param y columna del tauler
	 * @return valor que hi ha a la posicio x,y del tauler
	 */
	public int getValorTauler(int x, int y)
	{
		return tauler[x][y].getValor();
	}
	
	/**
	 * Posa un valor a una casella del tauler
	 * @param x fila del tauler
	 * @param y columna del tauler
	 * @param n valor entre 1 i mida del tauler
	 */
	public void setValorTauler(int x, int y, int n)
	{
		if (!tauler[x][y].isPor_defecto()) tauler[x][y].setValor(n);
	}
	
	
	/**
	 * Esborra el valor d'una casella del tauler
	 * @param x fila del tauler
	 * @param y columna del tauler
	 */
	public void esborrarValorTauler(int x, int y)
	{
		if (!tauler[x][y].isPor_defecto()) tauler[x][y].setValor(0); //Considerem 0 = casella no te valor
	}
	
	/**
	 * Obte els candidats d'una posicio del tauler
	 * @param x fila del tauler
	 * @param y columna del tauler
	 * @return els candidats de la posicio x,y del tauler
	 */
	public boolean[] getCandidatsTauler(int x, int y)
	{
		return tauler[x][y].getCandidatos();
	}
	
	/**
	 * Posa un candidat a una casella del tauler
	 * @param x fila del tauler
	 * @param y columna del tauler
	 * @param n valor entre 1 i mida del tauler
	 */
	public void setCandidatTauler(int x, int y, int n)
	{
		tauler[x][y].setCandidat(n);
	}
	
	
	/**
	 * Esborra un candidat d'una casella del tauler
	 * @param x fila del tauler
	 * @param y columna del tauler
	 * @param n valor entre 1 i mida del tauler
	 */
	public void esborrarCandidatTauler(int x, int y, int n)
	{
		tauler[x][y].esborrarCandidat(n);
	}
	

	/**
	 * Modifica si la casella es per defecte o no
	 * @param x fila del tauler
	 * @param y columna del tauler
	 * @param b si la casella es per defecte
	 */
	public void setCasellaPor_defecto(int x, int y, boolean b)
	{
		tauler[x][y].setPor_defecto(b);
	}
}
