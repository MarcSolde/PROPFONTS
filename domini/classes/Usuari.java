package domini.classes;

import java.util.*;

import domini.classes_compartides.Jugador;

import java.lang.*;

public class Usuari extends Jugador {

	private static final long serialVersionUID = 1L;

	public Usuari(String nombre, String password) {
		// this.nombre = nombre;
		// this.password = password;
		super(nombre, password);
	}
}