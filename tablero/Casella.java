package tablero;


public class Casella {
	private int num=0;
	private boolean[] ajuda;
	//private boolean valorfixat=false;
	private int tamany;
	//private boolean valid=false;   //aun esta por ver si es necesario o no o que hace falta modificar
	
	
	//CREADORES
	
	public Casella(int tam){
		tamany= tam;
		ajuda = new boolean[tamany];
		for(int i = 0; i<tamany;i++){ajuda[i]=false;}
	}
	
	//GETTERS i SETTERS
	
	public boolean afegirNum(int n) {
		if(n>tamany) return false;
		else{
			num=n;
			//valid=true;
			return true;
		}
	}

	public boolean borrarNum() {
		num=0;
		//valid=false;
		return true;
	}
	
	public boolean setCandidat(int n,boolean b){
		if(n>0 && n<tamany){ajuda[n-1]=b;return true;}
		else return false;
	}
	public boolean[] getCandidats(){return ajuda;}	
	
	public int getNum() {
		return num;
	}

	
	/*public void cleanCasella() {
		if(valorfixat==false){borrarNum();}
	}

	public void Valorfixat(boolean b) {
		this.valorfixat = b;
	}*/
	
	
	 /* public boolean[] getCandidats(boolean[] numPosible){
	    int old=num;
	    boolean[] auxCandidats= reg.getCandidats();
	    for(int i=1;i<auxCandidats.length;i++){
	    	  num=i;
	          if(auxCandidats[i]==true && !numPosible[i]==false) auxCandidats[i]=false;
	          else if(auxCandidats[i]==true) auxCandidats[i]=true;
	          else auxCandidats[i]=false;
	    }
	  	num=old;
	  	return auxCandidats;
	  }	*/
	
	/*public boolean comprovarSolucio(){
	if(num==solucio && num!=0) return true;
	else return false;
	}

	public void setSolucio(int s){
		solucio=s;
	}
	
	public int getSolucio() {
		return solucio;
	}
	
	public void setValid(boolean b) {
		this.valid=b;		
	}
	
	//public boolean getValid(){return valid;}
	*/
	  
}
