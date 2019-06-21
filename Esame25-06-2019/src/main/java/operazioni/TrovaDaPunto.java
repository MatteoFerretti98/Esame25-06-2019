package operazioni;
/**
 * @author Matteo Ferretti (s1083630@studenti.univpm.it), Angelo D'Agostino Bonomi (s1082444@studenti.univpm.it)
 * @version 1.0
 */
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dataset.Container;
import dataset.Lista;
/**
 * Dato un punto(lat,long) e un raggio, controlla se nella circonferenza designata vi sono delle agenzie.
 */
public class TrovaDaPunto {
	/**
	 * Questo è il costruttore di TrovaDaPunto
	 */
	public TrovaDaPunto() {
		
	}
	/**
	 * Inizializza una lista di dati come {@link dataset.Lista}.
	 */
	public Lista lista = new Lista();
/**
 * Mette dentro filtrata tutte le agenzie entro un certo raggio.
 * @param Lat Latitudine del centro della circonferenza
 * @param Lon Longitudine del centro della circonferenza
 * @param Radius Raggio della circonferenza
 * @param originale E' la {@link dataset.Lista} non ancora filtrata
 * @param filtrata E' la {@link dataset.Lista} che dovra' essere filtrata
 * @return filtrata E' la {@link dataset.Lista} filtrata
 * @throws NoSuchMethodException Se il metodo che vado a cercare non esiste
 * @throws SecurityException Se c'è stata una violazione nella sicurezza
 * @throws IllegalAccessException Se si tenta di accedere ad un metodo la cui visibilità non è consentita
 * @throws IllegalArgumentException Se il metodo ha passato un argomento non appropriato
 * @throws InvocationTargetException Controlla le eccezioni che sono chiamate da un invoke method
 */
	public Lista Find(Float Lat, Float Lon, Float Radius, Container originale, Lista filtrata) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		try {
		filtrata.getList().clear();
		int size=originale.getSize();
		float raggio = (float) (Radius*(Math.pow(10, 3))); //fa si che raggio sia in chilometri(metri *10^3)
		for(int i=0; i<size; i++) {
			Method u1 = lista.getDati(i).getPunto().getClass().getMethod("getLatitudine", null); 
			Object v1 = u1.invoke(originale.getDati(i).getPunto(), null); //prende il valore della lat
			Method u2 = lista.getDati(i).getPunto().getClass().getMethod("getLongitudine", null);
			Object v2 = u2.invoke(originale.getDati(i).getPunto(), null); //prende il valore della long
			float num = (float) 1.113; //Dato preso da internet dalla formula per convertire i gradi decimali in metri
			float rad1 = (float) (num*(Math.pow(10, 5))*(Math.abs((float)v1-Lat))); //formula per convertire i gradi decimali in metri
			float rad2 = (float) (num*(Math.pow(10, 5))*(Math.abs((float)v2-Lon)));
			float rad = (float) Math.sqrt(Math.abs((Math.pow(rad2, 2))-(Math.pow(rad1, 2)))); //teorema di pitagora per trovare il raggio del campo seguente
			if(rad <= raggio) filtrata.getList().add(originale.getDati(i)); //se il raggio seguente è compreso nella circonferenza che ha messo l'utente mette la riga in filtrata
		}
		}catch(NoSuchMethodError a) {a.printStackTrace();
		}catch (SecurityException b) {b.printStackTrace();
		}catch(IllegalAccessException c) {c.printStackTrace();
		}catch(IllegalArgumentException d) {d.printStackTrace();
		}catch(InvocationTargetException e) {e.printStackTrace();
		}
		return filtrata;
	}
}
