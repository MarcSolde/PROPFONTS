package persistencia;

import java.io.*;
import java.util.*;

import domini.classes.BestTime;
import domini.classes.MostSolved;
import domini.classes.Partida;
import domini.classes.TaulerKenken;

public class CtrlData {
	
	private int numTauler;
	/**
	 * Creadora
	 */
	public CtrlData() {
		File file = new File("data/usuaris");
		if (!file.exists()) {
			file.mkdirs();
		}
		file = new File("data/taulers");
		if (!file.exists()) {
			file.mkdirs();
			file = new File("data/taulers/num.txt");
			try {
				file.createNewFile();
				FileOutputStream f = new FileOutputStream(file);
				ObjectOutputStream out = new ObjectOutputStream(f);
				numTauler = 0;
				out.writeObject(numTauler);
				out.close();
				f.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				FileInputStream f = new FileInputStream("data/taulers/num.txt");
				try {
					ObjectInputStream in = new ObjectInputStream(f);
					try {
						numTauler = (int) in.readObject();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					in.close();
					f.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		file = new File("data/rankings");
		if (!file.exists()) {
			file.mkdirs();
			file = new File ("data/rankings/mostSolved.txt");
			try {
				file.createNewFile();
			} catch(IOException e) {
				e.printStackTrace();
			}
			file = new File("data/rankings/bestTime.txt");
			try {
				file.createNewFile();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean guardarUsuari(String nom, String pwd){
		File file = new File("data/usuaris/" + nom);
		if (file.exists()) return false;
		else {
			file.mkdirs();
			if (!file.isDirectory()) return false; //System.out.print("No s'ha pogut crear l'usuari\n");
			else {
				file = new File("data/usuaris/" + nom + "/contrasenya.txt");
				try {
					file.createNewFile();
				} catch(IOException e) {
					e.printStackTrace();
				}
				FileWriter fw;
				try {
					fw = new FileWriter(file);
					fw.write((pwd + " "), 0, (pwd + " ").length());
					fw.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}
	
	public boolean llegirUsuari(String nom, String pwd){
		File file = new File("data/usuaris/" + nom + "/contrasenya.txt");
		Scanner sc = null;
		if (file.exists()) {
			try {
				sc = new Scanner(file);
			} catch(IOException e) {
				e.printStackTrace();
			}
			sc.useDelimiter(" ");
			String s = sc.next();
			sc.close();
			if (s.equals(pwd)) return true;
		}
		return false;
	}
	
	public void guardarPartida(Partida p, String usr, String nom){
		File f = new File("data/usuaris/"+usr+"/"+nom+".txt");
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		try {
			FileOutputStream file = new FileOutputStream(f);
			try {
				ObjectOutputStream out = new ObjectOutputStream(file);
				out.writeObject(p);
				out.close();
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Partida llegirPartida(String usr, String nom) {
		Partida p = new Partida(0);
		File f = new File("data/usuaris/"+usr+"/"+nom+".txt");
		if (f.exists()) {
			try {
				FileInputStream file = new FileInputStream(f);
				try {
					ObjectInputStream in = new ObjectInputStream(file);
					try {
						p = (Partida) in.readObject();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					in.close();
					file.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			} catch(FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return p;
	}
	
	public void esborrarPartida(String usr, String nom) {
		File file = new File("data/usuaris/"+usr+ "/"+nom+".txt");
		file.delete();
	}
	
	public void guardarTauler(TaulerKenken t){
		numTauler += 1;
		t.setId(numTauler);
		try {
			FileOutputStream f = new FileOutputStream("data/taulers/num.txt");
			FileOutputStream file = new FileOutputStream("data/taulers/kenken"+numTauler+".txt");
			try {
				ObjectOutputStream outNum = new ObjectOutputStream(f);
				outNum.writeObject(numTauler);
				outNum.close();
				f.close();
				ObjectOutputStream out = new ObjectOutputStream(file);
				out.writeObject(t);
				out.close();
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public TaulerKenken llegirTauler(String id){
		TaulerKenken t = new TaulerKenken(0);
		File f = new File("data/taulers/kenken"+id+".txt");
		if (f.exists()) {
			try {
				FileInputStream file = new FileInputStream(f);
				try {
					ObjectInputStream in = new ObjectInputStream(file);
					try {
						t = (TaulerKenken) in.readObject();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					in.close();
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return t;
	}
	
	public void esborrarTauler(String id){
		File file = new File("data/taulers/kenken"+id+".txt");
		file.delete();
	}
	
	public void guardarMostSolved(MostSolved m){
		try {
			FileOutputStream file = new FileOutputStream("data/rankings/mostSolved.txt");
			try {
				ObjectOutputStream out = new ObjectOutputStream(file);
				out.writeObject(m);
				out.close();
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public MostSolved llegirMostSolved() {
		MostSolved m = new MostSolved();
		try {
			FileInputStream file = new FileInputStream("data/rankings/mostSolved.txt");
			try {
				ObjectInputStream in = new ObjectInputStream(file);
				try {
					m = (MostSolved) in.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				in.close();
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return m;
	}
	
	public void guardarBestTime(BestTime b){
		try {
			FileOutputStream file = new FileOutputStream("data/rankings/bestTime.txt");
			try {
				ObjectOutputStream out = new ObjectOutputStream(file);
				out.writeObject(b);
				out.close();
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public BestTime llegirBestTime(){
		BestTime b = new BestTime();
		try {
			FileInputStream file = new FileInputStream("data/rankings/bestTime.txt");
			try {
				ObjectInputStream in = new ObjectInputStream(file);
				try {
					b = (BestTime) in.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				in.close();
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public ArrayList<String> llistaPartides(String usr) {
		ArrayList<String> l = new ArrayList<String>();
		File f = new File("data/usuaris/"+usr);
		File[] paths = f.listFiles();
		for (File path:paths) {
			if (!path.getName().equals("contrasenya.txt")) {
				l.add(path.getName());
			}
		}
		return l;
	}
	
	public ArrayList<String> llistaKenkens() {
		ArrayList<String> l = new ArrayList<String>();
		File f = new File("data/taulers");
		File[] paths = f.listFiles();
		for (File path:paths) {
			if (!path.getName().equals("num.txt")) {
				l.add(path.getName());
			}
		}
		return l;
	}
	
	public boolean existeixPartida(String usr, String nom) {
		File file = new File("data/usuaris/"+usr+"/"+nom+".txt");
		if (file.exists()) return true;
		return false;
	}

}
