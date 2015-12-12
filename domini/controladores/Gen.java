package domini.controladores;
import java.util.*;

import domini.classes.*;
/**
 * 
 * @author pau
 *
 */
public class Gen {
	private static TaulerKenken t;
	private static int tamany;
	private static boolean jata;
	private static int contador_regions;// num regions
	private ArrayList<Integer> resultats; //solucio a la regio iterador
	private ArrayList<Integer> operacions;// 0 suma, 1 mult, 2 resta, 3 divisio, 4 cap
	
	/**
	 * 
	 * @param n taulerkenken a generar(esta buit)
	 */
	public Gen(TaulerKenken n){
		boolean b = false;	
			t = n;
			jata = false;
			tamany = t.getMida();	
			operacions = new ArrayList<Integer>();
			resultats = new ArrayList<Integer>();
			backtraking(0,0);
			contador_regions = 0;
			for (int i = 0; i< tamany; i++){
				for(int j = 0; j< tamany; j++){

					if(t.getIdRegio(i, j)== -1){
						System.out.print("pos "+i+" "+j+" id de regio = " + t.getIdRegio(i,j));
						contador_regions++;
						crear_regio(i,j);
					
						
					}
					
				}
			}		
			for(int x = 0; x < resultats.size(); x++){
				System.out.print(resultats.get(x)+" ");
			}
		}
		
	

	/**
	 * 
	 * @param x fila del tauler a emplenar
	 * @param y columna del tauler a emplenar
	 */
	public static void backtraking(int x, int y){		
		
		ArrayList<Integer> candidats = new ArrayList<Integer>();

		if(jata) return;
		else if(y ==tamany){
			backtraking(x+1,0);
		}
		else if( x != tamany){
			candidats = retornaCandidats(x,y);
			if(!candidats.isEmpty()){
				while(!candidats.isEmpty()){
					t.setValorTauler(x,y,candidats.get(0));
					candidats.remove(0);
					backtraking(x,y+1);
					if(jata){
						//t.imprimir();
						return;
					}
				}
			}			

		}
		else jata = true;
		return;
	}
	/**
	 * 
	 * @param posx
	 * @param posy
	 * @return retorna un array list amb els valors possibles
	 */
	public static ArrayList<Integer> retornaCandidats(int posx, int posy){
		ArrayList<Integer> vec = new ArrayList<Integer>(tamany);

		for(int i = 0; i< tamany; i++){
			vec.add(i+1);
		}
		Collections.shuffle(vec);
		ArrayList<Integer> usats = new ArrayList<Integer>();
		for(int i = posx-1; i>= 0; i--){
			usats.add(t.getValorTauler(i, posy));
		}
		for(int j = posy-1; j>= 0; j--){
			usats.add(t.getValorTauler(posx, j));
		}
		vec.removeAll(usats);

		return vec;
	}
	
	/**
	 * imprimeix el tauler 
	 */
	public void imprimir(){
		for(int i = 0; i< tamany; i++){
			for (int j = 0; j<tamany; j++){
				System.out.print(t.getValorTauler(i,j)+ " ");
			}
			System.out.println();
		}
	}
	
	
	private void intercanvi(int i, int j){
		t.getRegio(i, j).setObjectiu(resultats.get(contador_regions-1));
		if(operacions.get(contador_regions-1) == 0){
			t.getRegio(i, j).setOperacio("+");
		}
		else if(operacions.get(contador_regions-1) == 1){
			t.getRegio(i, j).setOperacio("*");
		}
		else if(operacions.get(contador_regions-1) == 2){
			t.getRegio(i, j).setOperacio("-");
		}
		else if (operacions.get(contador_regions-1)==3){
			t.getRegio(i, j).setOperacio("/");
		}
		else{
			t.getRegio(i, j).setOperacio("+");
		}
		//falta assignar objectiu i l'operacio
	}
	
	/**
	 * 
	 * @param i fila del tauler
	 * @param j columna del tauler
	 */
	private void crear_regio(int i, int j){
		Regio r;
		r = new Regio();
		t.setRegioTauler(i,j,r);
		Random rand = new Random();
		int reg;
		if(tamany>=9)reg= rand.nextInt(5);
		else reg= rand.nextInt(4);
		if(reg==4)reg=1;
		int x=i;
		int y=j;
		ArrayList<Integer> agrupa_nums = new ArrayList<Integer>();
		agrupa_nums.add(t.getValorTauler(i, j));
		while(reg != 0){			//assignar una casella mes a la regio
			System.out.println("he entrat al while amb reg = "+ reg);
			ArrayList<Integer> llista = new ArrayList<Integer>();
			if(i-1 >= 0 ){
				if(t.getIdRegio(i-1, j) == -1){
					llista.add(0);
				}
				
				/*if(matriu_reg[i-1][j] == 0){
					llista.add(0);
				}*/
			}
			if(j-1 >= 0 ){
				if(t.getIdRegio(i, j-1) == -1){
					llista.add(1);
				}
			}
			
			if(i+1 < tamany){
				if(t.getIdRegio(i+1, j) == -1){
					llista.add(2);
				}
			}
			if(j+1 < tamany){
				if(t.getIdRegio(i, j+1) == -1){
					llista.add(3);
				}
			}
			int tamanyl = llista.size();
			if(tamanyl == 0){
				break;
			}
			int random2 = rand.nextInt(tamanyl);//creem random del nombre de dirs disponibles
			random2 = llista.get(random2);	//seleccionem una direccio random
			
			if(random2 == 0){				//va amunt assignem casella d'amunt a la regio
				t.setRegioTauler(i-1,j,r);
				
				i = i-1;
			}
			
			if(random2 == 1){				//va cap a l'esquerra, assignem casella  a regio
				t.setRegioTauler(i,j-1,r);
				j = j-1;
			}
			
			if(random2 == 2){				//va  cap  avall i assigna la nova cassella a regio
				t.setRegioTauler(i+1,j,r);
				i = i+1;
			}
			if(random2 == 3){				//va cap a la dreta i assigna la casella a la regio
				t.setRegioTauler(i,j+1,r);
				
				j = j+1;
				
			}
			agrupa_nums.add(t.getValorTauler(i, j)); //fiquem la casella 
			--reg;
		}
		int rand_op;
		if(agrupa_nums.size() < 2){//vale, tenim les caselles ficades, operem a regio
			System.out.println("he entrat a operar una regio duna sola casella a la pos "+ i + " "+j);
			rand_op = 4;
			operacions.add(4);
			System.out.println("s'ha creat la regio amb valor" + t.getValorTauler(i, j)+ " a la pos : " + i + " " +j);
			resultats.add(t.getValorTauler(i,j));
		}
		else if( agrupa_nums.size() == 2){
			rand_op = rand.nextInt(4);
			operacions.add(rand_op);
			int num1 = agrupa_nums.get(0);
			int num2 = agrupa_nums.get(1);
			if(rand_op == 0){
				num1 += num2;
				resultats.add(num1);
			}
			else if(rand_op == 1){
				num1 *= num2;
				resultats.add(num1);
			}
			else if(rand_op == 2){
				if(num1 >= num2){
					num1 -= num2;
					resultats.add(num1);
				}
				else{
					num2 -= num1;
					resultats.add(num2);
				}
			}
			else{
				if(num1 >= num2){

					num1 /= num2;
					resultats.add(num1);
				}
				else{
					num2 /= num1;
					resultats.add(num2);
				}
			}
		}
		else{
			rand_op = rand.nextInt(2);

			operacions.add(rand_op);

			int resultat;
			if(rand_op == 0){
				resultat = 0;
				for(int k = 0; k < agrupa_nums.size(); k++){
					resultat += agrupa_nums.get(k);	
				}
			}
			else{
				resultat = 1;
				for(int k = 0; k < agrupa_nums.size(); k++){
					resultat *= agrupa_nums.get(k);	
				}					
			}
			
			resultats.add(resultat);

		}
		intercanvi(i,j);
		
		
	}
	
	
	

}
