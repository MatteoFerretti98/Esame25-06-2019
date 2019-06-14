package Progetto;

import java.util.ArrayList;

public class Statistiche {
private ArrayList<String> NumeroCampi=new ArrayList<>() ;
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
}
