package domini.controladores;
import java.util.Scanner;

import domini.classes.*;
import domini.controladores.drivers.*;
import persistencia.CtrlData;

/**
 * Es tracta del controlador del ranking Ranking
 * @author Marc Soldevilla
 *
 */
public class CtrlRanking {
	private BestTime BT;
	private MostSolved MS;
	private Scanner input = new Scanner(System.in);
	/**
	 * Creadora del controlador 
	 */
	public CtrlRanking()
	//Creadora del controlador
	{
		CtrlData cData = new CtrlData();
		BT = new BestTime();
		cData.llegirBestTime(BT);
		MS = new MostSolved();
		cData.llegirMostSolved(MS);
	}

	/**
	 * Introduce los valores en el ranking
	 * @param user Usuari
	 * @param tam_KK Tamany del KenKen
	 * @param f Temps invertit
	 */
	public void anadir_valores_fin_partida(String user, int tam_KK, int f)
	{
		MS.setIncreasePlayed(user);
		BT.setValue(f, user, tam_KK);
	}
	/**
	 * Consulta el ranking
	 */
	public void consultar() 
	//Retorna el valor del ranking
	{
		System.out.println("Que desea consultar? (Introduzca el numero)");
		System.out.println("1.- Los mejores tiempos");
		System.out.println("2.- Los mas resueltos");
		System.out.println("Otro.- Salir a menu");
		int x = input.nextInt();
		if (x == 1) {
			System.out.println("Elija:");
			System.out.println("1.- Ver todos los kenkens");
			System.out.println("2.- Elegir el tamano del kenken");
			System.out.println("Otro.- Salir a menu");
			int b = input.nextInt();
			boolean t = false;
			if (!t) {
				if (b == 1) BT.getBestTimeGlobal();
				else if (b == 2)BT.getBestTime();
				t = true;
			}
			
		}
		else if (x == 2) {
			System.out.println("Elija:");
			System.out.println("1.- Ver todos los kenkens");
			System.out.println("2.- Elegir el tamano del kenken");
			System.out.println("Otro.- Salir a menu");
			int b = input.nextInt();
			boolean t = false;
			if (!t) {
				if (b == 1) MS.getMostSolvedGlobal();
				else if (b == 2) {
					System.out.println("Introduzca el nombre de usuario");
					String s = input.next();
					MS.getMostSolvedUser(s);
				}
				t = false;
			}
				
		}
		
	}
	public BestTime getBestTime() {
		return BT;
	}
	public MostSolved getMostSolved() {
		return MS;
	}
}
