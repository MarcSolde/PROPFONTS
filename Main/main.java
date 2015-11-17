package Main;

import java.io.*;
import domini.*;
import domini.controladores.*;
import domini.controladores.drivers.*;
import persistencia.CtrlData;
import domini.*;

import java.util.*;

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
		cPartida.assignarUser(cUsr.getNom());
	
		if (b) menuPrograma();
		
		/*System.out.println("digues tamany tauler ");
		int N = sc.nextInt();
		TaulerKenken t = new TaulerKenken(N);
		int count = 0;
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j){
				System.out.println("digues num regio a la posicio "+i+" "+j+": ");
				System.out.println("El numero de regions es: "+count);
				int rg = sc.nextInt();
				if (rg > count) {
					Regio r = new Regio();
					System.out.println("digues l'objectiu i la operacio: ");
					r.setObjectiu(sc.nextInt());
					r.setOperacio(sc.nextLine());
					t.setRegioTauler(i, j, r);
					++count;
				}
				else {
					t.setRegioTauler(i, j, t.getRegioById(rg-1));
				}
			}
		}
		t.imprimirReg();*/
		
		//MAIN DE RANKING//
		/*
		
		//CtrlPartida c = new CtrlPartida();
		//c.creaPartida();
		//Esto es todo testeo!!!!
		
		Scanner input = new Scanner(System.in);
		//System.out.println("digues tamany tauler ");
		Scanner sc = new Scanner(System.in);
		//int i = sc.nextInt();
		//Ctrlpar = new CtrlPartida();
		//input.useLocale(Locale.US); //Ponemos esto para poder usar "." para los float y no ",".
		CtrlRanking CTRLRANKING = new CtrlRanking(); //Esto ira a la capa de presentacion WEY!!!
		int i = 0;
		while (i < 3) {
			CTRLRANKING.anadir_valores();
			++i;
		}
		CTRLRANKING.consultar();
		*/

		
		
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
	
	private static void menuPrograma(){
		System.out.println("\nTria que vols fer:\n\n");
		System.out.println("1: Nova partida\n");
		System.out.println("2: Resumir partida\n");
		System.out.println("3: Consultar ranking\n");
		//System.out.println("4: Esborrar un kenken guardat\n");
		System.out.println("0: Sortir\n");
		int op = scin.nextInt();
		switch (op) {
		case 0:
			iniciarSessio();
			break;

		case 1:
			novaPartida();
			menuPrograma();
			break;
		case 2:
			resumirPartida();
			menuPrograma();
			break;
		case 3:
			consultarRanking();
			menuPrograma();
			break;
		//case 4:
			//esborrarKenken();
		}

	}
	
	private static void novaPartida() {
		System.out.println("\nQuin kenken vols?\n\n");
		System.out.println("1: Kenken My Way\n");
		System.out.println("2: Triar kenken guardat\n");
		System.out.println("3: Jugar un kenken\n");
		System.out.println("0: Tornar al menu\n");
		int op = scin.nextInt();
		switch (op) {
		case 1:
			int N;
			String operacio;
			System.out.println("\n\nKENKEN MY WAY\n\n");
			System.out.println("Mida del kenken: ");
			N = scin.nextInt();
			cPartida.creaPartidaUser(N);
			/*
			System.out.println("\nOperacions del kenken:\n");
			System.out.println("1: +");
			System.out.println("2: + -");
			System.out.println("3: + - *");
			System.out.println("4: + - * :");
			System.out.println("0: Tornar enrere");
			int opx = sc.nextInt();
			switch (opx) {
			case 1:
				operacio = "+";
			case 2:
				operacio = "+-";
			case 3:
				operacio = "+-*";
			case 4:
				operacio = "+-*:";
			case 0:
				novaPartida();
			}*/
			break;
			
		case 2:
			System.out.println("\n\nTRIA UN KENKEN GUARDAT\n\n");
			/*File dir = new File("../data/kenkens");
			File[] fileList = dir.listFiles();
			for (File f: fileList) {
				if(f.isFile()) {
					System.out.println(f.getName());
				}
			}*/
			File f = new File("taulers/");
			File[] paths = f.listFiles();
			for (File path:paths) {
				if (!path.getName().equals("num.txt")) System.out.println(path);
			}
			System.out.println("\n\nNom del kenken: ");
			String s = scin.next();
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
			/*System.out.println("\n\nCOM VOLS INTRODUIR EL KENKEN?\n\n");
			System.out.println("1: Per terminal\n");
			System.out.println("2: Per interficie grafica\n");
			int opx = sc.nextInt();
			switch (opx) {
			case 1:
				//Llegir el kenken-> CtrlTauler ha de poder llegir kenkens
			case 2:
				//Cridar interficie grafica...com es fa?
			}
			//Despres de crear el kenken -> partida*/
			
		case 0:
			menuPrograma();
			break;
		}
	}
	
	public static void resumirPartida(){
		System.out.println("\n\nTRIA UNA PARTIDA\n\n");
		File f = new File("usuaris/"+cUsr.getNom());
		File[] paths = f.listFiles();
		int it = 0;
		for (File path:paths) {
		++it;
			if (!path.getName().equals("contrasenya.txt") && !path.getName().equals("num.txt")) {
				System.out.println(path);
			}
		}
		System.out.print("\n\nNom de la partida: ");
		String s = scin.next();
		if(Integer.parseInt(s)>it-2 || Integer.parseInt(s) <=0){
			System.out.println("No hi ha partides per a poder carregar. Tornant al menu");
			return;
		}
		cPartida.carregaPartida(s, cUsr.getNom());
		cPartida.inGame();
	}
	
	public static void consultarRanking(){
		System.out.println("");
		cRank.consultar();
	}
}