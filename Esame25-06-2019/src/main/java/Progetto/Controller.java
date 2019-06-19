package Progetto;

import org.springframework.web.bind.annotation.RestController;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@SuppressWarnings("all")
@RestController

/**
 * {@link Controller} Un semplice menù di scelta che ha lo scopo di testare le varie funzionalità implementate 
 * dalle classi del package ... secondo le specifiche del progetto.
 * @author Matteo Ferretti (s1083630@studenti.univpm.it), Angelo D'Agostino Bonomi (s1082444@studenti.univpm.it)
 * @version 1.0
 */

public class Controller {
	
	Container Prima = new Container();
	Lista filtrata = new Lista();
	MetaData ListMeta = new MetaData();
	Filtri filtro = new Filtri();
	Statistiche stats = new Statistiche();
	TrovaDaPunto trova = new TrovaDaPunto();
	
	@GetMapping("/data") //stampa il JSON del dataset
	public Lista getDati() throws FileNotFoundException, IOException {
		return Prima;
	}
	
	@GetMapping("/metadata") //Stampa il JSON dei metadati
	public ResponseEntity GetMetadata() throws FileNotFoundException, IOException, ClassNotFoundException
	{	
		return new ResponseEntity <MetaData> (ListMeta,HttpStatus.OK); 
	}
	
	@GetMapping("/CondFilter") //Filtri condizionali
	public ResponseEntity CondFilter (@RequestParam String tipo, String campo, String min, String max) throws JSONException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{	
		if((filtro.filterBT(tipo, campo, min, max, Prima, filtrata).isEmpty())&&(filtro.filterGLTE(tipo, campo, min, Prima, filtrata).isEmpty())&&(filtro.filterGLTE(tipo, campo, min, Prima, filtrata).isEmpty())) return new ResponseEntity ("Non esiste",HttpStatus.NOT_FOUND); //Se le liste sono vuote Non esiste 
		else if((!filtro.filterBT(tipo, campo, min, max, Prima, filtrata).isEmpty())&&(tipo.equals("$bt"))) return new ResponseEntity <Lista> (filtro.filterBT(tipo, campo, min, max, Prima, filtrata),HttpStatus.OK); //Se mette BT fa between
		else if((!filtro.filterGLTE(tipo, campo, min, Prima, filtrata).isEmpty())&&((tipo.equals("$lte"))||(tipo.equals("$gte")))) return new ResponseEntity <Lista> (filtro.filterGLTE(tipo, campo, min, Prima, filtrata),HttpStatus.OK); //Se mette gte controlla se è maggiore, se mette lte controlla se è minore
		else return new ResponseEntity ("Immetti dei valori consoni",HttpStatus.BAD_REQUEST); //Se non è nessuna delle precedenti è una Bad Request
	}
	
	@GetMapping("/LogFilter") //Filtri logici
	public ResponseEntity LogFilter (@RequestParam String tipo, String tipo1, String campo1, String tipo2, String campo2) throws JSONException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		if((filtro.filterAND(tipo1, campo1, tipo2, campo2, Prima, filtrata).isEmpty())&&(filtro.filterOR(tipo1, campo1, tipo2, campo2, Prima, filtrata).isEmpty())) return new ResponseEntity ("Non esiste",HttpStatus.NOT_FOUND); //Se le liste sono vuote Non esiste
		else if((!filtro.filterAND(tipo1, campo1, tipo2, campo2, Prima, filtrata).isEmpty())&&(tipo.equals("$and"))) return new ResponseEntity <Lista> (filtro.filterAND(tipo1, campo1, tipo2, campo2, Prima, filtrata),HttpStatus.OK); //Cerca tutte le aziende che hanno 2 specifici valori
		else if((!filtro.filterOR(tipo1, campo1, tipo2, campo2, Prima, filtrata).isEmpty())&&(tipo.equals("$or"))) return new ResponseEntity <Lista> (filtro.filterOR(tipo1, campo1, tipo2, campo2, Prima, filtrata),HttpStatus.OK); //Cerca tutte le aziende che hanno o uno o l'altro tipo di valore
		else return new ResponseEntity ("Immetti dei valori consoni",HttpStatus.BAD_REQUEST); //Se non è nessuna delle precedenti è una Bad Request
	}
	
	@GetMapping("/stat") //Statistiche
	public ResponseEntity Stats (@RequestParam String tipo, String campo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if((stats.StringStats(tipo, campo, Prima, filtrata).isEmpty())&&(stats.NumStats(tipo, campo, Prima, filtrata).isEmpty())) return new ResponseEntity ("Non esiste",HttpStatus.NOT_FOUND); //Se le liste sono vuote Non esiste
		else if(!(stats.StringStats(tipo, campo, Prima, filtrata).isEmpty())&&((tipo.equals("preNorm"))||(tipo.equals("comune"))||(tipo.equals("provincia"))||(tipo.equals("tipo"))))	return new ResponseEntity (stats.StringStats(tipo, campo, Prima, filtrata),HttpStatus.OK); //Se deve fare le statistiche per gli elementi unici
		else if(!(stats.NumStats(tipo, campo, Prima, filtrata).isEmpty())&&((tipo.equals("telefono"))||(tipo.equals("fax"))||(tipo.equals("latitudine"))||(tipo.equals("longitudine"))))	return new ResponseEntity (stats.NumStats(tipo, campo, Prima, filtrata),HttpStatus.OK); //Se deve fare le statistiche per numeri
		else return new ResponseEntity ("Immetti dei valori consoni",HttpStatus.BAD_REQUEST); //Se non è nessuna delle precedenti è una Bad Request
	}
	
	@GetMapping("/find") //Cerca tutte le aziende, fissato un punto, in un certo raggio d'azione
	public ResponseEntity Find (@RequestParam Float Lat, Float Lon, Float Radius) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if((trova.Find(Lat, Lon, Radius, Prima, filtrata).isEmpty())) return new ResponseEntity ("Non esiste",HttpStatus.NOT_FOUND); //Se le liste sono vuote Non esiste
		else if(!(trova.Find(Lat, Lon, Radius, Prima, filtrata).isEmpty()))	return new ResponseEntity (trova.Find(Lat, Lon, Radius, Prima, filtrata),HttpStatus.OK);
		return new ResponseEntity ("Immetti dei valori consoni",HttpStatus.BAD_REQUEST); //Se non è nessuna delle precedenti è una Bad Request
	}
}