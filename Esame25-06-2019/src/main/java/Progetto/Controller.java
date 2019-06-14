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
	Filtri filtro = new Filtri();
	
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
	
	/*@GetMapping("/filter")
    public ResponseEntity getbohh (@RequestParam String prov) throws JSONException{
		filtrata.getList().clear();
    	int size=Prima.getSize();
    	for(int a=0; a<=size-1; a++)
    	{
    		if(Prima.containsProvincia(prov, a))	filtrata.getList().add(Prima.getDati(a));
    	}
		if (filtrata.isEmpty()) return new ResponseEntity <String>("Non esiste",HttpStatus.NOT_FOUND);
		else return new ResponseEntity <Lista> (filtrata,HttpStatus.NOT_FOUND); 
	}*/

	@GetMapping("/filtr")
    public ResponseEntity filtro1 (@RequestParam String prov) throws JSONException
	{
		return filtro.filterEq(prov, Prima, filtrata);
	}
	@GetMapping("/filtro")
	public ResponseEntity filtro2 (@RequestParam String tipo,String campo, String min, String max) throws JSONException
	{
		return filtro.filterBT(tipo, campo, min, max, Prima, filtrata);
	}
}

