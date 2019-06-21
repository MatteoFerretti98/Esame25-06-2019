package operazioni;

/**
 * @author Matteo Ferretti (s1083630@studenti.univpm.it), Angelo D'Agostino Bonomi (s1082444@studenti.univpm.it)
 * @version 1.0
 */

/**
 * Questa è la classe del costruttore delle {@link Statistiche}.
 */
public class Statistics {
	
	private int Count;
	private float Media;
	private float DevStand;
	private double Somma;
	private double Max;
	private double Min;
	private String Field;
	/**
	 * Il tipo Statistics compone {@link Statistiche}.
	 * @param field E' il nome del tipo di attributo selezionato su cui si fanno le statistiche.
	 * @param count Conteggio degli elementi unici
	 * @param somma Somma di tutti gli elementi (solo numerici)
	 * @param media Media di tutti gli elementi (solo numerici)
	 * @param devStand Deviazione Standard di tutti gli elementi (solo numerici)
	 * @param max Max tra tutti gli elementi (solo numerici)
	 * @param min Min tra tutti gli elementi (solo numerici)
	 */
	//costruttore delle statistiche
	public Statistics(String field, int count,double somma, float media, float devStand, double max, double min) {
		super();
		this.Count=count;
		this.Somma=somma;
		this.Media=media;
		this.DevStand=devStand;
		this.Max=max;
		this.Min=min;
		this.Field=field;
	}
	//getters e setters
	/**
	 * Fornisce il conteggio degli elementi unici.
	 * @return Count o <strong>0</strong> se non ci sono elementi.
	 */
	public int getCount() {
		return Count;
	}
	/**
	 * Imposta il conteggio di tutti gli elementi.
	 * @param count Conteggio elementi unici.
	 */
	public void setCount(int count) {
		Count = count;
	}
	/**
	 * Fornisce la somma di tutti gli elementi ma solo per telefono, fax, latitudine e longitudine.
	 * @return Somma o <strong>0</strong> se non ci sono elementi.
	 */
	public double getSomma() {
		return Somma;
	}
	/**
	 * Imposta la somma.
	 * @param somma Somma di tutti gli elementi.
	 */
	public void setSomma(double somma) {
		Somma = somma;
	}
	/**
	 * Fornisce la media di tutti gli elementi ma solo per telefono, fax, latitudine e longitudine.
	 * @return Media o <strong>0</strong> se non ci sono elementi.
	 */
	public float getMedia() {
		return Media;
	}
	/**
	 * Imposta la media.
	 * @param media Fa la media di tutti gli elementi.
	 */
	public void setMedia(float media) {
		Media = media;
	}
	/**
	 * Fornisce la deviazione standard di tutti gli elementi ma solo per telefono, fax, latitudine e longitudine.
	 * @return DevStand o <strong>0</strong> se non ci sono elementi.
	 */
	public float getDevStand() {
		return DevStand;
	}
	/**
	 * Imposta la devStand.
	 * @param devStand Fa la deviazione standard di tutti gli elementi.
	 */
	public void setDevStand(float devStand) {
		DevStand = devStand;
	}
	/**
	 * Fornisce il Massimo tra tutti gli elementi ma solo per telefono, fax, latitudine e longitudine.
	 * @return Max o <strong>0</strong> se non ci sono elementi.
	 */
	public double getMax() {
		return Max;
	}
	/**
	 * Imposta il Max.
	 * @param max Fornisce il massimo fra gli elementi.
	 */
	public void setMax(double max) {
		Max = max;
	}
	/**
	 * Fornisce il Minimo tra tutti gli elementi ma solo per telefono, fax, latitudine e longitudine.
	 * @return Min o <strong>0</strong> se non ci sono elementi.
	 */
	public double getMin() {
		return Min;
	}
	/**
	 * Imposta il Min.
	 * @param min Fornisce il minimo fra gli elementi.
	 */
	public void setMin(double min) {
		Min = min;
	}
	/**
	 * Fornisce il campo degli elementi in osservazione.
	 * @return Field o <strong>0</strong> se non ci sono campi.
	 */
	public String getField() {
		return Field;
	}
	/**
	 * Imposta il field.
	 * @param field Fornisce il campo degli elementi.
	 */
	public void setField(String field) {
		Field = field;
	}
}
