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
    String LatLongSplit =","; //la virgola è il separatore di latitudine e lognitudine
    String FaxSplit= " ";
    float latitudine=0;
    float longitudine =0 ;
    try (BufferedReader br = new BufferedReader(new FileReader("APL-AgenziaPerIlLavoro.csv"))) {
       line = br.readLine(); //serve per saltare la prima riga del dataset che contiene i nomi dei campi
    	while ((line = br.readLine()) != null) {
    		//System.out.println(line);
    		
            String[] spazio = line.split(cvsSplitBy);
            if(spazio.length==10) {//se il vettore è più lungo del numero dei campi non inizializza
            String[] LatLong;
           	if(spazio[8].contains(",")) {
            LatLong = spazio[8].split(LatLongSplit);//mette in un vettore la parte intera e la mantissa della latidudine
             latitudine = (float) (Float.parseFloat(LatLong[0])+(Float.parseFloat(LatLong[1])/Math.pow(10, LatLong[1].length())));//converte il vettore che contiene la latitudine in un float
           	} 
           	else
           	{ latitudine=(float) (Float.parseFloat(spazio[8])/Math.pow(10, spazio[8].length() -2));}
           	if(spazio[9].contains(",")) {
           	LatLong = spazio[9].split(LatLongSplit);
            longitudine = (float) (Float.parseFloat(LatLong[0])+(Float.parseFloat(LatLong[1])/Math.pow(10, LatLong[1].length())));
           	}else
           	{ longitudine=(float) (Float.parseFloat(spazio[9])/Math.pow(10, spazio[9].length() -2));}
           	
          

            if (spazio[5].isEmpty())	{ spazio[5]="0";}
            if (spazio[5].contains(" ")||spazio[5].contains("/")||spazio[6].contains(".")) //corregge eventuali spazi nel numero 
            {
            	if(spazio[6].contains(".")) {FaxSplit=".";}
            	if(spazio[5].contains("/")) {FaxSplit="/";}
            	String[] telefono= spazio[5].split(FaxSplit); 
            	if (telefono.length==1) {		
            	 spazio[5]=telefono[0];		    //Serve per controllare se telefono 
            									//è stato splittato in 2 campi pieni o se
            	}								//uno dei 2 è vuoto
            	else if( telefono.length==2)
            	{
            		spazio[5]=telefono[0].concat(telefono[1]);
            	}
            	else {
            		spazio[5]=telefono[0].concat(telefono[1]).concat(telefono[2]);
            	}
            }
            if (spazio[6].isEmpty())	{ spazio[6]="0";}
            if (spazio[6].contains(" ")||spazio[6].contains("/")||spazio[6].contains(".")) //corregge eventuali spazi nel numero 
            {
            	if(spazio[6].contains(".")) {FaxSplit=".";}
            	if(spazio[6].contains("/")) {FaxSplit="/";}
            	if(spazio[6].contains(" ")) {FaxSplit=" ";}
            	String[] fax= spazio[6].split(FaxSplit);
            	
            	if (fax.length==1) {		

            		spazio[6]=fax[0];		//Serve per controllare se telefono 
            									//è stato splittato in 2 campi pieni o se uno dei 2 è vuoto
            	}			
            	else if (fax.length==2){
            		spazio[6]=fax[0].concat(fax[1]);
            	}	
            	else {spazio[6]=fax[0].concat(fax[1]).concat(fax[2]);
            							
            

            }
            System.out.println(line);
            lista.add(new Dati (Integer.parseInt(spazio[0]),spazio[1],spazio[2],spazio[3],spazio[4],Long.parseLong(spazio[5]),Long.parseLong(spazio[6]),spazio[7],latitudine,longitudine));
            //inizializza la lista
            } 
            }
            }
    	}catch (IOException e) {
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
