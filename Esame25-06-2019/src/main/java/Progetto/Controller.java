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

	/*@GetMapping("/filtr")
    public ResponseEntity filtro1 (@RequestParam String prov) throws JSONException
	{
		return new ResponseEntity (filtro.filterEq(prov, Prima, filtrata),HttpStatus.NOT_FOUND);
	}*/
	
	@GetMapping("/filtro")
	public ResponseEntity filtro1 (@RequestParam String tipo, String campo, String min, String max) throws JSONException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		if(tipo.equals("$bt"))	return new ResponseEntity (filtro.filterBT(tipo, campo, min, max, Prima, filtrata),HttpStatus.NOT_FOUND);
		else if(tipo.contentEquals("$prov")) return new ResponseEntity (filtro.filterEq(campo, Prima, filtrata),HttpStatus.NOT_FOUND);
		else return new ResponseEntity ("Immetti dei valori consoni",HttpStatus.NOT_FOUND);
	}
	
	/*@GetMapping("/media")
	public String Media(@RequestParam String campo) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		float media = stats.Media(Prima, campo);
		float devStand= stats.devStand(Prima, campo);
		return "  del campo "+ campo +  " la media e': "+ media +" "+ "la deviazione standard è:" +devStand;
		
	}*/
	
	@GetMapping("/stats")
	public String Count(@RequestParam String campo, String nome) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		float media = stats.Media(Prima, campo);
		float devStand= stats.devStand(Prima, campo);
		float count= stats.Count(Prima, campo, nome);
		if((campo!=null)&&(nome!=null)) return "Il conteggio del campo ''"+campo+"'' di ''"+nome+"'' è di: "+count;
		else if((campo!=null)&&(nome==null)) return "  La media del campo ''"+ campo +  "'' e': "+ media + " e la deviazione standard è: " +devStand;
		else return "Immettere i valori corretti";
	}
}

