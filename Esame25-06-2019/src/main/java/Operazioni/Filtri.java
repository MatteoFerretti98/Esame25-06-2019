package Operazioni;
/**
 * @author Matteo Ferretti (s1083630@studenti.univpm.it), Angelo D'Agostino Bonomi (s1082444@studenti.univpm.it)
 * @version 1.0
 */
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import dataset.Container;
import dataset.Lista;
/**
 * Filtra la {@link dataset.Lista} in base al tipo di filtro che si sceglie
 */
public class Filtri {
	private String tipo;
	private String campo;
	private String min;
	private String max;
	/**
	 * Inizializza lista come {@link dataset.Lista} .
	 */
	private Lista lista = new Lista();

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
		filtrata.getList().clear(); //Pulisce la lista 
		int size=originale.getSize(); //Prende la taglia della lista
		Object v=null; //inizializzo v
		if (tipo.equals("$bt")) {//controlla il tipo di filtro richiesto
			for(int i=0; i<max.length();i++) {
				if(Character.isLetter(max.charAt(i))) return filtrata;	//Serve per vedere se si immettono dei valori non appropriati
				}
			for(int i=0; i<min.length();i++) {
				if(Character.isLetter(min.charAt(i))) return filtrata;	//Serve per vedere se si immettono dei valori non appropriati
				}
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
		filtrata.getList().clear();
		int size=originale.getSize();
		Object v=null;
		for(int i=0; i<val.length();i++) {
			if((Character.isLetter(val.charAt(i)))) return filtrata; //Serve per vedere se si immettono dei valori non appropriati
			}		
		if (tipo.equals("$gte")) {//controlla il tipo di filtro richiesto	
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
