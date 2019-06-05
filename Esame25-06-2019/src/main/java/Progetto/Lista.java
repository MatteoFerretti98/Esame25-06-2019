package Progetto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Lista {
private List<Dati> lista = new ArrayList<>();

//costruttore che inizializza la lista
public Lista() {
	String line = "";
    String cvsSplitBy = ",";
    try (BufferedReader br = new BufferedReader(new FileReader("APL-AgenziaPerIlLavoro.csv"))) {
       line = br.readLine();
    	while ((line = br.readLine()) != null) {
            String[] spazio = line.split(cvsSplitBy);
            lista.add(new Dati (Integer.parseInt(spazio[0]),spazio[1],spazio[2],spazio[3],spazio[4],Long.parseLong(spazio[5]),Long.parseLong(spazio[6]),spazio[7],Float.parseFloat(spazio[8]),Float.parseFloat(spazio[9])));
            
            } } catch (IOException e) {
	            e.printStackTrace();
	            }
 
    }
}

