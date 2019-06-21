package dataset;

/**
 * @author Matteo Ferretti (s1083630@studenti.univpm.it), Angelo D'Agostino Bonomi (s1082444@studenti.univpm.it)
 * @version 1.0
 */

/**
 * Questa è la classe del costruttore principale e collegata ad essa abbiamo 
 * {@link Punto} che è la classe costruttore di latitudine e longitudine
 */
public class Dati {
private int Fid;
private String PreNorm;
private String Comune;
private String Provincia;
private String Indirizzo;
private long  Telefono;
private long Fax;
private String Tipo;
private Punto Punto;

/**
 * Il tipo Dati compone {@link Lista}
 * @param fid Numero della riga della lista
 * @param preNorm PreProcessoNormalizzazione: Nome dell'agenzia
 * @param comune Comune della sede dello stabilimento dell'agenzia
 * @param provincia Provincia del comune della sede dell'agenzia
 * @param indirizzo Indirizzo della sede dell'agenzia
 * @param telefono Numero telefonico dell'agenzia
 * @param fax Numero del fax dell'agenzia
 * @param tipo Tipologia dell'agenzia
 * @param latitudin Latitudine della locazione dell'agenzia
 * @param longitudin Longitudine della locazione dell'agenzia
 */
//costruttore della classe
public Dati(int fid, String preNorm, String comune, String provincia, String indirizzo, long telefono, long fax,
		String tipo,float latitudin, float longitudin) {
	super();
	Fid = fid;
	PreNorm = preNorm;
	Comune = comune;
	Provincia = provincia;
	Indirizzo = indirizzo;
	Telefono = telefono;
	Fax = fax;
	Tipo = tipo;
	Punto=new Punto(latitudin,longitudin);
}
//getters e setters
/**
 * Fornisce il codice della riga dell'agenzia.
 * @return Fid o <strong>0</strong> se il Fid inserito non è corretto.
 */
public int getFid() {
	return Fid;
}
/**
 * Imposta il codice della riga.
 * @param fid Numero Fid.
 */
public void setFid(int fid) {
	Fid = fid;
}
/**
 * Fornisce il nome dell'agenzia.
 * @return PreNorm o <strong>null</strong> se il PreNorm inserito non è corretto.
 */
public String getPreNorm() {
	return PreNorm;
}
/**
 * Imposta il nome dell'agenzia.
 * @param preNorm Processo di PreNormalizzazione.
 */
public void setPreNorm(String preNorm) {
	PreNorm = preNorm;
}
/**
 * Fornisce il comune della sede dell'agenzia.
 * @return Comune o <strong>null</strong> se il Comune inserito non è corretto.
 */
public String getComune() {
	return Comune;
}
/**
 * Imposta il comune della sede dell'agenzia.
 * @param comune Nome del Comune.
 */
public void setComune(String comune) {
	Comune = comune;
}
/**
 * Fornisce la provincia della sede dell'agenzia.
 * @return Provincia o <strong>null</strong> se il Provincia inserito non è corretto.
 */
public String getProvincia() {
	return Provincia;
}
/**
 * Imposta la provincia della sede dell'agenzia.
 * @param provincia Nome provincia.
 */
public void setProvincia(String provincia) {
	Provincia = provincia;
}
/**
 * Fornisce l'indirizzo della sede dell'agenzia.
 * @return Indirizzo o <strong>null</strong> se l'Indirizzo inserito non è corretto.
 */
public String getIndirizzo() {
	return Indirizzo;
}
/**
 * Imposta l'indirizzo della sede dell'agenzia.
 * @param indirizzo Stringa dell'indirizzo.
 */
public void setIndirizzo(String indirizzo) {
	Indirizzo = indirizzo;
}
/**
 * Fornisce il telefono dell'agenzia.
 * @return Telefono o <strong>0</strong> se il Telefono inserito non è corretto.
 */
public long getTelefono() {
	return Telefono;
}
/**
 * Imposta il telefono dell'agenzia.
 * @param telefono Numero di telefono.
 */
public void setTelefono(long telefono) {
	Telefono = telefono;
}
/**
 * Fornisce il fax dell'agenzia.
 * @return Fax o <strong>0</strong> se il Fax inserito non è corretto.
 */
public long getFax() {
	return Fax;
}
/**
 * Imposta il fax dell'agenzia.
 * @param fax Numero di fax.
 */
public void setFax(long fax) {
	Fax = fax;
}
/**
 * Fornisce il tipo di ente di cui fa parte l'agenzia.
 * @return Tipo o <strong>null</strong> se il Tipo inserito non è corretto.
 */
public String getTipo() {
	return Tipo;
}
/**
 * Imposta il tipo di ente dell'agenzia.
 * @param tipo Tipo di ente.
 */
public void setTipo(String tipo) {
	Tipo = tipo;
}
/**
 * Fornisce il {@link Punto} che al suo interno ha Latitudine e Longitudine.
 * @return Punto o <strong>0</strong> se il Punto inserito non è corretto.
 */
public Punto getPunto() {
	return Punto;
}
/**
 * Imposta il {@link Punto} che al suo interno ha Latitudine e Longitudine
 * @param punto Punto delle coordinate
 */
public void setPunto(Punto punto) {
	Punto = punto;
}

}