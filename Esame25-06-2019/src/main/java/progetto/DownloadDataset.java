package progetto;
/**
 * @author Matteo Ferretti (s1083630@studenti.univpm.it), Angelo D'Agostino Bonomi (s1082444@studenti.univpm.it)
 * @version 1.0
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import dataset.Lista;
/**
 * E' la classe che scarica il file tramite URL del JSON.
 */
public class DownloadDataset {
		
		String url = "https://www.dati.gov.it/api/3/action/package_show?id=bbfed5c9-13f0-44ed-845f-094834963827"; //url del JSON
		File nomeFile = new File ("APL-AgenziaPerIlLavoro.csv");	//Nome del file da scaricare
		/**
		 * E' la classe che scarica il file tramite URL del JSON, trova il CSV, 
		 * e lo scarica sottoforma di file appunto csv con il nome "APL-AgenziaPerIlLavoro.csv".
		 */
		public DownloadDataset() { try {
			
			URLConnection openConnection = new URL(url).openConnection();		//Si connette all'url
			openConnection.addRequestProperty("User-Agent", "Google Chrome");	//Con l'utente "User-Agent" e "Google Chrome"
			InputStream InP = openConnection.getInputStream();					//Raccoglie il flusso dei dati
			
			 String data = "";
			 String line = "";
			 
			 try {
			   InputStreamReader ISP = new InputStreamReader( InP ); 
			   BufferedReader Buf = new BufferedReader( ISP );		//Grazie a buffer read legge il flusso dei dati
			   														//e li mette dentro a line che va ad incrementare
			   while ( ( line = Buf.readLine() ) != null ) {		//data in questo ciclo while
				   data+= line;
				   //System.out.println( "Contenuto del JSON: " +line );	//Nel caso vorresti vedere tutto il JSON in riga
			   }
			 } finally {
			   InP.close();		//dopo aver raccolto tutti i dati chiude il flusso
			 }
			JSONObject obj1 = (JSONObject) JSONValue.parseWithException(data);	//Inizia il parsing dei dati come JSON Object
			JSONObject obj2 = (JSONObject) (obj1.get("result"));				//Cerca nel JSON il "result" e lo apre
			JSONArray obj3 = (JSONArray) (obj2.get("resources"));				//Poi cerca tutti i resources tra le parentesi {} del result
			
			for(Object Ob1: obj3){									//Con questo ciclo for controlla ogni resources
			    if ( Ob1 instanceof JSONObject ) {					 
			        JSONObject Ob2 = (JSONObject)Ob1; 				
			        String format = (String)Ob2.get("format");		//e "scorrendo" controlla i formati e li mette dentro format
			        URL url1 = new URL ((String)Ob2.get("url"));	//idem per url, appena ne vede uno lo mette in url
			        //System.out.println(format + " | " + url1);			//Questo print serve se si vuole vedere ogni url contenuto nel JSON
			        if(format.equals("csv")) {						//Se il formato di uno di quei url è cvs (ovvero il file che cerchiamo noi)
			        	FileUtils.copyURLToFile(url1, nomeFile);	//lo scarica nella cartella del progetto
			        }
			    }
			}
			//Eccezioni in caso di parse sbagliato
		} catch (IOException | ParseException e) {e.printStackTrace();	//o di url non adatto
		} catch (Exception e) {e.printStackTrace();
		}
	}
}
