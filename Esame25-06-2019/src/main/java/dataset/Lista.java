package dataset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Classe Lista che fa il parsing di tutti i dati
 * @author Matteo Ferretti (s1083630@studenti.univpm.it), Angelo D'Agostino Bonomi (s1082444@studenti.univpm.it)
 * @version 1.0
 */
public class Lista {
	/**
	 * Inizializza una lista di dati come ArrayList.
	 */
private List<Dati> lista = new ArrayList<>();

/**
 * Questa è la classe {@link Lista} che prendendo in Input il CSV scaricato
 * in {@link progetto.DownloadDataset} fa il Parsing di tutti i dati e 
 * li mette dentro un ArrayList che ha come costruttore {@link Dati}.
 */
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
    		String[] spazio = line.split(cvsSplitBy);
    		if(spazio.length>=10) {//se il vettore è più lungo del numero dei campi non inizializza
    			String[] LatLong;
    			if(spazio[8].contains(",")) {
    				surplus = spazio[8].indexOf(",");
    				spazio[8] = (spazio[8].substring(0, surplus) +"."+ spazio[8].substring(surplus + 1));
    				latitudine = Float.parseFloat(spazio[8]);
    			} 
    			else{ latitudine=(float) (Float.parseFloat(spazio[8])/Math.pow(10, spazio[8].length() -2));}

    			if(spazio[9].contains(",")) {
    				LatLong = spazio[9].split(LatLongSplit);
    				longitudine = (float) (Float.parseFloat(LatLong[0])+(Float.parseFloat(LatLong[1])/Math.pow(10, LatLong[1].length())));
    			}
    			else if (spazio[9].contains(".")){
    				surplus = spazio[9].indexOf(".");
    				spazio[9] = (spazio[9].substring(0, surplus) +"."+ spazio[9].substring(surplus + 1));
    				longitudine = Float.parseFloat(spazio[9]);
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

    			if (spazio[9].isEmpty())	{ spazio[9]="0";}	//se la Latitudine è vuota ci carica dentro 0
    			if (spazio[8].isEmpty())	{ spazio[8]="0";}	//se la Longitudine è vuota ci carica dentro 0
    			if (spazio[7].isEmpty())	{ spazio[7]=" ";}	//se il tipoEnte è vuoto ci carica dentro 0
    			if (spazio[4].isEmpty())	{ spazio[4]=" ";}	//se l'Indirizzo è vuoto ci carica dentro 0
    			if (spazio[3].isEmpty())	{ spazio[3]=" ";}	//se la Provincia è vuota ci carica dentro 0
    			if (spazio[2].isEmpty())	{ spazio[2]=" ";}	//se il Comune è vuoto ci carica dentro 0
    			if (spazio[1].isEmpty())	{ spazio[1]=" ";}	//se il PreNom è vuoto ci carica dentro 0
    			lista.add(new Dati (Integer.parseInt(spazio[0]),spazio[1],spazio[2],spazio[3],spazio[4],Long.parseLong(spazio[5]),Long.parseLong(spazio[6]),spazio[7],latitudine,longitudine));
    		
    		}
    	}
    }catch (IOException e) { e.printStackTrace(); }
}
	/**
	 * Inizializza la variabile Int size.
	 */
	public int size;
	/**
	 * Fornisce il numero di righe della lista.
	 * @return size
	 */
		public int getSize() {
		return size=lista.size();
		}
	/**
	 * Inizializza la lista
	 * @return lista
	 */
	public List<Dati> getList(){
		return lista;
	}
	//
	/**
	 * Ottiene l'oggetto "Dato" all'indice "i" selezionato.
	 * @param i Numero della riga.
	 * @return this.lista.get(i)
	 */
	public Dati getDati(int i) {
		return this.lista.get(i);
	}
	/**
	 * Controlla se la lista è vuota.
	 * @return true se è vuota, false se non lo è
	 */
	public boolean isEmpty() {
		if(lista.isEmpty()) return true;
		else return false;
	}

	
	
}
