package Progetto;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Filtri {
	private Lista lista = new Lista();

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
		int size=originale.getSize();
		for(int a=0; a<size; a++)
    	{
    		//Con questa funzione in base al nome tra longitudine e latitudine richiama il get consono
    		//Ho dovuto instaziare una nuova lista tipo lista per raggiungere il metodo altrimenti avrebbe dato errore perchè non è istanzata all'interno di questa classe
    		Method u1 = lista.getDati(a).getClass().getMethod("get"+tipo1.substring(0, 1).toUpperCase()+tipo1.substring(1), null);
    		//grazie invece all'invoke riprendo il valore appunto del metodo richiamato precendentemente
    		Object v1 = u1.invoke(originale.getDati(a), null); 
    		for(int i=0; i<size; i++)
        	{
        		//Con questa funzione in base al nome tra longitudine e latitudine richiama il get consono
        		//Ho dovuto instaziare una nuova lista tipo lista per raggiungere il metodo altrimenti avrebbe dato errore perchè non è istanzata all'interno di questa classe
        		Method u2 = lista.getDati(i).getClass().getMethod("get"+tipo2.substring(0, 1).toUpperCase()+tipo2.substring(1), null);
        		//grazie invece all'invoke riprendo il valore appunto del metodo richiamato precendentemente
        		Object v2 = u2.invoke(originale.getDati(i), null); 
        		//float V = ((Number)v).floatValue();	//Converto in float
        		if((campo1.equals(v1))&&(campo2.equals(v2)))	filtrata.getList().add(originale.getDati(a));
        	}
    	}
		return filtrata;
	}
	public Lista filterOR(String tipo1,String campo1,String tipo2, String campo2, Container originale,Lista filtrata) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		int size=originale.getSize();
		for(int a=0; a<size; a++)
    	{
    		//Con questa funzione in base al nome tra longitudine e latitudine richiama il get consono
    		//Ho dovuto instaziare una nuova lista tipo lista per raggiungere il metodo altrimenti avrebbe dato errore perchè non è istanzata all'interno di questa classe
    		Method u1 = lista.getDati(a).getClass().getMethod("get"+tipo1.substring(0, 1).toUpperCase()+tipo1.substring(1), null);
    		//grazie invece all'invoke riprendo il valore appunto del metodo richiamato precendentemente
    		Object v1 = u1.invoke(originale.getDati(a), null); 
    		for(int i=0; i<size; i++)
        	{
        		//Con questa funzione in base al nome tra longitudine e latitudine richiama il get consono
        		//Ho dovuto instaziare una nuova lista tipo lista per raggiungere il metodo altrimenti avrebbe dato errore perchè non è istanzata all'interno di questa classe
        		Method u2 = lista.getDati(i).getClass().getMethod("get"+tipo2.substring(0, 1).toUpperCase()+tipo2.substring(1), null);
        		//grazie invece all'invoke riprendo il valore appunto del metodo richiamato precendentemente
        		Object v2 = u2.invoke(originale.getDati(i), null); 
        		//float V = ((Number)v).floatValue();	//Converto in float
        		if((campo1.equals(v1))||(campo2.equals(v2)))	filtrata.getList().add(originale.getDati(a));
        	}
    	}
		return filtrata;
	}
	/*public Lista filterProv(String tipo, String prov, Container originale, Lista filtrata) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (tipo.equals("$prov")) {//controlla il tipo di filtro richiesto
		filtrata.getList().clear();
    	int size=originale.getSize();
    	for(int a=0; a<=size-1; a++)
    	{
    		Method s= lista.getDati(a).getClass().getMethod("getProvincia", null);
    		Object t = s.invoke(originale.getDati(a), null);
    		if(t.equals(prov))	filtrata.getList().add(originale.getDati(a));
    	}
		
	}
		return filtrata;
		//else return new ResponseEntity <String>("Fallito",HttpStatus.NOT_FOUND);
	}*/
}
