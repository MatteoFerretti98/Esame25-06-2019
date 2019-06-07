package Progetto;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@SuppressWarnings({ "unused" })
@RestController
public class Controller {
	
	@GetMapping("/data") //stampa il JSON del dataset
	public Lista getDati() {
		return new Lista();
	}
	
	/*@RequestMapping("/metadata")
	public ResponseEntity<MetaData> getMeDa() {
		MetaData MeDa = new MetaData();
		//return new MetaData();
		return new ResponseEntity <MetaData> (MeDa,HttpStatus.NOT_FOUND);
	}*/

}
