package tablero;

import java.util.Scanner;

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
	// TODO Auto-generated method stub
	return a.getNum();
}

public static void option3(int n) {
	a.setCandidat(n, true);
	System.out.println(a.getCandidats());
}
public static void option4(int n) {
	a.setCandidat(n,false);
	System.out.println(a.getCandidats());
}
public static void option5() {
	System.out.println(a.getCandidats());
}
	
public static void main(String[] args) {
	printOptions();
	System.out.println("0- EXIT");
	
 	Scanner s = new Scanner(System.in);
 	Integer option = s.nextInt();
 	System.out.println("ELIJE TAMANO);");
 	int n=s.nextInt();
 	a = new Casella(n);
 	while (option != 0) {
 		if(option == 1) option1(s.nextInt());
 		if(option == 2) option2();
 		if(option == 3) option3(s.nextInt());
 		if(option == 4) option4(s.nextInt());
 		if(option == 5) option5();
 		if(option == 6) option6();
	 	option = s.nextInt();
    }
 	s.close();
}
}