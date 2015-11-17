package domini.classes;
import java.util.Map;
import java.util.TreeMap;

/** Rankings de kenkens resueltos
 * @author Marc Soldevilla
 * **/
public class MostSolved extends Ranking{
	/**  TreeMap de la informacion, el String indica el usuario y el integer el cantidad de kenkens resueltos **/
	Map<String, Integer> map;

	
	/** Creadora de la clase */
	public MostSolved() {
		map = new TreeMap<String, Integer>();
	}
	/**INcrementa en 1 los kenkens finalizados por el usuario user
	 * @param	user	el usuario
	 * **/
	public void setIncreasePlayed(String user) 
	//Aumenta en uno los valores de Kenkens resueltos del Usuario
	{
		if (isThere(user)) {
			map.put(user, map.get(user)+1);
		}
		else map.put(user, 1);
	}
	/**Imprime por pantalla el numero de  kenkens finalizados por el usuario user
	 * @param	user	el usuario
	 * **/
	public void getMostSolvedUser(String user)
	//Retorna els kenken resolts per l'usuari
	{
		if (isThere(user)) {
			System.out.println(user+" "+map.get(user));
		}
	}
	//private void getMostSolved(int N)
	//Retorna los Usuarios con mas Kenken resueltos de tama�o NxN
	//{
		
	//}
	/**Imprime por pantalla el numero de kenkens finalizados por todos los usuarios, indicando cada user**/
	public void getMostSolvedGlobal()
	//Retorna los usuarios con mas KenKen resueltos.
	{
		for(String key: map.keySet()) {
			System.out.println(key+" "+map.get(key));
		}
	}
	private boolean isThere(String user) {
		return map.get(user) != null;
	}
	public Map<String, Integer> getMap() {
		return map;
	}
	
	public void setMap(Map<String, Integer> m) {
		map = m;
	}
}
