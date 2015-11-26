package domini.controladores;
import java.io.*;
import java.util.*;

import domini.classes.Partida;
import domini.classes.Usuari;


public class CtrlUsuari{
	
	private	Usuari usuari;

	
	/**
	 * creadora
	 */
	public CtrlUsuari(){

	}
	
	/**
	 * 
	 * @param nom
	 * @param pwd
	 */
	public void nouUsuari(String nom, String pwd){
		usuari = new Usuari(nom,pwd);
	}
	
	
	/**
	 * 
	 * @return nom usuari
	 */
	public String getNom(){
		return usuari.consultar_nombre();
	}
	

}