package persistencia;

import java.io.*;
import java.util.*;

import domini.classes.Partida;
import domini.classes.TaulerKenken;

public class CtrlPersistencia {
	
	/**
	 * Creadora
	 */
	public CtrlPersistencia() throws IOException {
		File file = new File("data/usuaris");
		if (!file.exists()) {
			file.mkdirs();
		}
		file = new File("data/taulers");
		if (!file.exists()) {
			file.mkdirs();
			file = new File("data/taulers/num.txt");
			FileWriter fw = new FileWriter(file);
			fw.write(("0 "), 0, ("0 ").length());
			fw.close();
		}
		file = new File("data/rankings");
		if (!file.exists()) {
			file.mkdirs();
			file = new File ("data/rankings/mostSolved.txt");
			file.createNewFile();
			file = new File("data/rankings/bestTime.txt");
			file.createNewFile();
		}
	}
	
	public boolean escriureUsuari(String nom, String pwd) throws IOException {
		File file = new File("data/usuaris/" + nom);
		if (file.exists()) return false;
		else {
			file.mkdirs();
			if (!file.isDirectory()) return false; //System.out.print("No s'ha pogut crear l'usuari\n");
			else {
				file = new File("data/usuaris/" + nom + "/contrasenya.txt");
				File f = new File("data/usuaris/" + nom + "/num.txt");
				file.createNewFile();
				f.createNewFile();
				FileWriter fw;
				fw = new FileWriter(file);
				fw.write((pwd + " "), 0, (pwd + " ").length());
				fw.close();
				fw = new FileWriter(f);
				fw.write(("0 "), 0, ("0 ").length());
				fw.close();
			}
		}
		return true;
	}
	
	public boolean llegirUsuari(String nom, String pwd) throws IOException {
		File file = new File("data/usuaris/" + nom + "/contrasenya.txt");
		Scanner sc = null;
		if (file.exists()) {
			sc = new Scanner(file);
			sc.useDelimiter(" ");
			String s = sc.next();
			sc.close();
			if (s.equals(pwd)) return true;
		}
		return false;
	}
	
	public void escriurePartida(Partida p, String nom) throws IOException {
		if (p.getId() == -1) {
			File aux = new File("data/usuaris/"+nom+"/num.txt");
			Scanner sc = new Scanner(aux);
			sc.useDelimiter(" ");
			String s = sc.next();
			int num = Integer.parseInt(s);
			num += 1;
			p.setId(num);
			FileOutputStream file = new FileOutputStream("data/usuaris/"+nom+"/partida"+Integer.toString(num)+".txt");
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(p);
			out.close();
			file.close();
			FileWriter fw = new FileWriter(aux);
			fw.write(Integer.toString(num),0,Integer.toString(num).length());
			fw.close();			
		} else {
			FileOutputStream file = new FileOutputStream("data/usuaris/"+nom+"/partida"+Integer.toString(p.getId())+".txt");
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(p);
			out.close();
			file.close();
		}
	}
	
	public Partida llegirPartida(String nom) throws IOException, ClassNotFoundException {
		Partida p = new Partida(0);
		System.out.println("\n\nTRIA UNA PARTIDA\n\n");
		File f = new File("data/usuaris/"+nom);
		File[] paths = f.listFiles();
		int it = 0;
		for (File path:paths) {
		++it;
			if (!path.getName().equals("contrasenya.txt") && !path.getName().equals("num.txt")) {
				System.out.println(path);
			}
		}
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		f = new File("data/usuaris/"+nom+"partides/partida"+s+".txt");
		if (f.exists()) {
			FileInputStream file = new FileInputStream(f);
			ObjectInputStream in = new ObjectInputStream(file);
			p = (Partida) in.readObject();
			in.close();
			file.close();
		}
		return p;
	}
	
	public void esborrarPartida(String nom) {
		System.out.println("\n\nTRIA UNA PARTIDA\n\n");
		File f = new File("data/usuaris/"+nom);
		File[] paths = f.listFiles();
		int it = 0;
		for (File path:paths) {
		++it;
			if (!path.getName().equals("contrasenya.txt") && !path.getName().equals("num.txt")) {
				System.out.println(path);
			}
		}
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		File file = new File("data/usuaris/"+nom+ "/partida"+s+".txt");
		file.delete();
	}
	
	public void escriureTauler(TaulerKenken t) throws IOException{
		File aux = new File("data/taulers/num.txt");
		Scanner sc = new Scanner(aux);
		sc.useDelimiter(" ");
		String s = sc.next();
		int num = Integer.parseInt(s);
		num += 1;
		t.setId(num);
		FileOutputStream file = new FileOutputStream("data/taulers/kenken"+Integer.toString(num)+".txt");
		ObjectOutputStream out = new ObjectOutputStream(file);
		out.writeObject(t);
		out.close();
		file.close();
		FileWriter fw = new FileWriter(aux);
		fw.write(Integer.toString(num),0,Integer.toString(num).length());
		fw.close();
	}
	
	public TaulerKenken llegirTauler() throws IOException, ClassNotFoundException {
		TaulerKenken t = new TaulerKenken(0);
		System.out.println("\n\nTRIA UN KENKEN\n\n");
		File f = new File("data/taulers/");
		File[] paths = f.listFiles();
		int it = 0;
		for (File path:paths) {
		++it;
			if (!path.getName().equals("num.txt")) {
				System.out.println(path);
			}
		}
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		f = new File("data/taulers/kenken"+s+".txt");
		if (f.exists()) {
			FileInputStream file = new FileInputStream(f);
			ObjectInputStream in = new ObjectInputStream(file);
			t = (TaulerKenken) in.readObject();
			in.close();
			file.close();
		}
		return t;
	}
	
	public void esborrarTauler(){
		System.out.println("\n\nTRIA UN KENKEN\n\n");
		File f = new File("data/taulers/");
		File[] paths = f.listFiles();
		int it = 0;
		for (File path:paths) {
		++it;
			if (!path.getName().equals("num.txt")) {
				System.out.println(path);
			}
		}
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		File file = new File("data/taulers/kenken"+s+".txt");
		file.delete();
	}
	



}
