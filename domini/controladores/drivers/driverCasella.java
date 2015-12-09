package domini.controladores.drivers;
import domini.classes_compartides.*;

import java.util.Scanner;


/**
 * 
 * @author arnau.zapata.i
 *
 */
public class driverCasella {
	public static Casilla_comp a;
		
		public static void printOptions() {
			System.out.println("OPERACIONS CASELLA");
			System.out.println("1-Afegir Valor");
			System.out.println("2-Esborrar Valor");
			System.out.println("3-Afegir Candidad");
			System.out.println("4-Borrar Candidad");
			System.out.println("5-get Candidats");
			System.out.println("6-get Valor");
			System.out.println("0-Exit");
		}
		
		public static void option2() {
			a.setValor(0);
			option6();
		}
public static void option1(int n) {
		a.setValor(n);
		option6();
}
private static void option6(){
	System.out.println("la casella te valor:");
	System.out.println(a.getValor());
	
}

public static void option3(int n) {
	a.setCandidat(n);
	option5();
}
public static void option4(int n) {
	a.esborrarCandidat(n);
	option5();
}
public static void option5() {
	System.out.println("els candidats introduits a la casella son:");
	boolean[] lb =a.getCandidatos();
	for(int i=0;i<lb.length;i++){
		if(lb[i]){System.out.print(String.valueOf(i+1)+ " ");}
	}
	System.out.println();
}
	
public static void main(String[] args) {
	
	
 	Scanner s = new Scanner(System.in);
 	System.out.println("ELIGEIX TAMANY DEL TAULER:");
 	int n=s.nextInt();
 	printOptions();
	Integer option = s.nextInt();
	boolean candidatos[] = new boolean[n];
 	a = new Casilla_comp(candidatos,false,0);
 	while (option != 0) {
 		if(option == 1){
 			try{
 				System.out.println("introdueix el valor de la casella");
 	 			String op=s.next();
 	 			boolean b= hihaLletra(op);
				if(b) throw new NumberFormatException();
				else{
					option1(Integer.valueOf(op));
				}	
			}catch(NumberFormatException e){
				System.out.println("numero no valid");
				printOptions();
				option = s.nextInt();
				continue;
			}
 			
 		}
 		if(option == 2) option2();
 		if(option == 3){
 			try{
 				System.out.println("introdueix el candidat que vols afegir");
 				int op=s.nextInt();
				if(a.getCandidatos().length>=op) throw new ArrayIndexOutOfBoundsException();
				else{
					option3(op);
				}	
			}catch(ArrayIndexOutOfBoundsException e){
				System.out.println("candidat incorrecte");
				printOptions();
				option = s.nextInt();
				continue;
			}
 			
 			
 		}
 		if(option == 4){
 			try{
 				System.out.println("introdueix el candidat que vols afegir");
 				int op=s.nextInt();
				if(a.getCandidatos().length>=op) throw new ArrayIndexOutOfBoundsException();
				else{
					option4(op);
				}	
			}catch(ArrayIndexOutOfBoundsException e){
				System.out.println("candidat incorrecte");
				printOptions();
				option = s.nextInt();
				continue;
			}
 		}
 		if(option == 5) option5();
 		if(option == 6) option6();
 		printOptions();
	 	option = s.nextInt();
    }
 	s.close();
}

private static boolean hihaLletra(String op) {
		op=op.replaceAll(" ", "");
		int i= op.length();
		for(int j=0;j<i;j++){
			char c=op.charAt(j);
			if(c==' '){}
			if(c>='a' && c<='z'){return false;}
			if(c>='A' && c<='Z'){return false;}
		}
		return true;
}
}