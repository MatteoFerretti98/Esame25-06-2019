package progetto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



/**
 * @author Matteo Ferretti (s1083630@studenti.univpm.it), Angelo D'Agostino Bonomi (s1082444@studenti.univpm.it)
 * @version 1.0
 */

/**
 * Application - Main del progetto - Crea il network server grazie a Spring.
 */
@SpringBootApplication
public class EsameApplication {
	/**
	 * Questo è il costruttore di EsameApplication
	 */
	public EsameApplication() {
		
	}
/**
 * Questo è il main che fa partire il server Spring e che al suo interno 
 * abbiamo insierito anche il {@link DownloadDataset} che parsa e scarica la lista csv all'avvio
 * direttamente nella cartella del progetto.
 * @param args Parametro del main
 */
	public static void main(String[] args) {	
		DownloadDataset classe = new DownloadDataset();		//Serve per scaricare il Dataset all'avvio
		SpringApplication.run(EsameApplication.class, args);
	}
}
