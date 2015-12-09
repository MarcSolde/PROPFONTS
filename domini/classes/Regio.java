package domini.classes;

import java.util.ArrayList;

import domini.classes_compartides.*;
/**
 * 
 * @author arnau.zapata.i
 *
 */
public class Regio{
	private String operacio;
	private int resultat;
	private int objectiu;
	private int tamany;
	private int caselles_ocupades;
	private ArrayList<Casilla_comp> listCasella= new ArrayList<Casilla_comp>();
	//private boolean[] candidats;
	

	public Regio(){
		resultat=0;
		objectiu=0;
		operacio=" ";
		tamany = 0;
		caselles_ocupades = 0;

		//candidats= new boolean[tamany];
		//for(int i = 0; i<candidats.length;i++){candidats[i]=false;}
		//actualitzarResultat();
	}	
	
	
	//CREACIO
	public void addCasella(Casilla_comp c){
		//tamany=listCasella.get(0).getCandidatos().length;
	    if(!listCasella.contains(c)) {
	    	listCasella.add(c);
	    	++tamany;
			//System.out.println("tamnay lisCasella:"+listCasella.size()+" tamany valor:"+tamany);

	    }
	}
	
	public void afegirTamany(int i){
		tamany = i;
	}
	
	public void removeCasella(Casilla_comp c){
	    if(listCasella.contains(c)){listCasella.remove(c);}
	    //actualitzarResultat();
	}
	
	public void removeAll(){
	   for(int i =0; i<listCasella.size();i++){
			listCasella.remove(i);
		}
		listCasella.clear();
	}	
 
	//GETERS Y SETTERS

	public ArrayList<Casilla_comp> getList(){
		return listCasella;
	}
	
	/*public boolean[] getCandidats(){
		return candidats;
	}*/

	public String getOperacio() {
		return operacio;
	}

	public boolean setOperacio(String n){
		if(n.equals("+") || n.equals("-") || n.equals("*")|| n.equals("/")|| n.equals(" ")){
			this.operacio = n;
			if (n.equals("/")||(n.equals("*"))) resultat = 1;
			//getCandidats();
			//actualitzarResultat();
			return true;
		}
		return false;
	}
	
	public int getResultat(){
		return resultat;
	}

	public int getObjectiu() {
		return objectiu;
	}

	public void setObjectiu(int objectiu) {
		this.objectiu = objectiu;
		//getCandidats();
		//actualitzarResultat();
	}
	public void setResultat(int k) {
		switch(operacio) {
		case "+": resultat += k; 
					break;
		case "-": if(caselles_ocupades ==0) resultat = k; 
		else{
			if(k> resultat) resultat = k-resultat;
			else resultat = resultat-k;
			}
		break;
		case "/": if(caselles_ocupades ==0) resultat = k; 
		else {
			if(k> resultat) resultat = k/resultat;
			else resultat = resultat/k;
		}
					break;
					
		case "*": resultat *= k; break;
		}
		caselles_ocupades++;
	}
	public void unsetResultat(int k) {

		switch(operacio) {
		case "+": resultat -= k; 
					break;
		case "-":
			if(caselles_ocupades ==2){
				resultat = listCasella.get(0).getValor() + listCasella.get(1).getValor();
			}
			else{
				resultat = 0;
			}
			break;
		case "/": 
			if(caselles_ocupades ==2){
				resultat = listCasella.get(0).getValor() + listCasella.get(1).getValor();
			}
			else{
				resultat = 1;
			}
			
			 break;
		case "*": resultat /= k; break;
		}
			caselles_ocupades--;
	}
	
	
	//OPERACIONS VALIDACIO
	
	private boolean regioCompletada(){
		for(int i =0; i<listCasella.size();i++){
			if(listCasella.get(i).getValor() == 0) return false;
		}
		return true;
	}
	
	/*public boolean actualitzacioPosible(Casella c,int num){
		int old=c.getNum();
		c.borrarNum();
		boolean b;
		if(operacio.equals("+") && consultarSuma()+num<=objectiu) b=true;
		else if(operacio.equals("-") && listCasella.size()==2 && (consultarResta()-num>=objectiu || num-consultarResta()>=objectiu)) b=true;
		else if(operacio.equals("*") && consultarMultiplicacio()*num<=objectiu)b=true;
		else if(operacio.equals("/") && listCasella.size()==2 && (consultarResta()/num>=objectiu || num/consultarResta()>=objectiu)) b=true;
		else if(operacio.equals(" ") && listCasella.size()==1 && num==objectiu)b=true;
		else {b=false;}
		c.afegirNum(old);
		return b;
	}
	
	public boolean actualitzarResultat(){
		if(operacio.equals("+")) resultat=consultarSuma();
		else if(operacio.equals("-")) resultat=consultarResta();
		else if(operacio.equals("*")) resultat=consultarMultiplicacio();
		else if(operacio.equals("/")) resultat=consultarDivisio();
		else if(operacio.equals(" ") && listCasella.size()==1) resultat=listCasella.get(0).getValor();
		else return false;
		return true;	
	}*/
	
	public boolean nearFull(){
		if(tamany - caselles_ocupades == 1) return true;
		return false;
		/*int numBuit=0;
		
		System.out.println("tamnay regio:"+listCasella.size());
		for(int i =0; i<listCasella.size();i++){
			if(listCasella.get(i).getValor() == 0) ++numBuit;
		}
		System.out.println("numBuit"+numBuit);
		if(numBuit==1)return true;
		else return false;*/
	}
	
	/*public boolean consultarValidesa(){
		if(regioCompletada()) return validarRegioCompletada();
		else if(operacio.equals("+") || operacio.equals("*")) {
			if(objectiu>=resultat)return true;
			else return false;
		}
		else if((operacio.equals("-") || operacio.equals("/")) && listCasella.size()==2){
			if(objectiu<=resultat) return true;
			else return false;
			
		}
		else if(operacio.equals(" ") && listCasella.size()==1) {
			if(objectiu==resultat) return true;
			else return false;
		}
	    else return false;
	}
	
	public boolean validarRegioCompletada(){
		if(operacio.equals("+") || operacio.equals("*")) {
			if(objectiu==resultat)return true;
			else return false;
		}
		else if((operacio.equals("-") || operacio.equals("/")) && listCasella.size()==2){
			if(objectiu==resultat) return true;
			else return false;
			
		}
		else if(operacio.equals(" ") && listCasella.size()==1) if(objectiu==resultat) return true;
	    else return false;
	    return true;
	}
	
	
	private int consultarSuma(){
	 	int res=0;
		for(int i =0; i<listCasella.size();i++){
			int aux= listCasella.get(i).getValor();
			res+=aux;
		}
		return res;
	}
	
	private int consultarResta(){
	 	int a=listCasella.get(0).getValor();
	 	int b=listCasella.get(1).getValor();
	 	if(a==0 && b==0)return tamany; //fuerza a consultarValidesa()==cierto siempre que regioCompletada()==false
	 	if(a>b)return a-b;
	 	else return b-a;
	}
	
	private int consultarMultiplicacio(){
	 	int res=1;
		for(int i =0; i<listCasella.size();i++){
			int aux= listCasella.get(i).getValor();
			if(aux == 0) aux=1;
			res*=aux;
		}
		return res;
	}
	
	private int consultarDivisio(){
		int a=listCasella.get(0).getValor();
	 	int b=listCasella.get(1).getValor();
	 	if(a==0 && b==0)return tamany; //fuerza a consultarValidesa()==cierto siempre que regioCompletada()==false
	 	else if(a==0)return b;
	 	else if(b==0)return a;
	 	else if(a>b && a%b==0)return a/b;
	 	else if (b>a && b%a==0)return b/a;
	 	else return 0;
	}

	public void imprimirValorCaselles(){
		for(int i = 0; i<listCasella.size(); i++){
			System.out.print(listCasella.get(i).getValor() + " ");
		}
	}
	*/
	
	
	//OPERACIONS PER ALGORITME RESOLUCIO
/*private void generarCandidats() {
	    for(int i = 0; i<candidats.length;i++){candidats[i]=false;}
		if(objectiu<=0){}
		else{
			if(operacio.equals("+")){generarCandidatsSuma();}
			else if(operacio.equals("-")){generarCandidatsResta();}
			else if(operacio.equals("*")){generarCandidatsMultiplicacio();}
			else if(operacio.equals("/")){generarCandidatsDivisio();} 
			else if(operacio.equals(" ")){candidats[objectiu]=true;} 
		}
}
private void generarCandidatsDivisio() {
	int tam = t.getTamany();
	int numCaselles = listCasella.size();
	if(objectiu<2 || objectiu>tam || numCaselles!=2)candidats[0]=true;
	else{
		int aux=objectiu;
		int i=1;
		candidats[1]=true;
		candidats[aux+1]=true;
		aux*=2;
		i*=2;
		while(aux<=tam){
			candidats[i]=true;
			candidats[aux+1]=true;
			aux*=2;
			i*=2;
		}
		candidats[0]=false;
	}
	
	
}
private void generarCandidatsMultiplicacio() {
	int tam = t.getTamany();
	if(objectiu<2){candidats[0]=true;}
	else{
		for(int i =0;i<=tam;i++){if(objectiu%i==0)candidats[i]=true;}
		candidats[0]=false;
	}	
}
private void generarCandidatsResta() {
	int tam = t.getTamany();
	int numCaselles = listCasella.size();
	int aux=tam-numCaselles;
	if(objectiu<1 || objectiu>aux || numCaselles!=2) candidats[0]=true;
	else{
		for(int i =0;i<tam-aux;i++){candidats[tam+1-i]=true;}
		for(int i =0;i<tam-aux;i++){candidats[i]=true;}
		candidats[0]=false;
	}
	
}
private void generarCandidatsSuma() {
	int tam = t.getTamany();
	int numCaselles = listCasella.size();
	int min=numCaselles+1;
	int max=tam;
	int tmin=0;
	for(int i = 1; i<numCaselles;i++){tmin+=i;}
	for(int i = 1; i<numCaselles;i++){max+=tam-i;}
	if(objectiu<min || objectiu>max){candidats[0]=true;}
	else{
		int aux=objectiu-tmin;
		if(aux>tam-numCaselles){for(int i=tam+1;i>=tam-aux;i--){candidats[i]=true;}}
		else{
			for(int i =0;i<=objectiu-min;i++){candidats[i]=true;}
		}
		candidats[0]=false;
	}	
}*/
	
	/*public boolean actualitzarResultat(int old, int nuevo){
	if(operacio.equals("+")) resultat+=nuevo-old;
	else if(operacio.equals("-") && listCasella.size()==2) resultat=consultarResta();
	else if(operacio.equals("*")){
		if(resultat==0)resultat+=nuevo;
		else if(nuevo==0)resultat/=old;
		else if(old==0)resultat*=nuevo;
		else resultat*=nuevo/old;
	}
	else if(operacio.equals("/") && listCasella.size()==2) resultat=consultarDivisio();
	else if(operacio.equals(" ") && listCasella.size()==1)resultat=nuevo;
	else return false;
	return true;	
}*/
	
}
