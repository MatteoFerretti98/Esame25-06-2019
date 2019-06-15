package Progetto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
@SuppressWarnings({"rawtypes","unused"})
public class Filtri {
	private String tipo;
	private String campo; 
	private String min;
	private String max;
	private String value;
	private Lista lista = new Lista();
	public Filtri() {
		tipo=null;
		campo=null;
		min=null;
		max=null;
		value=null;
	}
	
	public ResponseEntity filterBT(String tipo,String campo,String min,String max, Container originale,Lista filtrata) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		int size=originale.getSize()-1;
		if (tipo.equals("$bt")) {//controlla il tipo di filtro richiesto
				float MIN = Float.parseFloat(min);
				float MAX = Float.parseFloat(max);
				filtrata.getList().clear();
		    	for(int a=0; a<=size; a++)
		    	{
		    		//Con questa funzione in base al nome tra longitudine e latitudine richiama il get consono
		    		//Ho dovuto instaziare una nuova lista tipo lista per raggiungere il metodo altrimenti avrebbe dato errore perchè non è istanzata all'interno di questa classe
		    		Method u = lista.getDati(a).getPunto().getClass().getMethod("get"+campo.substring(0, 1).toUpperCase()+campo.substring(1), null);
		    		//grazie invece all'invoke riprendo il valore appunto del metodo richiamato precendentemente
		    		Object v = u.invoke(originale.getDati(a).getPunto(), null); 
		    		float V = ((Number)v).floatValue();	//Converto in float
		    		if((V >= MIN) && (V <= MAX))	filtrata.getList().add(originale.getDati(a));
		    	}
		    	if (filtrata.isEmpty()) return new ResponseEntity <String>("Non esiste",HttpStatus.NOT_FOUND);
				else return new ResponseEntity <Lista> (filtrata,HttpStatus.NOT_FOUND); 
		}
		else return new ResponseEntity <String>("Fallito",HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity filterEq(String prov, Container originale,Lista filtrata) {
		filtrata.getList().clear();
    	int size=originale.getSize();
    	for(int a=0; a<=size-1; a++)
    	{
    		if(originale.containsProvincia(prov, a))	filtrata.getList().add(originale.getDati(a));
    	}
		if (filtrata.isEmpty()) return new ResponseEntity <String>("Non esiste",HttpStatus.NOT_FOUND);
		else return new ResponseEntity <Lista> (filtrata,HttpStatus.NOT_FOUND); 
	}
}
