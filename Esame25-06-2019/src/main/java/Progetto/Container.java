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
	public void correggiErrori(String correzione) {
		String Split=""; 
		if (correzione.isEmpty())	{ correzione="0";}
        if (correzione.contains(" ")||correzione.contains("/")||correzione.contains(".")) //corregge eventuali spazi nel numero 
        {
        	if(correzione.contains(".")) {Split=".";}
        	if(correzione.contains("/")) {Split="/";}
        	if(correzione.contains(" ")) {Split=" ";}
        	String[] fax= correzione.split(Split);
        	
        	if (fax.length==1) {		

        		correzione=fax[0];		//Serve per controllare se telefono 
        									//è stato splittato in 2 campi pieni o se uno dei 2 è vuoto
        	}			
        	else if (fax.length==2){
        		correzione=fax[0].concat(fax[1]);
        	}	
        	else {correzione=fax[0].concat(fax[1]).concat(fax[2]);

        }
        }
	}
	}
	

