package domini.controladores.drivers;

import java.util.ArrayList;
import java.util.Scanner;

import domini.classes.Regio;
import domini.classes_compartides.Casilla_comp;

public class driverRegio {
		public static Regio a= new Regio();
			
			public static void printOptions() {
				System.out.println("OPERACIONS REGIO");
				System.out.println("15- crea una casella amb el valor que se li pasi per teclat");
				System.out.println("1-addCasella(Casella c)");
				System.out.println("2-removeCasella(c) //eliminara l'ultima casella");
				System.out.println("3-removeAll()");
				System.out.println("4-getList()");
				System.out.println("5-getOperacio()");
				System.out.println("6-setOperacio(String n)");
				System.out.println("7-getResultat()");
				System.out.println("8-getObjectiu()");
				System.out.println("9-getOperacio()");
				System.out.println("10-setObjectiu(int n)");
				System.out.println("11-setResultat(int n)");
				System.out.println("12-unsetResultat(int n)");
				System.out.println("14-nearFull()");
			}
			
			
	public static void option1(Casilla_comp c) {
		a.addCasella(c);
		option4();
	}
	public static void option2(Casilla_comp c) {
		a.removeCasella(c);
		option4();
	}
	

	public static void option3(int n) {
		a.removeAll();
		option4();
	}
	public static void option4() {
		System.out.println(a.getList());
	}
	public static void option5() {
		System.out.println(a.getOperacio());
	}
	private static void option6(String c) {
		a.setOperacio(c);
		option5();
	}

	private static void option14() {
		System.out.println(a.nearFull());
	}



	private static void option12(int n) {
		a.unsetResultat(n);
		System.out.println(a.getResultat());
		
	}


	private static void option11(int k) {
		a.setResultat(k);
		System.out.println(a.getResultat());
		
	}


	private static void option10(int objectiu) {
		a.setObjectiu(objectiu);
		System.out.println(a.getObjectiu());
		
	}


	private static void option9() {
		
		System.out.println(a.getOperacio());
		
	}


	private static void option8() {
		
		System.out.println(a.getObjectiu());
	}


	private static void option7() {
		
		System.out.println(a.getResultat());
	}
	public static void main(String[] args) {
		printOptions();
		System.out.println("0- EXIT");
		
	 	Scanner s = new Scanner(System.in);
	 	Integer option = s.nextInt();
	 	a = new Regio();
	 	Casilla_comp c = new Casilla_comp();
	 	while (option !=0) {
	 		if(option==15){int num=s.nextInt();c=new Casilla_comp();c.setValor(num);};
	 		if(option == 1) option1(c);
	 		if(option == 2) option2(c);
	 		if(option == 3) option3(s.nextInt());
	 		if(option == 4) option4();
	 		if(option == 5) option5();
	 		if(option == 6) option6(s.next());
	 		if(option == 7) option7();
	 		if(option == 8) option8();
	 		if(option == 9) option9();
	 		if(option == 10) option10(s.nextInt());
	 		if(option == 11) option11(s.nextInt());
	 		if(option == 12) option12(s.nextInt());
	 		if(option == 14) option14();
		 	option = s.nextInt();
	    }
	 	s.close();
	}




	}