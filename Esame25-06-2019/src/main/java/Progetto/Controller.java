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
	
	/*@GetMapping("/media")
	public String Media(@RequestParam String campo) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		float media = stats.Media(Prima, campo);
		float devStand= stats.devStand(Prima, campo);
		return "  del campo "+ campo +  " la media e': "+ media +" "+ "la deviazione standard è:" +devStand;
		
	}*/
	
	@GetMapping("/CondFilter")
	public ResponseEntity CondFilter (@RequestParam String tipo, String campo, String min, String max) throws JSONException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		filtrata.getList().clear();
		if((filtro.filterBT(tipo, campo, min, max, Prima, filtrata).isEmpty())&&(filtro.filterGLTE(tipo, campo, min, Prima, filtrata).isEmpty())&&(filtro.filterGLTE(tipo, campo, min, Prima, filtrata).isEmpty())) return new ResponseEntity ("Non esiste",HttpStatus.NOT_FOUND);
		else if((!filtro.filterBT(tipo, campo, min, max, Prima, filtrata).isEmpty())&&(tipo.equals("$bt"))) return new ResponseEntity <Lista> (filtro.filterBT(tipo, campo, min, max, Prima, filtrata),HttpStatus.OK);
		//else if((!filtro.filterGLTE(tipo, campo, min, Prima, filtrata).isEmpty())&&(tipo.equals("$gte"))) return new ResponseEntity <Lista> (filtro.filterGLTE(tipo, campo, min, Prima, filtrata),HttpStatus.OK);
		else if((!filtro.filterGLTE(tipo, campo, min, Prima, filtrata).isEmpty())&&((tipo.equals("$lte"))||(tipo.equals("$gte")))) return new ResponseEntity <Lista> (filtro.filterGLTE(tipo, campo, min, Prima, filtrata),HttpStatus.OK);
		else return new ResponseEntity ("Immetti dei valori consoni",HttpStatus.BAD_REQUEST);
		/*if(tipo.equals("$bt"))	return new ResponseEntity (filtro.filterBT(tipo, campo, min, max, Prima, filtrata),HttpStatus.NOT_FOUND);
		else if(tipo.contentEquals("$prov")) return new ResponseEntity <Lista> (filtro.filterProv(tipo, campo, Prima, filtrata),HttpStatus.OK);
		else if(filtrata.isEmpty()) return new ResponseEntity <String>("Non esiste",HttpStatus.NOT_FOUND);
		if(filtro.filterProv(tipo, campo, Prima, filtrata).isEmpty()) return new ResponseEntity ("Immetti dei valori consoni",HttpStatus.BAD_REQUEST);
		else return new ResponseEntity ("Immetti dei valori consoni",HttpStatus.BAD_REQUEST);*/
	}
	
	/*@GetMapping("/LogFilter")
	public ResponseEntity LogFilter (@RequestParam String tipo, String campo, String min, String max) throws JSONException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		filtrata.getList().clear();
		if(tipo.equals("$bt"))	return new ResponseEntity (filtro.filterBT(tipo, campo, min, max, Prima, filtrata),HttpStatus.NOT_FOUND);
		else if(tipo.contentEquals("$prov")) return new ResponseEntity <Lista> (filtro.filterProv(tipo, campo, Prima, filtrata),HttpStatus.OK);
		else if(filtrata.isEmpty()) return new ResponseEntity <String>("Non esiste",HttpStatus.NOT_FOUND);
		if(filtro.filterProv(tipo, campo, Prima, filtrata).isEmpty()) return new ResponseEntity ("Immetti dei valori consoni",HttpStatus.BAD_REQUEST);
		else return new ResponseEntity ("Immetti dei valori consoni",HttpStatus.BAD_REQUEST);
	}*/
	
	@GetMapping("/stat")
	public ResponseEntity Stats (@RequestParam String tipo, String campo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if((stats.StringStats(tipo, campo, Prima, filtrata).isEmpty())&&(stats.NumStats(tipo, campo, Prima, filtrata).isEmpty())) return new ResponseEntity ("Non esiste",HttpStatus.NOT_FOUND);
		else if(!(stats.StringStats(tipo, campo, Prima, filtrata).isEmpty())&&((tipo.equals("preNorm"))||(tipo.equals("comune"))||(tipo.equals("provincia"))||(tipo.equals("tipo"))))	return new ResponseEntity (stats.StringStats(tipo, campo, Prima, filtrata),HttpStatus.OK);
		//else if(!(stats.StringStats(tipo, campo, Prima, filtrata).isEmpty())&&(tipo.equals("comune")))	return new ResponseEntity (stats.StringStats(tipo, campo, Prima, filtrata),HttpStatus.OK);
		//else if(!(stats.StringStats(tipo, campo, Prima, filtrata).isEmpty())&&(tipo.equals("provincia")))	return new ResponseEntity (stats.StringStats(tipo, campo, Prima, filtrata),HttpStatus.OK);
		//else if(!(stats.StringStats(tipo, campo, Prima, filtrata).isEmpty())&&(tipo.equals("tipo")))	return new ResponseEntity (stats.StringStats(tipo, campo, Prima, filtrata),HttpStatus.OK);
		else if(!(stats.NumStats(tipo, campo, Prima, filtrata).isEmpty())&&((tipo.equals("telefono"))||(tipo.equals("fax"))||(tipo.equals("latitudine"))||(tipo.equals("longitudine"))))	return new ResponseEntity (stats.NumStats(tipo, campo, Prima, filtrata),HttpStatus.OK);
		//else if(!(stats.NumStats(tipo, campo, Prima, filtrata).isEmpty())&&(tipo.equals("fax")))	return new ResponseEntity (stats.NumStats(tipo, campo, Prima, filtrata),HttpStatus.OK);
		//else if(!(stats.NumStats(tipo, campo, Prima, filtrata).isEmpty())&&(tipo.equals("latitudine")))	return new ResponseEntity (stats.NumStats(tipo, campo, Prima, filtrata),HttpStatus.OK);
		//else if(!(stats.NumStats(tipo, campo, Prima, filtrata).isEmpty())&&(tipo.equals("longitudine")))	return new ResponseEntity (stats.NumStats(tipo, campo, Prima, filtrata),HttpStatus.OK);
		else return new ResponseEntity ("Immetti dei valori consoni",HttpStatus.BAD_REQUEST);

	}
	//////Da passare tutti gli stats sul responseEntity qui sotto
	/*@GetMapping("/stat")
	public ArrayList<Statistiche> stats(@RequestParam String tipo, String campo, String nome) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		//if(campo.equals("provincia"))	return new ResponseEntity (stats.(tipo, campo, Prima, filtrata),HttpStatus.NOT_FOUND);
		//if(tipo.equals("$count")) return new ResponseEntity (stats.stats(Prima, campo, nome),HttpStatus.NOT_FOUND);
		//return new ResponseEntity ("Immetti dei valori consoni",HttpStatus.NOT_FOUND);
		return stats.stats2(Prima, campo, nome);
	}*/
	/*	@GetMapping("/stats")
	public String Count(@RequestParam String campo, String nome) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		float media = stats.Media(Prima, campo);
		float devStand= stats.devStand(Prima, campo);
		float count= stats.Count(Prima, campo, nome);
		if((campo!=null)&&(nome!=null)) return "Il conteggio del campo ''"+campo+"'' di ''"+nome+"'' è di: "+count;
		else if((campo!=null)&&(nome==null)) return "  La media del campo ''"+ campo +  "'' e': "+ media + " e la deviazione standard è: " +devStand;
		else return "Immettere i valori corretti";
		//return "c";
	}*/
}

