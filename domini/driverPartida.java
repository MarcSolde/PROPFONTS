package domini;

import java.util.ArrayList;
import java.util.Scanner;

import classes_compartides.Casilla_comp;
import tablero.Casella;
import tablero.Regio;
import tablero.TaulerKenken;

public class driverPartida {
	public static Partida a= new Partida(7);
	static int tamany = 7;

	public static void printOptions() {
		System.out.println("OPERACIONS Partida");
		System.out.println("1-Start()");
		System.out.println("2-ini(int i)");
		System.out.println("3-imprimirReg()");
		System.out.println("4-getTauler()");
		System.out.println("5-comprovar(int i)");
		System.out.println("6-partidaFi()");
		System.out.println("7-partidaFi(true)");
		System.out.println("8-partidaFi(false)");
		System.out.println("10-montar(int t)");
		System.out.println("11-getTemps()");
		System.out.println("12-setId(int id)");
		System.out.println("13-getId()");
	}
	

public static void option1() {
	a.Start();
}

public static void option2(int i) {
a.ini(i);
}


public static void option3() {
a.imprimirReg();
}
public static void option4() {
	TaulerKenken t= a.getTauler();
	imprimirTauler();
}

public static void option6 (){
	System.out.println(a.partidaFi());
}


public static void option5(int i) {
a.comprovar(i);
}
private static void option7() {
a.partidaFi(true);
option6();
}

private static void option8() {
	a.partidaFi(false);
	option6();
}

private static void option10() {
a.montar();

}

private static void option11() {
System.out.println(a.getTemps());

}

private static void imprimirTauler() {
	TaulerKenken t=a.getTauler();
	for(int i=0;i<tamany;i++){
		System.out.println("");
		for(int j =0;j<tamany;j++){
			System.out.print(t.getValorTauler(i, j) +" ");
			
		}
	}
	
}


private static void option12(int id) {
a.setId(id);
option13();
}



private static void option13() {
System.out.println(a.getId());

}


public static void main(String[] args) {
printOptions();
System.out.println("0- EXIT");

	Scanner s = new Scanner(System.in);
	Integer option = s.nextInt();
	
	Regio r = new Regio();
	Casilla_comp c = new Casilla_comp();
	while (option != 0) {
		if(option == 1) option1();
		if(option == 2) option2(s.nextInt());
		if(option == 3) option3();
		if(option == 4) option4();
		if(option == 5) option5(s.nextInt());
		if(option == 6) option6();
		if(option == 7) option7();
		if(option == 8) option8();
		if(option == 10) option10();
		if(option == 11) option11();
		if(option == 12) option12(s.nextInt());
		if(option == 13) option13();
 	option = s.nextInt();
}
	s.close();
}


}
