package Progetto;
@SuppressWarnings("unused")
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
private float lat;
private float lon;


//costruttore della classe
public Dati(int fid, String preNorm, String comune, String provincia, String indirizzo, long telefono, long fax,
		String tipo,float latitudine, float longitudine) {
	super();
	Fid = fid;
	PreNorm = preNorm;
	Comune = comune;
	Provincia = provincia;
	Indirizzo = indirizzo;
	Telefono = telefono;
	Fax = fax;
	Tipo = tipo;
   // Punto.setLatitudine(latitudine);
   // Punto.setLongitudine(longitudine);
	lat=latitudine;
	lon=longitudine;
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
