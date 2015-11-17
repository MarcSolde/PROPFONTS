package controladores.dominio;

import tablero.TaulerKenken;
import java.util.*;

public class CtrlTauler {
	private TaulerKenken t;
	public CtrlData cData;
	
	public CtrlTauler(){
		cData = new CtrlData();
	}
	
	public TaulerKenken llegirTaulerFile(String id){
		t = cData.llegirTauler(id);
		return t;
	}
	
	//public TaulerKenken llegirTaulerInput
}
