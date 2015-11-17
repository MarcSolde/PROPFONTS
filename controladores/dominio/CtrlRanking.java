package controladores.dominio;
import java.util.Scanner;

import ranking.*;

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
		BT = new BestTime();
		MS = new MostSolved();
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
		int x = input.nextInt();
		if (x == 1) {
			System.out.println("Elige si global o no (1 o 2)?");
			int b = input.nextInt();
			while (b!= 1 && b != 2) {
				if (b == 1) BT.getBestTimeGlobal();
				else if (b == 2)BT.getBestTime();
				else break; 
			}
			
		}
		else if (x == 2) {
			System.out.println("Elige si global o no (1 o 2)?");
			int b = input.nextInt();
			while (b!= 1 && b != 2) {
				if (b == 1) MS.getMostSolvedGlobal();
				else if (b == 2) {
					String s = input.next();
					MS.getMostSolvedUser(s);
				}
				else break;
			}
				
		}
		
	}
}
