package domini.controladores;

import java.util.*;

import domini.classes.TaulerKenken;
import persistencia.CtrlData;
/**
 * 
 * @author anna
 *
 */
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
