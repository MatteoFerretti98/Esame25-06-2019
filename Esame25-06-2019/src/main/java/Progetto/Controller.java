package Progetto;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class Controller {
	@GetMapping("/data")
	public Lista getDati() {
		return new Lista();
	}

}
