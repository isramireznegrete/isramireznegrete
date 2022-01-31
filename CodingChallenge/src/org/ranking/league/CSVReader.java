package org.ranking.league;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class CSVReader {
	
	/**
	 * @param pathToCsv
	 * @return
	 * 
	 * Lions 3, Snakes 3 
	 * Tarantulas 1, FC Awesome 0 
	 * Lions 1, FC Awesome 1 
	 * Tarantulas 3, Snakes 1 
	 * Lions 4, Grouches 0
	 * Tarantulas 8, Locos 11
	 * Grouches 3, FC Awesome 5
	 * testers 0, Machines 2
	 * Machines 0, developers 1
	 */
	@SuppressWarnings("deprecation")
	public static String csvFileReader( String pathToCsv ) {
		
		String row = "";
		
		try {
			String[] data = null ;
			
			HashMap <String,Integer> mapTeams = new HashMap<String, Integer>();
			BufferedReader csvReader = new BufferedReader(new FileReader(pathToCsv));
			
			String  fKey   = "";
			Integer fValue = 0;
			
			String fKeyLocal = "";
			String fKeyVisit = "";
			
			Integer fValueLocal = 0;
			Integer fValueVisit = 0;
			
			while ((row = csvReader.readLine()) != null) {				 
				data = row.split(",");
				int teamLocation = 1;
				int points = 0;
				
				for ( String dato : data ) {						
					fKey   = dato.trim().substring( 0, (dato.trim()).lastIndexOf( " " ) ).trim();
					fValue = new Integer ( dato.trim().substring( (dato.trim()).lastIndexOf( " " ) + 1 )) ;

					if ( teamLocation == 1 ) {
						fKeyLocal   =  fKey  ;
						fValueLocal = fValue ;
						teamLocation++;
					}
					else {
						fKeyVisit   = fKey;
						fValueVisit = fValue ;
					}
				}

				if ( teamLocation > 1 ){

					if ( fValueLocal.intValue() < fValueVisit.intValue() ) {
						if ( mapTeams.containsKey( fKeyVisit )) {
							points = ( mapTeams.get(fKeyVisit) ) + 3 ;
							mapTeams.put( fKeyVisit, ( points  ) );
						}
						else {
							mapTeams.put(fKeyVisit, 3 );
						}
						if ( !mapTeams.containsKey( fKeyLocal )) {
							mapTeams.put(fKeyLocal, 0 );
						}
					}
					if ( fValueLocal.intValue() > fValueVisit.intValue() ) {
						if ( mapTeams.containsKey( fKeyLocal )) {
							points = ( mapTeams.get(fKeyLocal) ) + 3 ;
							mapTeams.put( fKeyLocal, ( points  ) );
						}
						else {
								mapTeams.put(fKeyLocal, 3 );
						}
						if ( !mapTeams.containsKey( fKeyVisit )) {
							mapTeams.put( fKeyVisit, 0 );
						}
					}
					if ( fValueLocal.intValue() == fValueVisit.intValue() ) {
						fKey =  fKeyLocal ;
						if ( mapTeams.containsKey( fKey )) {
							points = ( mapTeams.get(fKey) ) + 1 ;
							mapTeams.put( fKey, ( points  ) );
						}
						else {
								mapTeams.put( fKey, 1 );
						}
					}
					if ( fValueLocal.intValue() == fValueVisit.intValue() ) {
						fKey =  fKeyVisit ;
						if ( mapTeams.containsKey( fKey )) {
								points = ( mapTeams.get(fKey) ) + 1 ;
								mapTeams.put( fKey, ( points  ) );
						}
						else {
								mapTeams.put( fKey, 1 );
						}
					}
				points = 0;
				teamLocation = 1;
				}				
			}
						
			@SuppressWarnings("rawtypes")
			List<Entry<String, Integer>> finalList = new ArrayList(mapTeams.entrySet());
			finalList.sort( Entry.comparingByValue() );
			
			ListIterator lIterator = finalList.listIterator( finalList.size());
			
			int tablePosition = 1;
			while ( lIterator.hasPrevious() ){
				Object element = lIterator.previous();
				System.out.println( tablePosition++ + ". " + element.toString().replace("=", ", " ) + " pts" );
			}

			csvReader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return row;
	}
	
	/**
	 * @param args
	 * 
	 * Lions 3, Snakes 3 
	 * Tarantulas 1, "FC Awesome" 0 
	 * Lions 1, "FC Awesome" 1 
	 * Tarantulas 3, Snakes 1 
	 * Lions 4, Grouches 0
	 * Tarantulas 8, Locos 11
	 * Grouches 3, "FC Awesome" 5
	 * "testers team" 0, Machines 2
	 * Machines 0, "developers team" 1
	 */
	public static void csvCMDLineParser(String[] args) {
		HashMap <String,Integer> mapTeams = new HashMap<String, Integer>();

		int teamLocation = 1;
		
		String  fKey   = "";
		Integer fValue = 0;
		
		String fKeyLocal = "";
		String fKeyVisit = "";
		
		Integer fValueLocal = 0;
		Integer fValueVisit = 0;

		int i = 0;
		int points = 0;

		while ( i < args.length ) {
			
			for ( int j = 0; j <= 3; j++ ) {
				if ( j == 0 ) { fKeyLocal   = args[i];
				i++;}
				if ( j == 1 ) {fValueLocal = new Integer( args[i].substring(0, (args[i].length() - 1) ) ); 
				i++;}						
				if ( j == 2 ) {fKeyVisit   = args[i]; 
				i++;}			
				if ( j == 3 ) {fValueVisit = new Integer( args[i] ); 
				i++;}
			}
			teamLocation++;

			if ( teamLocation > 1 ){

				if ( fValueLocal.intValue() < fValueVisit.intValue() ) {
					if ( mapTeams.containsKey( fKeyVisit )) {
						points = ( mapTeams.get(fKeyVisit) ) + 3 ;
						mapTeams.put( fKeyVisit, ( points  ) );
					}
					else {
						mapTeams.put(fKeyVisit, 3 );
					}
					if ( !mapTeams.containsKey( fKeyLocal )) {
						mapTeams.put(fKeyLocal, 0 );
					}
				}
				if ( fValueLocal.intValue() > fValueVisit.intValue() ) {
					if ( mapTeams.containsKey( fKeyLocal )) {
						points = ( mapTeams.get(fKeyLocal) ) + 3 ;
						mapTeams.put( fKeyLocal, ( points  ) );
					}
					else {
							mapTeams.put(fKeyLocal, 3 );
					}
					if ( !mapTeams.containsKey( fKeyVisit )) {
						mapTeams.put( fKeyVisit, 0 );
					}
				}
				if ( fValueLocal.intValue() == fValueVisit.intValue() ) {
					fKey =  fKeyLocal ;
					if ( mapTeams.containsKey( fKey )) {
						points = ( mapTeams.get(fKey) ) + 1 ;
						mapTeams.put( fKey, ( points  ) );
					}else {
						mapTeams.put( fKey, 1 );
					}
				}
				if ( fValueLocal.intValue() == fValueVisit.intValue() ) {
					fKey =  fKeyVisit ;
					if ( mapTeams.containsKey( fKey )) {
						points = ( mapTeams.get(fKey) ) + 1 ;
						mapTeams.put( fKey, ( points  ) );
					}
					else {
						mapTeams.put( fKey, 1 );
					}
				}
			points = 0;
			teamLocation = 1;
			}
 
			fValue = 0;
		}

		@SuppressWarnings("rawtypes")
		List<Entry<String, Integer>> finalList = new ArrayList(mapTeams.entrySet());
		finalList.sort( Entry.comparingByValue() );
		
		ListIterator lIterator = finalList.listIterator( finalList.size());
		
		int tablePosition = 1;
		while ( lIterator.hasPrevious() ){
			Object element = lIterator.previous();
			System.out.println( tablePosition++ + ". " + element.toString().replace("=", ", " ) + " pts" );
		}

	}
	
	public static boolean csvFileExist( String pathToCsv ){
		try {
			FileReader f = new FileReader((pathToCsv));
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;		
	}
 
}


