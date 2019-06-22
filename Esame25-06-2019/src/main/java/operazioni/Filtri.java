package operazioni;
import java.io.IOException;
/**
 * @author Matteo Ferretti (s1083630@studenti.univpm.it), Angelo D'Agostino Bonomi (s1082444@studenti.univpm.it)
 * @version 1.0
 */
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.json.BasicJsonParser;

import dataset.Container;
import dataset.Lista;
/**
 * Filtra la {@link dataset.Lista} in base al tipo di filtro che si sceglie
 */
public class Filtri {
	/**
	 * Questo è il costruttore di Filtri
	 */
	public Filtri() {
		
	}
	/**
	 * Inizializza lista come {@link dataset.Lista} .
	 */
	private Lista lista = new Lista();
	/**
	 * Questo metodo ci aiuta a parsare la body della POST in modo da avere tutti i dati in una lista.
	 * @param body Body della richiesta Post in base al filtro scelto
	 * @return CAMPI Fa ritornare la lista dei dati contenuti nel Body
	 * @throws IOException Classe base per le eccezioni di flussi di dati
	 */
	//da rpivate è passato pubblico vediamo se va bene
	public static Map<String, Object> parseFilter(String body) throws IOException{
        Map<String, Object> parsedBody = new BasicJsonParser().parseMap(body);
        Map<String, Object> CAMPI = new HashMap<>(); //Crea l'Hashmap per il return
        String tipo = parsedBody.keySet().toArray(new String[0])[0];
        Object PrimoValore = parsedBody.get(tipo);
        Object Valore1 = null;
        Object Valore2 = null;
        String campo = null;
        Object val1 = null;
        Object val2 = null;
        Object val3 = null;
        Object val4 = null;
        Map filtro = null;
        if (PrimoValore instanceof Map) { //Qui controlla se quello che ha preso è anche'essa una mappa a sua volta
            filtro = (Map) PrimoValore; //Qui fa diventare ciò che c'è dentro a rawValue una mappa
            if(filtro.size()<=1) { ////Questo serve per i filtri logici
            campo = ((String) filtro.keySet().toArray()[0]).toLowerCase(); //Qui prende il tipo di filtro
            Valore1 = filtro.get(campo); //Qui mette dentro a val ciò che c'è dentro a val che sia uno o più numeri
            }
            else{ 
            	campo = ((String) filtro.keySet().toArray()[0]).toLowerCase(); //Qui prende il tipo di filtro
            	String campo2=((String) filtro.keySet().toArray()[1]).toLowerCase();
            	Valore1 = filtro.get(campo); //Qui mette dentro a val ciò che c'è dentro a val che sia uno o più numeri
            	Valore2 =filtro.get(campo2);
            }
        } 
        else return CAMPI; //Se c'è qualcosa che non va ritorna l'hash map vuota
        if ((Valore1 instanceof ArrayList)&&filtro.size()>1){
        	val1 = ((ArrayList) Valore1).get(0);
        	val2 = ((ArrayList) Valore1).get(1);
        	val3 = ((ArrayList) Valore2).get(0);
        	val4 = ((ArrayList) Valore2).get(1);
        	CAMPI.put("tipo", tipo);
        	CAMPI.put("campo", campo);
        	CAMPI.put("val1", String.valueOf(val1));
        	CAMPI.put("val2", String.valueOf(val2));
        	CAMPI.put("val3", String.valueOf(val3));
        	CAMPI.put("val4", String.valueOf(val4));
        	return CAMPI;
        }
        else if (Valore1 instanceof ArrayList){
        	val1 = ((ArrayList) Valore1).get(0);
        	val2 = ((ArrayList) Valore1).get(1);
        }
        
        CAMPI.put("tipo", tipo); //E mette dentro la map tutti i valori 
        CAMPI.put("campo", campo); //Assegnandogli a ciascuno un nome
        if(Valore1 instanceof ArrayList) {
        	CAMPI.put("val1", String.valueOf(val1));
        	CAMPI.put("val2", String.valueOf(val2));
        }
        else  CAMPI.put("val", String.valueOf(Valore1));
        return CAMPI;
    }
	
/**
 * Filtro condizionale che filtra tutti gli elementi compresi tra 2 valori impostati dall'utente.
 * @param tipo Dichiara il tipo di filtro scelto 
 * @param campo E' il tipo dell'attributo
 * @param min E' il valore minimo
 * @param max E' il valore massimo
 * @param originale E' la {@link dataset.Lista} non ancora filtrata
 * @param filtrata E' la {@link dataset.Lista} che dovrà essere filtrata
 * @return filtrata E' la {@link dataset.Lista} filtrata
 * @throws NoSuchMethodException Se il metodo che vado a cercare non esiste
 * @throws SecurityException Se c'è stata una violazione nella sicurezza
 * @throws IllegalAccessException Se si tenta di accedere ad un metodo la cui visibilità non è consentita
 * @throws IllegalArgumentException Se il metodo ha passato un argomento non appropriato
 * @throws InvocationTargetException Controlla le eccezioni che sono chiamate da un invoke method
 */
	public Lista filterBT(String tipo, String campo,String min,String max, Container originale,Lista filtrata) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		try {
		
		int size=originale.getSize(); //Prende la taglia della lista
		Object v=null; //inizializzo v
		if (tipo.equals("$bt")) {//controlla il tipo di filtro richiesto
			for(int i=0; i<max.length();i++) {
				if(Character.isLetter(max.charAt(i))) return filtrata;	//Serve per vedere se si immettono dei valori non appropriati
				}
			for(int i=0; i<min.length();i++) {
				if(Character.isLetter(min.charAt(i))) return filtrata;	//Serve per vedere se si immettono dei valori non appropriati
				}
				filtrata.getList().clear(); //Pulisce la lista 
				float MIN = Float.parseFloat(min); //Trasformo min in float
				float MAX = Float.parseFloat(max); //Trasformo max in float
		    	for(int a=0; a<size; a++)
		    	{
		    	
		    		if((campo.equals("latitudine"))||(campo.equals("longitudine"))) { //Controllo che serve perchè lat e long stanno in punto e non in dati come tutti gli altri
		    			//Con questa funzione in base al nome tra longitudine e latitudine richiama il get consono
		    			//Ho dovuto instaziare una nuova lista tipo lista per raggiungere il metodo altrimenti avrebbe dato errore perchè non è istanzata all'interno di questa classe
		    			Method u = lista.getDati(a).getPunto().getClass().getMethod("get"+campo.substring(0, 1).toUpperCase()+campo.substring(1), null);
		    			//grazie invece all'invoke riprendo il valore appunto del metodo richiamato precendentemente
		    			v = u.invoke(originale.getDati(a).getPunto(), null);
		    		}
		    		else if((campo.equals("telefono"))||(campo.equals("fax"))) {
		    			Method u= lista.getDati(a).getClass().getMethod("get"+campo.substring(0, 1).toUpperCase()+campo.substring(1), null);
		    			v = u.invoke(originale.getDati(a), null);
		    		}
		    		
		    			float V = ((Number)v).floatValue();	//Converto in float
		    			if((V >= MIN) && (V <= MAX))	filtrata.getList().add(originale.getDati(a)); //se il valore è tra max e min lo mette dentro filtrata
		    		}
		    	} 	
		}catch(NoSuchMethodError a) {a.printStackTrace();
		}catch (SecurityException b) {b.printStackTrace();
		}catch(IllegalAccessException c) {c.printStackTrace();
		}catch(IllegalArgumentException d) {d.printStackTrace();
		}catch(InvocationTargetException e) {e.printStackTrace();
		}
		return filtrata;
	}
	/**
	 * Filtro condizionale che filtra tutti gli elementi maggiori o minori del valore impostato dall'utente.
	 * @param tipo Dichiara il tipo di filtro scelto 
	 * @param campo Dichiara il tipo di attributo scelto
	 * @param val Dichiara il valore di soglia
	 * @param originale E' la {@link dataset.Lista} non ancora filtrata
	 * @param filtrata E' la {@link dataset.Lista} che dovrà essere filtrata
	 * @return filtrata E' la {@link dataset.Lista} filtrata
	 * @throws NoSuchMethodException Se il metodo che vado a cercare non esiste
	 * @throws SecurityException Se c'è stata una violazione nella sicurezza
	 * @throws IllegalAccessException Se si tenta di accedere ad un metodo la cui visibilità non è consentita
	 * @throws IllegalArgumentException Se il metodo ha passato un argomento non appropriato
	 * @throws InvocationTargetException Controlla le eccezioni che sono chiamate da un invoke method
	 */
	public Lista filterGLTE(String tipo,String campo,String val, Container originale,Lista filtrata) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		try {
		
		int size=originale.getSize();
		Object v=null;
		for(int i=0; i<val.length();i++) {
			if((Character.isLetter(val.charAt(i)))) return filtrata; //Serve per vedere se si immettono dei valori non appropriati
			}		
		if (tipo.equals("$gte")) {//controlla il tipo di filtro richiesto	
			filtrata.getList().clear();
			float VAL = Float.parseFloat(val);
		    	for(int a=0; a<size; a++)
		    	{
		    		if((campo.equals("latitudine"))||(campo.equals("longitudine"))) {
		    			Method u = lista.getDati(a).getPunto().getClass().getMethod("get"+campo.substring(0, 1).toUpperCase()+campo.substring(1), null);
		    			v = u.invoke(originale.getDati(a).getPunto(), null);
		    		}
		    		else if((campo.equals("telefono"))||(campo.equals("fax"))) {
		    			Method u= lista.getDati(a).getClass().getMethod("get"+campo.substring(0, 1).toUpperCase()+campo.substring(1), null);
		    			v = u.invoke(originale.getDati(a), null);
		    		}
		    		float V = ((Number)v).floatValue();	//Converto in float
		    		if(V >= VAL)	filtrata.getList().add(originale.getDati(a)); //se il valore è minore lo mette dentro filtrata
		    	} 	
		}
		else if (tipo.equals("$lte")) {//controlla il tipo di filtro richiesto
			filtrata.getList().clear();
			float VAL = Float.parseFloat(val);
	    	for(int a=0; a<size; a++)
	    	{
	    		if((campo.equals("latitudine"))||(campo.equals("longitudine"))) {
	    			Method u = lista.getDati(a).getPunto().getClass().getMethod("get"+campo.substring(0, 1).toUpperCase()+campo.substring(1), null);
	    			v = u.invoke(originale.getDati(a).getPunto(), null);
	    		}
	    		else if((campo.equals("telefono"))||(campo.equals("fax"))) {
	    			Method u= lista.getDati(a).getClass().getMethod("get"+campo.substring(0, 1).toUpperCase()+campo.substring(1), null);
	    			v = u.invoke(originale.getDati(a), null);
	    		}
	    		float V = ((Number)v).floatValue();	//Converto in float
	    		if(V <= VAL)	filtrata.getList().add(originale.getDati(a));  //se il valore è maggiore lo mette dentro filtrata
	    	} 	
		}
		}catch(NoSuchMethodError a) {a.printStackTrace();
		}catch (SecurityException b) {b.printStackTrace();
		}catch(IllegalAccessException c) {c.printStackTrace();
		}catch(IllegalArgumentException d) {d.printStackTrace();
		}catch(InvocationTargetException e) {e.printStackTrace();
		}
	return filtrata;
}
	/**
	 * Filtro Logico "And" che filtra tutti gli elementi che hanno 2 campi specificati dall'utente in comune.
	 * @param tipo1 Tipo del primo attributo
	 * @param campo1 Valore del primo campo
	 * @param tipo2 Tipo del secondo attributo
	 * @param campo2 Valore del secondo campo
	 * @param originale E' la {@link dataset.Lista} non ancora filtrata
	 * @param filtrata E' la {@link dataset.Lista} che dovrà essere filtrata
	 * @return filtrata E' la {@link dataset.Lista} filtrata
	 * @throws NoSuchMethodException Se il metodo che vado a cercare non esiste
	 * @throws SecurityException Se c'è stata una violazione nella sicurezza
	 * @throws IllegalAccessException Se si tenta di accedere ad un metodo la cui visibilità non è consentita
	 * @throws IllegalArgumentException Se il metodo ha passato un argomento non appropriato
	 * @throws InvocationTargetException Controlla le eccezioni che sono chiamate da un invoke method
	 */
	public Lista filterAND(String tipo1,String campo1,String tipo2, String campo2, Container originale,Lista filtrata) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		try {
		filtrata.getList().clear();
		int size=originale.getSize();
		Object v1=null;
		Object v2=null;
		for(int a=0; a<size; a++)
    	{
			if((tipo1.equals("latitudine"))||(tipo1.equals("longitudine"))) {
				Method u1= lista.getDati(a).getPunto().getClass().getMethod("get"+tipo1.substring(0, 1).toUpperCase()+tipo1.substring(1), null);
				v1 = u1.invoke(originale.getDati(a).getPunto(), null);
			}
			else {
				Method u1 = lista.getDati(a).getClass().getMethod("get"+tipo1.substring(0, 1).toUpperCase()+tipo1.substring(1), null);
				v1 = u1.invoke(originale.getDati(a), null); 
			}
			if((tipo2.equals("latitudine"))||(tipo2.equals("longitudine"))) {
				Method u2= lista.getDati(a).getPunto().getClass().getMethod("get"+tipo2.substring(0, 1).toUpperCase()+tipo2.substring(1), null);
				v2 = u2.invoke(originale.getDati(a).getPunto(), null);
			}
			else {
				Method u2 = lista.getDati(a).getClass().getMethod("get"+tipo2.substring(0, 1).toUpperCase()+tipo2.substring(1), null);
				v2 = u2.invoke(originale.getDati(a), null); 
			}
    		if((campo1.equals(v1))&&(campo2.equals(v2)))	filtrata.getList().add(originale.getDati(a)); //Se entrambi i campi sono presenti nella riga, mette la riga in filtrata
    	}
		}catch(NoSuchMethodError a) {a.printStackTrace();
		}catch (SecurityException b) {b.printStackTrace();
		}catch(IllegalAccessException c) {c.printStackTrace();
		}catch(IllegalArgumentException d) {d.printStackTrace();
		}catch(InvocationTargetException e) {e.printStackTrace();
		}
		return filtrata;
	}
	/**
	 * Filtro Logico "Or" che filtra tutti gli elementi che hanno uno dei 2 campi specificati dall'utente.
	 * @param tipo1 Tipo del primo attributo
	 * @param campo1 Valore del primo campo
	 * @param tipo2 Tipo del secondo attributo
	 * @param campo2 Valore del secondo campo
	 * @param originale E' la {@link dataset.Lista} non ancora filtrata
	 * @param filtrata E' la {@link dataset.Lista} che dovrà essere filtrata
	 * @return filtrata E' la {@link dataset.Lista} filtrata
	 * @throws NoSuchMethodException Se il metodo che vado a cercare non esiste
	 * @throws SecurityException Se c'è stata una violazione nella sicurezza
	 * @throws IllegalAccessException Se si tenta di accedere ad un metodo la cui visibilità non è consentita
	 * @throws IllegalArgumentException Se il metodo ha passato un argomento non appropriato
	 * @throws InvocationTargetException Controlla le eccezioni che sono chiamate da un invoke method
	 */
	public Lista filterOR(String tipo1,String campo1,String tipo2, String campo2, Container originale,Lista filtrata) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		try {
		filtrata.getList().clear();
		int size=originale.getSize();
		Object v1=null;
		Object v2=null;
		for(int a=0; a<size; a++)
    	{
			if((tipo1.equals("latitudine"))||(tipo1.equals("longitudine"))) {
				Method u1= lista.getDati(a).getPunto().getClass().getMethod("get"+tipo1.substring(0, 1).toUpperCase()+tipo1.substring(1), null);
				v1 = u1.invoke(originale.getDati(a).getPunto(), null);	
			}
			else {
				Method u1 = lista.getDati(a).getClass().getMethod("get"+tipo1.substring(0, 1).toUpperCase()+tipo1.substring(1), null);
				v1 = u1.invoke(originale.getDati(a), null); 
			}
			if((tipo2.equals("latitudine"))||(tipo2.equals("longitudine"))) {
				Method u2= lista.getDati(a).getPunto().getClass().getMethod("get"+tipo2.substring(0, 1).toUpperCase()+tipo2.substring(1), null);
				v2 = u2.invoke(originale.getDati(a).getPunto(), null);
			}
			else {
				Method u2 = lista.getDati(a).getClass().getMethod("get"+tipo2.substring(0, 1).toUpperCase()+tipo2.substring(1), null);
				v2 = u2.invoke(originale.getDati(a), null); 
			}
    		if((campo1.equals(v1))||(campo2.equals(v2)))	filtrata.getList().add(originale.getDati(a));  //Se uno dei due campi è presente nella riga, mette la riga in filtrata
    	}
		}catch(NoSuchMethodError a) {a.printStackTrace();
		}catch (SecurityException b) {b.printStackTrace();
		}catch(IllegalAccessException c) {c.printStackTrace();
		}catch(IllegalArgumentException d) {d.printStackTrace();
		}catch(InvocationTargetException e) {e.printStackTrace();
		}
		return filtrata;
	}
}
