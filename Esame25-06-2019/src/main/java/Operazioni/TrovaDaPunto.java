package Operazioni;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dataset.Container;
import dataset.Lista;

public class TrovaDaPunto {
	public Lista lista = new Lista();

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
