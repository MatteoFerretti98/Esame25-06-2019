package Progetto;

//import java.util.List;

public class Statistics {
	
	private int Count;
	private float Media;
	private float DevStand;
	private double Somma;
	private double Max;
	private double Min;
	private String Field;
	
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
	
	/*public Lista getLista() {
		return lista;
	}
	public void setLista(Lista lista) {
		this.lista = lista;
	}*/
	public int getCount() {
		return Count;
	}
	public void setCount(int count) {
		Count = count;
	}
	public double getSomma() {
		return Somma;
	}
	public void setSomma(double somma) {
		Somma = somma;
	}
	public float getMedia() {
		return Media;
	}
	public void setMedia(float media) {
		Media = media;
	}
	public float getDevStand() {
		return DevStand;
	}
	public void setDevStand(float devStand) {
		DevStand = devStand;
	}
	public double getMax() {
		return Max;
	}
	public void setMax(double max) {
		Max = max;
	}
	public double getMin() {
		return Min;
	}
	public void setMin(double min) {
		Min = min;
	}
	public String getField() {
		return Field;
	}
	public void setField(String field) {
		Field = field;
	}
}
