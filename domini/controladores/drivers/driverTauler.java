package domini.controladores.drivers;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import domini.classes.Regio;
import domini.classes.TaulerKenken;
import domini.classes_compartides.Casilla_comp;
import domini.classes_compartides.Tauler_comp;

public class driverTauler {
	public static TaulerKenken a= new TaulerKenken(7);
	static int tamany = 7;
	public static void printOptions() {
		System.out.println("OPERACIONS Tauler");
		System.out.println("1-setRegioTauler(int x, int y, Regio reg)");
		System.out.println("2-setNovaRegio(int x, int y, String op, int res)");
		System.out.println("3-setRegioById(int x, int y, int id)");
		System.out.println("4-imprimirReg()");
		System.out.println("5-setValorTau(int x, int y, int n)");
		System.out.println("6-setValorSol(int x, int y, int n)");
		System.out.println("7-esborrarValorTauler(int x, int y)");
		System.out.println("8-esborrarValorSolucio(int x, int y)");
		System.out.println("9-validesaCasella(int x, int y)");
		System.out.println("10-taulerEsValid()");
		System.out.println("11-pista()");
		System.out.println("13-getRegio(int x, int y)");
		System.out.println("14-getRegioById(int id)");
	}
	
	
public static void option1(int x, int y, Regio reg) {
	a.setRegioTauler(x, y, reg);
	option13(x,y);
}
public static void option2(int x,int y, String op, int res) {
a.setNovaRegio(x, y, op, res);
option13(x,y);
}


public static void option3(int x,int y,int id) {
a.setRegioById(x, y, id);
option14(id);
}
public static void option4() {
	a.imprimirReg();
}
public static void option6 (int x,int y,int n){
	a.setValorSol(x, y, n);
	imprimirTaulerSol();
}
private static void imprimirTaulerSol() {
	for(int i=0;i<tamany;i++){
		System.out.println("");
		for(int j =0;j<tamany;j++){
			int old=a.getValorTauler(i, j);
			for(int k=0;k<tamany;k++){
				a.setValorTauler(i, j, k);
				if(a.validesaCasella(i, j))System.out.print(a.getValorTauler(i, j) +" ");
			}
			a.setValorTauler(i, j, old);
		}
	}
}


public static void option5(int x,int y,int n) {
a.setValorTauler(x, y, n);
imprimirTauler();
}
private static void option7(int x,int y) {
a.esborrarValorTauler(x, y);
imprimirTauler();
}

private static void option8(int x,int y) {
	a.esborrarValorSolucio(x, y);
	imprimirTaulerSol();
}

private static void option9(int x, int y) {
System.out.println(a.validesaCasella(x, y));

}
private static void option10(int objectiu) {
System.out.println(a.taulerEsValid(objectiu));

}

private static void option11() {
a.pista();
imprimirTauler();

}

private static void imprimirTauler() {
	for(int i=0;i<tamany;i++){
		System.out.println("");
		for(int j =0;j<tamany;j++){
			System.out.print(a.getValorTauler(i, j) +" ");
			
		}
	}
	
}


private static void option13(int x, int y) {
imprimirRegio(a.getRegio(x, y));

}

private static void imprimirRegio(Regio r) {
	ArrayList<Casilla_comp> aux= r.getList();
	for(int i=0;i<aux.size();i++){
		System.out.println(aux.get(i).getValor());
	}
	
}


private static void option14(int id) {
imprimirRegio(a.getRegioById(id));
}






public static void main(String[] args) {
printOptions();
System.out.println("0- EXIT");

	Scanner s = new Scanner(System.in);
	Integer option = s.nextInt();
	
	Regio r = new Regio();
	Casilla_comp c = new Casilla_comp();
	while (option != 0) {
		if(option == 1) option1(s.nextInt(),s.nextInt(),r);
		if(option == 2) option2(s.nextInt(),s.nextInt(),s.next(),s.nextInt());
		if(option == 3) option3(s.nextInt(),s.nextInt(),s.nextInt());
		if(option == 4) option4();
		if(option == 5) option5(s.nextInt(),s.nextInt(),s.nextInt());
		if(option == 6) option6(s.nextInt(),s.nextInt(),s.nextInt());
		if(option == 7) option7(s.nextInt(),s.nextInt());
		if(option == 8) option8(s.nextInt(),s.nextInt());
		if(option == 9) option9(s.nextInt(),s.nextInt());
		if(option == 10) option10(s.nextInt());
		if(option == 11) option11();
		if(option == 13) option13(s.nextInt(),s.nextInt());
		if(option == 14) option14(s.nextInt());
 	option = s.nextInt();
}
	s.close();
}




}
