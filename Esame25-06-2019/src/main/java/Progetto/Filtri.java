package Progetto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
@SuppressWarnings("all")
public class Filtri {
	private Lista lista = new Lista();

	public ResponseEntity filterBT(String tipo,String campo,String min,String max, Container originale,Lista filtrata) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		int size=originale.getSize()-1;
		filtrata.getList().clear();
		//if (tipo.equals("$bt")) {//controlla il tipo di filtro richiesto
				float MIN = Float.parseFloat(min);
				float MAX = Float.parseFloat(max);
				
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
		//}
		//else return new ResponseEntity <String>("Fallito",HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity filterProv(String tipo, String prov, Container originale,Lista filtrata) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		//if (tipo.equals("$prov")) {//controlla il tipo di filtro richiesto
		filtrata.getList().clear();
    	int size=originale.getSize();
    	for(int a=0; a<=size-1; a++)
    	{
    		Method s= lista.getDati(a).getClass().getMethod("getProvincia", null);
    		Object t = s.invoke(originale.getDati(a), null);
    		if(t.equals(prov))	filtrata.getList().add(originale.getDati(a));
    	}
		if (filtrata.isEmpty()) return new ResponseEntity <String>("Non esiste",HttpStatus.NOT_FOUND);
		else return new ResponseEntity <Lista> (filtrata,HttpStatus.NOT_FOUND); 
	//}
		//else return new ResponseEntity <String>("Fallito",HttpStatus.NOT_FOUND);
	}
}
