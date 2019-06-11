package Progetto;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
	@SuppressWarnings({ "rawtypes", "unchecked", "resource"  })
	public class MetaData {

		String cvsSplitBy = ";";
		JSONArray metadata=new JSONArray();
		public MetaData() {
			try {
			BufferedReader br = new BufferedReader(new FileReader("APL-AgenziaPerIlLavoro.csv")); //file da leggere
			Class alias = Class.forName("Progetto.Dati"); //Prendo la classe dove c'è il costruttore
			Constructor listaDati[] = alias.getConstructors();  //Ottiene la lista del costruttore
			Field listaAlias[] = alias.getDeclaredFields();			//Prendo la lista degli alias
			Class  types[] = listaDati[0].getParameterTypes();  //Prendo i types di tutti gli alias
			String line;
			line = br.readLine(); 							//Prende la prima riga del dataset per i SourceField
			String[] spazio = line.split(cvsSplitBy,10); 
			//inserisco i dati raccolti in un array json
			for (int i=0; i < (listaAlias.length)-1; i++)	//listaAlias.length-1 perchè abbiamo dichiarato anche
			{   											//la classe punto nel costruttore quindi ne serve una in meno
				JSONObject obj = new JSONObject();
				Field campoCorr = listaAlias[i];
				//crea un oggetto con i parametri alias type e nome a cui andiamo ad aggiungere i valori presi
				obj.put("alias", campoCorr.getName());
				obj.put("sourceField", spazio[i] );
				obj.put("type", types[i].getName());
				String types2=(String) obj.get("type");  		//ottiene il tipo dell'oggetto e serve per il controllo delle String
				if(types2.equals("java.lang.String")) obj.put("type","String");   //Controllo per le stringhe: se l'oggetto è java.lang.String 
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
