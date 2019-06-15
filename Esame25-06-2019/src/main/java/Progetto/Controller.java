package Progetto;

import org.springframework.web.bind.annotation.RestController;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@SuppressWarnings("all")
@RestController
public class Controller {
	Container Prima = new Container();
	Lista filtrata = new Lista();
	MetaData ListMeta = new MetaData();
	Filtri filtro = new Filtri();
	Statistiche stats= new Statistiche();
	
	@GetMapping("/data") //stampa il JSON del dataset
	public Lista getDati() throws FileNotFoundException, IOException {
		return Prima;
	}
	
	@GetMapping("/metadata")
	public ResponseEntity GetMetadata() throws FileNotFoundException, IOException, ClassNotFoundException
	{	
		return new ResponseEntity <MetaData> (ListMeta,HttpStatus.NOT_FOUND); 
	}

	@GetMapping("/filtr")
    public ResponseEntity filtro1 (@RequestParam String prov) throws JSONException
	{
		return filtro.filterEq(prov, Prima, filtrata);
	}
	@GetMapping("/filtro")
	public ResponseEntity filtro2 (@RequestParam String tipo,String campo, String min, String max) throws JSONException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		return filtro.filterBT(tipo, campo, min, max, Prima, filtrata);
	}
	@GetMapping("/media")
	public float Media() {
		return stats.Media(Prima);
	}
	
	@GetMapping("/count")
	public float Count(@RequestParam String campo, String nome) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		return stats.Count(Prima,filtrata, campo, nome);
	}
}

