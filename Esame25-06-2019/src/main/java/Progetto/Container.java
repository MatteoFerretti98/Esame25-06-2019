package Progetto;

import java.util.ArrayList;
import java.util.List;

public class Container {
	protected List<Dati> lista = new ArrayList<>();
		
	//queste funzioni effettuano un controllo sulla presenza di un campo nella lista  
	public boolean containsFid(int e,int i) {
		if (e==lista.get(i).getFid()) return true; 
		else return false;
		}
	public boolean containsPreNom(String e,int i) {
		if (e.contains(lista.get(i).getPreNorm())) return true; 
		else return false;
		}
	public boolean containsComune(String e,int i) {
		if (e.contains(lista.get(i).getComune())) return true; 
		else return false;
		}
	
	public boolean containsIndirizzo(String e,int i) {
		if (e.contains(lista.get(i).getIndirizzo())) return true; 
		else return false;
		}
	public boolean containsTelefono(long e,int i) {
		if (e==lista.get(i).getTelefono()) return true; 
		else return false;
		}
	public boolean containsFax(long e,int i) {
		if (e==lista.get(i).getFax()) return true; 
		else return false;
		}
	public boolean containsTipo(String e,int i) {
		if (e.contains(lista.get(i).getTipo())) return true; 
		else return false;
		}
	public boolean containsPunto(Punto e,int i) {
		if ((e.getLatitudine()==lista.get(i).getPunto().getLatitudine())&&(e.getLongitudine()==lista.get(i).getPunto().getLongitudine())) return true; 
		else return false;
		}
	
	}
	

