package Progetto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
@SuppressWarnings({ "resource"  })
public class Lista extends Container {

public List<Dati> lista = new ArrayList<>();
private int size;


public Lista() {
	String line = "";
    String cvsSplitBy = ";";
    int surplus = 0;
    String LatLongSplit =","; //la virgola è il separatore di latitudine e lognitudine
    float latitudine=0;
    float longitudine =0 ;
    try {
    	BufferedReader br = new BufferedReader(new FileReader("APL-AgenziaPerIlLavoro.csv"));
    	line = br.readLine(); //serve per saltare la prima riga del dataset che contiene i nomi dei campi
    	while ((line = br.readLine()) != null) {

    		//System.out.println(line);
    		String[] spazio = line.split(cvsSplitBy);
    		if(spazio.length>=10) {//se il vettore è più lungo del numero dei campi non inizializza
    			String[] LatLong;
    			if(spazio[8].contains(",")) {
    				LatLong = spazio[8].split(LatLongSplit);//mette in un vettore la parte intera e la mantissa della latidudine
    				latitudine = (float) (Float.parseFloat(LatLong[0])+(Float.parseFloat(LatLong[1])/Math.pow(10, LatLong[1].length())));//converte il vettore che contiene la latitudine in un float
    			} 
    			else{ latitudine=(float) (Float.parseFloat(spazio[8])/Math.pow(10, spazio[8].length() -2));}

    			if(spazio[9].contains(",")) {
    				LatLong = spazio[9].split(LatLongSplit);
    				longitudine = (float) (Float.parseFloat(LatLong[0])+(Float.parseFloat(LatLong[1])/Math.pow(10, LatLong[1].length())));
    			}
    			else{ longitudine=(float) (Float.parseFloat(spazio[9])/Math.pow(10, spazio[9].length() -2));}

    			if (spazio[5].isEmpty())	{ spazio[5]="0";}	//se il numero di telefono è vuoto ci carica dentro 0 altrimenti da errore
    			if ((spazio[5].contains(" "))||(spazio[5].contains("/"))||(spazio[5].contains("."))) { //serve per non eseguire sempre i 3 if seguenti
    				if(spazio[5].contains(".")) surplus = spazio[5].indexOf(".");	//se la stringa contiene il "." il valore in eccesso è quello
    				if(spazio[5].contains("/")) surplus = spazio[5].indexOf("/");	//se la stringa contiene il "/" il valore in eccesso è quello
    				if(spazio[5].contains(" ")) surplus = spazio[5].indexOf(" ");	//se la stringa contiene il " " il valore in eccesso è quello
    				spazio[5] = (spazio[5].substring(0, surplus) + spazio[5].substring(surplus + 1));
    			}
           
    			if (spazio[6].isEmpty())	{ spazio[6]="0";} //se il numero del fax è vuoto ci carica dentro 0 altrimenti da errore
    			if ((spazio[6].contains(" "))||(spazio[6].contains("/"))||(spazio[6].contains("."))) { //serve per non eseguire sempre i 3 if seguenti
    				if(spazio[6].contains(".")) surplus = spazio[6].indexOf(".");	//se la stringa contiene il "." il valore in eccesso è quello
    				if(spazio[6].contains("/")) surplus = spazio[6].indexOf("/");	//se la stringa contiene il "/" il valore in eccesso è quello
    				if(spazio[6].contains(" ")) surplus = spazio[6].indexOf(" ");	//se la stringa contiene il " " il valore in eccesso è quello
    				spazio[6] = (spazio[6].substring(0, surplus) + spazio[6].substring(surplus + 1));
    			}
    			System.out.println(line);
    			lista.add(new Dati (Integer.parseInt(spazio[0]),spazio[1],spazio[2],spazio[3],spazio[4],Long.parseLong(spazio[5]),Long.parseLong(spazio[6]),spazio[7],latitudine,longitudine));
    		}
    	}
    }catch (IOException e) { e.printStackTrace(); }
}
 
     
	public int getSize() {
		return size;
	}
	
	//inizializza la lista
	public List<Dati> getList(){
		return lista;
	}
	//ottiene l'oggetto "dato" all'indice "i" selezionato
	public Dati getDati(int i) {
		return this.lista.get(i);
	}
	public boolean isEmpty() {
		if(lista.isEmpty()) return true;
		else return false;
	}
	
}
