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
        while ((line = br.readLine()) != null) {
            String[] spazio = line.split(cvsSplitBy);
            lista.add(new Dati ());
            
            } } catch (IOException e) {
	            e.printStackTrace();
	            }
 
    }
}
}
