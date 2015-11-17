package domini.controladores;

import domini.controladores.CtrlDomini;
import presentacio.*;
/**
 * 
 * @author arnau.zapata.i
 *
 */
public class CtrlPresentacio {
	int tamany=7;
	private CtrlDomini cd = new CtrlDomini();
	private VistaPartida vp;
	private VistaCreacio vc;
	//private VistaMenu vm= new VistaMenu(this);
	//private VistaLogin vl=new VistaLogin();
	public void inicializarPresentacion() {
			//vm.llamarVista();
			//vp=new VistaPartida(this);	
			//vp.llamarVista();
			vc= new VistaCreacio(this);
			vc.llamarVista();
	}
	public void enviarTablero(CasillaCP[][] caselles, int[][] regionsId) {
		cd.crearTauler();
		for(int i=0;i<tamany;i++)for(int j=0;j<tamany;j++){
			CasillaCP c= caselles[i][j];
			int valor=c.getValor();
			String op=c.getOperacio();
			String obj=c.getObjectiu();
			int id= regionsId[i][j];
			
			cd.introduirValorCasellaCreacio(i,j,valor);
			cd.introduirRegioCreacio(i,j,id);
			cd.introduirOperacioCreacio(i,j,id,op);
			cd.introduirResultatCreacio(i,j,id,obj);
		}
		if (cd.teSolucioUnica(cd.getTauler())) {
			cd.GuardarTauler();
		}
		//else{misatgeError+continuar en Vista Creacio}
	}
	
	public boolean afegirValor(int x, int y, int n) {
		return cd.introduirValorJugar(x,y,n);
	}

	public boolean borrarCandidat(int x, int y, int n) {
		return cd.borrarCandidatJugar(x,y,n);
	}

	public boolean addCandidat(int x, int y, int n) {
		return cd.addCandidatJugar(x,y,n);
	}
	public void llamarCreacio() {
		 vc=new VistaCreacio(this);
		 vc.llamarVista();
	}
	public void llamarPartida() {
		 vp=new VistaPartida(this);
		 vp.llamarVista();
	}
	
}
