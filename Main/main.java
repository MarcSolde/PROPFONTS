package Main;


import java.io.*;
import domini.controladores.*;
import persistencia.CtrlData;

import java.util.*;
/**
 * 
 * @author anna, contribuidor: pau
 * 
 *
 */
public class main {
	
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
			if(b) cPartida.assignarUser(cUsr.getNom());
			
			int op = -1;
			
			while (b && op != 0) {
				System.out.println("\nTria que vols fer:\n\n");
				System.out.println("1: Nova partida\n");
				System.out.println("2: Resumir partida\n");
				System.out.println("3: Consultar ranking\n");
				System.out.println("4: Esborrar una partida guardada\n");
				System.out.println("0: Sortir\n");
				op = scin.nextInt();
				switch (op) {
				case 1:
					System.out.println("\nQuin kenken vols?\n\n");
					System.out.println("1: Kenken My Way\n");
					System.out.println("2: Triar kenken guardat\n");
					System.out.println("3: Jugar un kenken\n");
					System.out.println("4: Tornar al menu\n");
					int x = scin.nextInt();
					switch(x) {
					case 1:
						int N;
						String operacio;
						System.out.println("\n\nKENKEN MY WAY\n\n");
						System.out.println("Mida del kenken: ");
						N = scin.nextInt();
						cPartida.creaPartidaUser(N);
						break;
					case 2:
						System.out.println("\n\nTRIA UN KENKEN GUARDAT\n\n");
						File f = new File("data/taulers/");
						File[] paths = f.listFiles();
						for (File path:paths) {
							if (!path.getName().equals("num.txt")) System.out.println(path);
						}
						System.out.println("\n\n 0: Tornar al menu");
						System.out.println("\nNumero del kenken: ");
						String s = scin.next();
						if (s.equals("0")) break;
						cPartida.assignarUser(cUsr.getNom());
						cPartida.creaPartidaBD(s);
						cPartida.inGame();
						if (cPartida.getAcabada()) {
							cRank.anadir_valores_fin_partida(cUsr.getNom(), cPartida.getPartida().getTauler().getMida(), cPartida.getPartida().getTemps());

						}
						break;
					case 3:
						CtrlPartida p;
						p = new CtrlPartida();
						System.out.println("digues tamany tauler ");
						p.creaPartidaRand(scin.nextInt());
						p.inGame();
						if (p.getAcabada()) {
							cRank.anadir_valores_fin_partida(cUsr.getNom(), p.getPartida().getTauler().getMida(), p.getPartida().getTemps());
						}
						break;
					case 4:
						break;
					}
					break;
				case 2:
					System.out.println("\n\nTRIA UNA PARTIDA\n\n");
					File f = new File("data/usuaris/"+cUsr.getNom());
					File[] paths = f.listFiles();
					int it = 0;
					for (File path:paths) {
					++it;
						if (!path.getName().equals("contrasenya.txt") && !path.getName().equals("num.txt")) {
							System.out.println(path);
						}
					}
					System.out.print("0: Tornar al menu");
					System.out.print("\n\nNumero de la partida: ");
					String s = scin.next();
					if (s.equals("0")) break;
					if(Integer.parseInt(s)>it-2 || Integer.parseInt(s) <=0){
						System.out.println("No hi ha partides per a poder carregar. Tornant al menu");
						return;
					}
					cPartida.carregaPartida(s, cUsr.getNom());
					cPartida.inGame();
					if (cPartida.getAcabada()) {
						cRank.anadir_valores_fin_partida(cUsr.getNom(), cPartida.getPartida().getTauler().getMida(), cPartida.getPartida().getTemps());
						cData.esborrarPartida(s, cUsr.getNom());
					}
					break;
				case 3:
					System.out.println("\n\nRANKING:\n\n");
					cRank.consultar();
					break;
				case 4:
					System.out.println("\n\nTRIA UNA PARTIDA\n\n");
					File file = new File("data/usuaris/"+cUsr.getNom());
					File[] p = file.listFiles();
					int ite = 0;
					for (File path:p) {
					++ite;
						if (!path.getName().equals("contrasenya.txt") && !path.getName().equals("num.txt")) {
							System.out.println(path);
						}
					}
					System.out.print("0: Tornar al menu");
					System.out.print("\n\nNom de la partida: ");
					String id = scin.next();
					if (id.equals("0")) break;
					if(Integer.parseInt(id)>ite-2 || Integer.parseInt(id) <=0){
						System.out.println("No hi ha partides per a poder carregar. Tornant al menu");
						break;
					}
					cData.esborrarPartida(id,cUsr.getNom());
				}
			}
			cData.escriureMostSolved(cRank.getMostSolved());
			cData.escriureBestTime(cRank.getBestTime());
		}
		
		/**
		 * inicia sessio amb un usuari existent o crea un usuari nou
		 */
		private static boolean iniciarSessio() {
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
