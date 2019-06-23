package progetto;

import org.springframework.web.bind.annotation.RestController;

import dataset.*;
import metadati.MetaData;
import operazioni.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Matteo Ferretti (s1083630@studenti.univpm.it), Angelo D'Agostino Bonomi (s1082444@studenti.univpm.it)
 * @version 1.0
 */
/**
 * E' la classe che inizializza le varie GET che servono per sperimentare le varie funzionalità 
 * implementate per il server Spring eseguito da {@link EsameApplication}.
 */
@RestController
public class Controller {
	/**
	 * Questo è il costruttore di Controller
	 */
	public Controller(){
		
	}
	/**
	 * Inizializza Prima come {@link dataset.Container} perchè a noi serve che sia lista ma anche che contenga i metodi di Container.
	 */
	private Container Prima = new Container();
	/**
	 * Inizializza la lista che servirà per la POST delle {@link operazioni.Statistiche} filtrate
	 */
	private Lista FilStat = new Lista();
	/**
	 * Inizializza Filtrata come {@link dataset.Lista} (ci servirà come lista da riempire nei filtri).
	 */
	private Lista filtrata = new Lista();
	/**
	 * Inizializza ListMeta come {@link dataset.Lista} di {@link metadati.MetaData}.
	 */
	private MetaData ListMeta = new MetaData();
	/**
	 * Inizializza filtro come {@link operazioni.Filtri} (servirà per richiamare i vari filtri).
	 */
	private Filtri filtro = new Filtri();
	/**
	 * Inizializza stats come {@link operazioni.Statistiche} (servirà per richiamare la lista delle {@link operazioni.Statistics}).
	 */
	private Statistiche stats = new Statistiche();
	/**
	 * Inizializza trova come {@link operazioni.TrovaDaPunto} (servirà per richiamare la classe per trovare le agenzie in una certa zona).
	 */
	private TrovaDaPunto trova = new TrovaDaPunto();
	
	/**
	 * Questa GET restituisce tutti i data della {@link dataset.Lista} del file csv.
	 * @return Prima E' la {@link dataset.Lista} di tutti i {@link dataset.Dati}
	 * @throws FileNotFoundException Se non trova il file
	 * @throws IOException Classe base per le eccezioni di flussi di dati
	 */
	@GetMapping("/data") //stampa il JSON del dataset
	public Lista getDati() throws FileNotFoundException, IOException {
		return Prima;
	}
	/**
	 * Questo GET serve per restituire una singola riga nel caso si voglia cercare con il numero di Fid
	 * @param Fid Numero della riga
	 * @return Prima.getDati(Fid-1) Resituisce la singola riga
	 */
	@GetMapping("/linea")
	public ResponseEntity  Linea(@RequestParam int Fid) {
		if(Fid==0) return new ResponseEntity ("Non esiste",HttpStatus.NOT_FOUND); //Se le liste sono vuote Non esiste
		else if(Fid!=0)	return new ResponseEntity (Prima.getDati(Fid-1),HttpStatus.OK);
		else return new ResponseEntity ("Immetti dei valori consoni",HttpStatus.BAD_REQUEST); //Se non è nessuna delle precedenti è una Bad Request
	}
	/**
	 * Questa GET restituisce tutti i {@link metadati.MetaData} del file csv. 
	 * @return ListaMeta E' la {@link dataset.Lista} di tutti i {@link metadati.MetaData}
	 * @throws FileNotFoundException Se non trova il file
	 * @throws IOException Classe base per le eccezioni di flussi di dati
	 * @throws ClassNotFoundException Se non trova la classe
	 */
	@GetMapping("/metadata") //Stampa il JSON dei metadati
	public ResponseEntity<MetaData> GetMetadata() throws FileNotFoundException, IOException, ClassNotFoundException
	{	
		return new ResponseEntity <MetaData> (ListMeta,HttpStatus.OK); 
	}
	/**
	 * Questi sono i {@link operazioni.Filtri} Condizionali e Logici.
	 * @param body Body della richiesta Post in base al filtro scelto
	 * @return filtrata Lista dei dati filtrati dai filtri
	 * @throws NoSuchMethodException Se il metodo che vado a cercare non esiste
	 * @throws SecurityException Se c'è stata una violazione nella sicurezza
	 * @throws IllegalAccessException Se si tenta di accedere ad un metodo la cui visibilità non è consentita
	 * @throws IllegalArgumentException Se il metodo ha passato un argomento non appropriato
	 * @throws InvocationTargetException Controlla le eccezioni che sono chiamate da un invoke method
	 * @throws IOException Classe base per le eccezioni di flussi di dati
	 */
	//  {"$gte" : {"latitudine" : 41}}		//Filtro condizionale
	//	{"$lte" : {"latitudine" : 40.5}}	//Filtro condizionale
	//  {"$bt":{"latitudine":[41,42]}}		//Filtro condizionale
	//  {"$and" : {"tipo":[provincia,comune],"campo":[AV,Avellino]}}	//Filtro logico
	//  {"$or" : {"tipo":[provincia,comune],"campo":[AV,Avellino]}}		//Filtro logico
	@PostMapping("/data") //Filtri
    public ResponseEntity Filtro(@RequestBody String body) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
        Map<String, Object> filtraBody = filtro.parseFilter(body);
        if(!(filtraBody.isEmpty())) {
        String campo = (String) filtraBody.get("campo");
        String tipo = (String) filtraBody.get("tipo");
        if(body.isEmpty()) return new ResponseEntity ("Non esiste",HttpStatus.NOT_FOUND); //Se le liste sono vuote Non esiste 
        else if(tipo.equals("$bt")) {
        	String min = (String) filtraBody.get("val1");
        	String max=(String) filtraBody.get("val2");
        	return new ResponseEntity (filtro.filterBT(tipo, campo, min, max, Prima, filtrata),HttpStatus.OK);
        }
        else if (tipo.equals("$gte")||tipo.equals("$lte")) {
        	String val = (String) filtraBody.get("val");
        	return new ResponseEntity (filtro.filterGLTE(tipo, campo, val, Prima, filtrata),HttpStatus.OK);
        }
        else if (tipo.equals("$and")) {
        	String tipo1 = (String) filtraBody.get("val1");
        	String tipo2=(String) filtraBody.get("val2");
        	String campo1 = (String) filtraBody.get("val3");
        	String campo2=(String) filtraBody.get("val4");
        	return new ResponseEntity <Lista> (filtro.filterAND(tipo1, campo1, tipo2, campo2, Prima, filtrata),HttpStatus.OK);
        }
        else if (tipo.equals("$or")) {
        	String tipo1 = (String) filtraBody.get("val1");
        	String tipo2=(String) filtraBody.get("val2");
        	String campo1 = (String) filtraBody.get("val3");
        	String campo2=(String) filtraBody.get("val4");
        	return new ResponseEntity <Lista> (filtro.filterOR(tipo1, campo1, tipo2, campo2, Prima, filtrata),HttpStatus.OK);
        }
        else return new ResponseEntity ("Immetti dei valori consoni",HttpStatus.BAD_REQUEST); //Se non è nessuna delle precedenti è una Bad Request
        }
        else return new ResponseEntity ("Immetti dei valori consoni",HttpStatus.BAD_REQUEST);
     }
	/**
	 * Questa è la POST per le statistiche filtrate, in base al filtro 
	 * fa ritornare le statistiche su un singolo attributo
	 * @param campo Serve per selezionare il tipo di attributo
	 * @param nome Da mettere solo nei tipi String, non si deve mettere nei tipi Number
	 * @param body Body della richiesta Post in base al filtro scelto
	 * @return statistics E' la lista (con costruttore {@link operazioni.Statistics} delle: Statistiche se sono Numeri, di elementi unici se sono Stringhe
	 * @throws NoSuchMethodException Se il metodo che vado a cercare non esiste
	 * @throws SecurityException Se c'è stata una violazione nella sicurezza
	 * @throws IllegalAccessException Se si tenta di accedere ad un metodo la cui visibilità non è consentita
	 * @throws IllegalArgumentException Se il metodo ha passato un argomento non appropriato
	 * @throws InvocationTargetException Controlla le eccezioni che sono chiamate da un invoke method
	 * @throws IOException Classe base per le eccezioni di flussi di dati
	 */
	@PostMapping("/stat") //Statistiche con filtri
	public ResponseEntity Stat (@RequestParam String campo,String nome, @RequestBody String body) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NullPointerException, IOException {
		if(!(body.isEmpty())) {
			FilStat = (Lista) this.Filtro(body).getBody(); 
		if((stats.StringStats(campo, nome, FilStat).isEmpty())&&(stats.NumStats(campo, nome, FilStat).isEmpty())) return new ResponseEntity ("Non esiste",HttpStatus.NOT_FOUND); //Se le liste sono vuote Non esiste
		else if(!(stats.StringStats(campo, nome, FilStat).isEmpty())&&((campo.equals("preNorm"))||(campo.equals("comune"))||(campo.equals("provincia"))||(campo.equals("tipo"))))	return new ResponseEntity (stats.StringStats(campo, nome, FilStat),HttpStatus.OK); //Se deve fare le statistiche per gli elementi unici
		else if(!(stats.NumStats(campo,nome, FilStat).isEmpty())&&((campo.equals("telefono"))||(campo.equals("fax"))||(campo.equals("latitudine"))||(campo.equals("longitudine"))))	return new ResponseEntity (stats.NumStats(campo, nome, FilStat),HttpStatus.OK); //Se deve fare le statistiche per numeri
		else return new ResponseEntity ("Immetti dei valori consoni",HttpStatus.BAD_REQUEST); //Se non è nessuna delle precedenti è una Bad Request
		}
		else return new ResponseEntity ("Inserisci il body o usa la GET",HttpStatus.BAD_REQUEST);
		}
	/**
	 * Questa GET richiama i metodi NumStats e StringStats per le {@link operazioni.Statistiche}.
	 * @param tipo Si chiede di inserire il tipo della variabile, ovvero, l'attributo.
	 * @param campo Si chiede di inserire il campo della variabile (solo se si ha scelto un tipo String).
	 * @return statistics E' la lista (con costruttore {@link operazioni.Statistics} delle: Statistiche se sono Numeri, di elementi unici se sono Stringhe
	 * @throws NoSuchMethodException Se il metodo che vado a cercare non esiste
	 * @throws SecurityException Se c'è stata una violazione nella sicurezza
	 * @throws IllegalAccessException Se si tenta di accedere ad un metodo la cui visibilità non è consentita
	 * @throws IllegalArgumentException Se il metodo ha passato un argomento non appropriato
	 * @throws InvocationTargetException Controlla le eccezioni che sono chiamate da un invoke method
	 */
	@GetMapping("/stat") //Statistiche
	public ResponseEntity Stats (@RequestParam String tipo, String campo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if((stats.StringStats(tipo, campo, Prima).isEmpty())&&(stats.NumStats(tipo, campo, Prima).isEmpty())) return new ResponseEntity ("Non esiste",HttpStatus.NOT_FOUND); //Se le liste sono vuote Non esiste
		else if(!(stats.StringStats(tipo, campo, Prima).isEmpty())&&((tipo.equals("preNorm"))||(tipo.equals("comune"))||(tipo.equals("provincia"))||(tipo.equals("tipo"))))	return new ResponseEntity (stats.StringStats(tipo, campo, Prima),HttpStatus.OK); //Se deve fare le statistiche per gli elementi unici
		else if(!(stats.NumStats(tipo, campo, Prima).isEmpty())&&((tipo.equals("telefono"))||(tipo.equals("fax"))||(tipo.equals("latitudine"))||(tipo.equals("longitudine"))))	return new ResponseEntity (stats.NumStats(tipo, campo, Prima),HttpStatus.OK); //Se deve fare le statistiche per numeri
		else return new ResponseEntity ("Immetti dei valori consoni",HttpStatus.BAD_REQUEST); //Se non è nessuna delle precedenti è una Bad Request
	}
	/**
	 * Questa GET richiama il metodo Find per il {@link operazioni.TrovaDaPunto} che grazie ad un punto fissato e un raggio trova tutte le agenzie all'interno di quella circonferenza.
	 * @param Lat Si chiede di inserire la latitudine del centro della circonferenza
	 * @param Lon Si chiede di inserire la longitudine del centro della circonferenza
	 * @param Radius Si chiede di inserire il raggio della circonferenza
	 * @return filtrata E' la {@link dataset.Lista} filtrata da {@link operazioni.TrovaDaPunto}
	 * @throws NoSuchMethodException Se il metodo che vado a cercare non esiste
	 * @throws SecurityException Se c'è stata una violazione nella sicurezza
	 * @throws IllegalAccessException Se si tenta di accedere ad un metodo la cui visibilità non è consentita
	 * @throws IllegalArgumentException Se il metodo ha passato un argomento non appropriato
	 * @throws InvocationTargetException Controlla le eccezioni che sono chiamate da un invoke method
	 */
	@GetMapping("/find") //Cerca tutte le aziende, fissato un punto, in un certo raggio d'azione
	public ResponseEntity Find (@RequestParam Float Lat, Float Lon, Float Radius) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Lista trovaAziende = trova.Find(Lat, Lon, Radius, Prima, filtrata);
		if((trovaAziende.isEmpty())) return new ResponseEntity ("Non esiste",HttpStatus.NOT_FOUND); //Se le liste sono vuote Non esiste
		else if(!(trovaAziende.isEmpty()))	return new ResponseEntity (trovaAziende,HttpStatus.OK);
		return new ResponseEntity ("Immetti dei valori consoni",HttpStatus.BAD_REQUEST); //Se non è nessuna delle precedenti è una Bad Request
	}
}