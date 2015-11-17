package Main;


import java.io.*;
import domini.controladores.*;
import persistencia.CtrlData;

import java.util.*;

public class mainYEAH {
	
		private static CtrlData cData;
		private static CtrlPartida cPartida;
		private static CtrlPresentacio cPres;
		private static CtrlUsuari cUsr;
		private static CtrlRanking cRank;

		private static Scanner scin;
		private static Console console;
		
		
		public static void main(String args[]) {
			
			cData = new CtrlData();
			cPartida = new CtrlPartida();
			cPres = new CtrlPresentacio();
			cUsr = new CtrlUsuari();
			cRank = new CtrlRanking();
			scin = new Scanner(System.in);
			console = System.console();
			
			System.out.println("BENVINGUT AL KENKEN MASTERS EDITION\n\n");
			
			boolean b = false;
			b = iniciarSessio();
			
			int op = -1;
			
			while (op != 0) {
				System.out.println("\nTria que vols fer:\n\n");
				System.out.println("1: Nova partida\n");
				System.out.println("2: Resumir partida\n");
				System.out.println("3: Consultar ranking\n");
				//System.out.println("4: Esborrar un kenken guardat\n");
				System.out.println("0: Sortir\n");
				op = scin.nextInt();
				switch (op) {
				case 1:
					System.out.println("\nQuin kenken vols?\n\n");
					//System.out.println("1: Kenken My Way\n");
					System.out.println("1: Triar kenken guardat\n");
					System.out.println("2: Jugar un kenken\n");
					System.out.println("3: Tornar al menu\n");
				}
			}
		}
		
		/**
		 * inicia sessio amb un usuari existent o crea un usuari nou
		 */
		private static void iniciarSessio() {
			//CARREGAR RANKING
			String nom, pwd;
			nom = "";
			pwd = "";
			System.out.println("Has d'iniciar sessio o registrar-te per jugar\n");
			System.out.println("1: Iniciar sessio\n");
			System.out.println("2: Registrar-me\n");
			System.out.println("0:sortir");
			int op = scin.nextInt();
			boolean b = false;
			switch (op) {
			case 0:
				b = false;
				return b;
				//FALTA GUARDAR RANKING A LA BD
			case 1:
				System.out.println("Nom d'usuari: ");
				nom = scin.next();
				//pwd = new String(console.readPassword("\nContrasenya: "));
				System.out.println("\nContrasenya: ");
				pwd = scin.next();
				//System.out.println(pwd);
				b = cData.llegirUsuari(nom, pwd); 
				if (b) {
					cUsr.nouUsuari(nom, pwd);
					return b;
				}
				
				System.out.println("\nNom d'usuari o Contrasenya incorrectes\n");
				b = iniciarSessio();
				break;
			case 2:
				System.out.println("Nom d'usuari: ");
				nom = scin.next();
				//pwd = new String(console.readPassword("\nContrasenya: "));
				System.out.println("\nContrasenya: ");
				pwd = scin.next();
				if (cData.escriureUsuari(nom, pwd)) {
					cUsr.nouUsuari(nom, pwd);
					b = true;					//ET FALTAVA B = TRUE, FEIA BUCLE A REGISTRARSE
					break;
				}
				System.out.println("\nJa existeix un usuari amb aquest nom, si us plau trieu un altre nom\n");
				b = iniciarSessio();
				break;
			}
			return b;
			
		}
}
