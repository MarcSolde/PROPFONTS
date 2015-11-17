package domini.controladores.drivers;

import java.util.Scanner;

import domini.classes.MostSolved;

public class driverMostSolved {
		public static MostSolved a= new MostSolved();
		public static void printOptions() {
			System.out.println("OPERACIONS MostSolved");
			System.out.println("1-setIncreasePlayed(String user)");
			System.out.println("2-getMostSolvedUser(String user)");
			
		}
		
		
	public static void option1(String user) {
		a.setIncreasePlayed(user);
	}
	public static void option2(String user) {
	a.getMostSolvedUser(user);
	}


	public static void main(String[] args) {
	printOptions();
	System.out.println("0- EXIT");

		Scanner s = new Scanner(System.in);
		Integer option = s.nextInt();
		while (option != 0) {
			if(option == 1) option1(s.next());
			if(option == 2) option2(s.next());
	 	option = s.nextInt();
	}
		s.close();
	}

}
