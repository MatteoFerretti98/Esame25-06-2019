package dataset;

import java.util.ArrayList;
import java.util.List;
/**
 * Questa è la classe delle funzioni per controllare se il valore di una variabile esterna è contenuto in uno dei campi.
 * @author Matteo Ferretti (s1083630@studenti.univpm.it), Angelo D'Agostino Bonomi (s1082444@studenti.univpm.it)
 * @version 1.0
 */
/**
 * E' la classe delle funzioni per controllare se il valore di una variabile esterna è contenuto in un determinato campo.
 * Questa classe estende {@link Lista} perchè avevamo bisogno che appunto Lista ereditasse tali metodi.
 */
public class Container extends Lista {
	/**
	 * Inizializza una lista di dati come ArrayList.
	 */
	private List<Dati> lista = new ArrayList<>();
	/**
	 * Controlla se la variabile corrente della colonna Fid nella linea i è uguale a e.
	 * @param e Variabile da importare.
	 * @param i Numero della riga.
	 * @return true Se l'elemento i-esimo è uguale a e, false se è il contrario.
	 */
	//queste funzioni effettuano un controllo sulla presenza di un campo nella lista  
	public boolean containsFid(int e,int i) {
		if (e==lista.get(i).getFid()) return true; 
		else return false;
		}
	/**
	 * Controlla se la variabile corrente della colonna PreNorm nella linea i è uguale a e.
	 * @param e Variabile da importare.
	 * @param i Numero della riga.
	 * @return true Se l'elemento i-esimo è uguale a e, false se è il contrario.
	 */
	public boolean containsPreNorm(String e,int i) {
		if (e.contains(lista.get(i).getPreNorm())) return true; 
		else return false;
		}
	/**
	 * Controlla se la variabile corrente della colonna Comune nella linea i e' uguale a e.
	 * @param e Variabile da importare.
	 * @param i Numero della riga.
	 * @return true Se l'elemento i-esimo è uguale a e, false se è il contrario.
	 */
	public boolean containsComune(String e,int i) {
		if (e.contains(lista.get(i).getComune())) return true; 
		else return false;
		}
	/**
	 * Controlla se la variabile corrente della colonna Provincia nella linea i è uguale a e.
	 * @param e Variabile da importare.
	 * @param i Numero della riga.
	 * @return true Se l'elemento i-esimo è uguale a e, false se è il contrario.
	 */
	public boolean containsProvincia(String e,int i) {
		if (e.contains(lista.get(i).getProvincia())) return true; 
		else return false;
		}
	/**
	 * Controlla se la variabile corrente della colonna Indirizzo nella linea i è uguale a e.
	 * @param e Variabile da importare.
	 * @param i Numero della riga.
	 * @return true Se l'elemento i-esimo è uguale a e, false se è il contrario.
	 */
	public boolean containsIndirizzo(String e,int i) {
		if (e.contains(lista.get(i).getIndirizzo())) return true; 
		else return false;
		}
	/**
	 * Controlla se la variabile corrente della colonna Telefono nella linea i è uguale a e.
	 * @param e Variabile da importare.
	 * @param i Numero della riga.
	 * @return true Se l'elemento i-esimo è uguale a e, false se è il contrario.
	 */
	public boolean containsTelefono(long e,int i) {
		if (e==lista.get(i).getTelefono()) return true; 
		else return false;
		}
	/**
	 * Controlla se la variabile corrente della colonna Fax nella linea i è uguale a e.
	 * @param e Variabile da importare.
	 * @param i Numero della riga.
	 * @return true Se l'elemento i-esimo è uguale a e, false se è il contrario.
	 */
	public boolean containsFax(long e,int i) {
		if (e==lista.get(i).getFax()) return true; 
		else return false;
		}
	/**
	 * Controlla se la variabile corrente della colonna Tipo nella linea i è uguale a e.
	 * @param e Variabile da importare.
	 * @param i Numero della riga.
	 * @return true Se l'elemento i-esimo è uguale a e, false se è il contrario.
	 */
	public boolean containsTipo(String e,int i) {
		if (e.contains(lista.get(i).getTipo())) return true; 
		else return false;
		}
	/**
	 * Controlla se la variabile corrente della colonna Punto nella linea i è uguale a e.
	 * @param e Variabile da importare.
	 * @param i Numero della riga.
	 * @return true Se l'elemento i-esimo è uguale a e, false se è il contrario.
	 */
	public boolean containsPunto(Punto e,int i) {
		if ((e.getLatitudine()==lista.get(i).getPunto().getLatitudine())&&(e.getLongitudine()==lista.get(i).getPunto().getLongitudine())) return true; 
		else return false;
		}
	}	

