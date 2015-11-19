package domini.controladores.drivers;
import domini.classes.*;
import java.util.Scanner;
/**
 * 
 * @author arnau.zapata.i
 *
 */
public class driverCasella {
	public static Casella a;
		
		public static void printOptions() {
			System.out.println("OPERACIONS CASELLA");
			System.out.println("1-afegirNum(int n)");
			System.out.println("2-borrarNum()");
			System.out.println("3-setCandidat(int n,true)");
			System.out.println("4-setCandidats(int n,false)");
			System.out.println("5-getCandidats()");
			System.out.println("6-getNum()");
		}
		
		public static void option2() {
			a.borrarNum();
			System.out.print(option6());
		}
public static void option1(int n) {
	a.afegirNum(n);
	System.out.print(option6());
}
private static int option6() {
	return a.getNum();
}

public static void option3(int n) {
	a.setCandidat(n, true);
	option5();
}
public static void option4(int n) {
	a.setCandidat(n,false);
	option5();
}
public static void option5() {
	boolean[] lb =a.getCandidats();
	for(int i=0;i<lb.length;i++){
		if(lb[i]){System.out.print(String.valueOf(i+1)+ " ");}
	}
}
	
public static void main(String[] args) {
	
	
 	Scanner s = new Scanner(System.in);
 	System.out.print("ELIJE TAMANO:");
 	int n=s.nextInt();
 	printOptions();
	System.out.println("0- EXIT");
	Integer option = s.nextInt();
 	a = new Casella(n);
 	while (option != 0) {
 		if(option == 1){
 			System.out.print("introdueix el valor de la casella");
 			option1(s.nextInt());
 		}
 		if(option == 2) option2();
 		if(option == 3){
 			System.out.print("introdueix el candidat que vols afegir");
 			option3(s.nextInt());
 		}
 		if(option == 4){
 			System.out.print("introdueix el candidat que vols treure");
 			option4(s.nextInt());
 		}
 		if(option == 5) option5();
 		if(option == 6) option6();
 		printOptions();
	 	option = s.nextInt();
    }
 	s.close();
}
}