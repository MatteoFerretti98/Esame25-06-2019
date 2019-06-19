package Operazioni;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import dataset.Container;
import dataset.Lista;
@SuppressWarnings("all")
public class Statistiche {
private Lista lista = new Lista();
private List<Statistics> statistics = new ArrayList<>();

public ArrayList<String> NumeroCampi(Container originale, String campo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	ArrayList<String> NumeroCampi=new ArrayList<>() ;
	try {	
	
		Object t =null;
	    for(int j=0; j< originale.getSize();j++) {
	    	if((campo.equals("latitudine"))||(campo.equals("longitudine"))) {
				Method u= lista.getDati(j).getPunto().getClass().getMethod("get"+campo.substring(0, 1).toUpperCase()+campo.substring(1), null);
				t = u.invoke(originale.getDati(j).getPunto(), null);
			}
			else {
				Method s= lista.getDati(j).getClass().getMethod("get"+campo.substring(0, 1).toUpperCase()+campo.substring(1), null);
				t = s.invoke(originale.getDati(j), null);
			}
			 if(NumeroCampi.isEmpty()) {
				 String T = t.toString();
				 NumeroCampi.add(T);
			 }
			 else if(!(NumeroCampi.contains(t))&&(t!=" ")) {
				 String T = t.toString();
				 NumeroCampi.add(T);
			 }
		 }
	}catch(NoSuchMethodError a) {a.printStackTrace();
	}catch (SecurityException b) {b.printStackTrace();
	}catch(IllegalAccessException c) {c.printStackTrace();
	}catch(IllegalArgumentException d) {d.printStackTrace();
	}catch(InvocationTargetException e) {e.printStackTrace();
	}
   return NumeroCampi;
   }
 
	public float Media (Container originale, String campo) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		float media=0;
		try {
		float somma=0;
		ArrayList<String> NumeroCampi= this.NumeroCampi(originale, campo);
		 for(int i=0; i<NumeroCampi.size();i++) {
			 somma += this.Count(originale, campo, NumeroCampi.get(i));
		 }
		 media = (float) (this.Somma(originale, campo)/(float)NumeroCampi.size());
		}catch(NoSuchMethodError a) {a.printStackTrace();
		}catch (SecurityException b) {b.printStackTrace();
		}catch(IllegalAccessException c) {c.printStackTrace();
		}catch(IllegalArgumentException d) {d.printStackTrace();
		}catch(InvocationTargetException e) {e.printStackTrace();
		}
		 return media;
	}
	
	public double Somma (Container originale, String campo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		double somma = 0;
		try {
		double T=0;
		Object t =null;
		int size=originale.getSize();
		for(int a=0; a<size; a++)
    	{
		if((campo.equals("latitudine"))||(campo.equals("longitudine"))) {
			Method u= lista.getDati(a).getPunto().getClass().getMethod("get"+campo.substring(0, 1).toUpperCase()+campo.substring(1), null);
			t = u.invoke(originale.getDati(a).getPunto(), null);
		}
		else {
			Method s= lista.getDati(a).getClass().getMethod("get"+campo.substring(0, 1).toUpperCase()+campo.substring(1), null);
			t = s.invoke(originale.getDati(a), null);
		}
		T = ((Number)t).doubleValue();
		somma=somma+T;
    	}
		}catch(NoSuchMethodError a) {a.printStackTrace();
		}catch (SecurityException b) {b.printStackTrace();
		}catch(IllegalAccessException c) {c.printStackTrace();
		}catch(IllegalArgumentException d) {d.printStackTrace();
		}catch(InvocationTargetException e) {e.printStackTrace();
		}
		return somma;
	}
	
	public int Count (Container originale,  String tipo, String nome) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		int count=0;
		try {
		Object t =null;
		int size=originale.getSize();
		for(int a=0; a<size; a++)
    	{
			if((tipo.equals("latitudine"))||(tipo.equals("longitudine"))) {
				Method u= lista.getDati(a).getPunto().getClass().getMethod("get"+tipo.substring(0, 1).toUpperCase()+tipo.substring(1), null);
				t = u.invoke(originale.getDati(a).getPunto(), null);
				count++;
			}
			else if((tipo.equals("fax"))||(tipo.equals("telefono"))) {
				Method s= lista.getDati(a).getClass().getMethod("get"+tipo.substring(0, 1).toUpperCase()+tipo.substring(1), null);
				t = s.invoke(originale.getDati(a), null);
				count++;
			}
			else{
				Method s= lista.getDati(a).getClass().getMethod("get"+tipo.substring(0, 1).toUpperCase()+tipo.substring(1), null);
				t = s.invoke(originale.getDati(a), null);
				if(t.equals(nome)) count++;
	    	}
		}
		}catch(NoSuchMethodError a) {a.printStackTrace();
		}catch (SecurityException b) {b.printStackTrace();
		}catch(IllegalAccessException c) {c.printStackTrace();
		}catch(IllegalArgumentException d) {d.printStackTrace();
		}catch(InvocationTargetException e) {e.printStackTrace();
		}
		return count;
	}
	
	public double maxmin (Container originale, String campo, String tipo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		int size=originale.getSize();
		double max = 0;
		double min = 999999999;
		double vuoto=0;
		try {
		Object t =null;
		double T=0;
		
			for(int a=0; a<size; a++)
		    {
				if((campo.equals("latitudine"))||(campo.equals("longitudine"))) {
			Method u= lista.getDati(a).getPunto().getClass().getMethod("get"+campo.substring(0, 1).toUpperCase()+campo.substring(1), null);
			t = u.invoke(originale.getDati(a).getPunto(), null);
			
		}
		else if((campo.equals("telefono"))||(campo.equals("fax"))){
			Method s= lista.getDati(a).getClass().getMethod("get"+campo.substring(0, 1).toUpperCase()+campo.substring(1), null);
			t = s.invoke(originale.getDati(a), null);
		}
		T = ((Number)t).doubleValue();
		if(T>max) max=T;
		if((T<min)&&(T!=0)) min=T;
		    }
		}catch(NoSuchMethodError a) {a.printStackTrace();
		}catch (SecurityException b) {b.printStackTrace();
		}catch(IllegalAccessException c) {c.printStackTrace();
		}catch(IllegalArgumentException d) {d.printStackTrace();
		}catch(InvocationTargetException e) {e.printStackTrace();
		}
		if(tipo.equals("max")) return max;
		else if(tipo.equals("min")) return min;
		else return vuoto;
	}
	
	public float DevStand(Container originale,String campo) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		float devStand=0;
		try {
		float media = this.Media(originale, campo);
		double somma=0;
		float var=0;
		ArrayList<String> NumeroCampi= this.NumeroCampi(originale, campo);
		for (int i=0;i<NumeroCampi.size();i++) {
			somma+=Math.pow(this.Count(originale, campo, NumeroCampi.get(i))-media, 2);
		}
		var=(float) (somma/(NumeroCampi.size()));
		devStand=(float) Math.sqrt(var);
		}catch(NoSuchMethodError a) {a.printStackTrace();
		}catch (SecurityException b) {b.printStackTrace();
		}catch(IllegalAccessException c) {c.printStackTrace();
		}catch(IllegalArgumentException d) {d.printStackTrace();
		}catch(InvocationTargetException e) {e.printStackTrace();
		}
		return devStand;
	}
	
	public List<Statistics> NumStats(String tipo,String campo, Container originale,Lista filtrata) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		try {
		filtrata.getList().clear();
		if((tipo.equals("telefono"))||(tipo.equals("fax"))||(tipo.equals("latitudine"))||(tipo.equals("longitudine"))) {
			filtrata.getList().clear();
			int count = this.Count(originale, tipo, campo);
			float media = this.Media(originale, tipo);
			float devStand = this.DevStand(originale, tipo); 
			double somma = this.Somma(originale, tipo);
			double max = this.maxmin(originale, tipo, "max");
			double min = this.maxmin(originale, tipo, "min");
			statistics.clear();
			statistics.add(new Statistics (tipo, count, somma, media, devStand, max, min));
			if(count==0) statistics.clear();//serve nel caso non ci sia nessun campo all'interno del tipo selezionato
		}
		}catch(NoSuchMethodError a) {a.printStackTrace();
		}catch (SecurityException b) {b.printStackTrace();
		}catch(IllegalAccessException c) {c.printStackTrace();
		}catch(IllegalArgumentException d) {d.printStackTrace();
		}catch(InvocationTargetException e) {e.printStackTrace();
		}
		return statistics;
	}
	
	public List<Statistics> StringStats(String tipo,String campo, Container originale,Lista filtrata) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		try{
		filtrata.getList().clear();
		if((tipo.equals("preNorm"))||(tipo.equals("comune"))||(tipo.equals("provincia"))||(tipo.equals("indirizzo"))||(tipo.equals("tipo"))) {
			
			int count = this.Count(originale, tipo, campo);
			statistics.clear();
			statistics.add(new Statistics (tipo+" "+campo, count, 0, 0, 0, 0, 0));
			if(count==0) statistics.clear();//serve nel caso non ci sia nessun campo all'interno del tipo selezionato
		}
		}catch(NoSuchMethodError a) {a.printStackTrace();
		}catch (SecurityException b) {b.printStackTrace();
		}catch(IllegalAccessException c) {c.printStackTrace();
		}catch(IllegalArgumentException d) {d.printStackTrace();
		}catch(InvocationTargetException e) {e.printStackTrace();
		}
		return statistics;
	}
}
