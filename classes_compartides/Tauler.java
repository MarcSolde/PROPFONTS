package classes_compartides;
public class Tauler {
	
	protected Casilla_comp[][] tauler;
	protected Casilla_comp[][] solucio;
	protected int mida;
	
	/**
	 * Crea un tauler de mida NxN
	 */
	public Tauler(int N)
	{
		tauler = new Casilla_comp[N][N];
		solucio = new Casilla_comp[N][N];
		int i,j;
		for (i = 0; i < N; ++i) {
			for (j = 0; j < N; ++j) {
				tauler[i][j] = new Casilla_comp(new boolean[mida], false, 0);
				solucio[i][j] = new Casilla_comp(new boolean[mida], false, 0);
			}
		}
		mida = N;
	}
	
	/**
	 * Retorna la mida del tauler
	 */
	public int getMida()
	{
		return mida;
	}
	
	/**
	 * Retorna el valor de la casella a la posicio
	 * x,y del tauler
	 */
	public int getValorTauler(int x, int y)
	{
		return tauler[x][y].getValor();
	}
	
	/**
	 * Retorna el valor de la casella a la posicio
	 * x,y del tauler solucio
	 */
	public int getValorSolucio(int x, int y)
	{
		return solucio[x][y].getValor();
	}
	
	/**
	 * Posa n a la casella que hi ha a la posicio
	 * x,y del tauler
	 */
	public void setValorTauler(int x, int y, int n)
	{
		if (!tauler[x][y].isPor_defecto()) tauler[x][y].setValor(n);
	}
	
	/**
	 * Posa n a la casella que hi ha a la posicio
	 * x,y del tauler solucio
	 */
	public void setValorSolucio(int x, int y, int n)
	{
		solucio[x][y].setValor(n);
	}
	
	/**
	 * Esborra el numero de la casella que hi ha
	 * a la posicio x,y del tauler
	 */
	public void esborrarValorTauler(int x, int y)
	{
		if (!tauler[x][y].isPor_defecto()) tauler[x][y].setValor(0); //Considerem 0 = casella no te valor
	}
	
	/**
	 * Esborra el numero de la casella que hi ha
	 * a la posicio x,y del tauler
	 */
	public void esborrarValorSolucio(int x, int y)
	{
		solucio[x][y].setValor(0); //Considerem 0 = casella no te valor
	}
	
	/**
	 * Retorna els candidats de la casella que hi ha
	 * a la posicio x,y del tauler
	 */
	public boolean[] getCandidatsTauler(int x, int y)
	{
		return tauler[x][y].getCandidatos();
	}
	
	/**
	 * Retorna els candidats de la casella que hi ha
	 * a la posicio x,y del tauler solucio
	 */
	public boolean[] getCandidatsSolucio(int x, int y)
	{
		return solucio[x][y].getCandidatos();
	}
	
	/**
	 * Posa n com a candidat de la casella que hi ha
	 * a la posicio x,y del tauler
	 */
	public void setCandidatTauler(int x, int y, int n)
	{
		tauler[x][y].setCandidat(n);
	}
	
	/**
	 * Posa n com a candidat de la casella que hi ha
	 * a la posicio x,y del tauler
	 */
	public void setCandidatSolucio(int x, int y, int n)
	{
		solucio[x][y].setCandidat(n);
	}
	
	/**
	 * Esborra n dels candidats de la casella que hi
	 * ha a la posici� x,y del tauler
	 */
	public void esborrarCandidatTauler(int x, int y, int n)
	{
		tauler[x][y].esborrarCandidat(n);
	}
	
	/**
	 * Esborra n dels candidats de la casella que hi
	 * ha a la posici� x,y del tauler
	 */
	public void esborrarCandidatSolucio(int x, int y, int n)
	{
		solucio[x][y].esborrarCandidat(n);
	}
	
}
