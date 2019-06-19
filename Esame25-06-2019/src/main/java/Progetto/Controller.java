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
 * in questa classe si fanno tante cose...
 * @author Pippo
 * @version 1.0
 */


public class Controller {
	

	Container Prima = new Container();
	Lista filtrata = new Lista();
	MetaData ListMeta = new MetaData();
	Filtri filtro1 = new Filtri();
	Filtri filtro2 = new Filtri();
	Statistiche stats= new Statistiche();
	
	@GetMapping("/data") //stampa il JSON del dataset
	public Lista getDati() throws FileNotFoundException, IOException {
		return Prima;
	}
	
	@GetMapping("/metadata")
	public ResponseEntity GetMetadata() throws FileNotFoundException, IOException, ClassNotFoundException
	{	
		return new ResponseEntity <MetaData> (ListMeta,HttpStatus.OK); 
	}
	
	@GetMapping("/CondFilter")
	public ResponseEntity CondFilter (@RequestParam String tipo, String campo, String min, String max) throws JSONException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		if((filtro1.filterBT(tipo, campo, min, max, Prima, filtrata).isEmpty())&&(filtro1.filterGLTE(tipo, campo, min, Prima, filtrata).isEmpty())&&(filtro1.filterGLTE(tipo, campo, min, Prima, filtrata).isEmpty())) return new ResponseEntity ("Non esiste",HttpStatus.NOT_FOUND);
		else if((!filtro1.filterBT(tipo, campo, min, max, Prima, filtrata).isEmpty())&&(tipo.equals("$bt"))) return new ResponseEntity <Lista> (filtro2.filterBT(tipo, campo, min, max, Prima, filtrata),HttpStatus.OK);
		else if((!filtro1.filterGLTE(tipo, campo, min, Prima, filtrata).isEmpty())&&((tipo.equals("$lte"))||(tipo.equals("$gte")))) return new ResponseEntity <Lista> (filtro2.filterGLTE(tipo, campo, min, Prima, filtrata),HttpStatus.OK);
		else return new ResponseEntity ("Immetti dei valori consoni",HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/LogFilter")
	public ResponseEntity LogFilter (@RequestParam String tipo, String tipo1, String campo1, String tipo2, String campo2) throws JSONException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		if((filtro1.filterAND(tipo1, campo1, tipo2, campo2, Prima, filtrata).isEmpty())&&(filtro1.filterOR(tipo1, campo1, tipo2, campo2, Prima, filtrata).isEmpty())) return new ResponseEntity ("Non esiste",HttpStatus.NOT_FOUND);
		else if((!filtro1.filterAND(tipo1, campo1, tipo2, campo2, Prima, filtrata).isEmpty())&&(tipo.equals("$and"))) return new ResponseEntity <Lista> (filtro2.filterAND(tipo1, campo1, tipo2, campo2, Prima, filtrata),HttpStatus.OK);
		else if((!filtro1.filterOR(tipo1, campo1, tipo2, campo2, Prima, filtrata).isEmpty())&&(tipo.equals("$or"))) return new ResponseEntity <Lista> (filtro2.filterOR(tipo1, campo1, tipo2, campo2, Prima, filtrata),HttpStatus.OK);
		else return new ResponseEntity ("Immetti dei valori consoni",HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/stat")
	public ResponseEntity Stats (@RequestParam String tipo, String campo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if((stats.StringStats(tipo, campo, Prima, filtrata).isEmpty())&&(stats.NumStats(tipo, campo, Prima, filtrata).isEmpty())) return new ResponseEntity ("Non esiste",HttpStatus.NOT_FOUND);
		else if(!(stats.StringStats(tipo, campo, Prima, filtrata).isEmpty())&&((tipo.equals("preNorm"))||(tipo.equals("comune"))||(tipo.equals("provincia"))||(tipo.equals("tipo"))))	return new ResponseEntity (stats.StringStats(tipo, campo, Prima, filtrata),HttpStatus.OK);
		else if(!(stats.NumStats(tipo, campo, Prima, filtrata).isEmpty())&&((tipo.equals("telefono"))||(tipo.equals("fax"))||(tipo.equals("latitudine"))||(tipo.equals("longitudine"))))	return new ResponseEntity (stats.NumStats(tipo, campo, Prima, filtrata),HttpStatus.OK);
		else return new ResponseEntity ("Immetti dei valori consoni",HttpStatus.BAD_REQUEST);

	}
}

