package Progetto;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.demo.Student1;
@SuppressWarnings({ "unused" })
public class MetaData {
	
	private String Fid;
	private String PreNorm;
	private String Comune;
	private String Provincia;
	private String Indirizzo;
	private String Telefono;
	private String Fax;
	private String Tipo;
	private String Lat;
	private String Lon;
	
public MetaData(String fid, String preNorm, String comune, String provincia, String indirizzo, String telefono, String fax,
		String tipo,String lat, String lon){
	super();
	Fid = fid;
	PreNorm = preNorm;
	Comune = comune;
	Provincia = provincia;
	Indirizzo = indirizzo;
	Telefono = telefono;
	Fax = fax;
	Tipo = tipo;
	Lat= lat;
	Lon= lon;
}	

	enum nomiDati{
		Fid,
		PreProcessoNormalizzazione,
		Comune,
		Provincia,
		Indirizzo,
		Telefono,
		Fax,
		TipoEnte,
		Latitudine,
		Longitudine
	}
	
	private List<MetaData> data = new ArrayList<>();
	public HashMap<String,String> metadatalist(){
		HashMap<String, String> Mappa = new HashMap<String, String>();
		
		String line = "";
		String cvsSplitBy = ";";
		try (BufferedReader br = new BufferedReader(new FileReader("APL-AgenziaPerIlLavoro.csv"))) {
			line = br.readLine();
			String[] spazio = line.split(cvsSplitBy);
			data.add(new MetaData (spazio[0],spazio[1],spazio[2],spazio[3],spazio[4],spazio[5],spazio[6],spazio[7],spazio[8],spazio[9]));
			for(int i=0; i<=data.size(); i++) {
				Mappa.put("Alias",spazio[i]);
				Mappa.put("SourceField",nomiDati.values(i));
			//data.add(new MetaData (spazio[0],spazio[1],spazio[2],spazio[3],spazio[4],spazio[5],spazio[6],spazio[7],spazio[8],spazio[9]));
			
			}
    	 

			Mappa.put("Alias","Fid");
			Mappa.put("Alias","Fid");
			Mappa.put("Alias","Fid");
			
     } catch (IOException e) {
        e.printStackTrace();
        }
	return Mappa;
	}
}




/*import java.util.HashMap;

public class MyClass {
  public static void main(String[] args) {
    HashMap<String, String> capitalCities = new HashMap<String, String>();
    capitalCities.put("England", "London");
    capitalCities.put("Germany", "Berlin");
    capitalCities.put("Norway", "Oslo");
    capitalCities.put("USA", "Washington DC");
    System.out.println(capitalCities); 
  }
}*/
