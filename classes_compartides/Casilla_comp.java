package classes_compartides;

public class Casilla_comp {

	private boolean candidatos[];
	private boolean por_defecto; // si esta en true es una casilla que esta
									// llena desde el comienzo.
	private int valor;

	/* Pre: cierto */
	public Casilla_comp() {
	}
	/* Post: se crea una casilla vac�a */

	/* Pre: cierto */
	public Casilla_comp(boolean candidatos[], boolean por_defecto, int valor) {
		this.candidatos = candidatos;
		this.por_defecto = por_defecto;
		this.valor = valor;
	}
	/*
	 * Post: se crea una casilla con la lista de candidatos y el valor dados por
	 * par�metros, y el booleano por_defecto (true -> dada desde el principio)
	 */

	public Casilla_comp(int n, boolean por_defecto) {
		candidatos = new boolean[n];
		int i;
		for (i = 0; i < n; ++i) {
			candidatos[i] = false;
		}
		this.por_defecto = por_defecto;
		valor = 0;
	}

	/* Pre: La lista de candidatos no est� vac�a */
	public boolean[] getCandidatos() {
		return candidatos;
	}

	public void setCandidat(int n) {
		this.candidatos[n] = true;
	}

	/* Post: se ha devuelto la lista de candidatos en un set. */

	/* Pre: cierto */
	public void setCandidatos(boolean candidatos[], int n) {
		this.candidatos = candidatos;
	}
	/* Post: candidatos es la nueva lista de candidatos del objeto. */

	/* Pre: n es un numero entre 0 y el tama��o del tablero */
	public void esborrarCandidat(int n) {
		this.candidatos[n] = false;
	}
	/* Post: n se ha borrado de la lista de candidatos de la casilla */

	/* Pre: la casilla no est� vac�a */
	public boolean isPor_defecto() {
		return por_defecto;
	}
	/* Post: devuelve si la casilla es de las colocadas por defecto */

	/* Pre: cierto */
	public void setPor_defecto(boolean por_defecto) {
		this.por_defecto = por_defecto;
	}
	/* Post: la casilla queda definida como "por defecto" o no */

	/* Pre: la casilla tiene valor */
	public int getValor() {
		return valor;
	}
	/* Post: devuelve el valor de la casilla */

	/* Pre: cierto */
	public void setValor(int valor) {
		this.valor = valor;
	}
	/* Post: el valor de la casilla queda actualizado */

}