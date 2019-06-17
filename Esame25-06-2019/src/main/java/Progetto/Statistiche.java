package Progetto;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
@SuppressWarnings("all")
public class Statistiche {
	/*public List<Statistiche> getStatistiche() {
		return statistiche;
	}
	public void setStatistiche(List<Statistiche> statistiche) {
		this.statistiche = statistiche;
	}*/
private Lista lista = new Lista();
public List<Statistics> statistics = new ArrayList<>();

public ArrayList<String> NumeroCampi(Container originale, String campo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		ArrayList<String> NumeroCampi=new ArrayList<>() ;
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
				 NumeroCampi.add((String) t);
			 }
			 else if(!(NumeroCampi.contains(t))&&(t!=" ")) {
				 NumeroCampi.add((String)t);
			 }
		 }	   
   return NumeroCampi;
   }
 
	public float Media (Container originale, String campo) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		float somma=0;
		/* for(int i=0; i< originale.getSize();i++) {
			for(int j=0; j< originale.getSize();j++)
			 if(NumeroCampi.isEmpty()) {
				 NumeroCampi.add(originale.getTipo(j));
			 }
			 else if(!(NumeroCampi.contains(originale.getTipo(j)))) {
				 NumeroCampi.add(originale.getTipo(j));
			 }
		 }*/
		ArrayList<String> NumeroCampi= this.NumeroCampi(originale, campo);
		 for(int i=0; i<NumeroCampi.size();i++) {
			 somma += this.Count(originale, campo, NumeroCampi.get(i));
			 //somma = this.Somma(originale, campo);
		 }
		 float media = somma/(float)NumeroCampi.size();
		 return media;
	}
	
	public float Somma (Container originale, String campo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		float somma = 0;
		float T=0;
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
		T = ((Double)t).floatValue();
		somma=somma+T;
    	}
		return somma;
	}
	
	public int Count (Container originale,  String tipo, String nome) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
	//public ResponseEntity Count(Container originale,  String campo, String nome) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		int count=0;
		int size=originale.getSize();
		for(int a=0; a<size; a++)
    	{
		Method s= lista.getDati(a).getClass().getMethod("get"+tipo.substring(0, 1).toUpperCase()+tipo.substring(1), null);
		Object t = s.invoke(originale.getDati(a), null);
		if(t.equals(nome)) count++;
    	}
		//return new ResponseEntity <Integer> (count,HttpStatus.NOT_FOUND);
		return count;
	}
	
	public float maxmin (Container originale, String campo, String tipo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		int size=originale.getSize();
		float max = 0;
		float min = 0;
		Object t =null;
		float T=0;
		//int count[];
		float p=0; //provissoria
		
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
		T = ((Number)t).floatValue();
		if(T>max) max=T;
		if(T<min) min=T;
		    }
		
		/*if(zona.equals("NA")) {}
		if(zona.equals("AV")) {}
		if(zona.equals("BN")) {}
		if(zona.equals("CE")) {}
		if(zona.equals("SA")) {}
		if(zona.equals("regione")) {}*/
		
		if(tipo.equals("max")) return max;
		else if(tipo.equals("min")) return min;
		else return p;
	}
	
	public float DevStand(Container originale,String campo) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		float media = this.Media(originale, campo);
		float somma=0;
		float var=0;
		ArrayList<String> NumeroCampi= this.NumeroCampi(originale, campo);
		for (int i=0;i<NumeroCampi.size();i++) {
			somma+=Math.pow(this.Count(originale, campo, NumeroCampi.get(i))-media, 2);
		}
		var=somma/(NumeroCampi.size());
		return (float) Math.sqrt(var);
	}

	/*public ResponseEntity stats(Container originale,  String campo, String nome) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
			/////Creare una classe in base al campo (PreNom, comune, provincia, ecc.) dia in output o solo count o count e media o count media e dev. standard ecc.
		statistics.clear();
		float a = this.devStand(originale, campo); 
		int i = this.Count(originale, campo, nome);
		statistics.add(new Statistics (i,a,a,a,a));

		//String resp = "Count: " + i + " DevStand: " + a;
		return new ResponseEntity <List> (statistics,HttpStatus.OK);
		//return new ResponseEntity <String> (resp,HttpStatus.NOT_FOUND);
	}*/
	
	public List<Statistics> NumStats(String tipo,String campo, Container originale,Lista filtrata) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		filtrata.getList().clear();
		if(tipo.equals("telefono")) {
			filtrata.getList().clear();
			int count = this.Count(originale, campo, tipo);
			float media = this.Media(originale, tipo);
			float devStand = this.DevStand(originale, tipo); 
			float somma = this.Somma(originale, campo);
			float max = this.maxmin(originale, campo, "max");
			float min = this.maxmin(originale, campo, "min");
			
			statistics.add(new Statistics (tipo, count, media, devStand, media, media));
		}
		else if(tipo.equals("fax")) {
			filtrata.getList().clear();
			int count = this.Count(originale, campo, tipo);
			float media = this.Media(originale, tipo);
			float devStand = this.DevStand(originale, tipo); 
			float somma = this.Somma(originale, campo);
			float max = this.maxmin(originale, campo, "max");
			float min = this.maxmin(originale, campo, "min");
			
			statistics.add(new Statistics (tipo, count, media, devStand, media, media));
		}
		else if(tipo.equals("latitudine")) {
			filtrata.getList().clear();
			int count = this.Count(originale, campo, tipo);
			float media = this.Media(originale, tipo);
			float devStand = this.DevStand(originale, tipo); 
			float somma = this.Somma(originale, campo);
			float max = this.maxmin(originale, campo, "max");
			float min = this.maxmin(originale, campo, "min");
			
			statistics.add(new Statistics (tipo, count, media, devStand, media, media));
		}
		else if(tipo.equals("longitudine")) {
			filtrata.getList().clear();
			int count = this.Count(originale, campo, tipo);
			float media = this.Media(originale, tipo);
			float devStand = this.DevStand(originale, tipo);
			float somma = this.Somma(originale, campo);
			float max = this.maxmin(originale, campo, "max");
			float min = this.maxmin(originale, campo, "min");
			
			statistics.add(new Statistics (tipo, count, media, devStand, media, media));
		}
		return statistics;
	}
	
	public List<Statistics> StringStats(String tipo,String campo, Container originale,Lista filtrata) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		filtrata.getList().clear();
		if((tipo.equals("preNorm"))||(tipo.equals("comune"))||(tipo.equals("provincia"))||(tipo.equals("indirizzo"))||(tipo.equals("tipo"))) {
			
			int count = this.Count(originale, tipo, campo);
			//float media = this.Media(originale, tipo);
			//float devStand = this.DevStand(originale, tipo); 
			
			statistics.add(new Statistics (tipo+" "+campo, count, 0, 0, 0, 0));
		}
		return statistics;
	}
	
	/*public ArrayList<Statistiche> stats2(Container originale,  String campo, String nome) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		/////Creare una classe in base al campo (PreNom, comune, provincia, ecc.) dia in output o solo count o count e media o count media e dev. standard ecc.
	ArrayList<Statistiche> s = new ArrayList<>();
	float a = this.devStand(originale, campo); 
	int i = this.Count(originale, campo, nome);
	statistics.add(new Statistics (i,a,a,a,a));

	//String resp = "Count: " + i + " DevStand: " + a;
	return statistics;
	//return new ResponseEntity <String> (resp,HttpStatus.NOT_FOUND);
}*/
}


		/*
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
		*/
