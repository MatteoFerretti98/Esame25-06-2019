package Progetto;

import org.springframework.web.bind.annotation.RestController;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@SuppressWarnings({ "rawtypes" })
@RestController
public class Controller {
	Container Prima = new Container();
	Lista filtrata = new Lista();
	MetaData ListMeta = new MetaData();
	
	@GetMapping("/data") //stampa il JSON del dataset
	public Lista getDati() throws FileNotFoundException, IOException {
		return Prima;
	}
	
	@GetMapping("/metadata")
	public ResponseEntity GetMetadata() throws FileNotFoundException, IOException, ClassNotFoundException
	{	
		return new ResponseEntity <MetaData> (ListMeta,HttpStatus.NOT_FOUND); 
		//return new MetaData();
	}
	
	@GetMapping("/filter")
    public ResponseEntity getbohh (@RequestParam String prov) throws JSONException{
		filtrata.getList().clear();
    	int size=Prima.getSize();
    	for(int a=0; a<=size-1; a++)
    	{
    		if(Prima.containsProvincia(prov, a))	filtrata.getList().add(Prima.getDati(a));
    	}
		if (filtrata.isEmpty()) return new ResponseEntity <String>("Non esiste",HttpStatus.NOT_FOUND);
		else return new ResponseEntity <Lista> (filtrata,HttpStatus.NOT_FOUND); 
	}
	@GetMapping("/filtro")
	public ResponseEntity Filtro(@RequestParam String tipo,String campo, String min, String max) {
		if (tipo.equals("$bt")) {
			//System.out.println("sono entrato");
			if(campo.equals("latitudine")) {
				//System.out.println("sono entrato lat");
				filtrata.getList().clear();
		    	int size=Prima.getSize();
		    	for(int a=0; a<=size-1; a++)
		    	{
		    		if(Prima.getLat(a)>= Float.parseFloat(min)&&Prima.getLat(a)<= Float.parseFloat(max))	filtrata.getList().add(Prima.getDati(a));
		    	}
		    	if (filtrata.isEmpty()) return new ResponseEntity <String>("Non esiste",HttpStatus.NOT_FOUND);
				else return new ResponseEntity <Lista> (filtrata,HttpStatus.NOT_FOUND); 
			}
			if(campo.equals("longitudine")) {
				filtrata.getList().clear();
		    	int size=Prima.getSize();
		    	for(int a=0; a<=size-1; a++)
		    	{
		    		if(Prima.getLon(a)>= Float.parseFloat(min)&&Prima.getLat(a)<= Float.parseFloat(max))	filtrata.getList().add(Prima.getDati(a));
		    	}
		    	if (filtrata.isEmpty()) return new ResponseEntity <String>("Non esiste",HttpStatus.NOT_FOUND);
				else return new ResponseEntity <Lista> (filtrata,HttpStatus.NOT_FOUND); 
			}
			else return new ResponseEntity <String>("trova un nuovo campo",HttpStatus.NOT_FOUND);
		}
		else return new ResponseEntity <String>("Fallito",HttpStatus.NOT_FOUND);
	}
}

