package Progetto;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.NumberFormat;

import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.core.io.NumberInput;
@SuppressWarnings("all")
public class Filtri {
	public Lista lista = new Lista();
	public Lista filterBT(String tipo,String campo,String min,String max, Container originale,Lista filtrata) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		filtrata.getList().clear();
		int size=originale.getSize();
		Object v=null;
		if (tipo.equals("$bt")) {//controlla il tipo di filtro richiesto
			for(int i=0; i<max.length();i++) {
				if(Character.isLetter(max.charAt(i))) return filtrata;	//Serve per vedere se si immettono dei valori non appropriati
				}
			for(int i=0; i<min.length();i++) {
				if(Character.isLetter(min.charAt(i))) return filtrata;	//Serve per vedere se si immettono dei valori non appropriati
				}
				float MIN = Float.parseFloat(min);
				float MAX = Float.parseFloat(max);
		    	for(int a=0; a<size; a++)
		    	{
		    	
		    		if((campo.equals("latitudine"))||(campo.equals("longitudine"))) {
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
		    			if((V >= MIN) && (V <= MAX))	filtrata.getList().add(originale.getDati(a));
		    		}
		    	} 	
	//}
		return filtrata;
	}
	
	public Lista filterGLTE(String tipo,String campo,String val, Container originale,Lista filtrata) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
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
		    		if(V >= VAL)	filtrata.getList().add(originale.getDati(a));
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
	    		if(V <= VAL)	filtrata.getList().add(originale.getDati(a));
	    	} 	
		}
	return filtrata;
}
	
	public Lista filterAND(String tipo1,String campo1,String tipo2, String campo2, Container originale,Lista filtrata) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
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
    		if((campo1.equals(v1))&&(campo2.equals(v2)))	filtrata.getList().add(originale.getDati(a));
    	}
		return filtrata;
	}
	
	public Lista filterOR(String tipo1,String campo1,String tipo2, String campo2, Container originale,Lista filtrata) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
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
    		if((campo1.equals(v1))||(campo2.equals(v2)))	filtrata.getList().add(originale.getDati(a));
    	}
		return filtrata;
	}
}
