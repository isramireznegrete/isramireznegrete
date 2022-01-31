package org.ranking.league;

public class RankingLeague {
	
	public static void main( String args[] ) { 
		
		switch( args.length ) {
			case 0:
				System.out.println( "Error, no data indicated please provide it" ); 
				break;
			case 1:
				//System.out.println( "Filename indicated or only a sample set of data" );
				if ( CSVReader.csvFileExist( args[0] ) )
					CSVReader.csvFileReader( args[0] );
				break;
			default :
				//System.out.println( "Fullset of data indicated" );
				CSVReader.csvCMDLineParser(args);
				break;
		}			
	}
	
	public static boolean isInteger(String numero){
	    try{
	        Integer.parseInt(numero);
	        return true;
	    }catch(NumberFormatException e){
	        return false;
	    }
	}
}
