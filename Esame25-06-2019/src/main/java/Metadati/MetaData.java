package Metadati;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
@SuppressWarnings("all")
	public class MetaData {

		String cvsSplitBy = ";";
		JSONArray metadata=new JSONArray();
		public MetaData() {
			try {
			BufferedReader br = new BufferedReader(new FileReader("APL-AgenziaPerIlLavoro.csv")); //file da leggere
			Class alias1 = Class.forName("Progetto.Dati"); 		//Prendo la classe dove c'è il costruttore Dati
			Class alias2 = Class.forName("Progetto.Punto"); 	//Prendo la classe dove c'è il costruttore Punto
			Constructor listaDati[] = alias1.getConstructors();  	//Ottiene la lista del costruttore Dati
			Constructor listaPunto[] = alias2.getConstructors(); 	//Ottiene la lista del costruttore Punto
			Field listaAlias1[] = alias1.getDeclaredFields();		//Prendo la lista degli alias di Dati
			Field listaAlias2[] = alias2.getDeclaredFields();		//Prendo la lista degli alias di Punto
			Class  types1[] = listaDati[0].getParameterTypes();  	//Prendo i types di tutti gli alias di Dati
			Class  types2[] = listaPunto[1].getParameterTypes(); 	//Prendo i types di tutti gli alias di Punto
			String line;
			line = br.readLine(); 							//Prende la prima riga del dataset per i SourceField
			String[] spazio = line.split(cvsSplitBy,10); 
			//inserisco i dati raccolti in un array json
			for (int i=0; i < (listaAlias1.length)-1; i++)	//listaAlias.length-1 perchè abbiamo dichiarato anche
			{   											//la classe punto nel costruttore quindi ne serve una in meno
				JSONObject obj = new JSONObject();
				Field campoCorr = listaAlias1[i];
				//crea un oggetto con i parametri alias type e nome a cui andiamo ad aggiungere i valori presi
				obj.put("alias", campoCorr.getName());
				obj.put("sourceField", spazio[i] );
				obj.put("type", types1[i].getName());
				String tipo=(String) obj.get("type");  		//ottiene il tipo dell'oggetto e serve per il controllo delle String
				if(tipo.equals("java.lang.String")) obj.put("type","String");   //Controllo per le stringhe: se l'oggetto è java.lang.String 
				metadata.add(obj);   						//lo metto come String e aggiungo di volta in volta l'oggetto all'Array json
			}
			for (int i=0; i < (listaAlias2.length); i++)	//listaAlias.length-1 perchè abbiamo dichiarato anche
			{   											//la classe punto nel costruttore quindi ne serve una in meno
				JSONObject obj = new JSONObject();
				Field campoCorr = listaAlias2[i];
				//crea un oggetto con i parametri alias type e nome a cui andiamo ad aggiungere i valori presi
				obj.put("alias", campoCorr.getName());
				obj.put("sourceField", spazio[i+8] );
				obj.put("type", types2[i].getName());
				String tipo=(String) obj.get("type");  		//ottiene il tipo dell'oggetto e serve per il controllo delle String
				if(tipo.equals("java.lang.String")) obj.put("type","String");   //Controllo per le stringhe: se l'oggetto è java.lang.String 
				metadata.add(obj);   						//lo metto come String e aggiungo di volta in volta l'oggetto all'Array json
			}
			}catch (IOException e) {e.printStackTrace();
			}catch (ClassNotFoundException d) {d.printStackTrace();
			}
		}
		public JSONArray getMetadata() {
			return metadata;
		}
		public void setMetadata(JSONArray metadata) {
			this.metadata = metadata;
		}

	}
