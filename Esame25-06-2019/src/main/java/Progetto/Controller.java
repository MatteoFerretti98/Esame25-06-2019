package Progetto;

import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@SuppressWarnings({ "unused" })
@RestController
public class Controller {
	
	@GetMapping("/data") //stampa il JSON del dataset
	public Lista getDati() throws FileNotFoundException, IOException{
		return new Lista();
	}
	
	@GetMapping("/metadata")
	public MetaData GetMetadata() throws FileNotFoundException, IOException, ClassNotFoundException
	{
		return new MetaData();
	}
}
