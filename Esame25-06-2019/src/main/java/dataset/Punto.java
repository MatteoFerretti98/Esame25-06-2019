package dataset;

//mette insieme la latitudine e la longitudine nel dataset
public class Punto {

private float Latitudine;
private float Longitudine;

	public Punto() {
		this.Latitudine=0;
		this.Longitudine=0;
	}
	public Punto(float latitudine, float longitudine) {
		this.Latitudine=latitudine;
		this.Longitudine=longitudine;
	}
	//getters e setters
	public float getLatitudine() {
		return Latitudine;
	}
	public void setLatitudine(float latitudine) {
		this.Latitudine= latitudine;
	}
	public float getLongitudine() {
		return Longitudine;
	}
	public void setLongitudine(float longitudine) {
		this.Longitudine = longitudine;
	}
	
}