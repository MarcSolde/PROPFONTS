package domini.classes_compartides;

import java.io.Serializable;

/**
 * 	Clase compartida de casilla. Esta casilla define una casilla para un general 
 * 
 * 	@author		Grup 45 Sudoku
 *  @version	1.0
 *  @since		12-11-2015
 */
public class Casilla_comp implements Serializable {
	
	 private static final long serialVersionUID = 1L;
	/**
	 * Vector de los candidatos que contiene la casilla
	 * <p>
	 * Los candidatos se definen por un tama&ntilde;o. Las posiciones con el valor true definen el valor util segon su posici&oacute;n numerica. 
	 * </p>
	 */
    protected boolean candidatos[];
        /**
         * Define si el valor de una casilla esta definido o no
         */
    protected boolean por_defecto; // si esta en true es una casilla que esta llena desde el comienzo.
    /**
     * El valor definitivo de la casilla.
     * <p>
     * Si su valor es menor o igual que cero su valor o
     * si uno de sus numeros es mayor que el total de fila/columnas
     * </p>
     */
    protected int valor;
 
    /*Pre: cierto */    
    /**
     * Constructora por defecto
     * 
     */
    public Casilla_comp() {
    }
    /*Post: se crea una casilla vacía*/
 
    /*Pre: cierto */
    /**
     * Constructora con parametros de Casilla_comp
     * <p>
     * Definimos el contenido por completo de la casilla al crearla
     * </p>
     * @param candidatos vector de booleanos de las cuales su posicion+1 es un valor posible
     * @param por_defecto Define si esta casilla esta definida o no
     * @param valor El valor que contiene la casilla
     */
        public Casilla_comp(boolean candidatos[], boolean por_defecto, int valor) {
        this.candidatos = candidatos;
        this.por_defecto = por_defecto;
        this.valor = valor;
    }
    /*Post: se crea una casilla con la lista de candidatos y el valor dados por parámetros, y el booleano por_defecto (true -> dada desde el principio)*/
        /**
         * Constructa con parametros de Casilla_comp
         * <p>
         * Definimos una casilla nueva por completo
         * </p>
         * @param	n				Define el numero de candidatos posibles.
         * @param	por_defecto 	Define si el valor que adquiere la casilla es el que hay por defecto o no
         */
    public Casilla_comp(int n, boolean por_defecto)
    {
        candidatos = new boolean[n];
        int i;
        for (i = 0; i < n; ++i) {
                candidatos[i] = true;
        }
        this.por_defecto = por_defecto;
        valor = 0;
    }
       
    /*Pre: La lista de candidatos no está vacía*/
    /**
     * Devuelve los candidatos de la casilla en un vector de booleanos
     * 
     * 
     * @return	Devuelve un vector de booleanos de tama&ntilde;o variable
     */
    public boolean[] getCandidatos() {
        return candidatos;
    }    
    /**
     * A&ntilde;ade un candidato a la casilla
     * <p>
     * A&ntilde;ade al vector de candidatos posibles para el valor de la casilla la posibilidad de que el numero. 
     * Si existia con anterioridad no habran cambios apreciables.
     * </p>
     * @param	n 	Define el candidato que deseamos a&ntilde;adir.
     * @exception	java.lang.ArrayIndexOutOfBoundsException Ocurre al introducir un valor n que no se encuentra en el rango de valores validos 
     */ 
    public void setCandidat(int n) {
        this.candidatos[n] = true;
    }
   
    /*Post: se ha devuelto la lista de candidatos en un set. */
 
    /*Pre: cierto */
    /**
     * Actualiza la lista de candidatos posibles de la casilla
     * <p>
     * Los candidatos posibles de la casilla son retirados y se les da unos nuevos candidatos. 
     * </p>
     * @param candidatos vector que contiene los nuevos candidatos
     */
    public void setCandidatos(boolean candidatos[]) {
        this.candidatos = candidatos;
    }
    /*Post: candidatos es la nueva lista de candidatos del objeto. */
   
    /*Pre: n es un numero entre 0 y el tamañño del tablero*/
    /**
     * Retira un candidato de la casilla
     * <p>
     * Retira del vector de candidatos posibles el numero que define el candidato
     * </p>
     * @param	n 	Define el candidato que deseamos retirar.
     * @exception	java.lang.ArrayIndexOutOfBoundsException Ocurre al introducir un valor n que no se encuentra en el rango de valores validos
     */ 
    public void esborrarCandidat(int n)
    {
        this.candidatos[n] = false;
    }
    /*Post: n se ha borrado de la lista de candidatos de la casilla*/
   
    /*Pre: la casilla no está vacía */
    /**
     * Devuelve si la casilla ha sido definida por defecto
     * @return Devuelve un booleano: true si esta casilla ha sido definida por defecto, falso en caso contrario
     */
    public boolean isPor_defecto() {
        return por_defecto;
    }
    /*Post: devuelve si la casilla es de las colocadas por defecto */
 
 
    /*Pre: cierto */
    /**
     * Devuelve si la casilla ha sido definida por defecto
     * @param por_defecto Define si el valor de la casilla es el que hay por defecto o no
     */
    public void setPor_defecto(boolean por_defecto) {
        this.por_defecto = por_defecto;
    }
    /*Post: la casilla queda definida como "por defecto" o no */
 
    /* Pre: la casilla tiene valor */
    /**
     * Devuelve el valor asociado a esta casilla
     * @return Devuelve el valor asociado a esta casilla
     */
    public int getValor() {
        return valor;
    }
    /*Post: devuelve el valor de la casilla */
 
    /*Pre: cierto */
    /**
     * Asigna un valor a la casilla
     * @param valor El valor que queremos asignar a la casilla
     */
    public void setValor(int valor) {
        this.valor = valor;
    }
    /*Post: el valor de la casilla queda actualizado */
 
 
}