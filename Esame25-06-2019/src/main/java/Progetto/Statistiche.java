package Progetto;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Statistiche {
private ArrayList<String> NumeroCampi=new ArrayList<>() ;
private Lista lista = new Lista();
	public int ContoCampi(Container originale, String campo) {
		int counter=0;
		for(int i =0; i<originale.getSize();i++)
		if(campo.equals(originale.getTipo(i))) {
			counter++;
		}
		return counter;
	}

	public float Media (Container originale) {
		float somma=0;
		 for(int i=0; i< originale.getSize();i++) {
			for(int j=0; j< originale.getSize();j++)
			 if(NumeroCampi.isEmpty()) {
				 NumeroCampi.add(originale.getTipo(j));
			 }
			 else if(!(NumeroCampi.contains(originale.getTipo(j)))) {
				 NumeroCampi.add(originale.getTipo(j));
			 }
		 }
		 for(int i=0; i<NumeroCampi.size();i++) {
			 somma += this.ContoCampi(originale, NumeroCampi.get(i));
		 }
		 
		 return (somma/(float)NumeroCampi.size());
	}
	
	public int Count (Container originale, String campo) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		int count=0;
		int size=originale.getSize()-1;
		for(int a=0; a<=size; a++)
    	{
		//Per tutte gli altri get
		Method s= lista.getDati(a).getClass().getMethod("get"+campo.substring(0, 1).toUpperCase()+campo.substring(1), null);
		System.out.println(s);
		Object t = s.invoke(originale.getDati(a), null);
		System.out.println(t);
		
		//Per il getLatitudine e getLongitudine
		Method u= lista.getDati(a).getPunto().getClass().getMethod("get"+campo.substring(0, 1).toUpperCase()+campo.substring(1), null);
		System.out.println(u);
		Object v = u.invoke(originale.getDati(a).getPunto(), null);
		System.out.println(v);
    	}
		return count;
	}
	
}
