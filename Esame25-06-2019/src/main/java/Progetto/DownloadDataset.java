package Progetto;

//import java.io.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
//import java.net.*;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

public class DownloadDataset {
	
	public void RichiamaClasse() {
		
		String url = "https://www.dati.gov.it/api/3/action/package_show?id=bbfed5c9-13f0-44ed-845f-094834963827";
		File nomeFile = new File ("APL-AgenziaPerIlLavoro.csv");
		try {
			
			URLConnection openConnection = new URL(url).openConnection();
			openConnection.addRequestProperty("User-Agent", "Google Chrome");
			InputStream InP = openConnection.getInputStream();
			
			 String data = "";
			 String line = "";
			 
			 try {
			   InputStreamReader ISP = new InputStreamReader( InP );
			   BufferedReader Buf = new BufferedReader( ISP );
			  
			   while ( ( line = Buf.readLine() ) != null ) {
				   data+= line;
				   //System.out.println( "Contenuto del JSON: " +line );
			   }
			 } finally {
			   InP.close();
			 }
			JSONObject obj1 = (JSONObject) JSONValue.parseWithException(data); 
			JSONObject obj2 = (JSONObject) (obj1.get("result"));
			JSONArray obj3 = (JSONArray) (obj2.get("resources"));
			
			for(Object Ob1: obj3){
			    if ( Ob1 instanceof JSONObject ) {
			        JSONObject Ob2 = (JSONObject)Ob1; 
			        String format = (String)Ob2.get("format");
			        URL url1 = new URL ((String)Ob2.get("url"));
			        if(format.equals("csv")) {
			        	FileUtils.copyURLToFile(url1, nomeFile);
			        }
			    }
			}
			System.out.println( "Il file è stato scaricato" );
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
