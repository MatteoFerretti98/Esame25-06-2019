package Progetto;

//import java.util.List;

public class Statistics {
	
	private int Count;
	private float Media;
	private float DevStand;
	private float Max;
	private float Min;
	
	public Statistics(int count, float media, float devStand, float max, float min) {
		super();
		this.Count=count;
		this.Media=media;
		this.DevStand=devStand;
		this.Max=max;
		this.Min=min;
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
	public float getMax() {
		return Max;
	}
	public void setMax(float max) {
		Max = max;
	}
	public float getMin() {
		return Min;
	}
	public void setMin(float min) {
		Min = min;
	}
}
