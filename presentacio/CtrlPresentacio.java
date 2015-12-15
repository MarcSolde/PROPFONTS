package Presentacio;
//import Domini.ControladorDomini;

public class CtrlPresentacio {
	//private ControladorDomini cd = new ControladorDomini();
	private VistaPartida vp;
	private VistaCreacio vc;
	//private VistaMenu vm= new VistaMenu();
	//private VistaLogin vl=new VistaLogin();
	public void inicializarPresentacion() {
			vp=new VistaPartida(this);	
			//vp.llamarVista();
			vc= new VistaCreacio(this);
			vc.llamarVista();
	}
	
}
