package operazioni;
/**
 * @author Matteo Ferretti (s1083630@studenti.univpm.it), Angelo D'Agostino Bonomi (s1082444@studenti.univpm.it)
 * @version 1.0
 */
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import dataset.Container;
import dataset.Lista;
/**
 * Fa le statistiche in base all'attriubuto inserito
 */
public class Statistiche {
	/**
	 * Questo è il costruttore di Statistiche
	 */
	public Statistiche() {
		
	}
	/**
	 * Inizializza una lista di dati come {@link dataset.Lista}.
	 */
private Lista lista = new Lista();
/**
 * Inizializza una lista di statistiche come {@link Statistics}.
 */
private List<Statistics> statistics = new ArrayList<>();
/**
 * Fa una lista con tutte le righe che hanno un certo campo.
 * @param originale E' la {@link dataset.Lista} non ancora filtrata
 * @param campo E' il campo su cui si va filtrare 
 * @return NumeroCampi E' la {@link dataset.Lista} filtrata
 * @throws NoSuchMethodException Se il metodo che vado a cercare non esiste
 * @throws SecurityException Se c'è stata una violazione nella sicurezza
 * @throws IllegalAccessException Se si tenta di accedere ad un metodo la cui visibilità non è consentita
 * @throws IllegalArgumentException Se il metodo ha passato un argomento non appropriato
 * @throws InvocationTargetException Controlla le eccezioni che sono chiamate da un invoke method
 */
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
 /**
  * Fa la media tra la {@link #Somma(Container, String)} e {@link #Count(Container, String, String)}
  * @param originale E' la {@link dataset.Lista} di tutti i dati
  * @param campo E' il campo su cui si va a fare la media
  * @return media E' la media
  * @throws NoSuchMethodException Se il metodo che vado a cercare non esiste
  * @throws SecurityException Se c'è stata una violazione nella sicurezza
  * @throws IllegalAccessException Se si tenta di accedere ad un metodo la cui visibilità non è consentita
  * @throws IllegalArgumentException Se il metodo ha passato un argomento non appropriato
  * @throws InvocationTargetException Controlla le eccezioni che sono chiamate da un invoke method
  */
	public float Media (Container originale, String campo) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		float media=0;
		try {
		double somma=0;
		ArrayList<String> NumeroCampi= this.NumeroCampi(originale, campo);
		 for(int i=0; i<NumeroCampi.size();i++) {
			 somma += this.Somma(originale, campo);
		 }
		 media = (float) ((float)somma/(float)NumeroCampi.size());
		}catch(NoSuchMethodError a) {a.printStackTrace();
		}catch (SecurityException b) {b.printStackTrace();
		}catch(IllegalAccessException c) {c.printStackTrace();
		}catch(IllegalArgumentException d) {d.printStackTrace();
		}catch(InvocationTargetException e) {e.printStackTrace();
		}
		 return media;
	}
	/**
	 * E' la somma di tutti i numeri di un certo campo (solo con quelli numerici tra telefono, fax, latitudine e longitudine)
	 * @param originale E' la {@link dataset.Lista} di tutti i dati
	 * @param campo E' il campo su cui si va a fare la somma
	 * @return somma E' la somma
	 * @throws NoSuchMethodException Se il metodo che vado a cercare non esiste
	 * @throws SecurityException Se c'è stata una violazione nella sicurezza
	 * @throws IllegalAccessException Se si tenta di accedere ad un metodo la cui visibilità non è consentita
	 * @throws IllegalArgumentException Se il metodo ha passato un argomento non appropriato
	 * @throws InvocationTargetException Controlla le eccezioni che sono chiamate da un invoke method
	 */
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
	/**
	 * Fa il conteggio di tutti gli elementi unici (ovviamnete con i numeri ha poco senso perchè sono spesso tutti unici).
	 * @param originale E' la {@link dataset.Lista} di tutti i dati
	 * @param tipo E' il tipo di attributo 
	 * @param nome E' il nome del tipo nel caso si voglia andare a contare un specifico gruppo di elementi di quell'attributo
	 * @return count Il numero di elementi unici.
	 * @throws NoSuchMethodException Se il metodo che vado a cercare non esiste
	 * @throws SecurityException Se c'è stata una violazione nella sicurezza
	 * @throws IllegalAccessException Se si tenta di accedere ad un metodo la cui visibilità non è consentita
	 * @throws IllegalArgumentException Se il metodo ha passato un argomento non appropriato
	 * @throws InvocationTargetException Controlla le eccezioni che sono chiamate da un invoke method
	 */
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
	/**
	 * Trova il massimo o il minimo tra tutti gli elementi numerici dell'attributo scelto
	 * @param originale E' la {@link dataset.Lista} di tutti i dati
	 * @param tipo E' il tipo di attributo  
	 * @param campo E' la scelta del return in base a ciò che si indica se max o min
	 * @return max se campo è uguale a max e min se tipo è uguale a min
	 * @throws NoSuchMethodException Se il metodo che vado a cercare non esiste
	 * @throws SecurityException Se c'è stata una violazione nella sicurezza
	 * @throws IllegalAccessException Se si tenta di accedere ad un metodo la cui visibilità non è consentita
	 * @throws IllegalArgumentException Se il metodo ha passato un argomento non appropriato
	 * @throws InvocationTargetException Controlla le eccezioni che sono chiamate da un invoke method
	 */
	public double maxmin (Container originale, String tipo, String campo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		int size=originale.getSize();
		double max = 0;
		double min = 999999999;
		double vuoto=0;
		try {
		Object t =null;
		double T=0;
		
			for(int a=0; a<size; a++)
		    {
				if((tipo.equals("latitudine"))||(tipo.equals("longitudine"))) {
			Method u= lista.getDati(a).getPunto().getClass().getMethod("get"+tipo.substring(0, 1).toUpperCase()+tipo.substring(1), null);
			t = u.invoke(originale.getDati(a).getPunto(), null);
			
		}
		else if((tipo.equals("telefono"))||(tipo.equals("fax"))){
			Method s= lista.getDati(a).getClass().getMethod("get"+tipo.substring(0, 1).toUpperCase()+tipo.substring(1), null);
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
		if(campo.equals("max")) return max;
		else if(campo.equals("min")) return min;
		else return vuoto;
	}
	/**
	  * Fa la deviazione Standard 
	  * @param originale E' la {@link dataset.Lista} di tutti i dati
	  * @param campo E' il campo su cui si va a fare la devStand
	  * @return devStand E' la Deviazione standard
	  * @throws NoSuchMethodException Se il metodo che vado a cercare non esiste
	  * @throws SecurityException Se c'è stata una violazione nella sicurezza
	  * @throws IllegalAccessException Se si tenta di accedere ad un metodo la cui visibilità non è consentita
	  * @throws IllegalArgumentException Se il metodo ha passato un argomento non appropriato
	  * @throws InvocationTargetException Controlla le eccezioni che sono chiamate da un invoke method
	 */
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
	/**
	 * Questa è la lista delle statistiche solo per i campi con numeri 
	 * @param tipo E' un controllo per vedere se l'attributo e' numerico e serve anche poi per le varie funzioni sopra elencate
	 * @param campo Serve per le funzioni sopra elencate come campo
	 * @param originale E' la {@link dataset.Lista} di tutti i dati
	 * @return statistics E' la lista di statistiche che ha come costruttore {@link Statistics}
	 * @throws NoSuchMethodException Se il metodo che vado a cercare non esiste
	 * @throws SecurityException Se c'è stata una violazione nella sicurezza
	 * @throws IllegalAccessException Se si tenta di accedere ad un metodo la cui visibilità non è consentita
	 * @throws IllegalArgumentException Se il metodo ha passato un argomento non appropriato
	 * @throws InvocationTargetException Controlla le eccezioni che sono chiamate da un invoke method
	 */
	public List<Statistics> NumStats(String tipo,String campo, Container originale) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		try {
		if((tipo.equals("telefono"))||(tipo.equals("fax"))||(tipo.equals("latitudine"))||(tipo.equals("longitudine"))) {
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
	/**
	 * Questa è la lista delle statistiche solo per i campi con Stringhe per calcolare gli elementi unici 
	 * @param tipo E' il tipo di attributo su cui fare il conteggio degli elementi unici
	 * @param campo Serve se si vuole fare il conteggio solo su un gruppo ristretto di elementi (es. solo provincia di napoli)
	 * @param originale E' la {@link dataset.Lista} di tutti i dati
	 * @return statistics E' la lista di statistiche che ha come costruttore {@link Statistics}
	 * @throws NoSuchMethodException Se il metodo che vado a cercare non esiste
	 * @throws SecurityException Se c'è stata una violazione nella sicurezza
	 * @throws IllegalAccessException Se si tenta di accedere ad un metodo la cui visibilità non è consentita
	 * @throws IllegalArgumentException Se il metodo ha passato un argomento non appropriato
	 * @throws InvocationTargetException Controlla le eccezioni che sono chiamate da un invoke method
	 */
	public List<Statistics> StringStats(String tipo,String campo, Container originale) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		try{
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

