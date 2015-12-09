package domini.controladores.drivers;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import domini.classes_compartides.*;
import domini.classes.*;
/**
 * 
 * @author arnau.zapata.i
 *
 */
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
		System.out.println(aux.get(i).getValor() + " ");
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
		
		if(option == 1){ 
			int i=0;
			System.out.println("indica la posicion donde esta la Regio que vols que tingui la casella");
			r=a.getRegio(s.nextInt(), s.nextInt());
			System.out.println("indica la posicion de la casella a la que li vols afegir la Regio");
			int op1=s.nextInt();
			int op2=s.nextInt();
			option1(op1,op2,r);
			}
		if(option == 2){ 
			System.out.println("indica la posicion de la casella a la que li vols afegir la Regio");
			int op1=s.nextInt();
			int op2=s.nextInt();
			System.out.println("indica la operacio y objectiu de la Regio");
			String op3 = s.next();
			int op4 =s.nextInt();
			option2(op1,op2,op3,op4);	
		}
		if(option == 3){
			System.out.println("indica la posicion de la casella a la que li vols afegir la Regio");
			int op1=s.nextInt();
			int op2=s.nextInt();
			System.out.println("indica el id de la Regio");
			int op3 = s.nextInt();
			option3(op1,op2,op3);
		}
		if(option == 4) option4();
		if(option == 5){
			System.out.println("indica la posicion de la casella");
			int op1=s.nextInt();
			int op2=s.nextInt();
			System.out.println("indica el valor de la casella");
			int op3 = s.nextInt();
			option5(op1,op2,op3);
		}
		if(option == 6){
			System.out.println("indica la posicion de la casella");
			int op1=s.nextInt();
			int op2=s.nextInt();
			System.out.println("indica el valor de la solucio de la casella");
			int op3 = s.nextInt();
			option6(op1,op2,op3);
		}
		if(option == 7){
			System.out.println("indica la posicion de la casella");
			int op1=s.nextInt();
			int op2=s.nextInt();
			option7(op1,op2);
		}
		if(option == 8){
			System.out.println("indica la posicion de la casella");
			int op1=s.nextInt();
			int op2=s.nextInt();
			option8(op1,op2);
		}
		if(option == 9){
			System.out.println("indica la posicion de la casella");
			int op1=s.nextInt();
			int op2=s.nextInt();
			option9(op1,op2);
		}
		if(option == 10){
			System.out.println("introdueix un valor");
			int op1=s.nextInt();
			option10(op1);
		}
		if(option == 11) option11();
		if(option == 13){
			System.out.println("indica la posicion de la Regio");
			int op1=s.nextInt();
			int op2=s.nextInt();
			option13(op1,op2);
		}
		
		if(option == 14){
			System.out.println("indica el id de la Regio");
			option14(s.nextInt());
		}
		printOptions();
		option = s.nextInt();
	}
	s.close();
}
}




