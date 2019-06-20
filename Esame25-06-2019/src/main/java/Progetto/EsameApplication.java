package Progetto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

/**
 * {@link EsameApplication} Application per far partire il server spring dove vi è anche il Main
 * @author Matteo Ferretti (s1083630@studenti.univpm.it), Angelo D'Agostino Bonomi (s1082444@studenti.univpm.it)
 * @version 1.0
 */

public class EsameApplication {

	public static void main(String[] args) {	
		DownloadDataset classe = new DownloadDataset();		//Serve per scaricare il Dataset all'avvio
		SpringApplication.run(EsameApplication.class, args);
	}
}
