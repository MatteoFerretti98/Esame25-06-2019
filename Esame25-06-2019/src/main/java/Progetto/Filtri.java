package Progetto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
@SuppressWarnings({"rawtypes","unused"})
public class Filtri {
	private String tipo;
	private String campo; 
	private String min;
	private String max;
	private String value;
	public Filtri() {
		tipo=null;
		campo=null;
		min=null;
		max=null;
		value=null;
	}
	
	public ResponseEntity filterBT(String tipo,String campo,String min,String max, Container originale,Lista filtrata) {
		int size=originale.getSize()-1;
		if (tipo.equals("$bt")) {//controlla il tipo di filtro richiesto
			if(campo.equals("latitudine")) {
				filtrata.getList().clear();
		    	for(int a=0; a<=size; a++)
		    	{
		    		if(originale.getLat(a)>= Float.parseFloat(min)&&originale.getLat(a)<= Float.parseFloat(max))	filtrata.getList().add(originale.getDati(a));
		    	}
		    	if (filtrata.isEmpty()) return new ResponseEntity <String>("Non esiste",HttpStatus.NOT_FOUND);
				else return new ResponseEntity <Lista> (filtrata,HttpStatus.NOT_FOUND); 
			}
			if(campo.equals("longitudine")) {
				filtrata.getList().clear();
		    	for(int a=0; a<=size; a++)
		    	{
		    		if(originale.getLon(a)>= Float.parseFloat(min)&&originale.getLon(a)<= Float.parseFloat(max))	filtrata.getList().add(originale.getDati(a));
		    	}
		    	if (filtrata.isEmpty()) return new ResponseEntity <String>("Non esiste",HttpStatus.NOT_FOUND);
				else return new ResponseEntity <Lista> (filtrata,HttpStatus.NOT_FOUND); 
			}
			else return new ResponseEntity <String>("trova un nuovo campo",HttpStatus.NOT_FOUND);
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
