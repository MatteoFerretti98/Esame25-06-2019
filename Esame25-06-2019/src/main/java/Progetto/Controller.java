package Progetto;

import org.springframework.web.bind.annotation.RestController;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@SuppressWarnings({ "unused" })
@RestController
public class Controller {
   	Lista Prima = new Lista();
	Lista filtrata = new Lista();
	
	
	@GetMapping("/data") //stampa il JSON del dataset
	public Lista getDati() throws FileNotFoundException, IOException {
		return Prima;
	}
	
	@GetMapping("/metadata")
	public MetaData GetMetadata() throws FileNotFoundException, IOException, ClassNotFoundException
	{
		return new MetaData();
	}
	
	@GetMapping("/filter")
    public ResponseEntity getbohh (@RequestParam String prov) throws JSONException{
		filtrata.getList().clear();
    	int i=0;
    	int a=0;
    	do {	
    	if(Prima.containsProvincia(prov, a)) {
    		filtrata.getList().add(Prima.getDati(a));
    	}
    	a++;
    	if((!(filtrata.isEmpty()))&&(a==(Prima.getSize()-1))) {
    		i=1;
    	}
    
    	}while((i!=1)||(a==(Prima.getSize()-1)));
    	
		if (filtrata.isEmpty()) return new ResponseEntity <String>("finito",HttpStatus.NOT_FOUND);
		else return new ResponseEntity <Lista> (filtrata,HttpStatus.NOT_FOUND); 
	}
}
