package domini.classes_compartides;

import java.io.Serializable;

/**
 * 	Clase compartida de una partida
 * 	<p>
 * 	Esta clase define la partida que un usuario crea para poder jugar una partida de un juego con un Tablero_comp 
 * 	</p>
 * 	@author		Grup 45 Sudoku
 *  @version	1.0
 *  @since		12-11-2015
 */
public class Partida_comp implements Serializable{
	
	 private static final long serialVersionUID = 1L;
	/**
	 * 	El tablero con el que trabajaremos esta partida
	 *  @see Tablero_comp
	 */	
    protected Tablero_comp tableroP;

	/**
	 * 	El usuario que jugara esta partida
	 *  @see Usuario_comp
	 */
    protected Usuario_comp usuarioP;
	/**
	 * 	Codigo integer para identificar la partida
	 */
    protected int id;

    /*Pre: cierto */
	/**
	 * 	Constructora sin parametros
	 */
    public Partida_comp() {

    }
    /* Post: se crea una partida sin parámetros */

    /*Pre: t, u existen y no están vacíos */
	/**
	 * 	Constructora con los parametros mostrados
	 * 
	 * 	@param	t	Define el tablero con el que jugaremos
	 *  @param	u	Define el usuario asociado a esta partida
	 * 	@param	id	Define el identificador del juego
	 */
    public Partida_comp(Tablero_comp t, Usuario_comp u, int id) {
    		this.tableroP = t;
    		this.usuarioP = u;
    		this.id = id;
    }
    /*Post: se crea una partida con el Tablero t, jugada por el usuario u, y con identificador de partida "id" */

    /*Pre: cierto */
    //para borrar, valor = 0;
    /**
     * 	Modifica el valor asignado a una casilla.
     * 	<p>
     * 	Si el valor asignado es 0, se considera que la casilla esta vac&iacute;a
     * 	</p>
     * 	@param 	i		posici&oacute;n fila
     * 	@param	j		posici&oacute;n columna
     * 	@param 	valor	valor que asignaremos al sistema 
     */
    public void modificarValorCasilla (int i, int j, int valor) {
    	tableroP.setValorTauler(i,j,valor);
    }
    /*Post: la casilla de la posicion (i,j) tiene el valor "valor" */
	/**
	 * 	Devuelve el tablero asignado a la partida
	 * 
	 * 	@return Devuelve el tablero
	 * 
	 */
    public Tablero_comp getTablero() {
    	return tableroP;
    }
	/**
	 * 	Devuelve el usuario asignado a la partida
	 * 
	 * 	@return Devuelve un objeto tipo Usuario_comp
	 */
    public Usuario_comp getUsuario() {
    	return usuarioP;
    }
	/**
	 * 	Devuelve el identificador de la partida
	 * 
	 * 	@return El identificador de la partida
	 */
    public Integer getID() {
    	return id;
    }
}
