package Progetto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EsameApplication {

	public static void main(String[] args) {
		SpringApplication.run(EsameApplication.class, args);
		DownloadDataset classe = new DownloadDataset();
	//	classe.RichiamaClasse();
		
	}
}
