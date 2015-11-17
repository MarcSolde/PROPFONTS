package controladores.dominio;

import java.io.*;
import java.util.*;

import classes_compartides.Casilla_comp;
import classes_compartides.Tauler;
import domini.Partida;
import domini.Usuari;
import tablero.Regio;
import tablero.TaulerKenken;

/**
 * @author Anna Mascaro
 *
 */
public class CtrlData {

	//private CtrlUsuari cUsuari;
	//private CtrlPartida cPartida;
	//private CtrlTauler cTauler;


	/**
	 * Creadora de CtrlData 
	 */
	public CtrlData() {
		//cUsuari = new CtrlUsuari();
		//cPartida = new CtrlPartida();
		//cTauler = new CtrlTauler();
		File file = new File("usuaris");
		if (!file.exists()) {
			file.mkdirs();
		}
		file = new File("taulers");
		if (!file.exists()) {
			file.mkdirs();
			file = new File("taulers/num.txt"); //the borrat el num de taulers, no fa falta, amb el nom ja sabem de quina partida son
		}
		file = new File("rankings");
		if (!file.exists()) {
			file.mkdirs();
			//file = new File ("")
		}
	}

	
	/**
	 * Escriu un usuari a la BD, si no existeix un altre amb
	 * el mateix nom d'usuari
	 * @param nom nom del usuari
	 * @param pwd contrasenya del usuari
	 * @return retorna si s'ha escrit o no l'usuari
	 */
	public boolean escriureUsuari(String nom, String pwd) {
		File file = new File("usuaris/" + nom);
		if (file.exists()) {
			return false;
		} else {
			file.mkdirs();
			if (!file.isDirectory()) {
				System.out.print("no s'ha pogut crear l'usuari\n");
			} else {
				file = new File("usuaris/" + nom + "/contrasenya.txt");
				File f = new File("usuaris/"+nom+"/num.txt");
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					f.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				FileWriter fw;
				try {
					fw = new FileWriter(file);
					try {
						fw.write((pwd+" "), 0, (pwd+" ").length());
						fw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				try {
					fw = new FileWriter(f);
					try {
						fw.write(("0 "), 0, ("0 ").length());
						fw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return true;
	}


	/**
	 * Llegeix un usuari de la BD
	 * @param nom nom del usuari
	 * @param pwd contrasenya del usuari
	 * @return retorna si existeix un usuari amb nom = nom i
	 * contrasenya = pwd a la BD
	 */
	public boolean llegirUsuari(String nom, String pwd) {
		File file = new File("usuaris/" + nom + "/contrasenya.txt");
		Scanner sc = null;
		if (file.exists()) {
			try {
				sc = new Scanner(file);
				sc.useDelimiter(" ");
				String s = sc.next();
				if (s.equals(pwd)) {
					sc.close();
					return true;
				}
				sc.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * Guarda una partida a la BD
	 * @param p partida
	 * @param nom nom de l'usuari
	 * @return retorna si s'ha pogut guardar la partida a la BD
	 */
	public boolean escriurePartida(Partida p, String nom){
		boolean b = false;
		File file = new File("0");
		Scanner sc;
		int num = 0;
		int numk = 0;
		if (p.getId() != -1) {
			num = p.getId();
			file = new File("usuaris/"+nom+"/partida"+Integer.toString(num)+".txt");
		} else {
			File aux = new File("usuaris/"+nom+"/num.txt");
			try {
				sc = new Scanner(aux);
				sc.useDelimiter(" ");
				String s = sc.next();
				num = Integer.parseInt(s);
				num += 1;
				p.setId(num);					//CALCULES ID MOLT B, PERO NO L'ASSIGNES A PARTIDA (EVIDENTMENT LA LINIA AQUESTA L'HE FET JO
				file = new File("usuaris/"+nom+"/partida"+Integer.toString(num)+".txt");
				FileWriter fw;
				try {
					fw = new FileWriter(aux);
					fw.write(Integer.toString(num),0,Integer.toString(num).length());
					fw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				numk = p.getId();			//TENIES PARTIDA.NOSEK . GETID(() ) UNA PARTIDA TINDRA UN TAULER, DONCS
			} catch (FileNotFoundException e) {				//LIDENTIFICADOR DE PARTIDA SERA EL NUM DE TXT DE TAULERS
				e.printStackTrace();
			}
		}
		if (!file.getName().equals("0")) {
			if (file.exists()) {
				num = Partida.getTauler().getId();
			} else {
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			FileWriter fw;
			try {
				fw = new FileWriter(file);
				int offset = 0;
				String s = Integer.toString(numk)+" ";
				fw.write(s, offset, s.length());
				s = Integer.toString(p.getTemps()) +" ";
				fw.write(s,offset,s.length());
				s = Integer.toString(Partida.getTauler().getMida())+" ";
				if (p.partidaFi()) s = "1 ";
				else s = "0 ";
				fw.write(s,offset,s.length());
				for (int x = 0; x < Partida.getTauler().getMida(); ++x) {
					for (int y = 0; y < Partida.getTauler().getMida(); ++y) {
						s = Integer.toString(Partida.getTauler().getValorTauler(x, y))+" ";
						fw.write(s,offset,s.length());
					}
				}
				fw.close();
		//AQUI TENIES UN RETURN... MAI ARRIBAVES A CREAR TAULER GJ
			} catch (IOException e) {
				e.printStackTrace();
			}
			b= true;
		}
		escriureTauler(Partida.getTauler(), p.getId());				//TAULER LI PASSES LID DE LA PARTIDA.
		
		return b;
	}

	/**
	 * Llegeix una partida guardada de la BD
	 * @param id identificador de la partida
	 * @param nom nom de l'usuari
	 * @return retorna la partida guardada per l'usuari amb
	 * nom = nom que te identificador = id
	 */
	public Partida llegirPartida(String id, String nom) {
		Partida p = new Partida(0);
		File file = new File("usuaris/"+ nom + "/partida" + id + ".txt");//BUSQUES UNA PARTIDA AMB DIRECTORI CORRECTE
		Scanner sc;
		try {
			sc = new Scanner(file);
			sc.useDelimiter(" ");
			String kenken = sc.next();
			TaulerKenken t = llegirTauler("taulers/kenken"+kenken+".txt");
			p = new Partida(t);
			p.setTemps(Integer.parseInt(sc.next()));
			if (sc.next()=="1") p.partidaFi(true);
			else p.partidaFi(false);
			
			for (int x = 0; x < Partida.getTauler().getMida();++x) {
				for (int y = 0; y < Partida.getTauler().getMida();++y) {
					if (sc.hasNext()) { 
						String s = sc.next();
						t.setValorTauler(x, y, Integer.parseInt(s));

					}
				}
			}
			
			
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return p;
	}

	/**
	 * Guarda un tauler a la BD
	 * @param t tauler que es vol escriure
	 * @param id identificador del fitxer
	 */
	public void escriureTauler(TaulerKenken t, int id) {
		File file = new File("taulers/"+Integer.toString(id));
		Scanner sc;
		//System.out.println("estic dins d¡escriure tauler");
		if (!file.exists()) {
			//System.out.println("crea tauler");
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			FileWriter fw;
			try {
				fw = new FileWriter(file);
				fw.write(Integer.toString(id),0,Integer.toString(id).length());
				fw.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			file = new File("taulers/kenken" + Integer.toString(id)+".txt");
			try {
				file.createNewFile();
				fw = new FileWriter(file);
				int offset = 0;
				String s;
				s = Integer.toString(t.getMida())+" ";
				fw.write(s,offset,s.length());
				s = Integer.toString(t.getNumRegions())+" ";
				fw.write(s,offset,s.length());
				for (int x = 0; x < t.getMida(); ++x) {
					for (int y = 0; y < t.getMida(); ++y){
						s = Integer.toString(t.getValorSol(x, y))+" ";
						fw.write(s,offset,s.length());
					}
				}
				for (int i = 0; i < t.getNumRegions(); ++i) {
					Regio r = t.getRegioById(i);
					s = r.getOperacio()+" "+Integer.toString(r.getObjectiu())+" ";
					fw.write(s,offset,s.length());
				}
				fw.write(s,offset,s.length());
				int reg = 0;
				for (int x = 0; x < t.getMida(); ++x) {
					for (int y = 0; y < t.getMida(); ++y) {
						if (reg < t.regions[x][y]) reg = t.regions[x][y];
						s = Integer.toString(t.regions[x][y])+" ";
						fw.write(s,offset,s.length());
					}
				}
				for (int x = 0; x < t.getMida(); ++x) {
					for (int y = 0; y < t.getMida(); ++y) {
						s = Integer.toString(t.getValorTauler(x, y))+" ";
						fw.write(s,offset,s.length());
					}
				}
				fw.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

}
	
	/**
	 * Llegeix un tauler de la BD
	 * @param nom nom del fitxer que conte un tauler
	 * @return tauler que hi ha guardat al fitxer nom
	 */
	public TaulerKenken llegirTauler (String nom) {
		TaulerKenken t;
		File file = new File(nom);
		Scanner sc;
		int nr;
		String s;
		try {
			sc = new Scanner(file);
			sc.useDelimiter(" ");
			int mida = Integer.parseInt(sc.next());
			t = new TaulerKenken(mida);
			ArrayList<String> regions = new ArrayList<String>();
			s = sc.next();
			nr = Integer.parseInt(s)+1;
			Boolean[] regs = new Boolean[nr];
			for (int x = 0; x < mida; ++x) {
				for (int y = 0; y < mida; ++y) {
					s = sc.next();
					t.setValorSol(x, y, Integer.parseInt(s));
				}
			}
			int i = 0;
			while (i < (nr*2)) {
				s = sc.next();
				regions.add(s);
				regs[i/2] = false;
				++i;
			}
			for (int x = 0; x < mida; ++x) {
				for (int y = 0; y < mida; ++y) {
					s = sc.next();
					int id = Integer.parseInt(s);
					t.regions[x][y] = id;
					if (!regs[id]) {
						int sol = Integer.parseInt(regions.get(id*2+1));
						t.setNovaRegio(x, y, regions.get(id*2), sol);
						regs[id] = true;
					} else t.setRegioById(x, y, id);
				}
			}
			/*for (int x = 0; x < mida; ++x) {
				for (int y = 0; y < mida; ++y) {
					if(x== 0 && y == 0){
						int val = Integer.parseInt(s);
						t.setValorTauler(x,y,val);
					}
															
					int val = Integer.parseInt(s);
					t.setValorTauler(x,y,val);
					s = sc.next();
					
				}
			}*/
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			t = new TaulerKenken(0);
		}
		return t;
	}
}
