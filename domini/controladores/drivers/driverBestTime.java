package domini.controladores.drivers;

import java.util.ArrayList;
import java.util.Scanner;

import domini.classes.BestTime;
import domini.classes.pair;

public class driverBestTime {
		public static BestTime a= new BestTime();
		static int tamany = 7;
		
		public static void printOptions() {
			System.out.println("OPERACIONS BestTime");
			System.out.println("1-getBestTimeGlobal()");
			System.out.println("2-getBestTimeUser()");
			System.out.println("3-setValue(Float f, String user, Integer tam_KK)");
			System.out.println("4-getBestTime()");
			
		}
		
		
	public static void option1() {
		a.getBestTimeGlobal();
	}
	public static void option2() {
	a.getBestTimeUser();
	}


	public static void option3(int f,String user,int tam_KK) {
	a.setValue(f, user, tam_KK);
	}
	public static void option4() {
		a.getBestTime();
		}


	public static void main(String[] args) {
	printOptions();
	System.out.println("0- EXIT");

		Scanner s = new Scanner(System.in);
		Integer option = s.nextInt();
		while (option != 0) {
			if(option == 1) option1();
			if(option == 2) option2();
			if(option == 3) option3(s.nextInt(),s.next(),s.nextInt());
			if(option == 4) option4();
	 	option = s.nextInt();
	}
		s.close();
	}




	}

