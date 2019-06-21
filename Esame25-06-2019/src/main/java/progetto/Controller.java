package progetto;

import org.springframework.web.bind.annotation.RestController;

import dataset.*;
import metadati.MetaData;
import operazioni.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
		return new ResponseEntity ("Immetti dei valori consoni",HttpStatus.BAD_REQUEST); //Se non è nessuna delle precedenti è una Bad Request
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
	 * Questa GET richiama i metodi filterBT e filterGLTE per i {@link operazioni.Filtri} Condizionali.
	 * @param tipo Si chiede di inserire il tipo di {@link operazioni.Filtri} che si ha scelto tra $bt, $lte, $gte.
	 * @param campo Si chiede di inserire il campo, ovvero, l'attributo dove si vuole fare il filtro scelto precendentemente.
	 * @param min Si chiede di inserire o il valore minimo (nel caso si abbia scelto $bt) o il valore unico (nel caso si abbia scelto $lte o $gte).
	 * @param max Si chiede di inserire il valore massimo solo pero' nel caso si abbia scelto il filtro $bt.
	 * @return filtrata E' la {@link dataset.Lista} filtrata dai filtri condizionali
	 * @throws JSONException Se c'e' un problema con il JSON
	 * @throws NoSuchMethodException Se il metodo che vado a cercare non esiste
	 * @throws SecurityException Se c'e' stata una violazione nella sicurezza
	 * @throws IllegalAccessException Se si tenta di accedere ad un metodo la cui visibilita' non e' consentita
	 * @throws IllegalArgumentException Se il metodo ha passato un argomento non appropriato
	 * @throws InvocationTargetException Controlla le eccezioni che sono chiamate da un invoke method
	 */
	@GetMapping("/CondFilter") //Filtri condizionali
	public ResponseEntity CondFilter (@RequestParam String tipo, String campo, String min, String max) throws JSONException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException

	{	
		Lista filtroBT =filtro.filterBT(tipo, campo, min, max, Prima, filtrata);
		Lista filtroGLTE =filtro.filterGLTE(tipo, campo, min, Prima, filtrata);
		if((filtroBT.isEmpty())&&(filtroGLTE.isEmpty())) return new ResponseEntity ("Non esiste",HttpStatus.NOT_FOUND); //Se le liste sono vuote Non esiste 
		else if((!filtroBT.isEmpty())&&(tipo.equals("$bt"))) return new ResponseEntity <Lista> (filtroBT,HttpStatus.OK); //Se mette BT fa between
		else if((!filtroGLTE.isEmpty())&&((tipo.equals("$lte"))||(tipo.equals("$gte")))) return new ResponseEntity <Lista> (filtro.filterGLTE(tipo, campo, min, Prima, filtrata),HttpStatus.OK); //Se mette gte controlla se è maggiore, se mette lte controlla se è minore
		else return new ResponseEntity ("Immetti dei valori consoni",HttpStatus.BAD_REQUEST); //Se non è nessuna delle precedenti è una Bad Request

	}
	/**
	 * Questa GET richiama i metodi filterAND e filterOR per i {@link operazioni.Filtri} Logici.
	 * @param tipo Si chiede di inserire il tipo di {@link operazioni.Filtri} che si ha scelto tra $and o $or.
	 * @param tipo1 Si chiede di inserire il tipo della prima variabile, ovvero, l'attributo.
	 * @param campo1 Si chiede di inserire il valore del primo tipo scelto.
	 * @param tipo2 Si chiede di inserire il tipo della seconda variabile, ovvero, l'attributo.
	 * @param campo2 Si chiede di inserire il valore del secondo tipo scelto.
	 * @return filtrata  E' la {@link dataset.Lista} filtrata dai filtri Logici
	 * @throws JSONException Se c'e' un problema con il JSON
	 * @throws NoSuchMethodException Se il metodo che vado a cercare non esiste
	 * @throws SecurityException Se c'è stata una violazione nella sicurezza
	 * @throws IllegalAccessException Se si tenta di accedere ad un metodo la cui visibilità non è consentita
	 * @throws IllegalArgumentException Se il metodo ha passato un argomento non appropriato
	 * @throws InvocationTargetException Controlla le eccezioni che sono chiamate da un invoke method
	 */
	@GetMapping("/LogFilter") //Filtri logici

	public ResponseEntity LogFilter (@RequestParam String tipo, String tipo1, String campo1, String tipo2, String campo2) throws JSONException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		if((filtro.filterAND(tipo1, campo1, tipo2, campo2, Prima, filtrata).isEmpty())&&(filtro.filterOR(tipo1, campo1, tipo2, campo2, Prima, filtrata).isEmpty())) return new ResponseEntity ("Non esiste",HttpStatus.NOT_FOUND); //Se le liste sono vuote Non esiste
		else if((!filtro.filterAND(tipo1, campo1, tipo2, campo2, Prima, filtrata).isEmpty())&&(tipo.equals("$and"))) return new ResponseEntity <Lista> (filtro.filterAND(tipo1, campo1, tipo2, campo2, Prima, filtrata),HttpStatus.OK); //Cerca tutte le aziende che hanno 2 specifici valori
		else if((!filtro.filterOR(tipo1, campo1, tipo2, campo2, Prima, filtrata).isEmpty())&&(tipo.equals("$or"))) return new ResponseEntity <Lista> (filtro.filterOR(tipo1, campo1, tipo2, campo2, Prima, filtrata),HttpStatus.OK); //Cerca tutte le aziende che hanno o uno o l'altro tipo di valore
		else return new ResponseEntity ("Immetti dei valori consoni",HttpStatus.BAD_REQUEST); //Se non è nessuna delle precedenti è una Bad Request
	
		/*Lista filtroAND = filtro.filterAND(tipo1, campo1, tipo2, campo2, Prima, filtrata);
		Lista filtroOR = filtro.filterOR(tipo1, campo1, tipo2, campo2, Prima, filtrata);
		if((filtroAND.isEmpty())&&(filtroOR.isEmpty())) return new ResponseEntity ("Non esiste",HttpStatus.NOT_FOUND); //Se le liste sono vuote Non esiste
		else if((!filtroAND.isEmpty())&&(tipo.equals("$and"))) return new ResponseEntity <Lista> (filtroAND,HttpStatus.OK); //Cerca tutte le aziende che hanno 2 specifici valori
		else if((!filtroOR.isEmpty())&&(tipo.equals("$or"))) return new ResponseEntity <Lista> (filtroOR,HttpStatus.OK); //Cerca tutte le aziende che hanno o uno o l'altro tipo di valore
		else return new ResponseEntity ("Immetti dei valori consoni",HttpStatus.BAD_REQUEST); //Se non è nessuna delle precedenti è una Bad Request
	*/
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
		List<Statistics> StringStat =stats.StringStats(tipo, campo, Prima);
		List<Statistics> NumStat =stats.NumStats(tipo, campo, Prima);
		if((StringStat.isEmpty())&&(NumStat.isEmpty())) return new ResponseEntity ("Non esiste",HttpStatus.NOT_FOUND); //Se le liste sono vuote Non esiste
		else if(!(StringStat.isEmpty())&&((tipo.equals("preNorm"))||(tipo.equals("comune"))||(tipo.equals("provincia"))||(tipo.equals("tipo"))))	return new ResponseEntity (StringStat,HttpStatus.OK); //Se deve fare le statistiche per gli elementi unici
		else if(!(NumStat.isEmpty())&&((tipo.equals("telefono"))||(tipo.equals("fax"))||(tipo.equals("latitudine"))||(tipo.equals("longitudine"))))	return new ResponseEntity (NumStat,HttpStatus.OK); //Se deve fare le statistiche per numeri
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