package Progetto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Lista extends Container {
//private List<Dati> lista = new ArrayList<>();

//costruttore che inizializza la lista
public Lista() {
	String line = "";
    String cvsSplitBy = ";";
    try (BufferedReader br = new BufferedReader(new FileReader("APL-AgenziaPerIlLavoro.csv"))) {
       line = br.readLine(); //serve per saltare la prima riga del dataset che contiene i nomi dei campi
    	while ((line = br.readLine()) != null) {
            String[] spazio = line.split(cvsSplitBy);
            lista.add(new Dati (Integer.parseInt(spazio[0]),spazio[1],spazio[2],spazio[3],spazio[4],Long.parseLong(spazio[5]),Long.parseLong(spazio[6]),spazio[7],Float.parseFloat(spazio[8]),Float.parseFloat(spazio[9])));
            //inizializza la lista
            } } catch (IOException e) {
	            e.printStackTrace();
	            }
 
    }
	public List<Dati> getList(){
		return lista;
	}
	//ottiene l'oggetto "dato" all'indice "i" selezionato
	public Dati getDati(int i) {
		return this.lista.get(i);
	}
	
}
