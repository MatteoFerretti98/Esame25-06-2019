package dataset;

/**
 * {@link Dati} : Questa è la classe del costruttore principale e collegata ad essa abbiamo 
 * {@link Punto} che è la classe costruttore di latitudine e longitudine
 * @author Matteo Ferretti (s1083630@studenti.univpm.it), Angelo D'Agostino Bonomi (s1082444@studenti.univpm.it)
 * @version 1.0
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
 * @param punto Questo è il costruttore per la latitudine e la longitudine in {@link Punto} 
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
public int getFid() {
	return Fid;
}
public void setFid(int fid) {
	Fid = fid;
}
public String getPreNorm() {
	return PreNorm;
}
public void setPreNorm(String preNorm) {
	PreNorm = preNorm;
}
public String getComune() {
	return Comune;
}
public void setComune(String comune) {
	Comune = comune;
}
public String getProvincia() {
	return Provincia;
}
public void setProvincia(String provincia) {
	Provincia = provincia;
}
public String getIndirizzo() {
	return Indirizzo;
}
public void setIndirizzo(String indirizzo) {
	Indirizzo = indirizzo;
}
public long getTelefono() {
	return Telefono;
}
public void setTelefono(long telefono) {
	Telefono = telefono;
}
public long getFax() {
	return Fax;
}
public void setFax(long fax) {
	Fax = fax;
}
public String getTipo() {
	return Tipo;
}
public void setTipo(String tipo) {
	Tipo = tipo;
}
public Punto getPunto() {
	return Punto;
}
public void setPunto(Punto punto) {
	Punto = punto;
}

}