package ISP;

import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import org.junit.jupiter.api.Test;

/**
 * 
 * @author Kemal Yavuz
 * 
 *
 */
public class CasualTest {
    
	@Test
	void constructorCasual() {
		Town testTown = new Town(4, 4);
		Casual casual = new Casual(testTown, 0, 0);
		
		assertEquals(casual.col, 0); // Checks whether the column value input in the constructor is received correctly.
		assertEquals(casual.row, 0); // Checks whether the row value input in the constructor is received correctly.
		assertEquals(casual.plain, testTown); // Checks whether the Town object input in the constructor is received correctly.
	}

    @Test
    public void testWho() {
    	Town testTown = new Town(4, 4);
        Casual casual = new Casual(testTown, 0, 0); // Creates a Casual object
        assertEquals(State.CASUAL, casual.who());   
    }
    
    @Test
    public void testNextUnchanged() throws FileNotFoundException {  
    	Town testTown = new Town("./CtoC.txt");
    	/*
    	 *  E E E E
    	 * (C)C O E
    	 *  C O E O
    	 *  C E E E
      	 */
    	assertEquals(testTown.grid[1][0].who(), State.CASUAL);
    	//Casual -> Casual
    	/*
    	 *  R C C C
    	 * (C)C E C
    	 *  C E C E
    	 *  C C C C
    	 */
    	// Because the TownCell in the location (1,0) not apply any of the rules it remains as Casual.
    	assertEquals(testTown.grid[1][0].next(testTown).who(), State.CASUAL);       
    }
    
    @Test
    public void testNextResellerNeighbor() throws FileNotFoundException {
        // Test the next method for the Casual class with a Reseller neighbor
    	Town testTown = new Town("./CtoO.txt");
    	/*
    	 *  O R O R
    	 *  E E(C)O
    	 *  E S O S
    	 *  E O R R
    	 */
    	assertEquals(testTown.grid[1][2].who(), State.CASUAL);
    	//Casual -> Outage
    	/*
    	 *  E E E E
    	 *  C C(O)E
    	 *  C O E O
    	 *  C E E E
      	 */
    	// Because the TownCell in the location (1,2) applies the rule of having 1 or more Reseller
    	// neighbor it becomes Outage.
    	assertEquals(testTown.grid[1][2].next(testTown).who(), State.OUTAGE);
    }
    
    @Test
    public void testNextCasualNeighbors() throws FileNotFoundException {
    	// Test the next method for the Casual class with 5 or more Casual neighbors
    	Town testTown = new Town("./CtoS2.txt");
    	/*
    	 *  R C C C
    	 *  C C E C
    	 *  C E(C)E
    	 *  C C C C
    	 */
    	assertEquals(testTown.grid[2][2].who(), State.CASUAL);   
    	//Casual -> Streamer
    	/*
    	 *  E R R R
    	 *  R O C C
    	 *  R R(S)R
    	 *  R R C R
    	 */
    	// Because the TownCell in the location (2,2) applies the rule of having 5 or more Casual
    	// neighbors it becomes a Streamer.
    	assertEquals(testTown.grid[2][2].next(testTown).who(), State.STREAMER);       
    }
    
    @Test
    public void testNextStreamerNeighbor() throws FileNotFoundException {
    	// Test the next method for the Casual class with a Streamer neighbor
    	Town testTown = new Town("./CtoS1.txt");
    	/*
    	 *  R C O E
         *  R S(C)C
         *  R S C E
         *  R C R E
    	 */
    	assertEquals(testTown.grid[1][2].who(), State.CASUAL); 
    	//Casual -> Streamer
    	/*
    	 *  E R E R
		 *  E R(S)C
		 *  E R O R
		 *  E R E R
    	 */
    	// Because the TownCell in the location (1,2) applies the rule of having a Streamer
    	// neighbor it becomes a Streamer.
    	assertEquals(testTown.grid[1][2].next(testTown).who(), State.STREAMER);       
    }
    
    @Test
    public void testNextOneOrLessEmptyOrOutageNeighbors() throws FileNotFoundException {
    	// Test the next method for the Casual class with one or less Empty or Outage neighbors
    	Town testTown = new Town("./CtoR.txt");
    	/*
    	 *  R(C)C C
    	 *  C C E C
    	 *  C E C E
    	 *  C C C C
    	 */
    	assertEquals(testTown.grid[0][1].who(), State.CASUAL);   
    	//Casual -> Reseller
    	/*
    	 *  E(R)R R
    	 *  R O C C
    	 *  R R S R
    	 *  R R C R
    	 */
    	// Because the TownCell in the location (0,1) applies the rule of having 1 or less Empty
    	// or Outage neighbors it becomes a Reseller.
    	assertEquals(testTown.grid[0][1].next(testTown).who(), State.RESELLER);       
    }
}