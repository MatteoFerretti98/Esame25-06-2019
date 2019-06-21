package dataset;

/**
 * @author Matteo Ferretti (s1083630@studenti.univpm.it), Angelo D'Agostino Bonomi (s1082444@studenti.univpm.it)
 * @version 1.0
 */

/**
 * Questa è la classe del costruttore delle coordinate latitudine e longitudine.
 */
public class Punto {
	/**
	 * Anche il tipo Punto, insieme a Dati, compone il costruttore di {@link Lista}
	 * @param latitudine Coordinata della latitudine
	 * @param longitudine Coordinate della longitudine
	 */
private float Latitudine;
private float Longitudine;

	/**
	 * Imposta il costruttore
	 */
	public Punto() {
		this.Latitudine=0;
		this.Longitudine=0;
	}
	/**
	 * Imposta le coordinate
	 * @param latitudine Imposta la Latitudine
	 * @param longitudine Imposta la Longitudine
	 */
	public Punto(float latitudine, float longitudine) {
		this.Latitudine=latitudine;
		this.Longitudine=longitudine;
	}
	//getters e setters
	/**
	 * Fornisce la coordinata della latitudine.
	 * @return {@link Latitudine} o <strong>0</strong> se la Latitudine inserito non è corretta.
	 */
	public float getLatitudine() {
		return Latitudine;
	}
	/**
	 * Imposta la coordinata della latitudine.
	 * @param latitudine Prende Latitudine.
	 */
	public void setLatitudine(float latitudine) {
		this.Latitudine= latitudine;
	}
	/**
	 * Fornisce la coordinata della longitudine
	 * @return {@link Longitudine} o <strong>0</strong> se la Longitudine inserito non è corretta.
	 */
	public float getLongitudine() {
		return Longitudine;
	}
	/**
	 * Imposta la coordinata della longitudine
	 * @param longitudine Prende Longitudine.
	 */
	public void setLongitudine(float longitudine) {
		this.Longitudine = longitudine;
	}
	
}