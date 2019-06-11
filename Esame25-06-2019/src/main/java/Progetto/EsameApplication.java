package Progetto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SuppressWarnings("unused")

public class EsameApplication {

	public static void main(String[] args) {	
		DownloadDataset classe = new DownloadDataset();		//Serve per scaricare il Dataset all'avvio
		SpringApplication.run(EsameApplication.class, args);
	}
}
