package Operazioni;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import dataset.Container;
import dataset.Lista;

public class Filtri {
	private String tipo;
	private String campo;
	private String min;
	private String max;
	private Lista lista = new Lista();

	public Lista filterEQ( String campo, String valore,Container originale, Lista filtrata) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		{//controlla il tipo di filtro richiesto
		filtrata.getList().clear();
    	int size=originale.getSize();
    	Object t =null;
    	for(int a=0; a<=size-1; a++)
    	{
    		Method u= lista.getDati(a).getPunto().getClass().getMethod("get"+campo.substring(0, 1).toUpperCase()+campo.substring(1), null);
			t = u.invoke(originale.getDati(a).getPunto(), null);
    		if(t.equals(valore))	filtrata.getList().add(originale.getDati(a));
    	}
		
	}
		return filtrata;
		//else return new ResponseEntity <String>("Fallito",HttpStatus.NOT_FOUND);
	}

	public Lista filterBT(String tipo,String campo,String min,String max, Container originale,Lista filtrata) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
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
	
	public Lista filterGLTE(String tipo,String campo,String val, Container originale,Lista filtrata) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		try {
		filtrata.getList().clear();
		int size=originale.getSize();
		Object v=null;
		for(int i=0; i<val.length();i++) {
			if((Character.isLetter(val.charAt(i)))) return filtrata; //Serve per vedere se si immettono dei valori non appropriati
			}		if (tipo.equals("$gte")) {//controlla il tipo di filtro richiesto	
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
