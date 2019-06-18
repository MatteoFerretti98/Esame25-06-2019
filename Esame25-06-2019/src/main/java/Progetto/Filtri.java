package Progetto;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
@SuppressWarnings("all")
public class Filtri {
	public Lista lista = new Lista();
	public Lista filterBT(String tipo,String campo,String min,String max, Container originale,Lista filtrata) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		int size=originale.getSize();
		if (tipo.equals("$bt")) {//controlla il tipo di filtro richiesto
				float MIN = Float.parseFloat(min);
				float MAX = Float.parseFloat(max);
		    	for(int a=0; a<size; a++)
		    	{
		    		//Con questa funzione in base al nome tra longitudine e latitudine richiama il get consono
		    		//Ho dovuto instaziare una nuova lista tipo lista per raggiungere il metodo altrimenti avrebbe dato errore perchè non è istanzata all'interno di questa classe
		    		Method u = lista.getDati(a).getPunto().getClass().getMethod("get"+campo.substring(0, 1).toUpperCase()+campo.substring(1), null);
		    		//grazie invece all'invoke riprendo il valore appunto del metodo richiamato precendentemente
		    		Object v = u.invoke(originale.getDati(a).getPunto(), null); 
		    		float V = ((Number)v).floatValue();	//Converto in float
		    		if((V >= MIN) && (V <= MAX))	filtrata.getList().add(originale.getDati(a));
		    	} 	
		}
		return filtrata;
	}
	
	public Lista filterGLTE(String tipo,String campo,String val, Container originale,Lista filtrata) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		int size=originale.getSize();
		if (tipo.equals("$gte")) {//controlla il tipo di filtro richiesto
				float VAL = Float.parseFloat(val);
		    	for(int a=0; a<size; a++)
		    	{
		    		//Con questa funzione in base al nome tra longitudine e latitudine richiama il get consono
		    		//Ho dovuto instaziare una nuova lista tipo lista per raggiungere il metodo altrimenti avrebbe dato errore perchè non è istanzata all'interno di questa classe
		    		Method u = lista.getDati(a).getPunto().getClass().getMethod("get"+campo.substring(0, 1).toUpperCase()+campo.substring(1), null);
		    		//grazie invece all'invoke riprendo il valore appunto del metodo richiamato precendentemente
		    		Object v = u.invoke(originale.getDati(a).getPunto(), null); 
		    		float V = ((Number)v).floatValue();	//Converto in float
		    		if(V >= VAL)	filtrata.getList().add(originale.getDati(a));
		    	} 	
		}
		else if (tipo.equals("$lte")) {//controlla il tipo di filtro richiesto
			float VAL = Float.parseFloat(val);
	    	for(int a=0; a<size; a++)
	    	{
	    		//Con questa funzione in base al nome tra longitudine e latitudine richiama il get consono
	    		//Ho dovuto instaziare una nuova lista tipo lista per raggiungere il metodo altrimenti avrebbe dato errore perchè non è istanzata all'interno di questa classe
	    		Method u = lista.getDati(a).getPunto().getClass().getMethod("get"+campo.substring(0, 1).toUpperCase()+campo.substring(1), null);
	    		//grazie invece all'invoke riprendo il valore appunto del metodo richiamato precendentemente
	    		Object v = u.invoke(originale.getDati(a).getPunto(), null); 
	    		float V = ((Number)v).floatValue();	//Converto in float
	    		if(V <= VAL)	filtrata.getList().add(originale.getDati(a));
	    	} 	
	}
		return filtrata;
	}
	public Lista filterAND(String tipo1,String campo1,String tipo2, String campo2, Container originale,Lista filtrata) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Lista filter = new Lista();
		filtrata.getList().clear();
		filter.getList().clear();
		int size=originale.getSize();
		Method u1=null;
		Method u2=null;
		Object v1=null;
		Object v2=null;
		for(int a=0; a<size; a++)
    	{
			if((tipo1.equals("latitudine"))||(tipo1.equals("longitudine"))) {
				u1= lista.getDati(a).getPunto().getClass().getMethod("get"+tipo1.substring(0, 1).toUpperCase()+tipo1.substring(1), null);
				v1 = u1.invoke(originale.getDati(a).getPunto(), null);
				
			}
			else {
				u1 = lista.getDati(a).getClass().getMethod("get"+tipo1.substring(0, 1).toUpperCase()+tipo1.substring(1), null);
				v1 = u1.invoke(originale.getDati(a), null); 
			}
			if(campo1.equals(v1)) filtrata.getList().add(originale.getDati(a));
    	}
		int size2=filtrata.getSize();
		for(int i=0; i<size2; i++)
    	{
			if((tipo2.equals("latitudine"))||(tipo2.equals("longitudine"))) {
				u2= lista.getDati(i).getPunto().getClass().getMethod("get"+tipo2.substring(0, 1).toUpperCase()+tipo2.substring(1), null);
				v2 = u2.invoke(originale.getDati(i).getPunto(), null);
			}
			else {
				u2 = lista.getDati(i).getClass().getMethod("get"+tipo2.substring(0, 1).toUpperCase()+tipo2.substring(1), null);
				v2 = u2.invoke(originale.getDati(i), null); 
			}
			if(campo2.equals(v2)) filter.getList().add(filtrata.getDati(i));
    	}
		/*for(int a=0; a<size; a++)
    	{
			if((tipo1.equals("latitudine"))||(tipo1.equals("longitudine"))) {
				u1= lista.getDati(a).getPunto().getClass().getMethod("get"+tipo1.substring(0, 1).toUpperCase()+tipo1.substring(1), null);
				v1 = u1.invoke(originale.getDati(a).getPunto(), null);
				
			}
			else {
				u1 = lista.getDati(a).getClass().getMethod("get"+tipo1.substring(0, 1).toUpperCase()+tipo1.substring(1), null);
	    		v1 = u1.invoke(originale.getDati(a), null); 
			}
    		
    		for(int i=0; i<size; i++)
        	{
    			if((tipo2.equals("latitudine"))||(tipo2.equals("longitudine"))) {
    				u2= lista.getDati(i).getPunto().getClass().getMethod("get"+tipo2.substring(0, 1).toUpperCase()+tipo2.substring(1), null);
    				v2 = u2.invoke(originale.getDati(i).getPunto(), null);
    			}
    			else {
    				u2 = lista.getDati(i).getClass().getMethod("get"+tipo2.substring(0, 1).toUpperCase()+tipo2.substring(1), null);
    	    		v2 = u2.invoke(originale.getDati(i), null); 
    			}
        		if((campo1.equals(v1))&&(campo2.equals(v2))) {
        			filtrata.getList().add(originale.getDati(a));
        		}
        	}
    	}*/
		return filter;
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
    		for(int i=0; i<size; i++)
        	{
    			if((tipo2.equals("latitudine"))||(tipo2.equals("longitudine"))) {
    				Method u2= lista.getDati(i).getPunto().getClass().getMethod("get"+tipo2.substring(0, 1).toUpperCase()+tipo2.substring(1), null);
    				v2 = u2.invoke(originale.getDati(i).getPunto(), null);
    			}
    			else {
    				Method u2 = lista.getDati(i).getClass().getMethod("get"+tipo2.substring(0, 1).toUpperCase()+tipo2.substring(1), null);
    	    		v2 = u2.invoke(originale.getDati(i), null); 
    			}
        		if((campo1.equals(v1))||(campo2.equals(v2)))	filtrata.getList().add(originale.getDati(a));
        	}
    	}
		return filtrata;
	}
}
