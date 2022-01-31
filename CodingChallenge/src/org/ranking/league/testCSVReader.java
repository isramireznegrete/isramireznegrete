package org.ranking.league;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class testCSVReader {

	@Test
	void testCsvFileReader() {
				
		String expectedResult = 
				  "1. Tarantulas, 6 pts\r\n"
				+ "2. Lions, 5 pts\r\n"
				+ "3. FC Awesome, 4 pts\r\n"
				+ "4. Developers team, 3 pts\r\n"
				+ "5. Machines, 3 pts\r\n"
				+ "6. Locos, 3 pts\r\n"
				+ "7. Snakes, 1 pts\r\n"
				+ "8. Grouches, 0 pts\r\n"
				+ "9. Testers team, 0 pts\r\n"
				+ "";

		String realResult = (new CSVReader()).csvFileReader( "/fileCSV.txt" );
		
		assertEquals(expectedResult.toString(), realResult.toString() );
	}

	@Test
	void testCsvFileExist() {
		String sFile = "/fileCSV.txt";
		assertTrue( ( new CSVReader().csvFileExist( sFile )) );
	}

}
