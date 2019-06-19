package Progetto;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
@SuppressWarnings("all")
public class TrovaDaPunto {
	public Lista lista = new Lista();

	public Lista Find(Float Lat, Float Lon, Float Radius, Container originale, Lista filtrata) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		filtrata.getList().clear();
		int size=originale.getSize();
		float raggio = (float) (Radius*(Math.pow(10, 3)));
		for(int i=0; i<size; i++) {
			Method u1 = lista.getDati(i).getPunto().getClass().getMethod("getLatitudine", null);
			Object v1 = u1.invoke(originale.getDati(i).getPunto(), null);
			Method u2 = lista.getDati(i).getPunto().getClass().getMethod("getLongitudine", null);
			Object v2 = u2.invoke(originale.getDati(i).getPunto(), null);
			float num = (float) 1.113;
			float rad1 = (float) (num*(Math.pow(10, 5))*(Math.abs((float)v1-Lat)));
			float rad2 = (float) (num*(Math.pow(10, 5))*(Math.abs((float)v2-Lon)));
			float rad = (float) Math.sqrt(Math.abs((Math.pow(rad2, 2))-(Math.pow(rad1, 2))));
			if(rad <= raggio) filtrata.getList().add(originale.getDati(i));
		}
		return filtrata;
	}
}
