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
public class StreamerTest {

	@Test
	void constructorStreamer() {
		Town testTown = new Town(4, 4);
		Streamer streamer = new Streamer(testTown, 0, 0);
		
		assertEquals(streamer.col, 0); // Checks whether the column value input in the constructor is received correctly.
		assertEquals(streamer.row, 0); // Checks whether the row value input in the constructor is received correctly.
		assertEquals(streamer.plain, testTown); // Checks whether the Town object input in the constructor is received correctly.
	}
	
    @Test
    public void testWho() {
    	Town testTown = new Town(4, 4);
        Streamer streamer = new Streamer(testTown, 0, 0); // Creates a Streamer object
        assertEquals(State.STREAMER, streamer.who());
    }

    @Test
    public void testNextOneOrLessEmptyOrOutageNeighbors() throws FileNotFoundException {
    	Town testTown = new Town("./CtoS1.txt");
    	/*
    	 *  R C O E
         *  R(S)C C
         *  R S C E
         *  R C R E
    	 */
    	assertEquals(testTown.grid[1][1].who(), State.STREAMER); 
    	//Streamer -> Reseller
    	/*
    	 *  E R E R
		 *  E(R)S C
		 *  E R O R
		 *  E R E R
    	 */
    	// Because the TownCell in the location (1,1) applies the rule of having having 1 or less Empty
    	// or Outage neighbors it becomes a Reseller.
    	assertEquals(testTown.grid[1][1].next(testTown).who(), State.RESELLER);       
    }
    
    @Test
    public void testNextResellerNeighbor() throws FileNotFoundException {
        // Test the next method for the Streamer class with a Reseller neighbor
    	Town testTown = new Town("./CtoO.txt");
    	/*
    	 *  O R O R
    	 *  E E C O
    	 *  E(S)O S
    	 *  E O R R
    	 */
    	assertEquals(testTown.grid[2][1].who(), State.STREAMER);
    	//Streamer -> Outage
    	/*
    	 *  E E E E
    	 *  C C O E
    	 *  C(O)E O
    	 *  C E E E
      	 */
    	// Because the TownCell in the location (2,1) applies the rule of having 1 or more Reseller
    	// neighbor it becomes Outage.
    	assertEquals(testTown.grid[2][1].next(testTown).who(), State.OUTAGE);
    }
    
    @Test
    public void testNextOutageNeighbor() throws FileNotFoundException {
        // Test the next method for the Streamer class with a Reseller neighbor
    	Town testTown = new Town("./S.txt");
    	/*
    	 *  O(S)O
    	 */
    	assertEquals(testTown.grid[0][1].who(), State.STREAMER);
    	//Streamer -> Empty
    	/*
    	 *  E(E)E
      	 */
    	// Because the TownCell in the location (0,1) applies the rule of having any Outage
    	// it becomes Empty.
    	assertEquals(testTown.grid[0][1].next(testTown).who(), State.EMPTY);
    }
    
    @Test
    public void testNextCasualNeighbors() throws FileNotFoundException {
    	// Test the next method for the Streamer class with 5 or more Casual neighbors
    	Town testTown = new Town("./S2.txt");
    	/*
    	 *  C C C
    	 *  C S C
    	 *  C E E
    	 */
    	assertEquals(testTown.grid[1][1].who(), State.STREAMER);   
    	//Streamer -> Streamer
    	// Because the TownCell in the location (1,1) applies the rule of having 5 or more Casual
    	// neighbors it remains as Streamer.
    	assertEquals(testTown.grid[1][1].next(testTown).who(), State.STREAMER);       
    }
    
    @Test
    public void testNext() throws FileNotFoundException {
    	Town testTown = new Town("./S3.txt");
    	/*
    	 *  E S E
    	 */
    	assertEquals(testTown.grid[0][1].who(), State.STREAMER);   
    	//Streamer -> Streamer
    	// Because the TownCell in the location (0,1) applies none of the rules
    	// it remains as Streamer.
    	assertEquals(testTown.grid[0][1].next(testTown).who(), State.STREAMER);       
    }
}