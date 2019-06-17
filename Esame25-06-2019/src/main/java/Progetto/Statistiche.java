package Progetto;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
@SuppressWarnings("all")
public class Statistiche {
private Lista lista = new Lista();
/*
private int Count;
private float Media;
private float DevStand;
private float Max;
private float Min;
private Statistiche(int count, float media, float devStand, float max, float min) {
	super();
	Count=count;
	Media=media;
	DevStand=devStand;
	Max=max;
	Min=min;
}
public List<Statistiche> st = new ArrayList<>();
*/
   public ArrayList<String> NumeroCampi(Container originale, String campo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	   ArrayList<String> NumeroCampi=new ArrayList<>() ;
	
	   for(int j=0; j< originale.getSize();j++) {
				Method s= lista.getDati(j).getClass().getMethod("get"+campo.substring(0, 1).toUpperCase()+campo.substring(1), null);
				Object t = s.invoke(originale.getDati(j), null);

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
		 }
		 float media = somma/(float)NumeroCampi.size();
		 return media;
	}

	public int Count (Container originale,  String campo, String nome) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
	//public ResponseEntity Count(Container originale,  String campo, String nome) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		int count=0;
		int size=originale.getSize();
		for(int a=0; a<size; a++)
    	{
		Method s= lista.getDati(a).getClass().getMethod("get"+campo.substring(0, 1).toUpperCase()+campo.substring(1), null);
		Object t = s.invoke(originale.getDati(a), null);
		if(t.equals(nome)) count++;
    	}
		//return new ResponseEntity <Integer> (count,HttpStatus.NOT_FOUND);
		return count;
	}
	
	/*public int maxmin (Container originale, String zona, String tipo) {
		int size=originale.getSize()-1;
		int max = 0;
		int min = 0;
		int count[];
		int p=0; //provissoria
		
			for(int a=0; a<=size; a++)
		    {
				
				
		    }
		
		if(zona.equals("NA")) {}
		if(zona.equals("AV")) {}
		if(zona.equals("BN")) {}
		if(zona.equals("CE")) {}
		if(zona.equals("SA")) {}
		if(zona.equals("regione")) {}
		
		if(tipo.equals("max")) return max;
		else if(tipo.equals("min")) return min;
		else return p;
	}*/
	public float devStand(Container originale,String campo) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
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
	public ResponseEntity stats(Container originale,  String campo, String nome) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
				/////Creare una classe in base al campo (PreNom, comune, provincia, ecc.) dia in output o solo count o count e media o count media e dev. standard ecc.
		float a = this.devStand(originale, campo); 
		int i = this.Count(originale, campo, nome);
		//st.add(new Statistiche (i,a,a,a,a));

		String resp = "Count: " + i + " DevStand: " + a;
		 //return new ResponseEntity <List> (st,HttpStatus.NOT_FOUND);
		return new ResponseEntity <String> (resp,HttpStatus.NOT_FOUND);
	}
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
